package com.cxl;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;

public class Main extends Application {

    private static WebView view = new WebView();
    private static WebEngine webEngine = view.getEngine();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("/home/cxl/cxl/test-demo/case/login.html");
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile);
        String url = absoluteFile.toURI().toString();
        webEngine.load(url);
        view.setContextMenuEnabled(false);
        JSLogin jsLogin=new JSLogin();
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue== Worker.State.SUCCEEDED){
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("jsLogin",jsLogin);
            }
        });
        StackPane root = new StackPane();
        root.getChildren().addAll(view);
        Scene scene = new Scene(root,450D,375D);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

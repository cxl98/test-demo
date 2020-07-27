package com.cxl;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.plugin.javascript.JSObject;

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
        JSLogin jsLogin=new JSLogin();
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue== Worker.State.SUCCEEDED){
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("jsLogin",jsLogin);
                System.out.println(">>>>"+window);
            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(view);
        Scene scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

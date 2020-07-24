package com.cxl;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class Main extends Application {

    private static WebView view = new WebView();
    private static WebEngine webEngine = view.getEngine();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("xxxx");
        File file = new File("registry.html");
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile);
        String url = absoluteFile.toURI().toString();
        System.out.println(url);
        webEngine.load(url);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(observable.getValue());
            System.out.println("old"+oldValue);
            System.out.println(newValue);
            if (newValue==Worker.State.SUCCEEDED){

            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().add(view);
        Scene scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

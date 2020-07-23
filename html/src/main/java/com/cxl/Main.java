package com.cxl;

import com.sun.javafx.application.PlatformImpl;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class Main {
   private static Stage stage = new Stage();
   private static WebView view = new WebView();
   private static WebEngine webEngine = view.getEngine();
    public void run() {
        Button buttonURL = new Button();
        buttonURL.setOnAction(event -> {
            File file = new File("registry.html");
            File absoluteFile = file.getAbsoluteFile();
            String url = absoluteFile.toURI().toString();
            webEngine.load(url);
        });

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(buttonURL, view);
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Main main = new Main();
        PlatformImpl.startup(main::run);
    }
}

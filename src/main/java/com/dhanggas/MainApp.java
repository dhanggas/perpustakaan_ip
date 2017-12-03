package com.dhanggas;

import com.dhanggas.controllers.HomeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ApplicationContext springContext = null;

    private static String[] argument;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                springContext = SpringApplication.run(MainApp.class, argument);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            HomeController controller = springContext.getBean(HomeController.class);
            Parent parent = (Parent) controller.initView();
            Scene scene = new Scene(parent);
            
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Main Applikasi");
            primaryStage.show();
        });
        task.setOnFailed(e ->{
            System.exit(0);
            Platform.exit();
        });
        task.run();
    }

    public static void main(String[] args) {
        MainApp.argument = args;
        launch(args);

    }

}

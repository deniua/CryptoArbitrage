package com.md.cryptoarbitrage;

import com.md.cryptoarbitrage.globals.GlobalCore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        GlobalCore.setApplicationStart(false);
    }

    public void start(Stage stage) {
        GlobalCore.globalstage = stage;
        try {

            //Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/main.fxml"));
            Parent root = loader.load();


            stage.setTitle("Межбиржевой арбитраж(мониторинг)");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

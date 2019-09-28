package com.md.cryptoarbitrage;

import com.md.cryptoarbitrage.globals.GlobalCore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/main.fxml"));
            Parent root = loader.load();

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon16.png")));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon32.png")));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon64.png")));



            stage.setTitle("Межбиржевой арбитраж(мониторинг)");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

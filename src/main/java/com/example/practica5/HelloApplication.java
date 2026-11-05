package com.example.practica5;

import com.example.practica5.controlador.Controlador;
import com.example.practica5.vista.Vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Vista root = new Vista();
        Scene scene = new Scene(root, 1366, 768);
        stage.setTitle("Explorador de Cartas");
        stage.setScene(scene);
        stage.show();
        Controlador controlador = new Controlador(root);
    }
}

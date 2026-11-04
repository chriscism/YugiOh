package com.example.practica5.vista;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Vista extends BorderPane {
    public Vista(){
        this.setStyle("-fx-background-color: #161618;");
    }

    private VBox crearMenuLateral(){
        VBox menu = new VBox();
        menu.setPadding(new Insets(20));
        menu.setSpacing(10);
        menu.setPrefWidth(220);
        menu.setStyle("-fx-background-color: #1a1a1c; -fx-border-color: transparent #2a2a2d transparent transparent;");

        Label titulo = new Label("Explorar por: ");
        titulo.setStyle("-fx-text-fill: #8a8a8e; -fx-font-size: 13px; -fx-padding: 0 0 10 0;");
        Button botonTodas = new Button("Todas");
        Button botonNivel = new Button("Nivel");
        Button botonAtributo = new Button("Atributo");
        Button botonAtaque = new Button("Ataque");
        Button botonDefensa = new Button("Defensa");

        Region espaciador = new Region();
        VBox.setVgrow(espaciador, Priority.ALWAYS);
        Button botonBusqueda = new Button("Buscar...");

        menu.getChildren().addAll(titulo, botonTodas, botonNivel, botonAtributo, botonAtaque, botonDefensa, botonBusqueda);
        return menu;
    }
}

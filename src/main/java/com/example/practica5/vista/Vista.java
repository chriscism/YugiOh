package com.example.practica5.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Vista extends BorderPane {
    public Vista(){
        this.setStyle("-fx-background-color: #161618;");
        this.setLeft(crearMenuLateral());
        this.setCenter(crearPanelCentral());
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

    private VBox crearPanelCentral(){
        VBox panel = new VBox();
        panel.setPadding(new Insets(20, 40, 20, 40));
        panel.setSpacing(25);
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setStyle("-fx-background-color: transparent;");

        HBox encabezado = new HBox();
        encabezado.setAlignment(Pos.CENTER_LEFT);
        Label queMuestra = new Label("Mostrando: todas las cartas");
        queMuestra.setStyle("-fx-text-fill: #e8e8e8;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label lblContador = new Label("34 / 283");
        lblContador.setStyle("-fx-text-fill: #e8e8e8; -fx-border-color: #333336; -fx-border-radius: 5; -fx-padding: 4 10;");
        encabezado.getChildren().addAll(queMuestra, spacer, lblContador);

        HBox carrusel = new HBox(20);
        carrusel.setAlignment(Pos.CENTER);

        Button botonAnterior = new Button("<");
        StackPane contenedorImagen = new StackPane();
        contenedorImagen.setPrefSize(280, 400);
        contenedorImagen.setStyle("-fx-background-color: #1a1a1c; -fx-border-color: #333336; -fx-border-radius: 12; -fx-background-radius: 12;");
        Label lblPlaceholder = new Label("Imagen de la carta");
        lblPlaceholder.setStyle("-fx-text-fill: #8a8a8e;");
        contenedorImagen.getChildren().add(lblPlaceholder);
        Button botonSiguiente = new Button(">");

        carrusel.getChildren().addAll(botonAnterior, contenedorImagen, botonSiguiente);

        VBox titulos = new VBox(10);
        titulos.setAlignment(Pos.CENTER);
        Label nombreCarta = new Label("Nombre de la carta");
        nombreCarta.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        Label nivelCarta = new Label("Nivel X");
        nivelCarta.setStyle("-fx-background-color: #0d2a4a; -fx-text-fill: #5ba1f5; -fx-padding: 4 12; -fx-background-radius: 12; -fx-font-size: 12px;");
        titulos.getChildren().addAll(nombreCarta, nivelCarta);
        panel.getChildren().addAll(encabezado, carrusel, titulos);
        return panel;
    }
}

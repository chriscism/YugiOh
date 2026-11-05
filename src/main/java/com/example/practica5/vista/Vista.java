package com.example.practica5.vista;

import com.example.practica5.modelo.CartaYugioh;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;

public class Vista extends BorderPane {
    public Button botonAnterior;
    public Button botonSiguiente;

    private StackPane contenedorImagenCentral;
    private StackPane contenedorMiniAnt;
    private StackPane contenedorMiniSig;

    private Label queMuestra;
    private Label lblContador;
    private Label nombreCarta;
    private Label nivelCarta;

    private Label lblValAtributo;
    private Label lblValNivel;
    private Label lblValAtaque;
    private Label lblValDefensa;
    private Label lblValDescripcion;

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

        Label titulo = new Label("Explore by: ");
        titulo.setStyle("-fx-text-fill: #8a8a8e; -fx-font-size: 13px; -fx-padding: 0 0 10 0;");
        Button botonTodas = botonBonito("All", true);
        MenuButton botonNivel = botonDesplegable("Level", "Low to High", "High to Low");
        MenuButton botonAtributo = botonDesplegable("Attribute", "DARK", "LIGHT", "EARTH", "FIRE", "WATER", "WIND");
        MenuButton botonAtaque = botonDesplegable("Attack", "Low to High", "High to Low");
        MenuButton botonDefensa = botonDesplegable("Defense", "Low to High", "High to Low");
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

        // encabezados
        HBox encabezado = new HBox();
        encabezado.setAlignment(Pos.CENTER_LEFT);
        queMuestra = new Label("Mostrando: todas las cartas");
        queMuestra.setStyle("-fx-text-fill: #e8e8e8;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        lblContador = new Label("1 / 283");
        lblContador.setStyle("-fx-text-fill: #e8e8e8; -fx-border-color: #333336; -fx-border-radius: 5; -fx-padding: 4 10;");
        encabezado.getChildren().addAll(queMuestra, spacer, lblContador);

        // carrusel
        HBox carrusel = new HBox(20);
        carrusel.setAlignment(Pos.CENTER);

        VBox miniaturaAntLayout = hacerCartaChiquitaMaqueta("Anterior");
        contenedorMiniAnt = (StackPane) miniaturaAntLayout.getChildren().get(0);
        botonAnterior = flechaBonita("<");

        contenedorImagenCentral = new StackPane();
        contenedorImagenCentral.setPrefSize(280, 400);
        contenedorImagenCentral.setStyle("-fx-background-color: #1a1a1c; -fx-border-color: #333336; -fx-border-radius: 12; -fx-background-radius: 12;");

        botonSiguiente = flechaBonita(">");
        VBox miniaturaSigLayout = hacerCartaChiquitaMaqueta("Siguiente");
        contenedorMiniSig = (StackPane) miniaturaSigLayout.getChildren().get(0);

        carrusel.getChildren().addAll(miniaturaAntLayout, botonAnterior, contenedorImagenCentral, botonSiguiente, miniaturaSigLayout);

        // nombre y nivel
        VBox titulos = new VBox(10);
        titulos.setAlignment(Pos.CENTER);
        nombreCarta = new Label("Nombre de la carta");
        nombreCarta.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        nivelCarta = new Label("Nivel X");
        nivelCarta.setStyle("-fx-background-color: #0d2a4a; -fx-text-fill: #5ba1f5; -fx-padding: 4 12; -fx-background-radius: 12; -fx-font-size: 12px;");
        titulos.getChildren().addAll(nombreCarta, nivelCarta);

        // stats
        VBox stats = new VBox(20);
        stats.setStyle("-fx-background-color: #1a1a1c; -fx-background-radius: 12; -fx-padding: 25;");
        GridPane gridStats = new GridPane();
        gridStats.setHgap(60);
        gridStats.setVgap(15);

        lblValAtributo = crearLabelValor("X");
        lblValNivel = crearLabelValor("X");
        lblValAtaque = crearLabelValor("X");
        lblValDefensa = crearLabelValor("X");

        gridStats.add(panelDeDatosConLabel("Atributo: ", lblValAtributo), 0, 0);
        gridStats.add(panelDeDatosConLabel("Nivel: ", lblValNivel), 1, 0);
        gridStats.add(panelDeDatosConLabel("Ataque: ", lblValAtaque), 2, 0);
        gridStats.add(panelDeDatosConLabel("Defensa: ", lblValDefensa), 0, 1);

        lblValDescripcion = crearLabelValor("X");
        VBox seccionEfecto = panelDeDatosConLabel("Descripción: ", lblValDescripcion);

        stats.getChildren().addAll(gridStats, seccionEfecto);
        panel.getChildren().addAll(encabezado, carrusel, titulos, stats);
        return panel;
    }

    private VBox hacerCartaChiquitaMaqueta(String etiqueta) {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        StackPane rectangulo = new StackPane();
        rectangulo.setPrefSize(70, 100);
        rectangulo.setStyle("-fx-background-color: transparent; -fx-border-color: #333336; -fx-border-radius: 8;");
        Label etiquetaRectangulo = new Label(etiqueta);
        etiquetaRectangulo.setStyle("-fx-text-fill: #8a8a8e; -fx-font-size: 11px;");
        vbox.getChildren().addAll(rectangulo, etiquetaRectangulo);
        return vbox;
    }

    private Label crearLabelValor(String valor) {
        Label lbl = new Label(valor);
        lbl.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-wrap-text: true;");
        return lbl;
    }

    private VBox panelDeDatosConLabel(String etiqueta, Label lblVal) {
        VBox vbox = new VBox(3);
        Label lblEtiq = new Label(etiqueta);
        lblEtiq.setStyle("-fx-text-fill: #8a8a8e; -fx-font-size: 12px;");
        vbox.getChildren().addAll(lblEtiq, lblVal);
        return vbox;
    }

    private MenuButton botonDesplegable(String texto, String... opciones){
        MenuButton boton = new MenuButton(texto);
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setStyle("-fx-background-color: transparent; -fx-text-fill: #e8e8e8; -fx-alignment: BASELINE_LEFT; -fx-padding: 8 15; -fx-border-color: #333336; -fx-border-radius: 8; -fx-background-radius: 8;");
        for(String opc:opciones){
            boton.getItems().add(new MenuItem(opc));
        }
        return boton;
    }

    private Button botonBonito(String texto, boolean activo) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        if (activo) {
            btn.setStyle("-fx-background-color: #0d2a4a; -fx-text-fill: #5ba1f5; -fx-alignment: BASELINE_LEFT; -fx-padding: 12 15; -fx-background-radius: 8; -fx-font-weight: bold;");
        } else {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #e8e8e8; -fx-alignment: BASELINE_LEFT; -fx-padding: 12 15; -fx-border-color: #333336; -fx-border-radius: 8; -fx-background-radius: 8;");
        }
        return btn;
    }

    private Button flechaBonita(String texto) {
        Button btn = new Button(texto);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: #333336; -fx-border-radius: 5; -fx-padding: 5 10; -fx-cursor: hand;");
        return btn;
    }

    public void mostrarCarta(CartaYugioh actual, CartaYugioh anterior, CartaYugioh siguiente, int indice, int total, String tituloVista) {
        if (actual == null) return;

        // Actualizar textos
        queMuestra.setText(tituloVista);
        lblContador.setText((indice + 1) + " / " + total);

        nombreCarta.setText(actual.getNombre());
        nivelCarta.setText(actual.getAtributo().name().equals("SPELL") || actual.getAtributo().name().equals("TRAP") ? "N/A" : "Nivel " + actual.getNivel());

        lblValAtributo.setText(actual.getAtributo().name());
        lblValNivel.setText(String.valueOf(actual.getNivel()));
        lblValAtaque.setText(String.valueOf(actual.getAtaque()));
        lblValDefensa.setText(String.valueOf(actual.getDefensa()));
        lblValDescripcion.setText(actual.getDescripcion());

        // Actualizar imagenes de las cartitas
        contenedorImagenCentral.getChildren().clear();
        contenedorImagenCentral.getChildren().add(CartaVisual.crearCartaVisual(actual, 280, 400));

        contenedorMiniAnt.getChildren().clear();
        if (anterior != null) {
            contenedorMiniAnt.getChildren().add(CartaVisual.crearCartaVisual(anterior, 70, 100));
            botonAnterior.setDisable(false);
        } else {
            botonAnterior.setDisable(true);
        }

        contenedorMiniSig.getChildren().clear();
        if (siguiente != null) {
            contenedorMiniSig.getChildren().add(CartaVisual.crearCartaVisual(siguiente, 70, 100));
            botonSiguiente.setDisable(false);
        } else {
            botonSiguiente.setDisable(true);
        }
    }
}
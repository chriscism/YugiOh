package com.example.practica5.vista;

import com.example.practica5.modelo.CartaYugioh;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class CartaVisual {
    private static final String rutaFotos = "src/main/resources/imagenes_lata_25_aniversario_yugipedia/";

    public static ImageView crearCartaVisual(CartaYugioh carta, double x, double y) {
        ImageView foto = new ImageView();
        if (carta != null) {
            String nombreArchivo = carta.getNumCarta() + ".png";
            File file = new File(rutaFotos + nombreArchivo);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                foto.setImage(image);
            } else {
                System.out.println("No se encontró la foto: " + file.getAbsolutePath());
            }
        }
        foto.setFitWidth(x);
        foto.setFitHeight(y);
        foto.setPreserveRatio(true);
        foto.setSmooth(true);
        return foto;
        }
    }

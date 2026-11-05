package com.example.practica5.controlador;

import com.example.practica5.modelo.Atributo;
import com.example.practica5.modelo.CartaYugioh;
import com.example.practica5.modelo.DoubleRoundList;
import com.example.practica5.vista.Vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Controlador {
    private DoubleRoundList<CartaYugioh> listaTodas;
    private DoubleRoundList<CartaYugioh> listaPorNivelMenorMayor;
    private DoubleRoundList<CartaYugioh> listaPorNivelMayorMenor;
    private DoubleRoundList<CartaYugioh> listaPorAtaqueMenorMayor;
    private DoubleRoundList<CartaYugioh>  listaPorAtaqueMayorMenor;
    private DoubleRoundList<CartaYugioh> listaPorDefensaMenorMayor;
    private DoubleRoundList<CartaYugioh> listaPorDefensaMayorMenor;
    private DoubleRoundList<CartaYugioh> listaDark;
    private DoubleRoundList<CartaYugioh> listaLight;
    private DoubleRoundList<CartaYugioh> listaEarth;
    private DoubleRoundList<CartaYugioh> listaWater;
    private DoubleRoundList<CartaYugioh> listaFire;
    private DoubleRoundList<CartaYugioh> listaWind;
    private DoubleRoundList<CartaYugioh> listaSpell;
    private DoubleRoundList<CartaYugioh> listaTrap;
    private DoubleRoundList<CartaYugioh> listaActual;
    private int indiceActual = 0;
    private String titulo = "Mostrando: Todas las cartas";
    private Vista vista;

    public Controlador(Vista vista){
        this.vista = vista;
        listaTodas = new DoubleRoundList<>();
        listaPorNivelMenorMayor = new DoubleRoundList<>();
        listaPorNivelMayorMenor = new DoubleRoundList<>();
        listaPorAtaqueMenorMayor = new DoubleRoundList<>();
        listaPorAtaqueMayorMenor = new DoubleRoundList<>();
        listaPorDefensaMenorMayor = new DoubleRoundList<>();
        listaPorDefensaMayorMenor = new DoubleRoundList<>();
        listaDark = new DoubleRoundList<>();
        listaLight = new DoubleRoundList<>();
        listaEarth = new DoubleRoundList<>();
        listaWater = new DoubleRoundList<>();
        listaFire = new DoubleRoundList<>();
        listaWind = new DoubleRoundList<>();
        listaTrap = new DoubleRoundList<>();
        listaSpell = new DoubleRoundList<>();

        llenarListas("src/main/java/com/example/practica5/modelo/yugioh_cards_cleaned_v4.csv");
        ordenarListas();
        listaActual = listaTodas;
        configurarBotones();
        configurarFiltros();
        actualizarPantalla();
    }



    public void llenarListas(String ruta){
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine();
            int contadorDeCarta = 1;
            while ((linea = br.readLine()) != null) {
                // regex para separar por comas e ignorar las comillas y/o comas del propio texto
                String[] columnas = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (columnas.length >= 6) {
                    try {
                        String nombre = columnas[0].trim();
                        Atributo atributo = Atributo.valueOf(columnas[1].trim().toUpperCase());
                        // esto porque las cartas tipo spell, trap etc no tienen nivel ataque ni defensa y en el csv estan vacios
                        int nivel = 0;
                        if (!columnas[2].trim().isEmpty()) {
                            nivel = (int) Double.parseDouble(columnas[2].trim());
                        }
                        int ataque = 0;
                        if (!columnas[3].trim().isEmpty()) {
                            ataque = (int) Double.parseDouble(columnas[3].trim());
                        }
                        int defensa = 0;
                        if (!columnas[4].trim().isEmpty()) {
                            defensa = (int) Double.parseDouble(columnas[4].trim());
                        }

                        String descripcion = columnas[5].trim();
                        if (descripcion.startsWith("\"") && descripcion.endsWith("\"")) {
                            descripcion = descripcion.substring(1, descripcion.length() - 1);
                        }

                        // Se crea el objeto carta
                        CartaYugioh carta = new CartaYugioh(nombre, atributo, nivel, ataque, defensa, descripcion, contadorDeCarta);
                        // lleno todas las listas, las que se necesite después se van a ordenar muajaja
                        listaTodas.insertEnd(carta);
                        listaPorNivelMenorMayor.insertEnd(carta);
                        listaPorNivelMayorMenor.insertEnd(carta);
                        listaPorAtaqueMenorMayor.insertEnd(carta);
                        listaPorAtaqueMayorMenor.insertEnd(carta);
                        listaPorDefensaMenorMayor.insertEnd(carta);
                        listaPorDefensaMayorMenor.insertEnd(carta);
                        // aqui la meto en la lista que es según el atributo que tenga
                        switch (atributo) {
                            case DARK: listaDark.insertEnd(carta); break;
                            case LIGHT: listaLight.insertEnd(carta); break;
                            case EARTH: listaEarth.insertEnd(carta); break;
                            case WATER: listaWater.insertEnd(carta); break;
                            case FIRE: listaFire.insertEnd(carta); break;
                            case WIND: listaWind.insertEnd(carta); break;
                            case SPELL: listaSpell.insertEnd(carta); break;
                            case TRAP: listaTrap.insertEnd(carta); break;
                        }
                        contadorDeCarta++;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Fila omitida por error en el formato: " + columnas[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public void ordenarListas(){
        listaPorNivelMenorMayor.sort(CartaYugioh.compararNivel);
        listaPorAtaqueMenorMayor.sort(CartaYugioh.compararAtaque);
        listaPorDefensaMenorMayor.sort(CartaYugioh.compararDefensa);
        // las revierto para que sea en orden descendente
        listaPorNivelMayorMenor.sort(CartaYugioh.compararNivel.reversed());
        listaPorAtaqueMayorMenor.sort(CartaYugioh.compararAtaque.reversed());
        listaPorDefensaMayorMenor.sort(CartaYugioh.compararDefensa.reversed());
    }

    private void configurarBotones() {
        vista.botonSiguiente.setOnAction(e -> {
            if (indiceActual < listaActual.getSize() - 1) {
                indiceActual++;
                actualizarPantalla();
            }
        });

        vista.botonAnterior.setOnAction(e -> {
            if (indiceActual > 0) {
                indiceActual--;
                actualizarPantalla();
            }
        });
    }

    private void actualizarPantalla() {
        if (listaActual.getSize() == 0) return;

        CartaYugioh actual = listaActual.get(indiceActual);
        CartaYugioh anterior = (indiceActual > 0) ? listaActual.get(indiceActual - 1) : null;
        CartaYugioh siguiente = (indiceActual < listaActual.getSize() - 1) ? listaActual.get(indiceActual + 1) : null;

        vista.mostrarCarta(actual, anterior, siguiente, indiceActual, listaActual.getSize(), titulo);
    }

    private void aplicarFiltro(DoubleRoundList<CartaYugioh> nuevaLista, String nuevoTitulo) {
        if (nuevaLista != null && nuevaLista.getSize() > 0) {
            this.listaActual = nuevaLista;
            this.indiceActual = 0;
            this.titulo = nuevoTitulo;
            actualizarPantalla();
        } else {
            System.out.println("Esa categoría no tiene cartas actualmente.");
        }
    }

    private void configurarFiltros() {
        //Botón de Todas las Cartas
        vista.botonTodas.setOnAction(e -> aplicarFiltro(listaTodas, "All cards"));

        // nivel
        vista.botonNivel.getItems().get(0).setOnAction(e -> aplicarFiltro(listaPorNivelMenorMayor, "Level (lowest to highest)"));
        vista.botonNivel.getItems().get(1).setOnAction(e -> aplicarFiltro(listaPorNivelMayorMenor, "Level (highest to lowest)"));

        // ataque
        vista.botonAtaque.getItems().get(0).setOnAction(e -> aplicarFiltro(listaPorAtaqueMenorMayor, "Attack (lowest to highest)"));
        vista.botonAtaque.getItems().get(1).setOnAction(e -> aplicarFiltro(listaPorAtaqueMayorMenor, "Attack (highest to lowest)"));

        // defensa
        vista.botonDefensa.getItems().get(0).setOnAction(e -> aplicarFiltro(listaPorDefensaMenorMayor, "Defense (lowest to highest)"));
        vista.botonDefensa.getItems().get(1).setOnAction(e -> aplicarFiltro(listaPorDefensaMayorMenor, "Defense (highest lo lowest)"));
        // botones de atributo
        vista.botonAtributo.getItems().get(0).setOnAction(e -> aplicarFiltro(listaDark, "Attribute: DARK"));
        vista.botonAtributo.getItems().get(1).setOnAction(e -> aplicarFiltro(listaLight, "Attribute: LIGHT"));
        vista.botonAtributo.getItems().get(2).setOnAction(e -> aplicarFiltro(listaEarth, "Attribute:  EARTH"));
        vista.botonAtributo.getItems().get(3).setOnAction(e -> aplicarFiltro(listaFire, "Attribute: FIRE"));
        vista.botonAtributo.getItems().get(4).setOnAction(e -> aplicarFiltro(listaWater, "Attribute: WATER"));
        vista.botonAtributo.getItems().get(5).setOnAction(e -> aplicarFiltro(listaWind, "Attribute: WIND"));
        vista.botonAtributo.getItems().get(6).setOnAction(e -> aplicarFiltro(listaSpell, "SPELL"));
        vista.botonAtributo.getItems().get(7).setOnAction(e -> aplicarFiltro(listaTrap, "TRAP"));
    }
}

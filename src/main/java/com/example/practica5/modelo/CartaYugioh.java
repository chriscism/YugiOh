package com.example.practica5.modelo;

import java.util.Comparator;

public class CartaYugioh{
    private String nombre;
    private Atributo atributo;
    private int nivel;
    private int ataque;
    private int defensa;
    private String descripcion;


    public static final Comparator<CartaYugioh> compararNivel = new Comparator<CartaYugioh>() {
        @Override
        public int compare(CartaYugioh c1, CartaYugioh c2) {
            return Integer.compare(c1.getNivel(), c2.getNivel());
        }
    };

    public static final Comparator<CartaYugioh> compararAtaque = new Comparator<CartaYugioh>() {
        @Override
        public int compare(CartaYugioh c1, CartaYugioh c2) {
            return Integer.compare(c1.getAtaque(), c2.getAtaque());
        }
    };

    public static final Comparator<CartaYugioh> compararDefensa = new Comparator<CartaYugioh>() {
        @Override
        public int compare(CartaYugioh c1, CartaYugioh c2) {
            return Integer.compare(c1.getDefensa(), c2.getDefensa());
        }
    };
    public CartaYugioh(String nombre, Atributo atributo, int nivel, int ataque, int defensa, String descripcion) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defensa = defensa;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getNivel() {
        return nivel;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public String getNombre() {
        return nombre;
    }
}

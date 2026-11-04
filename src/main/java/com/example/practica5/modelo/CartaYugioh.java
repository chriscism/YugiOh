package com.example.practica5.modelo;

public class CartaYugioh {
    private String nombre;
    private Atributo atributo;
    private int nivel;
    private int ataque;
    private int defensa;
    private String descripcion;

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

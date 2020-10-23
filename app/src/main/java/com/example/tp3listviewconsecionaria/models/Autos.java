package com.example.tp3listviewconsecionaria.models;

public class Autos {
        private int id;
        private String marca;
        private Float precio;
        private int modelo;
        private float km;
        private String ciudad;
        private String descipcion;
        private int imagen;

    public Autos() {
    }

    public Autos(int id, String marca, Float precio, int modelo, float km, String ciudad, String descipcion, int imagen) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.modelo = modelo;
        this.km = km;
        this.ciudad = ciudad;
        this.descipcion = descipcion;
        this.imagen = imagen;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}

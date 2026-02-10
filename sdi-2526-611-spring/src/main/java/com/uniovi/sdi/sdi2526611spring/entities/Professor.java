package com.uniovi.sdi.sdi2526611spring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Professor {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String dni;
    private String apellido;
    private String categoria; //Titular, Catedrático, Asociado, Ayudante

    // Constructores, Getters y Setters
    public Professor(Long id,String dni, String nombre, String apellido, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
        this.dni=dni;
    }

    public Professor() {

    }

    public void setDni(String dni){
        this.dni=dni;
    }

    public String getDni(){
        return this.dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id1) {
        this.id = id1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", apellido='" + apellido + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}

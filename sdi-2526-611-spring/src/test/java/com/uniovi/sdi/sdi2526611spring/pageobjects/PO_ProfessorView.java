package com.uniovi.sdi.sdi2526611spring.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ProfessorView extends PO_NavView {

    static public void fillForm(WebDriver driver, String dnip, String nombrep, String apellidop) {
        // Campo DNI
        WebElement dni = driver.findElement(By.name("dni"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);

        // Campo Nombre
        WebElement nombre = driver.findElement(By.name("nombre"));
        nombre.click();
        nombre.clear();
        nombre.sendKeys(nombrep);

        // Campo Apellido
        WebElement apellido = driver.findElement(By.name("apellido"));
        apellido.click();
        apellido.clear();
        apellido.sendKeys(apellidop);

        // Botón Guardar (clase btn-primary en tu add.html)
        By boton = By.className("btn-primary");
        driver.findElement(boton).click();
    }

    static public void fillEditForm(WebDriver driver, String dnip, String nombrep, String apellidop, String categoriap) {
        WebElement dni = driver.findElement(By.name("dni"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);

        // Campos según tu edit.html: name="name" y name="lastName"
        WebElement nombre = driver.findElement(By.name("name"));
        nombre.click();
        nombre.clear();
        nombre.sendKeys(nombrep);

        WebElement apellido = driver.findElement(By.name("lastName"));
        apellido.click();
        apellido.clear();
        apellido.sendKeys(apellidop);

        WebElement categoria = driver.findElement(By.name("category"));
        categoria.click();
        categoria.clear();
        categoria.sendKeys(categoriap);

        // Botón de envío (clase btn-primary en tu edit.html)
        By boton = By.className("btn-primary");
        driver.findElement(boton).click();
    }
}
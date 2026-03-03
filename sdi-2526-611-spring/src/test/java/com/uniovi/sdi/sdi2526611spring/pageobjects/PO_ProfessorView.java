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

    static public void fillEditForm(WebDriver driver, String dnip, String namep, String lastnamep, String categoryp) {
        WebElement dni = driver.findElement(By.name("dni"));
        dni.click(); dni.clear(); dni.sendKeys(dnip);

        // IMPORTANTE: Tu edit.html usa name="name" y name="lastName"
        WebElement name = driver.findElement(By.name("name"));
        name.click(); name.clear(); name.sendKeys(namep);

        WebElement lastname = driver.findElement(By.name("lastName"));
        lastname.click(); lastname.clear(); lastname.sendKeys(lastnamep);

        WebElement category = driver.findElement(By.name("category"));
        category.click(); category.clear(); category.sendKeys(categoryp);

        // Buscamos el botón por el texto "Modificar" que aparece en tu HTML
        By boton = By.xpath("//button[text()='Modificar']");
        driver.findElement(boton).click();
    }
}
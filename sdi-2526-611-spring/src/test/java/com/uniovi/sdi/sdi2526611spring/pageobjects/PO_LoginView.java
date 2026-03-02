package com.uniovi.sdi.sdi2526611spring.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {
    static public void fillLoginForm(WebDriver driver, String dnip, String passwordp) {
        // Localizamos el campo DNI por su atributo name="username"
        WebElement dni = driver.findElement(By.name("username"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);

        // Localizamos el campo Password por su atributo name="password"
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        // Pulsamos el botón de Login
        By boton = By.className("btn-primary");
        driver.findElement(boton).click();
    }
}
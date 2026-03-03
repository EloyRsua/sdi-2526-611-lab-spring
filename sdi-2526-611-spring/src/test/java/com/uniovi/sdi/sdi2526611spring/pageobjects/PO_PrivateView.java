package com.uniovi.sdi.sdi2526611spring.pageobjects;

import com.uniovi.sdi.sdi2526611spring.SeleniumUtils;
import com.uniovi.sdi.sdi2526611spring.pageobjects.PO_NavView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
//Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
//Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
    static public void goToLastPage(WebDriver driver) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.getLast().click();
    }

    static public void goToAddMarkPage(WebDriver driver) {
        // Click en el menú de Notas
        PO_View.checkElementBy(driver, "free", "//*[@id='myNavbar']/ul[1]/li[2]").getFirst().click();
        // Click en Añadir Nota
        PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'mark/add')]").getFirst().click();
    }

    static public void goToListMarksPage(WebDriver driver) {
        // Click en el menú de Notas
        PO_View.checkElementBy(driver, "free", "//*[@id='myNavbar']/ul[1]/li[2]").getFirst().click();
        // Click en Listado de notas
        PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'mark/list')]").getFirst().click();
    }
}
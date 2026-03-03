package com.uniovi.sdi.sdi2526611spring;

import com.uniovi.sdi.sdi2526611spring.pageobjects.*;
import com.uniovi.sdi.sdi2526611spring.SeleniumUtils;
import com.uniovi.sdi.sdi2526611spring.services.ProfessorsService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfesoresTests {

    @Autowired
    private ProfessorsService professorsService;

    @LocalServerPort
    private int port;

    private String baseUrl;
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port;
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void testRegistroProfesorValido() {
        PO_NavView.clickOption(driver, "/login", "text", "Identifícate");
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        SeleniumUtils.waitLoadElementsBy(driver, "id", "profesorDropdown", PO_View.getTimeout()).getFirst().click();

        PO_NavView.clickOption(driver, "/professor/add", "text", "Añadir Profesor");

        PO_ProfessorView.fillForm(driver, "87654321K", "Ramón", "García");

        PO_View.checkElementBy(driver, "text", "87654321K");
    }

    @Test
    @Order(2)
    public void testRegistroProfesorInvalido() {
        PO_NavView.clickOption(driver, "/login", "text", "Identifícate");
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        SeleniumUtils.waitLoadElementsBy(driver, "id", "profesorDropdown", PO_View.getTimeout()).getFirst().click();
        PO_NavView.clickOption(driver, "/professor/add", "text", "Añadir Profesor");

        PO_ProfessorView.fillForm(driver, "123", "Nombre", "Apellido");

        PO_View.checkElementBy(driver, "text", "DNI incorrecto: debe tener 8 números y una letra mayúscula.");
    }

    @Test
    @Order(3)
    public void testRestriccionAccesoProfesores() {
        driver.get(baseUrl + "/professor/add");

        PO_View.checkElementBy(driver, "text", "Identifícate");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @Order(4)
    public void testRestriccionNoAut() {
        PO_NavView.clickOption(driver, "/login", "text", "Identifícate");
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");

        By menuAdmin = By.id("profesorDropdown");
        Assertions.assertTrue(driver.findElements(menuAdmin).isEmpty(), "Un estudiante no debería ver el menú de gestión.");

        driver.get(baseUrl + "/professor/add");
        PO_View.checkElementBy(driver, "text", "Whitelabel Error Page");
        PO_View.checkElementBy(driver, "text", "Forbidden");
    }

    @Test
    @Order(5)
    public void testListadoProfesores() {
        PO_NavView.clickOption(driver, "/login", "text", "Identifícate");
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        SeleniumUtils.waitLoadElementsBy(driver, "id", "profesorDropdown", PO_View.getTimeout()).getFirst().click();

        PO_NavView.clickOption(driver, "/professor/list", "id", "main-container");

        List<WebElement> filas = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr", PO_View.getTimeout());

        int totalEsperado = professorsService.getProfessors().size();
        Assertions.assertEquals(totalEsperado, filas.size(), "El número de filas no coincide con los profesores del sistema");
    }

}
package es.ja.cgj.webdriver.paginas;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

import es.ja.cgj.webdriver.config.Config;
import es.ja.cgj.webdriver.utils.MouseUtils;

public class PaginaLogin extends Pagina {
	
	// Define a static log variable
	static Logger log = Logger.getLogger(PaginaLogin.class);
	
	public PaginaLogin(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
	}
	
	public void loginConCertificado() {
		
		driver.get(Config.BASE_URL + "/sawa2007/?unidad=VOL&tipo=EV");
		
		//Movemos el raton para evitar que la pagina se quede colgada tras esperar 2 segundos
		MouseUtils.waitAndMoveMouse(3000);

		driver.findElement(By.cssSelector("a > img")).click();
		
		driver.findElement(By.linkText("ACCESO AL SISTEMA")).click();
		
		if (Config.LOGIN_CON_CERTIFICADO_POR_DEFECTO) {
			// Espera para que cargue la pagina de Autenticación
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				log.error("Error inesperado", e);
			}

			log.info("despues de esperar 15 seg: " + driver.getCurrentUrl());
		} else {
			// Esperamos a que salga la ventana de seleccion del certficado y
			// hacemos click en OK
			ScreenRegion s = new DesktopScreenRegion();
			s.wait(new ImageTarget(new File("src/main/resources/images/OK.png")),15000);

			Mouse mouse = new DesktopMouse();
			ScreenRegion botonOK = s.find(new ImageTarget(new File("src/main/resources/images/OK.png")));
			mouse.click(botonOK.getCenter());
		}
		
		// Espera hasta que el boton de 'Crear Solicitud' esta activo
		wait.until(ExpectedConditions.elementToBeClickable(By.name("alta")));
		log.info("Al final de login: " + driver.getCurrentUrl());
	}
}

package es.ja.cgj.webdriver.paginas;

import java.io.File;
import java.util.Set;

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

import es.ja.cgj.webdriver.utils.MouseUtils;

public class PaginaFirmarDocumentos extends Pagina {

	// Define a static log variable
	static Logger log = Logger.getLogger(PaginaFirmarDocumentos.class);

	public PaginaFirmarDocumentos(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
	}
	
	
	public void procesoFirma(String inicialesCertificado) {
		// Esperamos a que abra la nueva pestaña

		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Listado"));
		// --> No funciona ya que necesitariamos WindowsToBeAvailableAdnSwitchToIt
		// wait.until(ExpectedConditions.

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}

		// Cogemos el foco de la nueva ventana que se ha abierto
		driver.switchTo().window("Listado");
		log.info("2. Ventana:" + driver.getWindowHandle());

		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr/td[1]/table[2]/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/a/img")));
		// Pulsamos el icono de la "v" roja
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr/td[1]/table[2]/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/a/img"))
				.click();

		// Esperamos a que cargue la pagina y aparezca el icono de firma
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//*[@id=\"formDocum\"]/table[2]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[4]/td[2]/a/img")));

		// Pulsamos el icono de firma
		driver.findElement(
				By.xpath("//*[@id=\"formDocum\"]/table[2]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[4]/td[2]/a/img"))
				.click();

		/*
		 * // Wait for the page to load, timeout after 10 seconds (new
		 * WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
		 * public Boolean apply(WebDriver d) { return
		 * d.getPageSource().toLowerCase().contains("Firmar Documentos"); } });
		 */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}

		Set<String> ventanas = driver.getWindowHandles();
		log.info("Ventanas: " + ventanas.toString());
		log.info("3. Ventana: " + ventanas.toArray()[2].toString());

		// Cogemos el foco de la nueva ventana que se ha abierto
		driver.switchTo().window(ventanas.toArray()[2].toString());

		//Movemos el raton para evitar que la pagina se quede colgada tras esperar 10+10 segundos
		MouseUtils.waitAndMoveMouse(10000, 10000);
				
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr/td/table[2]/tbody/tr[2]/td/span/img")));

		// Pulsamos el icono de la firma
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr/td/table[2]/tbody/tr[2]/td/span/img"))
				.click();
		
		// Esperamos a que salga el applet de @firma
		ScreenRegion s = new DesktopScreenRegion();
		s.wait(new ImageTarget(new File("src/main/resources/images/applet_afirma.png")),15000);
		
		// Create a mouse object
		Mouse mouse = new DesktopMouse();
		ScreenRegion desplegarCombo = s.find(new ImageTarget(new File("src/main/resources/images/afirma_desplegar_combo.png")));
		mouse.click(desplegarCombo.getCenter());
		s.wait(new ImageTarget(new File("src/main/resources/images/afirma_"+inicialesCertificado+".png")),15000);
		ScreenRegion afirmaJVP = s.find(new ImageTarget(new File("src/main/resources/images/afirma_"+inicialesCertificado+".png")));
		mouse.click(afirmaJVP.getCenter());
		ScreenRegion afirmaOK = s.find(new ImageTarget(new File("src/main/resources/images/OK_applet_afirma.png")));
		mouse.click(afirmaOK.getCenter());
						
		/*wait.until(ExpectedConditions.textToBePresentInElement(
				By.xpath("/html/body/table[2]/tbody/tr[1]/td"),
				"El documento ha sido firmado correctamente")); --> No se por que no funciona*/ 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}
		
		// Cerrar ventana una vez firmado el ANEXO 0
		driver.close();

		// Cerrar ventana de firma de documentos
		driver.switchTo().window("Listado").close();
		
		// Cogemos el foco de la ventana principal
		driver.switchTo().window(ventanas.toArray()[0].toString());
		
		// Espera hasta que el boton de Alta de Solicitud está activo
		wait.until(ExpectedConditions.elementToBeClickable(By.name("alta")));
	}
}

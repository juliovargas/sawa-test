package es.ja.cgj.webdriver.paginas;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import es.ja.cgj.webdriver.config.Config;

public class PaginaPrincipal extends Pagina {
	
	// Define a static log variable
	static Logger log = Logger.getLogger(PaginaPrincipal.class);
	
	//Botones
	@FindBy(name = "alta")
	private WebElement botonAlta;
	@FindBy(name = "firmar")
	private WebElement botonFirmar;
	@FindBy(name = "presentar")
	private WebElement botonPresentar;
	
	public PaginaPrincipal(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
	}
	
	private void loadPage() {
		
		driver.get(Config.BASE_URL_SSL + "/sawa2007/solicitudes.jsp");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}
				
		// Espera hasta que el boton de 'Crear Solicitud' esta activo
		wait.until(ExpectedConditions.elementToBeClickable(By.name("alta")));
	}
	
	public PaginaCrearSolicitud pulsarBotonCrearSolicitud() {
		
		// Meto una espera de 2 seg para evitar un error de Javascript [JavaScript Error: "e is null" {file: "file:///C:/Users/A144179/AppData/Local/Temp/anonymous6968854035100811153webdriver-profile/extensions/fxdriver@googlecode.com/components/command_processor.js" line: 9904}]' when calling method: [nsICommandProcessor::execute]
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("alta")));
	
		botonAlta.click();
		//driver.findElement(By.name("alta")).click();
		
		return new PaginaCrearSolicitud(driver, wait);
	}

	public PaginaFirmarDocumentos pulsarBotonFirmar() {
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.name("firmar")));
		// Pulsamos el boton 'Firmar'
		botonFirmar.click();
		//driver.findElement(By.name("firmar")).click();
		
		return new PaginaFirmarDocumentos(driver, wait);
	}
	
	public void presentarDocumentos() {
		
		loadPage();
		
		// Vamos al iframe de solicitudes pendientes
		driver.switchTo().frame("iframe_pendientes");
		String idSolicitud = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td[3]")).getText();
		log.info("idSolicitud: " + idSolicitud);
		
		// Volvemos al contenido principal
		driver.switchTo().defaultContent();
				
		// Pulsamos el boton 'Presentar'
		botonPresentar.click();

		// OK al popup 'Ya existe una solicitud de esta entidad presentada, si
		// prosigue se anulará la solicitud ya presentada ¿desea continuar?'
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}

		// OK al popup 'La solicitud que se va a presentar no tiene peticiones,
		// ¿desea continuar?'
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		
		// Vamos al iframe de solicitudes presentadas
		driver.switchTo().frame("iframe_presentadas");
		
		String idSolicitudPresentadas = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td[3]")).getText();
		log.info("idSolicitudPresentadas: " + idSolicitudPresentadas);
		
		assert idSolicitud.equals(idSolicitudPresentadas);
		
		//Volvemos del iframe
		driver.switchTo().defaultContent();
				
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}*/
	}
}

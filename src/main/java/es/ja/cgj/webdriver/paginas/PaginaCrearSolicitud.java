package es.ja.cgj.webdriver.paginas;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class PaginaCrearSolicitud extends Pagina {

	// Define a static log variable
	static Logger log = Logger.getLogger(PaginaCrearSolicitud.class);
	
	//Campos del formulario
	@FindBy(id = "denominacion") private WebElement textFieldDenominacion;
		
	//Botones
	@FindBy(id = "guardar") private WebElement botonGuardar;
		
	public PaginaCrearSolicitud(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
	}

	public void dejarFormularioVacioYGuardar() {
		
		// Meto una espera de 4 seg para evitar un error de Javascript [JavaScript Error: "e is null" {file: "file:///C:/Users/A144179/AppData/Local/Temp/anonymous6968854035100811153webdriver-profile/extensions/fxdriver@googlecode.com/components/command_processor.js" line: 9904}]' when calling method: [nsICommandProcessor::execute]
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			log.error("Error inesperado", e);
		}
		
		// Esperamos a que cargue la página
		wait.until(ExpectedConditions.elementToBeClickable(By.id("guardar")));

		// Hacemos click en guardar sin rellenar ningun campo del formulario
		botonGuardar.click();
		
		// Busca el mensaje de error
		wait.until(ExpectedConditions.textToBePresentInElement(
				By.xpath("/html/body/div/table/tbody/tr[2]/td[2]/div/p"),
				"Este campo es requerido."));

		// Esperamos a que el botón 'Volver' esté activo
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//*[@id=\"form1\"]/table[5]/tbody/tr/td/table/tbody/tr/td/p/input[2]")));

		// Click en boton Volver
		driver.findElement(
				By.xpath("//*[@id=\"form1\"]/table[5]/tbody/tr/td/table/tbody/tr/td/p/input[2]"))
				.click();

		// Espera hasta que el boton de Alta de Solicitud está activo
		wait.until(ExpectedConditions.elementToBeClickable(By.name("alta")));
	}

	public void rellenarFormulario(String denominacion, String nifContacto,
			String tipoViaSecretario, String nombreViaSecretario,
			String numeroViaSecretario, String codigoPostalSecretario,
			String provinciaSecretario, String localidadSecretario,
			String paisSecretario, String emailEntidad, String dniSecretario,
			String nombreSecretario, String apellido1Secretario,
			String apellido2Secretario, String dniRepresentante,
			String nombreRepresentante, String apellido1Representante,
			String apellido2Representante, String tipoViaNotificacion,
			String nombreViaNotificacion, String numeroViaNotificacion,
			String codigoPostalNotificacion, String provinciaNotificacion,
			String localidadNotificacion, String paisNotificacion,
			String emailComunicaNotificacion,
			String movilNotificacionElectronica, String codigoEntidad,
			String codigoSucursal, String codigoControl, String numeroCuenta,
			String nombreEntidadBancaria, String domicilioEntidadBancaria,
			String provinciaEntidadBancaria, String localidadEntidadBancaria,
			String codigoPostalEntidadBancaria,
			String cargoRepresentanteUniversidad, String diaNombraRepreUni,
			String mesNombraRepreUni, String anyoNombraRepreUni,
			String numeroBojaUni, String facultadoUniversidad,
			String lugarSolicitud, String diaSolicitud, String mesSolicitud,
			String anyoSolicitud) {

		// Esperamos a que cargue la página
		wait.until(ExpectedConditions.elementToBeClickable(By.id("guardar")));

		// Rellenamos los campos obligatorios
		textFieldDenominacion.clear();
		textFieldDenominacion.sendKeys(denominacion);
		//driver.findElement(By.id("denominacion")).clear();
		//driver.findElement(By.id("denominacion")).sendKeys(denominacion);
		driver.findElement(By.id("nifContacto")).clear();
		driver.findElement(By.id("nifContacto")).sendKeys(nifContacto);
		new Select(driver.findElement(By.id("tipoViaSecretario")))
				.selectByVisibleText(tipoViaSecretario);
		driver.findElement(By.id("nombreViaSecretario")).clear();
		driver.findElement(By.id("nombreViaSecretario")).sendKeys(
				nombreViaSecretario);
		driver.findElement(By.id("numeroViaSecretario")).clear();
		driver.findElement(By.id("numeroViaSecretario")).sendKeys(
				numeroViaSecretario);
		driver.findElement(By.id("codigoPostalSecretario")).clear();
		driver.findElement(By.id("codigoPostalSecretario")).sendKeys(
				codigoPostalSecretario);
		new Select(driver.findElement(By.id("provinciaSecretario")))
				.selectByVisibleText(provinciaSecretario);
		new Select(driver.findElement(By.id("localidadSecretario")))
				.selectByVisibleText(localidadSecretario);
		driver.findElement(By.id("paisSecretario")).clear();
		driver.findElement(By.id("paisSecretario")).sendKeys(paisSecretario);
		driver.findElement(By.id("emailEntidad")).clear();
		driver.findElement(By.id("emailEntidad")).sendKeys(emailEntidad);
		driver.findElement(By.id("dniSecretario")).clear();
		driver.findElement(By.id("dniSecretario")).sendKeys(dniSecretario);
		driver.findElement(By.id("nombreSecretario")).clear();
		driver.findElement(By.id("nombreSecretario"))
				.sendKeys(nombreSecretario);
		driver.findElement(By.id("apellido1Secretario")).clear();
		driver.findElement(By.id("apellido1Secretario")).sendKeys(
				apellido1Secretario);
		driver.findElement(By.id("apellido2Secretario")).clear();
		driver.findElement(By.id("apellido2Secretario")).sendKeys(
				apellido2Secretario);
		driver.findElement(By.id("dniRepresentante")).clear();
		driver.findElement(By.id("dniRepresentante"))
				.sendKeys(dniRepresentante);
		driver.findElement(By.id("nombreRepresentante")).clear();
		driver.findElement(By.id("nombreRepresentante")).sendKeys(
				nombreRepresentante);
		driver.findElement(By.id("apellido1Representante")).clear();
		driver.findElement(By.id("apellido1Representante")).sendKeys(
				apellido1Representante);
		driver.findElement(By.id("apellido2Representante")).clear();
		driver.findElement(By.id("apellido2Representante")).sendKeys(
				apellido2Representante);
		new Select(driver.findElement(By.id("tipoViaNotificacion")))
				.selectByVisibleText(tipoViaNotificacion);
		driver.findElement(By.id("nombreViaNotificacion")).clear();
		driver.findElement(By.id("nombreViaNotificacion")).sendKeys(
				nombreViaNotificacion);
		driver.findElement(By.id("numeroViaNotificacion")).clear();
		driver.findElement(By.id("numeroViaNotificacion")).sendKeys(
				numeroViaNotificacion);
		driver.findElement(By.id("codigoPostalNotificacion")).clear();
		driver.findElement(By.id("codigoPostalNotificacion")).sendKeys(
				codigoPostalNotificacion);
		new Select(driver.findElement(By.id("provinciaNotificacion")))
				.selectByVisibleText(provinciaNotificacion);
		new Select(driver.findElement(By.id("localidadNotificacion")))
				.selectByVisibleText(localidadNotificacion);
		driver.findElement(By.id("paisNotificacion")).clear();
		driver.findElement(By.id("paisNotificacion"))
				.sendKeys(paisNotificacion);
		driver.findElement(By.id("notificacionElectronica")).click();
		driver.findElement(By.id("emailComunicaNotificacion")).clear();
		driver.findElement(By.id("emailComunicaNotificacion")).sendKeys(
				emailComunicaNotificacion);
		driver.findElement(By.id("movilNotificacionElectronica")).clear();
		driver.findElement(By.id("movilNotificacionElectronica")).sendKeys(
				movilNotificacionElectronica);
		driver.findElement(By.id("siConsiente")).click();
		driver.findElement(By.id("codigoEntidad")).clear();
		driver.findElement(By.id("codigoEntidad")).sendKeys(codigoEntidad);
		driver.findElement(By.id("codigoSucursal")).clear();
		driver.findElement(By.id("codigoSucursal")).sendKeys(codigoSucursal);
		driver.findElement(By.id("codigoControl")).clear();
		driver.findElement(By.id("codigoControl")).sendKeys(codigoControl);
		driver.findElement(By.id("numeroCuenta")).clear();
		driver.findElement(By.id("numeroCuenta")).sendKeys(numeroCuenta);
		driver.findElement(By.id("nombreEntidadBancaria")).clear();
		driver.findElement(By.id("nombreEntidadBancaria")).sendKeys(
				nombreEntidadBancaria);
		driver.findElement(By.id("domicilioEntidadBancaria")).clear();
		driver.findElement(By.id("domicilioEntidadBancaria")).sendKeys(
				domicilioEntidadBancaria);
		new Select(driver.findElement(By.id("provinciaEntidadBancaria")))
				.selectByVisibleText(provinciaEntidadBancaria);
		new Select(driver.findElement(By.id("localidadEntidadBancaria")))
				.selectByVisibleText(localidadEntidadBancaria);
		driver.findElement(By.id("codigoPostalEntidadBancaria")).clear();
		driver.findElement(By.id("codigoPostalEntidadBancaria")).sendKeys(
				codigoPostalEntidadBancaria);
		driver.findElement(By.id("certificadoEntidadUniversidad")).click();
		driver.findElement(By.id("cargoRepresentanteUniversidad")).clear();
		driver.findElement(By.id("cargoRepresentanteUniversidad")).sendKeys(
				cargoRepresentanteUniversidad);
		new Select(driver.findElement(By.id("diaNombraRepreUni")))
				.selectByVisibleText(diaNombraRepreUni);
		new Select(driver.findElement(By.id("mesNombraRepreUni")))
				.selectByVisibleText(mesNombraRepreUni);
		driver.findElement(By.id("anyoNombraRepreUni")).clear();
		driver.findElement(By.id("anyoNombraRepreUni")).sendKeys(
				anyoNombraRepreUni);
		driver.findElement(By.id("numeroBojaUni")).clear();
		driver.findElement(By.id("numeroBojaUni")).sendKeys(numeroBojaUni);
		driver.findElement(By.id("facultadoUniversidad")).clear();
		driver.findElement(By.id("facultadoUniversidad")).sendKeys(
				facultadoUniversidad);
		driver.findElement(By.id("noSolicitado")).click();
		driver.findElement(By.id("lugarSolicitud")).clear();
		driver.findElement(By.id("lugarSolicitud")).sendKeys(lugarSolicitud);
		new Select(driver.findElement(By.id("diaSolicitud")))
				.selectByVisibleText(diaSolicitud);
		new Select(driver.findElement(By.id("mesSolicitud")))
				.selectByVisibleText(mesSolicitud);
		driver.findElement(By.id("anyoSolicitud")).clear();
		driver.findElement(By.id("anyoSolicitud")).sendKeys(anyoSolicitud);

	}

	public void guardarSolicitud() {
		driver.findElement(By.id("guardar")).click();
	}
}

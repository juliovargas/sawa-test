package es.ja.cgj.webdriver.scripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import es.ja.cgj.webdriver.config.Config;
import es.ja.cgj.webdriver.paginas.PaginaCrearSolicitud;
import es.ja.cgj.webdriver.paginas.PaginaFirmarDocumentos;
import es.ja.cgj.webdriver.paginas.PaginaLogin;
import es.ja.cgj.webdriver.paginas.PaginaPrincipal;
import es.ja.cgj.webdriver.utils.ExcelReader;

public class SAWA_VOL_EV_Representante {

	// Define a static log variable
	static Logger log = Logger.getLogger(SAWA_VOL_EV_Representante.class);

	private WebDriver driver;
	private Wait<WebDriver> wait;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Parameters({ "browser" })
	@BeforeClass
	public void setUp(@Optional("firefox") String browser) {

		log.info("Browser parameter TestNG: " + browser);
		driver = WebdriverConfiguration.setupDriver(browser, "Selenium");
		driver.manage().timeouts()
				.implicitlyWait(Config.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Config.EXPLICIT_WAIT_TIME);

		// Login con Certificado
		PaginaLogin paginaLogin = new PaginaLogin(driver, wait);
		paginaLogin.loginConCertificado();
	}
	
	@Test
	public void testValidacionCreacionFormularioSAWA() {

		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);
		PaginaCrearSolicitud paginaCrearSolicitud = paginaPrincipal
				.pulsarBotonCrearSolicitud();

		paginaCrearSolicitud.dejarFormularioVacioYGuardar();
	}

	@DataProvider(name = "DP_CrearFormulario")
	public String[][] createData1() throws Exception {

		String[][] retObjArr = ExcelReader.getTableArray(
				"src\\main\\resources\\data\\SAWA_VOL_EV.xls", "Datos",
				"formularioSolicitud");
		return (retObjArr);
	}

	@Test(dataProvider = "DP_CrearFormulario")
	public void testCrearFormulario_OK(String denominacion, String nifContacto,
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
			String anyoSolicitud) throws Exception {

		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);

		PaginaCrearSolicitud paginaCrearSolicitud = paginaPrincipal
				.pulsarBotonCrearSolicitud();

		paginaCrearSolicitud.rellenarFormulario(denominacion, nifContacto,
				tipoViaSecretario, nombreViaSecretario, numeroViaSecretario,
				codigoPostalSecretario, provinciaSecretario,
				localidadSecretario, paisSecretario, emailEntidad,
				dniSecretario, nombreSecretario, apellido1Secretario,
				apellido2Secretario, dniRepresentante, nombreRepresentante,
				apellido1Representante, apellido2Representante,
				tipoViaNotificacion, nombreViaNotificacion,
				numeroViaNotificacion, codigoPostalNotificacion,
				provinciaNotificacion, localidadNotificacion, paisNotificacion,
				emailComunicaNotificacion, movilNotificacionElectronica,
				codigoEntidad, codigoSucursal, codigoControl, numeroCuenta,
				nombreEntidadBancaria, domicilioEntidadBancaria,
				provinciaEntidadBancaria, localidadEntidadBancaria,
				codigoPostalEntidadBancaria, cargoRepresentanteUniversidad,
				diaNombraRepreUni, mesNombraRepreUni, anyoNombraRepreUni,
				numeroBojaUni, facultadoUniversidad, lugarSolicitud,
				diaSolicitud, mesSolicitud, anyoSolicitud);

		paginaCrearSolicitud.guardarSolicitud();

	}

	@Test
	// (dependsOnMethods = { "testCrearFormulario_OK" })
	public void testFirmaRepresentante() {

		log.info("1. Ventana:" + driver.getWindowHandle());

		// Pulsamos el boton 'Firmar'
		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);
		PaginaFirmarDocumentos paginaFirmarDocumentos = paginaPrincipal
				.pulsarBotonFirmar();

		paginaFirmarDocumentos
				.procesoFirma(Config.INICIALES_CERT_REPRESENTANTE);
	}

	@Test(dependsOnMethods = { "testFirmaRepresentante" })
	public void testPresentarSolicitud() {

		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);
		paginaPrincipal.presentarDocumentos();
	}

	@Test
	public void logoutYCerrarFirefox() {
		driver.quit();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
}
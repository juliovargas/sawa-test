package es.ja.cgj.webdriver.scripts;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import es.ja.cgj.webdriver.config.Config;
import es.ja.cgj.webdriver.paginas.PaginaFirmarDocumentos;
import es.ja.cgj.webdriver.paginas.PaginaLogin;
import es.ja.cgj.webdriver.paginas.PaginaPrincipal;


public class SAWA_VOL_EV_Secretario {
	
	// Define a static logger variable
	static Logger log = Logger.getLogger(SAWA_VOL_EV_Representante.class);
		
	private WebDriver driver;
	private Wait<WebDriver> wait;
	private StringBuffer verificationErrors = new StringBuffer();

	//@BeforeMethod
	@Parameters({ "browser" })
	@BeforeClass
	public void setUp(@Optional("firefox") String browser) {

		driver = WebdriverConfiguration.setupDriver(browser, "Selenium_MA");
		driver.manage().timeouts().implicitlyWait(Config.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Config.EXPLICIT_WAIT_TIME); // wait for max of 15 seconds

		// Login con Certificado
		PaginaLogin paginaLogin = new PaginaLogin(driver, wait);
		paginaLogin.loginConCertificado();
	}

	@Test
	public void testFirmaSecretario() {
		
		log.info("1. Ventana:" + driver.getWindowHandle());
		// Pulsamos el boton 'Firmar'
		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);
		PaginaFirmarDocumentos paginaFirmarDocumentos = paginaPrincipal.pulsarBotonFirmar();
		
		paginaFirmarDocumentos.procesoFirma(Config.INICIALES_CERT_SECRETARIO);
	}
	
	@Test//(dependsOnMethods = { "testFirmaSecretario" })
	public void testPresentarSolicitud() {
		
		PaginaPrincipal paginaPrincipal = new PaginaPrincipal(driver, wait);
		paginaPrincipal.presentarDocumentos();
	}

	//@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
}
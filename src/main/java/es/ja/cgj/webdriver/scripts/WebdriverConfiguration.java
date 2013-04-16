package es.ja.cgj.webdriver.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import es.ja.cgj.webdriver.config.Config;


public class WebdriverConfiguration {
	
	private static final String FIREFOX = "firefox";
    private static final String IEXPLORER = "iexplorer";
    
    public static WebDriver setupDriver(String browser, String firefoxProfile) {
        
    	WebDriver driver;
        
        if (FIREFOX.equals(browser)) {
        	ProfilesIni profilesIni = new ProfilesIni();
    		// Clone the named profile
    		FirefoxProfile profile = profilesIni.getProfile(firefoxProfile);
    		// profile.setAssumeUntrustedCertificateIssuer(false);
    		// profile.setAcceptUntrustedCertificates (true);
    		// driver = new SikuliFirefoxDriver();
    		driver = new FirefoxDriver(profile);
        } else if (IEXPLORER.equals(browser)) {
            /*DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);*/
            driver = new InternetExplorerDriver();
        } else {
            throw new RuntimeException("You must define webdriver type");
        }
        
        return driver;
    }
    
    
    public static WebDriver setupDriver(String firefoxProfile) {
        
    	WebDriver driver;
        
        if (FIREFOX.equals(Config.WEBDRIVER)) {
        	ProfilesIni profilesIni = new ProfilesIni();
    		// Clone the named profile
    		FirefoxProfile profile = profilesIni.getProfile(firefoxProfile);
    		// profile.setAssumeUntrustedCertificateIssuer(false);
    		// profile.setAcceptUntrustedCertificates (true);
    		// driver = new SikuliFirefoxDriver();
    		driver = new FirefoxDriver(profile);
        } else if (IEXPLORER.equals(Config.WEBDRIVER)) {
            /*DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);*/
            driver = new InternetExplorerDriver();
        } else {
            throw new RuntimeException("You must define webdriver type");
        }
        
        return driver;
    }
}

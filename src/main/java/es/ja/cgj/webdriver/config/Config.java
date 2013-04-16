package es.ja.cgj.webdriver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class Config {

    private static final Logger log = Logger.getLogger(Config.class);

    public static final String BASE_URL;
    public static final String BASE_URL_SSL;
    public static final String WEBDRIVER;
    public static final boolean LOGIN_CON_CERTIFICADO_POR_DEFECTO;
    public static final long IMPLICIT_WAIT_TIME;
    public static final long EXPLICIT_WAIT_TIME;
    public static final String INICIALES_CERT_REPRESENTANTE;
    public static final String INICIALES_CERT_SECRETARIO;
    

    static {
        String config = "/webdriver.properties";
        if (StringUtils.isEmpty(config)) {
            throw new RuntimeException("Couldn't read properties");
        }
    
        Properties props = new Properties();
        try {
            //props = PropertiesLoaderUtils.loadAllProperties(config);
        	InputStream in = Config.class.getResourceAsStream(config);
        	props.load(in);
        	in.close();
        } catch (IOException e) {
            log.error("Error al leer del fichero properties", e);
        }

        BASE_URL = props.getProperty("app.url");
        BASE_URL_SSL = props.getProperty("app.url.ssl");
        WEBDRIVER = props.getProperty("webdriver");
        LOGIN_CON_CERTIFICADO_POR_DEFECTO = new Boolean(props.getProperty("loginConCertificadoPorDefecto"));
        IMPLICIT_WAIT_TIME = new Long(props.getProperty("implicitWaitTime"));
        EXPLICIT_WAIT_TIME = new Long(props.getProperty("explicitWaitTime"));
        INICIALES_CERT_REPRESENTANTE = props.getProperty("inicialesCertRepresentante");
        INICIALES_CERT_SECRETARIO = props.getProperty("inicialesCertSecretario");
       

        log.info("Base url:   " + BASE_URL);
        log.info("Base url https:   " + BASE_URL_SSL);
        log.info("WebDriver:  " + WEBDRIVER);
        log.info("Login con certificado por defecto:  " + LOGIN_CON_CERTIFICADO_POR_DEFECTO);
        log.info("Implicit Wait Time:  " + IMPLICIT_WAIT_TIME);
        log.info("Explicit Wait Time:  " + EXPLICIT_WAIT_TIME);
        log.info("Representante:  " + INICIALES_CERT_REPRESENTANTE);
        log.info("Secretario:  " + INICIALES_CERT_SECRETARIO);
    }
}

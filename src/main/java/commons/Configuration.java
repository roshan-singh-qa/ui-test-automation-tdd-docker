package commons;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Config that is supplied to tests via a properties file. Holds data that is used by the tests and the
 * url of the environment under test.
 */
public enum Configuration {

    url,
    username,
    password;

    private final String configPath = System.getProperty("configPath");

    private Properties properties;

    private String value;

    private void init() {
        if (properties == null) {

            // Check config path is available.
            if (configPath == null) {
                throw new RuntimeException("You must pass configPath as a system property.");
            }

            properties = new Properties();

            try {
                properties.load(new FileInputStream(configPath));
            } catch (Exception e) {
                throw new RuntimeException("Unable to load test properties from:" + configPath + ".", e);
            }
        }
        value = (String) properties.get(this.toString());
    }

    public Object getValue() {
        if (value == null) {
            init();
        }
        return value;
    }

    public String asString() {
        return (String) getValue();
    }
}

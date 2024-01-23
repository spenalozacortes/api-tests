package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/environment.json");

    public static String getBaseURI() {
        return CONFIG_READER.getValueByKey("baseURI");
    }
}

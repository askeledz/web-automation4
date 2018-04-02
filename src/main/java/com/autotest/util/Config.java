package com.autotest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Andrej Skeledzija 2017
 */

public class Config {

    //Logger
    private static final Logger logger = LogManager.getLogger(Config.class);

    private static final Properties PROPS = new Properties();

    static {
        try {
            PROPS.load(Config.class.getResourceAsStream("/config-main.properties"));
            String configName = System.getProperty("configName");
            if (configName != null) {
                PROPS.load(Config.class.getResourceAsStream("/config-" + configName + ".properties"));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //get properties from file
    public static final String USER_NAME = get("user.name");
    public static final String USER_PASSWORD = get("user.password");
    public static String USER_URL = get("user.url");

    public static final String MYSQL_DBURL = get("mysql.dburl");
    public static final String MYSQL_USER = get("mysql.user");
    public static final String MYSQL_PASSWORD = get("mysql.password");
    public static final String MYSQL_DRIVER = get("mysql.driver");




    public static String get(String key) {
        if (!PROPS.containsKey(key)) {
            //throw new IllegalStateException("Required configuration property " + key + " is not found.");

            // If "user.url" is commented out in properties file, you should provide url in command line
            // e.g. mvn clean test -am -DtestSuite=testnglocal.xml -Durl="http://automationpractice.com/"
            return USER_URL = System.getProperty("user.url");
        }

        return PROPS.getProperty(key);
    }
}

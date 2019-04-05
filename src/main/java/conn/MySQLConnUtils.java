package conn;

import org.apache.log4j.Logger;
import utils.ClassNameUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySQLConnUtils {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Driver class wasn't found " + e);
        }
    }

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {


        String url = "jdbc:mysql://localhost:3306/cafejavacore?useSSL=false";
        log.info("set url for data base connection");

        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect","true");
        prop.put("characterEncoding", "utf-8");
        prop.put("useUnicode", "true");
        prop.put("serverTimezone", "UTC");
        log.info("set properties for data base connection");

        log.info("establish connection to data base by url = " + url + ";" +
                " properties = " + prop.stringPropertyNames().toString());
        return DriverManager.getConnection(url,prop);
    }
}

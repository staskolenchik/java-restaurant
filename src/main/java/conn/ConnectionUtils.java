package conn;

import org.apache.log4j.Logger;
import utils.ClassNameUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        log.info("get connection to data base");
        return MySQLConnUtils.getMySQLConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            log.info("data base connection closed, connection = " + conn);
            conn.close();
        } catch (Exception e) {
            log.error("couldn't close current connection: " + e);
        }
    }

    public static void rollbackQuietly(Connection conn) {

        try {
            log.info("Undo all changes made in the current connection, connection = " + conn);
            conn.rollback();
        } catch (Exception e) {
            log.error("All changes were made in the current connection: " + e);
        }
    }

}

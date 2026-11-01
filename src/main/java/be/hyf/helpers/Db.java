package be.hyf.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {

    private final Properties props;

    private Db() {
        this.props = null;
    }

    public Db(String configPath) {
        this.props = new Properties();
        loadProperties(configPath);
    }

    public Db(Properties props) {
        this.props = new Properties();
        if (props != null) {
            this.props.putAll(props);
        }
    }

    public Db(String url, String user, String password) {
        this.props = new Properties();
        this.props.setProperty("db.url", url);
        this.props.setProperty("db.user", user);
        this.props.setProperty("db.password", password);
    }

    public void loadProperties(String path) {
        try (FileInputStream f = new FileInputStream(path)) {
            props.load(f);
        } catch (IOException e) {
            System.err.println("Could not read config file: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }

    public static Connection getConnection(String configPath) throws SQLException {
        return new Db(configPath).getConnection();
    }

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection(Properties props) throws SQLException {
        return new Db(props).getConnection();
    }
}

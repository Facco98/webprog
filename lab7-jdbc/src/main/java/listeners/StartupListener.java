package listeners;

import common.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.sql.SQLException;

public class StartupListener implements ServletContextListener {

    public static DatabaseManager databaseManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try{
            Class.forName(DatabaseManager.DRIVER);
            databaseManager = DatabaseManager.newInstance();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }

}

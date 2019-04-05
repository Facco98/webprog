package common;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseManager {

    public static final String DB = "postgresql";
    public static final String HOST = "localhost";
    public static final String PORT = "5432";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "psw";
    public static final String DATABASE_NAME = "dbname";
    public static final String DRIVER = "org.postgresql.Driver";


    private Connection databaseConnection;



    private DatabaseManager() throws SQLException {

        var URL = "jdbc:" + DB + "://" + HOST + ":" + PORT +"/" + DATABASE_NAME;
        var props = new Properties();
        props.setProperty("username", USERNAME);
        props.setProperty("password", PASSWORD);

        this.databaseConnection = DriverManager.getConnection(URL, props);

    }

    public ResultSet getUsers(String... fields) throws SQLException {

        var query = "SELECT ";
        int n = fields.length;

        for( int i = 0; i < n; i++ ) {
            query += fields[i];
            if( i != n-1 )
                query += ",";
            query += " ";
        }

        query += "FROM users U;";

        var stmt = this.databaseConnection.createStatement();
        stmt.execute(query);
        return stmt.getResultSet();


    }

    public ResultSet getShoppingList(int userID) throws SQLException {

        var query = "SELECT SL.name as sl_name, SL.description as sl_desc " +
                "FROM shopping_list SL JOIN users_shopping_list USL ON SL.id = USL.id_shopping_list " +
                "JOIN users U ON USL.id_user = U.id AND U.id = ?";
        var preparedStatement = this.databaseConnection.prepareStatement(query);
        preparedStatement.setInt(1, userID);

        return preparedStatement.executeQuery();



    }

    public static DatabaseManager newInstance() throws SQLException {

        return new DatabaseManager();

    }

}

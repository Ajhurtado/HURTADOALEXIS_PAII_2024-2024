package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

	
		private static final String URL = "jdbc:mysql://localhost:3306/instituto";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Password1";
	    
	 // Instancia única de la conexión
	    private static Connection connection;
	    
	    private ConnectionSingleton() {
	    	
	    }
	
	 // Método estático para obtener la instancia única de la conexión
	    public static Connection  getConnection() throws SQLException {
	        if (connection == null || connection.isClosed()) {
	            // Si la conexión es nula o está cerrada, se crea una nueva conexión
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        }
	        return connection;
	    }

	    // Método estático para cerrar la conexión
	    public static void closeConnection() throws SQLException {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    }
}

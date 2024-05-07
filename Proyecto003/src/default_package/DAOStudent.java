package default_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOStudent implements IDAO<Student>{

	/*Connection connection = null;
	public DAOStudent() throws SQLException{
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "Password1");
	}
	
	//Solo prueba
	public void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	*/
	
	
	@Override
	public void create(Student student) throws SQLException {
		 Connection connection = ConnectionSingleton.getConnection();
		// TODO Auto-generated method stub
		 try (PreparedStatement ps = connection.prepareStatement("INSERT INTO student VALUES (?,?,?,?)")) {
	            ps.setInt(1, student.getId());
	            ps.setString(2, student.getName());
	            ps.setString(3, student.getLastname());
	            ps.setInt(4, student.getAge());
	            ps.executeUpdate();
	        } finally {
	            ConnectionSingleton.closeConnection();
	        }
		 
	}
	@Override
	public void read() throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        // Preparar la consulta SELECT
	        String query = "SELECT * FROM student";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Ejecutar la consulta y obtener los resultados
	            try (ResultSet rs = ps.executeQuery()) {
	                // Procesar los resultados
	                while (rs.next()) {
	                    // Leer los datos de cada fila
	                    int id = rs.getInt("id");
	                    String name = rs.getString("name");
	                    String lastname = rs.getString("lastname");
	                    int age = rs.getInt("age");
	                    // Haz lo que necesites con los datos leídos, como imprimirlos en la consola
	                    System.out.println("ID: " + id + ", Name: " + name + ", Lastname: " + lastname + ", Age: " + age);
	                }
	            }
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}
	@Override
	public void update(Student student) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        // Preparar la consulta UPDATE
	        String query = "UPDATE student SET name=?, lastname=?, age=? WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Establecer los valores de los parámetros en la consulta
	            ps.setString(1, student.getName());
	            ps.setString(2, student.getLastname());
	            ps.setInt(3, student.getAge());
	            ps.setInt(4, student.getId());
	            // Ejecutar la consulta
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}
	@Override
	public void delete(int studentId) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        // Preparar la consulta DELETE
	        String query = "DELETE FROM student WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Establecer el valor del parámetro en la consulta
	            ps.setInt(1, studentId);
	            // Ejecutar la consulta
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}
	

	

	

}

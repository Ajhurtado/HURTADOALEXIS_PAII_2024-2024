package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOTeacher implements IDAO<Teacher>{

	Connection connection = null;
	public DAOTeacher() throws SQLException{
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "Password1");
	}
	
	//Solo prueba
	public void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	
	
	@Override
	public void create(Teacher teacher) throws SQLException {
		// TODO Auto-generated method stub
		 Connection connection = ConnectionSingleton.getConnection();
		try(PreparedStatement ps = connection.prepareStatement("INSERT INTO teacher VALUES (?,?,?,?)")){
			ps.setInt(1, teacher.id);
			ps.setString(2, teacher.name);
			ps.setString(3, teacher.lastname);
			ps.setInt(4, teacher.age);
			 ps.execute();
			 ps.close();
		}finally {
            ConnectionSingleton.closeConnection();
        }
	}

	@Override
	public void read() throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();	     
	        String query = "SELECT * FROM teacher";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String name = rs.getString("name");
	                    String lastname = rs.getString("lastname");
	                    int age = rs.getInt("age");
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
	public void update(Teacher teacher) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "UPDATE teacher SET name=?, lastname=?, age=? WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {	    
	        	ps.setInt(1, teacher.getId());
	        	ps.setString(2, teacher.getName());
	            ps.setString(3, teacher.getLastname());
	            ps.setInt(4, teacher.getAge());	          
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

	@Override
	public void delete(int teacherId) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "DELETE FROM teacher WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, teacherId);
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

}

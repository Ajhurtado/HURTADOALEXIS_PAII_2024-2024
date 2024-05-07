package default_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSubject implements IDAO<Subject>{

	
	@Override
	public void create(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		 Connection connection = ConnectionSingleton.getConnection();
		 try (PreparedStatement ps = connection.prepareStatement("INSERT INTO subject VALUES (?,?,?,?)")){
			ps.setInt(1, subject.id);
			ps.setString(2, subject.name);
			ps.setString(3, subject.description);
			ps.setInt(4, subject.level);
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
	        String query = "SELECT * FROM subject";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String name = rs.getString("name");
	                    String description = rs.getString("description");
	                    int level = rs.getInt("level");
	                    System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description + ", Level: " + level);
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
	public void update(Subject subject) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "UPDATE subject SET name=?, description=?, level=? WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {	         
	            ps.setString(1, subject.getName());
	            ps.setString(2, subject.getDescription());
	            ps.setInt(3, subject.getLevel());
	            ps.setInt(4, subject.getId());
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

	@Override
	public void delete(int subjectId) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "DELETE FROM subject WHERE id=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, subjectId);
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

}

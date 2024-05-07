package default_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSchedule implements IDAO<Schedule> {

	@Override
	public void create(Schedule schedule) throws SQLException {
		// TODO Auto-generated method stub
		 Connection connection = ConnectionSingleton.getConnection();
			try(PreparedStatement ps = connection.prepareStatement("INSERT INTO schedule VALUES (?,?,?,?,?,?)")){
				ps.setInt(1, schedule.id_subject);
				ps.setInt(2, schedule.id_student);
				ps.setInt(3, schedule.id_teacher);
				ps.setInt(4, schedule.star_time);
				ps.setInt(5, schedule.end_time);
				ps.setString(6, schedule.day_week);
				 ps.execute();
				 ps.close();
			}finally {
	            ConnectionSingleton.closeConnection();
	        }
	}

	@Override
	public void read() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "SELECT * FROM schedule";
	        try (PreparedStatement ps = connection.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                int id_subject = rs.getInt("id_subject");
	                int id_student = rs.getInt("id_student");
	                int id_teacher = rs.getInt("id_teacher");
	                int star_time = rs.getInt("star_time");
	                int end_time = rs.getInt("end_time");
	                String day_week = rs.getString("day_week");
	                System.out.println("ID subject: " + id_subject + ", ID student: " + id_student +
	                                   ", ID teacher: " + id_teacher + ", Start time: " + star_time +
	                                   ", End time: " + end_time + ", Day week: " + day_week);
	            }
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

	@Override
	public void update(Schedule schedule) throws SQLException {
		// TODO Auto-generated method stub
		 Connection connection = null;
		    try {
		        connection = ConnectionSingleton.getConnection();
		        String query = "UPDATE schedule SET id_student=?, id_teacher=?, star_time=?, end_time=?, day_week=? WHERE id_subject=?";
		        try (PreparedStatement ps = connection.prepareStatement(query)) {    
		            ps.setInt(1, schedule.id_student);
		            ps.setInt(2, schedule.id_teacher);
		            ps.setInt(3, schedule.star_time);
		            ps.setInt(4, schedule.end_time);
		            ps.setString(5, schedule.day_week);
		            ps.setInt(6, schedule.id_subject); // Usar id_subject para identificar el registro a actualizar
		            ps.executeUpdate();
		        }
		    } finally {
		        if (connection != null) {
		            ConnectionSingleton.closeConnection();
		        }
		    }
	}

	@Override
	public void delete(int scheduleId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
	    try {
	        connection = ConnectionSingleton.getConnection();
	        String query = "DELETE FROM schedule WHERE id_student=?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, scheduleId);
	            ps.executeUpdate();
	        }
	    } finally {
	        if (connection != null) {
	            ConnectionSingleton.closeConnection();
	        }
	    }
	}

}

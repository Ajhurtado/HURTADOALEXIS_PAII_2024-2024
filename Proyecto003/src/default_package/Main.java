package default_package;

import java.sql.SQLException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try {
			
			//Student student = new Student(2453, "Juan", "Perez", 17);
			//Student student = new Student(17203215, "Joe", "Ruiz", 21);
			//Student student = new Student(17203215, "Maria", "Ruiz", 14);
			
			
			DAOStudent dao = new DAOStudent();
			//dao.create(student);
			
			//dao.read();
			//dao.update(student);
			//dao.read();
			//dao.delete(17203215);
			dao.read();
			
			
			//Profesor
		//	Teacher teacher = new Teacher(35687, "Robert", "Almen", 45);
			DAOTeacher daot = new DAOTeacher();
		//	daot.create(teacher);
			daot.read();
			
			//Materias
		//	Subject subject = new Subject(15, "Math", "Math", 1);
			Subject subject = new Subject(15, "English", "English", 2);
			DAOSubject daos = new DAOSubject();
		//	daos.create(subject);
			daos.read();
			daos.update(subject);
			daos.read();
			
			//Horario
		//	Schedule schedule = new Schedule(15, 2453, 35687, 8, 13, "Monday");
			Schedule schedule = new Schedule(15, 253, 456213, 15, 20, "Friday");
			DAOSchedule daosc = new DAOSchedule();
		//	daosc.create(schedule);
			//Id_student
		//	daosc.delete(2453);
			daosc.read();
			daosc.update(schedule);
			daosc.read();
		
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}

package default_package;

public class Schedule {

	int id_subject;
	int id_student;
	int id_teacher;
	int star_time;
	int end_time;
	String day_week;
	
	Schedule(){
		
	}

	public Schedule(int id_subject, int id_student, int id_teacher, int star_time, int end_time, String day_week) {
		super();
		this.id_subject = id_subject;
		this.id_student = id_student;
		this.id_teacher = id_teacher;
		this.star_time = star_time;
		this.end_time = end_time;
		this.day_week = day_week;
	}

	public int getId_subject() {
		return id_subject;
	}

	public void setId_subject(int id_subject) {
		this.id_subject = id_subject;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public int getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(int id_teacher) {
		this.id_teacher = id_teacher;
	}

	public int getStar_time() {
		return star_time;
	}

	public void setStar_time(int star_time) {
		this.star_time = star_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public String getDay_week() {
		return day_week;
	}

	public void setDay_week(String day_week) {
		this.day_week = day_week;
	}


	
}

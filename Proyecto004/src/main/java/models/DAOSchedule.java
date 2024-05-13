package models;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOSchedule extends JFrame{

	private final SessionFactory sessionFactory;
	
	
    public DAOSchedule(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
    public void createSchedule() {
        JTextField studentIdField = new JTextField();
        JTextField teacherIdField = new JTextField();
        JTextField subjectIdField = new JTextField();
        JTextField startTimeField = new JTextField();
        JTextField endTimeField = new JTextField();
        JTextField dayWeekField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("ID Estudiante:"));
        panel.add(studentIdField);
        panel.add(new JLabel("ID Profesor:"));
        panel.add(teacherIdField);
        panel.add(new JLabel("ID Materia:"));
        panel.add(subjectIdField);
        panel.add(new JLabel("Hora de inicio:"));
        panel.add(startTimeField);
        panel.add(new JLabel("Hora de fin:"));
        panel.add(endTimeField);
        panel.add(new JLabel("Día de la semana:"));
        panel.add(dayWeekField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese los datos del horario",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int studentId = Integer.parseInt(studentIdField.getText());
            int teacherId = Integer.parseInt(teacherIdField.getText());
            int subjectId = Integer.parseInt(subjectIdField.getText());
            int startTime = Integer.parseInt(startTimeField.getText());
            int endTime = Integer.parseInt(endTimeField.getText());
            String dayWeek = dayWeekField.getText();

            Session session = sessionFactory.openSession();
            Student student = session.get(Student.class, studentId);
            Teacher teacher = session.get(Teacher.class, teacherId);
            Subject subject = session.get(Subject.class, subjectId);
            Schedule schedule = new Schedule();

            schedule.setStudent(student);
            schedule.setTeacher(teacher);
            schedule.setSubject(subject);
            schedule.setTimeStart(startTime);
            schedule.setTimeEnd(endTime);
            schedule.setDayWeek(dayWeek);

            try {
                session.beginTransaction();
                session.persist(schedule);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "Horario creado exitosamente.");
            } catch (Exception ex) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al crear el horario.");
            } finally {
                session.close();
            }
        }
    }

    public void searchSchedule() {

        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID del estudiante:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Buscar Horario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String idText = idField.getText();
            int studentId = Integer.parseInt(idText);
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                Schedule schedule = session.get(Schedule.class, studentId);
                if (schedule != null) {
                    JOptionPane.showMessageDialog(this,
                            "ID Estudiante: " + schedule.getStudent().getId() + "\n" +
                                    "ID Profesor: " + schedule.getTeacher().getId() + "\n" +
                                    "ID Materia: " + schedule.getSubject().getId() + "\n" +
                                    "Hora de inicio: " + schedule.getTimeStart() + "\n" +
                                    "Hora de fin: " + schedule.getTimeEnd() + "\n" +
                                    "Día de la semana: " + schedule.getDayWeek(),
                            "Detalles del Horario", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún horario con el ID del estudiante proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                session.getTransaction().commit();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al buscar el horario.", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                session.close();
            }
        }
    }

    public void updateSchedule() {

        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID del horario:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Actualizar Horario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String idText = idField.getText();
                int scheduleId = Integer.parseInt(idText);
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Schedule schedule = session.get(Schedule.class, scheduleId);
                if (schedule != null) {
                    JTextField studentIdField = new JTextField(Integer.toString(schedule.getStudent().getId()));
                    JTextField teacherIdField = new JTextField(Integer.toString(schedule.getTeacher().getId()));
                    JTextField subjectIdField = new JTextField(Integer.toString(schedule.getSubject().getId()));
                    JTextField startTimeField = new JTextField(Integer.toString(schedule.getTimeStart()));
                    JTextField endTimeField = new JTextField(Integer.toString(schedule.getTimeEnd()));
                    JTextField dayOfWeekField = new JTextField(schedule.getDayWeek());

                    JPanel updatePanel = new JPanel(new GridLayout(6, 2));
                    updatePanel.add(new JLabel("ID Estudiante:"));
                    updatePanel.add(studentIdField);
                    updatePanel.add(new JLabel("ID Profesor:"));
                    updatePanel.add(teacherIdField);
                    updatePanel.add(new JLabel("ID Materia:"));
                    updatePanel.add(subjectIdField);
                    updatePanel.add(new JLabel("Hora de inicio:"));
                    updatePanel.add(startTimeField);
                    updatePanel.add(new JLabel("Hora de fin:"));
                    updatePanel.add(endTimeField);
                    updatePanel.add(new JLabel("Día de la semana:"));
                    updatePanel.add(dayOfWeekField);

                    int updateResult = JOptionPane.showConfirmDialog(this, updatePanel, "Actualizar Horario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (updateResult == JOptionPane.OK_OPTION) {
                        int newStudentId = Integer.parseInt(studentIdField.getText());
                        int newTeacherId = Integer.parseInt(teacherIdField.getText());
                        int newSubjectId = Integer.parseInt(subjectIdField.getText());
                        int newStartTime = Integer.parseInt(startTimeField.getText());
                        int newEndTime = Integer.parseInt(endTimeField.getText());
                        String newDayOfWeek = dayOfWeekField.getText();

                        Student student = session.get(Student.class, newStudentId);
                        Teacher teacher = session.get(Teacher.class, newTeacherId);
                        Subject subject = session.get(Subject.class, newSubjectId);

                        schedule.setStudent(student);
                        schedule.setTeacher(teacher);
                        schedule.setSubject(subject);
                        schedule.setTimeStart(newStartTime);
                        schedule.setTimeEnd(newEndTime);
                        schedule.setDayWeek(newDayOfWeek);

                        session.getTransaction().commit();
                        JOptionPane.showMessageDialog(this, "Horario actualizado exitosamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún horario con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                session.close();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos en los campos de ID, hora de inicio y hora de fin.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar el horario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

   
    public void eraseSchedule() {

        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID del horario:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Borrar Horario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
            	
                String idText = idField.getText();
                int scheduleId = Integer.parseInt(idText);
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Schedule schedule = session.get(Schedule.class, scheduleId);
                
                if (schedule != null) {
                    session.delete(schedule);
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(this, "Horario eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún horario con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        	    
    }
    
    
    
    
    
}
   

    


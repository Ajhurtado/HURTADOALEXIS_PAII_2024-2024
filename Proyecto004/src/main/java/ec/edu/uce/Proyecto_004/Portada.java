package ec.edu.uce.Proyecto_004;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.DAOStudent;

public class Portada extends JFrame implements ActionListener{
	   private JButton studentButton, teacherButton,subjectButton,scheduleButton;
	   
	public Portada() {
		studentButton = new JButton("Estudiante");
	    teacherButton = new JButton("Profesor");
	    subjectButton = new JButton("Materia");
	    scheduleButton = new JButton("Horario");
	
	    JPanel panel = new JPanel(new GridLayout(2, 1));
	    panel.add(studentButton);
	    panel.add(teacherButton);
	    panel.add(subjectButton);
	    panel.add(scheduleButton);
	
	    getContentPane().add(panel);
	    setSize(300, 200);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);

	    studentButton.addActionListener(this);
	    teacherButton.addActionListener(this);
	    subjectButton.addActionListener(this);
	    scheduleButton.addActionListener(this);
    
	  
	}
	

	 @Override
		public void actionPerformed(ActionEvent e) {	  
			// TODO Auto-generated method stub
			if (e.getSource() == studentButton) {
				  AppStudent app = new AppStudent();
				  setVisible(false);
	        } else if (e.getSource() == teacherButton) {
	        	 AppTeacher app = new AppTeacher();
	        	 setVisible(false);
	        } else if (e.getSource() == subjectButton) {
	        	 AppSubject app = new AppSubject();
	        }else if (e.getSource() == scheduleButton) {
	        	 AppSchedule app = new AppSchedule();
	        }
		} 
	
}

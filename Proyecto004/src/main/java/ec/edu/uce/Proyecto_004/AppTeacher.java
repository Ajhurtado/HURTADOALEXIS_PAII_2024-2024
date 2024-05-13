package ec.edu.uce.Proyecto_004;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.DAOTeacher;
import models.Teacher;

public class AppTeacher extends JFrame implements ActionListener{

	   private JButton createButton, readButton, updateButton, deleteButton,returnButton;
	   private SessionFactory sessionFactory;
	   
	    public AppTeacher() {
	        super("CRUD INSTITUCION");

	        Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			configuration.addAnnotatedClass(Teacher.class);
			
			// Crear la fábrica de sesiones de Hibernate
	        sessionFactory = configuration.buildSessionFactory();
	        
	        createButton = new JButton("Crear");
	        readButton = new JButton("Buscar");
	        updateButton = new JButton("Actualizar");
	        deleteButton = new JButton("Eliminar");
	        returnButton = new JButton("Regresar");

	        JPanel panel = new JPanel(new FlowLayout());
	        panel.add(createButton);
	        panel.add(readButton);
	        panel.add(updateButton);
	        panel.add(deleteButton);
	        panel.add(returnButton);

	        getContentPane().add(panel);
	        setSize(400, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setVisible(true);

	        // Agregar ActionListener
	        createButton.addActionListener(this);
	        readButton.addActionListener(this);
	        updateButton.addActionListener(this);
	        deleteButton.addActionListener(this);
	        returnButton.addActionListener(this);
}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DAOTeacher teacherCRUD = new DAOTeacher(sessionFactory);
			// TODO Auto-generated method stub
			if (e.getSource() == createButton) {
				teacherCRUD.createTeacher();;   
	        } else if (e.getSource() == readButton) {
	        	teacherCRUD.searchTeacher();
	        } else if (e.getSource() == updateButton) {
	        	teacherCRUD.updateTeacher();
	        } else if (e.getSource() == deleteButton) {
	        	teacherCRUD.eraseTeacher();         
	        } else if (e.getSource() == returnButton) {
	        	setVisible(false);
	            dispose(); // Liberar los recursos asociados con la ventana
	            Portada portada = new Portada();
	            portada.setVisible(true);
	        }
		}
}

package models;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOStudent extends JFrame{

	private final SessionFactory sessionFactory;
	
	
    public DAOStudent(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	 public void createStudent() {
	    	// Entrada de Texto
	        JTextField idField = new JTextField();
	        JTextField nameField = new JTextField();
	        JTextField lastnameField = new JTextField();
	        JTextField ageField = new JTextField();

	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID:"));
	        panel.add(idField);
	        panel.add(new JLabel("Nombre:"));
	        panel.add(nameField);
	        panel.add(new JLabel("Apellido:"));
	        panel.add(lastnameField);
	        panel.add(new JLabel("Edad:"));
	        panel.add(ageField);

	        // Mostrar un cuadro de diálogo para que el usuario ingrese los datos
	        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese los datos del estudiante",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (result == JOptionPane.OK_OPTION) {
	        	int id = Integer.parseInt(idField.getText());
	            String name = nameField.getText();
	            String lastname = lastnameField.getText();
	            int age = Integer.parseInt(ageField.getText());
	        	
			Session session = sessionFactory.openSession();
	        Student student = new Student();
	        student.setId(id);
	        student.setName(name);
	        student.setLastname(lastname);
	        student.setAge(age);  

	        try {       
	            session.beginTransaction();
	            session.persist(student);
	            session.getTransaction().commit();
	            JOptionPane.showMessageDialog(this, "Estudiante creado exitosamente.");
	        } catch (Exception ex) {
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback(); // Hacer rollback si hay un error
	            }
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al crear el estudiante.");
	        } finally {
	            session.close();
	        }
	    }
	  }
		
	  
	    public void searchStudent() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID estudiante:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Buscar Estudiante", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int studentId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Student student = session.get(Student.class, studentId);
	                // Verificar si se encontró el estudiante
	                if (student != null) {
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this, 
	                            "ID: " + student.getId() + "\n" +
	                            "Nombre: " + student.getName() + "\n" +
	                            "Apellido: " + student.getLastname() + "\n" +
	                            "Edad: " + student.getAge(),
	                            "Detalles del Estudiante", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún estudiante con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.getTransaction().commit();
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (Exception ex) {
	                if (session.getTransaction() != null) {
	                    session.getTransaction().rollback();
	                }
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al buscar el estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
	            } finally {
	                session.close();
	            }
	        }
	        	      
	    }
	    
	    
	    public void updateStudent() {
	    	
	    	JTextField idField = new JTextField();
	        JPanel panel = new JPanel(new GridLayout(2, 2));
	        panel.add(new JLabel("ID estudiante:"));
	        panel.add(idField);
	        
	        int result = JOptionPane.showConfirmDialog(this, panel, "Actualizar Estudiante", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	            try {
	                // Obtener el ID ingresado por el usuario
	                String idText = idField.getText();
	                int studentId = Integer.parseInt(idText);
	                Session session = sessionFactory.openSession();
	                session.beginTransaction();
	                Student student = session.get(Student.class, studentId);
	                if (student != null) {
	                    JTextField nameField = new JTextField(student.getName());
	                    JTextField lastnameField = new JTextField(student.getLastname());
	                    JTextField ageField = new JTextField(Integer.toString(student.getAge()));
	                    
	                    JPanel updatePanel = new JPanel(new GridLayout(4, 2));
	                    updatePanel.add(new JLabel("Nombre:"));
	                    updatePanel.add(nameField);
	                    updatePanel.add(new JLabel("Apellido:"));
	                    updatePanel.add(lastnameField);
	                    updatePanel.add(new JLabel("Edad:"));
	                    updatePanel.add(ageField);
	                    int updateResult = JOptionPane.showConfirmDialog(this, updatePanel, "Actualizar Datos del Estudiante", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	                    if (updateResult == JOptionPane.OK_OPTION) {
	                        String name = nameField.getText();
	                        String lastname = lastnameField.getText();
	                        int age = Integer.parseInt(ageField.getText());                        
	                        // Actualizar los atributos del estudiante
	                        student.setName(name);
	                        student.setLastname(lastname);
	                        student.setAge(age);
	                        session.getTransaction().commit();
	                        
	                        JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente.");
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún estudiante con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    
	    public void eraseStudent() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID estudiante:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Borrar Estudiante", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int studentId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Student student = session.get(Student.class, studentId);
	                // Verificar si se encontró el estudiante
	                if (student != null) {
	                	session.delete(student);
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this,
	                            "Estudiante borrado con EXITO");
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún estudiante con el ID proporcionado, escriba correctamente el ID o el estudiante ya fue borrado", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.getTransaction().commit();
	            }catch (Exception ex) {
	                if (session.getTransaction() != null) {
	                    session.getTransaction().rollback();
	                }
	                session.close();
	            }
	        }
	        	    
	    }
	    
}

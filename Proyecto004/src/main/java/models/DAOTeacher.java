package models;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOTeacher extends JFrame{
	
private final SessionFactory sessionFactory;
	
	
    public DAOTeacher(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	 public void createTeacher() {
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
	        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese los datos del profesor",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (result == JOptionPane.OK_OPTION) {
	        	int id = Integer.parseInt(idField.getText());
	            String name = nameField.getText();
	            String lastname = lastnameField.getText();
	            int age = Integer.parseInt(ageField.getText());
	        	
			Session session = sessionFactory.openSession();
	        Teacher teacher = new Teacher();
	        teacher.setId(id);
	        teacher.setName(name);
	        teacher.setLastName(lastname);
	        teacher.setAge(age);  

	        try {       
	            session.beginTransaction();
	            session.persist(teacher);
	            session.getTransaction().commit();
	            JOptionPane.showMessageDialog(this, "Profesor creado exitosamente.");
	        } catch (Exception ex) {
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback(); // Hacer rollback si hay un error
	            }
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al crear el profesor.");
	        } finally {
	            session.close();
	        }
	    }
	  }
		
	  
	    public void searchTeacher() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID profesor:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Buscar Profesor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int teacherId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Teacher teacher = session.get(Teacher.class, teacherId);
	                // Verificar si se encontró el estudiante
	                if (teacher != null) {
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this, 
	                            "ID: " + teacher.getId() + "\n" +
	                            "Nombre: " + teacher.getName() + "\n" +
	                            "Apellido: " + teacher.getLastName() + "\n" +
	                            "Edad: " + teacher.getAge(),
	                            "Detalles del Profesor", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún profesor con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.getTransaction().commit();
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (Exception ex) {
	                if (session.getTransaction() != null) {
	                    session.getTransaction().rollback();
	                }
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al buscar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
	            } finally {
	                session.close();
	            }
	        }
	        	      
	    }
	    
	    
	    public void updateTeacher() {
	    	
	    	JTextField idField = new JTextField();
	        JPanel panel = new JPanel(new GridLayout(2, 2));
	        panel.add(new JLabel("ID profesor:"));
	        panel.add(idField);
	        
	        int result = JOptionPane.showConfirmDialog(this, panel, "Actualizar Profesor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	            try {
	                // Obtener el ID ingresado por el usuario
	                String idText = idField.getText();
	                int teacherId = Integer.parseInt(idText);
	                Session session = sessionFactory.openSession();
	                session.beginTransaction();
	                Teacher teacher = session.get(Teacher.class, teacherId);
	                if (teacher != null) {
	                    JTextField nameField = new JTextField(teacher.getName());
	                    JTextField lastnameField = new JTextField(teacher.getLastName());
	                    JTextField ageField = new JTextField(Integer.toString(teacher.getAge()));
	                    
	                    JPanel updatePanel = new JPanel(new GridLayout(4, 2));
	                    updatePanel.add(new JLabel("Nombre:"));
	                    updatePanel.add(nameField);
	                    updatePanel.add(new JLabel("Apellido:"));
	                    updatePanel.add(lastnameField);
	                    updatePanel.add(new JLabel("Edad:"));
	                    updatePanel.add(ageField);
	                    int updateResult = JOptionPane.showConfirmDialog(this, updatePanel, "Actualizar Datos del Profesor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	                    if (updateResult == JOptionPane.OK_OPTION) {
	                        String name = nameField.getText();
	                        String lastname = lastnameField.getText();
	                        int age = Integer.parseInt(ageField.getText());                        
	                        // Actualizar los atributos del estudiante
	                        teacher.setName(name);
	                        teacher.setLastName(lastname);
	                        teacher.setAge(age);
	                        session.getTransaction().commit();
	                        
	                        JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún profesor con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al actualizar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    
	    public void eraseTeacher() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID profesor:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Borrar Profesor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int teacherId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Teacher teacher = session.get(Teacher.class, teacherId);
	                // Verificar si se encontró el estudiante
	                if (teacher != null) {
	                	session.delete(teacher);
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this,
	                            "Profesor borrado con EXITO");
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ningún profesor con el ID proporcionado, escriba correctamente el ID o el profesor ya fue borrado", "Error", JOptionPane.ERROR_MESSAGE);
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

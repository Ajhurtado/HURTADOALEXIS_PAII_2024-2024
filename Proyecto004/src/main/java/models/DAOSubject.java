package models;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOSubject extends JFrame{

	private final SessionFactory sessionFactory;
	
	
    public DAOSubject(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	 public void createSubject() {
	    	// Entrada de Texto
	        JTextField idField = new JTextField();
	        JTextField nameField = new JTextField();
	        JTextField descriptionField = new JTextField();
	        JTextField levelField = new JTextField();

	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID:"));
	        panel.add(idField);
	        panel.add(new JLabel("Nombre:"));
	        panel.add(nameField);
	        panel.add(new JLabel("Descripcion:"));
	        panel.add(descriptionField);
	        panel.add(new JLabel("Nivel:"));
	        panel.add(levelField);

	        // Mostrar un cuadro de diálogo para que el usuario ingrese los datos
	        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese los datos de la materia",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (result == JOptionPane.OK_OPTION) {
	        	int id = Integer.parseInt(idField.getText());
	            String name = nameField.getText();
	            String description = descriptionField.getText();
	            int level = Integer.parseInt(levelField.getText());
	        	
			Session session = sessionFactory.openSession();
	        Subject subject = new Subject();
	        subject.setId(id);
	        subject.setName(name);
	        subject.setDescription(description);
	        subject.setLevel(level);  

	        try {       
	            session.beginTransaction();
	            session.persist(subject);
	            session.getTransaction().commit();
	            JOptionPane.showMessageDialog(this, "Materia creada exitosamente.");
	        } catch (Exception ex) {
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback(); // Hacer rollback si hay un error
	            }
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al crear la materia.");
	        } finally {
	            session.close();
	        }
	    }
	  }
		
	  
	    public void searchSubject() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID materia:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Buscar Materia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int subjectId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Subject subject = session.get(Subject.class, subjectId);
	                // Verificar si se encontró el estudiante
	                if (subject != null) {
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this, 
	                            "ID: " + subject.getId() + "\n" +
	                            "Nombre: " + subject.getName() + "\n" +
	                            "Descripcion: " + subject.getDescription() + "\n" +
	                            "Nivel: " + subject.getLevel(),
	                            "Detalles de la Materia", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ninguna Materia con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.getTransaction().commit();
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (Exception ex) {
	                if (session.getTransaction() != null) {
	                    session.getTransaction().rollback();
	                }
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al buscar el materia.", "Error", JOptionPane.ERROR_MESSAGE);
	            } finally {
	                session.close();
	            }
	        }
	        	      
	    }
	    
	    
	    public void updateSubject() {
	    	
	    	JTextField idField = new JTextField();
	        JPanel panel = new JPanel(new GridLayout(2, 2));
	        panel.add(new JLabel("ID materia:"));
	        panel.add(idField);
	        
	        int result = JOptionPane.showConfirmDialog(this, panel, "Actualizar Materia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	            try {
	                // Obtener el ID ingresado por el usuario
	                String idText = idField.getText();
	                int subjectId = Integer.parseInt(idText);
	                Session session = sessionFactory.openSession();
	                session.beginTransaction();
	                Subject subject = session.get(Subject.class, subjectId);
	                if (subject != null) {
	                    JTextField nameField = new JTextField(subject.getName());
	                    JTextField descriptionField = new JTextField(subject.getDescription());
	                    JTextField levelField = new JTextField(Integer.toString(subject.getLevel()));
	                    
	                    JPanel updatePanel = new JPanel(new GridLayout(4, 2));
	                    updatePanel.add(new JLabel("Nombre:"));
	                    updatePanel.add(nameField);
	                    updatePanel.add(new JLabel("Descripcion:"));
	                    updatePanel.add(descriptionField);
	                    updatePanel.add(new JLabel("Nivel:"));
	                    updatePanel.add(levelField);
	                    int updateResult = JOptionPane.showConfirmDialog(this, updatePanel, "Actualizar Datos de la Materia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	                    if (updateResult == JOptionPane.OK_OPTION) {
	                        String name = nameField.getText();
	                        String description = descriptionField.getText();
	                        int level = Integer.parseInt(levelField.getText());                        
	                        // Actualizar los atributos del estudiante
	                        subject.setName(name);
	                        subject.setDescription(description);
	                        subject.setLevel(level);
	                        session.getTransaction().commit();
	                        
	                        JOptionPane.showMessageDialog(this, "Materia actualizada exitosamente.");
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ninguna materia con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                session.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al actualizar la materia.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    
	    public void eraseSubject() {
	    	
	    	JTextField idField = new JTextField();		 
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        panel.add(new JLabel("ID materia:" ));
	        panel.add(idField);
	        //Mostrar opcion para ingresar id
	        int result = JOptionPane.showConfirmDialog(this, panel, "Borrar Materia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);              
	        if (result == JOptionPane.OK_OPTION) {
	            String idText = idField.getText();
	            int subjectId = Integer.parseInt(idText);
	            Session session = sessionFactory.openSession();
	            try {
	                session.beginTransaction();
	                // Realizar la búsqueda
	                Subject subject = session.get(Subject.class, subjectId);
	                // Verificar si se encontró el estudiante
	                if (subject != null) {
	                	session.delete(subject);
	                    // Mostrar los detalles del estudiante
	                    JOptionPane.showMessageDialog(this,
	                            "Materia borrada con EXITO");
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontró ninguna materia con el ID proporcionado, escriba correctamente el ID o la materia ya fue borrada", "Error", JOptionPane.ERROR_MESSAGE);
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

package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	/**
	 * Autor: Alexis Hurtado
	 * Titulo: Contenedores Java
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Layout ventana = new Layout();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	       

	}

static class Layout extends JFrame{

	public Layout() {
		setTitle("Dibujar figuras");
		setBounds(600,350,600,300);
		Panel lamina = new Panel();
		lamina.setBackground(Color.cyan);
		add(lamina);
		
	}
	
}


static class Panel extends JPanel implements ActionListener {

    private String figuraSeleccionada;

    public Panel() {
        add(crearBoton("Cuadrado"));
        add(crearBoton("Triangulo"));
        add(crearBoton("Circulo"));
    }

    private JButton crearBoton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        return button;
    }
    
    //Cuando damos click en el boton accionamos el evento
    @Override
    public void actionPerformed(ActionEvent e) {
        figuraSeleccionada = ((JButton) e.getSource()).getText();
        repaint(); // Vuelve a dibujar la pantalla
    }

    //drawRect,Polygon,Oval metodos de la clase Graphics, fill para rellenar
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (figuraSeleccionada != null) {
            g.setColor(Color.BLACK);
            switch (figuraSeleccionada) {
	            case "Cuadrado":
	                g.setColor(Color.BLUE);
	                int squareSize = 150;
	                int x = (getWidth() - squareSize) / 2; // Centrar h
	                int y = (getHeight() - squareSize) / 2; // Centrar v
	                g.fillRect(x, y, squareSize, squareSize);
	                break;
	            case "Triangulo":
	                g.setColor(Color.GREEN);
	                int[] xPoints = {(getWidth() - 150) / 2, getWidth() / 2, (getWidth() + 150) / 2};
	                int[] yPoints = {(getHeight() / 2) + 50, (getHeight() / 2) - 50, (getHeight() / 2) + 50};
	                g.fillPolygon(xPoints, yPoints, 3);
	                break;
	            case "Circulo":
	                g.setColor(Color.RED);
	                int ovalSize = 150;
	                int xOval = (getWidth() - ovalSize) / 2; // Centrar h
	                int yOval = (getHeight() - ovalSize) / 2; // Centrar v
	                g.fillOval(xOval, yOval, ovalSize, ovalSize);
	                break;
            }
        }
    }
}

	
}

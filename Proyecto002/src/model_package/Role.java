package model_package;


public class Role {
//Clase padre role
	    private int[] cord_x;
	    private int[] cord_y;

	    public Role(int[] cord_x, int[] cord_y) {
			// TODO Auto-generated constructor stub
	    	this.cord_x = cord_x;
	        this.cord_y = cord_y;
		}

		// Getters y Setters
	    public int[] getCordX() {
	        return cord_x;
	    }

	    public void setCordX(int[] cord_x) {
	        this.cord_x = cord_x;
	    }

	    public int[] getCordY() {
	        return cord_y;
	    }

	    public void setCordY(int[] cord_y) {
	        this.cord_y = cord_y;
	    }
	    
}

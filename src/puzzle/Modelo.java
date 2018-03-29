
package puzzle;



public class Modelo {
    
    private final int dimension;
    private Tablero tableroGanador;
    private Tablero tableroActual;

    public Modelo(int dimension, String pathImg) {
       
    	this.dimension = dimension; // Es una matriz de 3x3
        this.tableroGanador = new Tablero(this.dimension, pathImg); // Piezas en su lugar
        this.tableroActual = new Tablero(this.dimension, pathImg); // Sujeto a movimientos
    }
    
    public Tablero getTableroActual() {
        return this.tableroActual;
    }
    
    
    public int getDimension() {
		return dimension;
	}


	public boolean esGanador(){
        return this.tableroActual.toString().equals(this.tableroGanador.toString());
    }
    
}

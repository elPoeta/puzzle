
package puzzle;

import java.util.ArrayList;

public class Tablero {
    private final Pieza[][] piezas; // Matriz de piezas
    private int vaciaX; // Coordenada X de pieza vacia
    private int vaciaY; // Coordenada Y de pieza vacia

    public Tablero(int dim, String ruta) { // Dimension (por defecto 3)
    
    	this.piezas = new Pieza[dim][dim]; // Nueva matriz (por defecto, 3x3)
        crearPiezas(ruta); 
        establecerCoordenadasPiezaVacia( dim-1 , dim-1 ); // Posicion inicial de la pieza vacia (por defecto, 2;2)
    }
    
    private ArrayList<String> rutaImg(String path){
    	ArchivadorTexto ar = new ArchivadorTexto(path);
    	return ar.leerLineas();
    }

    private void crearPiezas(String ruta) {
        int contador = 0;
       
        for (int i = 0; i < this.piezas.length; i++) {
            for (int j = 0; j < this.piezas[0].length; j++) {
                this.piezas[i][j] = new Pieza(contador,rutaImg(ruta).get(contador) );
                contador++;
            }
        }
    }
    
    public Pieza devolverPieza(int i, int j) { // Mediante coordenadas en matriz
        return this.piezas[i][j];
    }
    
    public Pieza devolverPieza(int n) { // Mediante numero de pieza
        int contador = 0;
        for (int i = 0; i < this.piezas.length; i++) {
            for (int j = 0; j < this.piezas[0].length; j++) {
                if (contador == n) {
                    return this.piezas[i][j];
                }
                else {
                    contador++;
                }
            }
        }
        return null;
    }
  
    public void intercambiar (int x1, int y1, int x2, int y2) {
        // IMPLEMENTAR
        
        Pieza piezaAux = this.piezas[x2][y2];
        this.piezas[x2][y2]=this.piezas[x1][y1];
        this.piezas[x1][y1] = piezaAux;
        establecerCoordenadasPiezaVacia(x2, y2);
    }
    
    public void establecerCoordenadasPiezaVacia (int x, int y) {
        this.vaciaX = x;
        this.vaciaY = y;
    }

    public int getVaciaX() {
        return vaciaX;
    }

    public int getVaciaY() {
        return vaciaY;
    }

    @Override
    public String toString() {
        String representacion = "";
        for (int i = 0; i < this.piezas.length; i++) {
            for (int j = 0; j < this.piezas[0].length; j++) {
                representacion += this.piezas[i][j] + " ";
            }
            representacion += "\n";
        }
        return representacion;
    }
}

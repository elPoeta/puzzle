
package puzzle;

public class Pieza {
    private  final int numeroDePieza;
    private  final String nombreDeLaImagen;
  
    
    
    public Pieza(int n, String nombreDeLaImagen) {
        this.numeroDePieza = n;
        this.nombreDeLaImagen = nombreDeLaImagen;
    }

    public int getNumeroDePieza() {
        return numeroDePieza;
    }

    public String getNombreDeLaImagen() {
        return nombreDeLaImagen;
    }

    @Override
    public String toString() {
        return "P" + this.numeroDePieza;
    }
    
    
}

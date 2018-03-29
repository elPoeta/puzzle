

package puzzle;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;



public class Controlador 
{
    private Modelo m;
    private Vista v;
    private VistaSeleccion vs;
    
    public void iniciar() {
    	this.vs = new VistaSeleccion();
    	this.vs.jbuttonJugarListener(new JbuttonJugarHandler());
    	this.vs.jRadioBotonListener(new JRadioBotonHandler());
    	this.vs.iniciarVistaSeleccion();
    }
    
    
    public void iniciarJuego(int dim, String ruta)
    {
        this.m = new Modelo(dim,ruta); // Nuevo Modelo
        this.v = new Vista( this.m ); // Nueva Vista con una referencia al Modelo
        this.v.deshabilitarMenuItem(false);
        this.v.tecladoListener( new TecladoHandler() ); // Manejar el evento de teclado
        this.v.jMenuItemListener(new JMenuItemHandler());
        this.v.iniciarVista(); // Le ordeno a la vista que se muestre
        this.mezclar(m.getDimension() * 30);
    }
    
    private void desplazarPiezaVacia(int sentidoX, int sentidoY) {
        int actualX = this.m.getTableroActual().getVaciaX(); // Coordenada X actual de pieza vacia
        int actualY = this.m.getTableroActual().getVaciaY(); // Coordenada Y actual de pieza vacia
        int nuevaX = actualX + sentidoX; // Nueva coordenada X de pieza vacia
        int nuevaY = actualY + sentidoY; // Nueva coordenada Y de pieza vacia
        
        if ( esCoordenadaValida(nuevaX, nuevaY) ) { // Si no se desborda
            // INTERCAMBIO LOGICO
            // IMPLEMENTAR
            this.m.getTableroActual().intercambiar(actualX, actualY, nuevaX, nuevaY);
            // INTERCAMBIO VISUAL
            // IMPLEMENTAR
            this.v.intercambiar(this.m.getTableroActual().devolverPieza(nuevaX, nuevaY).getNumeroDePieza(), this.m.getTableroActual().devolverPieza(actualX, actualY).getNumeroDePieza());
        }
    }
    
    private void moverSegunTecla (int codigoDeTecla) {
        /*
            Lo que realmente se mueve es la pieza vacia.
            Si se desea que los cursores muevan la pieza vacia, next = 1 y back = -1
            Si se desea que los cursores muevan las piezas visibles, next = -1 y back = 1
        */
        int next = -1; // Hacia adelante
        int back = 1; // Hacia atras
        // IMPLEMENTAR
        
        switch(codigoDeTecla){
            case 37 :  
                       desplazarPiezaVacia(0, back);
                        break;
            case 38:
                       desplazarPiezaVacia(back, 0);
                        break;
            case 39:
                    
                       desplazarPiezaVacia(0, next);
                     
                      break;
            case 40:
                        desplazarPiezaVacia(next, 0);
                        break;            
          }
           
        
    }
    
    private boolean esCoordenadaValida (int x, int y) {
        // IMPLEMENTAR
        return( x >= 0 && x < this.m.getDimension() ) && ( y >= 0 && y < this.m.getDimension() ); 
    }
  
    private void mezclar(int n){
        for (int i = 0; i < n; i++) {
            moverSegunTecla((int)(Math.random() * 4 + 37));
        }
    }
   
    private void chequearSiGano(){
 	   if(this.m.esGanador()) 
     	   this.v.cartelGano();
    }
    
    private void seleccionTablero(int indice) {
	    vs.cambioImagen(getClass().getResource("/recursos/radio"+(indice + 3)+"x"+(indice + 3)+".png"));
		vs.setRuta(getClass().getResource("/recursos/lista"+(indice + 3)+"x"+(indice + 3)+".txt").getFile());
		vs.setDimension(+(indice + 3));
   }
   
   private void cambiarTablero(int indice) {
	   switch(indice) {
		case 0:
			mezclar(m.getDimension() * 30);
			break;
		default:
			v.cerrarVista();
	    	iniciarJuego(indice + 2, getClass().getResource("/recursos/lista"+(indice + 2)+"x"+(indice + 2)+".txt").getFile());
			
		}
   }
    private class TecladoHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            /*Los cursores estan mapeados de esta manera:
                Izquierda (37)
                Arriba (38)
                Derecha (39)
                Abajo (40)
            */
            // IMPLEMENTAR
            moverSegunTecla(e.getKeyCode());
            ReproductorDeAudio.reproducir("src/recursos/guitarra-beep.wav");
            chequearSiGano();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
  
    private class JbuttonJugarHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			iniciarJuego(vs.getDimension(), vs.getRuta());
			vs.cerrarVista();;
		}
    	
    }
    
    private class JRadioBotonHandler implements ActionListener{
    

		@Override
		public void actionPerformed(ActionEvent e) {
			seleccionTablero(Integer.parseInt(e.getActionCommand()));
			
			
		}
    }
 
   private class JMenuItemHandler implements ActionListener{
	
	   @Override
		public void actionPerformed(ActionEvent e) {
				cambiarTablero(Integer.parseInt(e.getActionCommand()));
				
		}
    	
    }
   
  

}

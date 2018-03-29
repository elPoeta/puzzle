
package puzzle;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


public class Vista extends javax.swing.JFrame 
{
	private ArrayList<JLabel> jLabelArray = new ArrayList<>();
    private javax.swing.JPanel jPanelTablero;
    private JPopupMenu jPopUpMenuEmergente;
    private ArrayList<JMenuItem> jMenuItemArray = new ArrayList<>();
    private Modelo m;
    
    public Vista() {
        initComponents();
    }
    
    public Vista(Modelo m) {
        this.m = m;
        initComponents();
    }
    
    public void iniciarVista()
    {
        this.setLocationRelativeTo(null); // Aparecerá en el centro de la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // La aplicación por defecto se cierra al tocar la cruz
        this.establecerIcono(); // OPCIONAL. Personaliza el ícono de la ventana
        this.colocarTablero();
        this.hacerInvisible( (this.m.getDimension() * this.m.getDimension()) - 1 );
        this.setVisible(true); // Hace la ventana visible
    }
    
    private void establecerIcono() {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/iconpuzzle.png"));
        setIconImage(icon);
    }
    
    public void tecladoListener (KeyListener kl) {
        this.jPanelTablero.requestFocus();
        this.jPanelTablero.addKeyListener(kl);
    }
    
  

public void cerrarVista() {
	   this.dispose();
   }
    private void colocarTablero() {
        for (int i = 0; i < this.m.getDimension(); i++) {
            for (int j = 0; j < this.m.getDimension(); j++) {
                Pieza p = this.m.getTableroActual().devolverPieza(i, j);
                JLabel contenedor = (JLabel) this.jPanelTablero.getComponent( p.getNumeroDePieza() );
                contenedor.setIcon(new ImageIcon(getClass().getResource("/recursos/" + p.getNombreDeLaImagen())));
                contenedor.setText("");
            }
        }
    }
    
    private void hacerInvisible (int numeroDePieza) {
        JLabel contenedor = (JLabel) this.jPanelTablero.getComponent( numeroDePieza );
        contenedor.setVisible(false);
    }
    
    public void intercambiar (int piezaA, int piezaB) {
        Component[] listaDeComponentes = this.jPanelTablero.getComponents();
        int indicePiezaA = buscarPieza(piezaA);
        int indicePiezaB = buscarPieza(piezaB);
        this.jPanelTablero.removeAll();
        Component temp = listaDeComponentes[indicePiezaA];
        listaDeComponentes[indicePiezaA] = listaDeComponentes[indicePiezaB];
        listaDeComponentes[indicePiezaB] = temp;
        for (int i = 0; i < listaDeComponentes.length; i++) {
            this.jPanelTablero.add(listaDeComponentes[i]);
        }             
        this.jPanelTablero.validate();
    }
    
    private int buscarPieza (int numero) {
        Component[] listaDeComponentes = this.jPanelTablero.getComponents();
        for (int i = 0; i < listaDeComponentes.length; i++) {
            if ( listaDeComponentes[i].getName().equals("pieza"+numero) ) {
                return i;
            }
        }
        return 0;
    }
    
    
    public void cartelGano(){
       
            JOptionPane.showMessageDialog(null, "Ganaste!!!", "Ganador", 1);
    }
    


    private void initComponents() {

        jPanelTablero = new javax.swing.JPanel();
        setTitle("Slide Puzzle");
        setBackground(new java.awt.Color(253, 238, 197));
        setResizable(false);
        setSize(new java.awt.Dimension(690, 690));
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        
        jPanelTablero.setBackground(new java.awt.Color(253, 238, 197));
        jPanelTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 98, 37), 5));
        jPanelTablero.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanelTablero.setLayout(new java.awt.GridLayout(this.m.getDimension(), this.m.getDimension()));
      
         for (int i = 0; i < this.m.getDimension() * this.m.getDimension(); i++) {
        	  jLabelArray.add(new JLabel("pieza"+i,javax.swing.SwingConstants.CENTER));
        	  jLabelArray.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 164, 39), 2));
        	  jLabelArray.get(i).setName("pieza"+i);
              jPanelTablero.add(jLabelArray.get(i));
		}
        
        jPopUpMenuEmergente = new JPopupMenu();
        
       for(int i = 0; i<cargarItem().size(); i++) {
        	String [] splitTxt = cargarItem().get(i).split(" "); 
        	jMenuItemArray.add(new JMenuItem(splitTxt[0],new ImageIcon(getClass().getResource("/recursos/" + splitTxt[1]))));
        	jMenuItemArray.get(i).setActionCommand(String.valueOf(i));
        	jPopUpMenuEmergente.add(jMenuItemArray.get(i));
        	if(i==0)
        		 jPopUpMenuEmergente.addSeparator();
        }
        this.jPanelTablero.setComponentPopupMenu(jPopUpMenuEmergente);

        getContentPane().add(jPanelTablero);

        pack();
    }
	public ArrayList<String> cargarItem() {
		ArchivadorTexto ar = new ArchivadorTexto(getClass().getResource("/recursos/menuItem.txt").getFile());
		return  ar.leerLineas();
	}
    public void jMenuItemListener(ActionListener al) {
    	for(int i=0; i<jMenuItemArray.size(); i++)
    	this.jMenuItemArray.get(i).addActionListener(al);
    }
    
    public void deshabilitarMenuItem(boolean esHabilitado) {
    	
    	this.jMenuItemArray.get(m.getDimension()-2).setEnabled(esHabilitado);
        } 
   
}

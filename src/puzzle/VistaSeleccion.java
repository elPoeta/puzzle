package puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class VistaSeleccion extends JFrame {
   private  JButton jButtonJugar;
   private ButtonGroup grupo;

   private JLabel labelImg;
   private int dimension;
   private String ruta;
   private ArrayList<JRadioButton> jRadioBotonArray = new ArrayList<>();
  
	public VistaSeleccion() {
		iniciarComponentes();
	}
	
	 public void iniciarVistaSeleccion()
	    {   
		    this.setTitle("Slide Puzzle");
		    this.setResizable(false);
	        this.setSize(new java.awt.Dimension(490, 390));
	        this.setLocationRelativeTo(null); // Aparecerá en el centro de la pantalla
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // La aplicación por defecto se cierra al tocar la cruz
	        this.establecerIcono(); // OPCIONAL. Personaliza el ícono de la ventana
	        this.dimension = 3;
	        this.ruta = getClass().getResource("/recursos/lista3x3.txt").getFile();
	        this.setVisible(true); // Hace la ventana visible
	        
	    }
	 
	   private void establecerIcono() {
	        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/iconpuzzle.png"));
	        setIconImage(icon);
	    }
	  
	   public void jbuttonJugarListener(ActionListener al) {
		   this.jButtonJugar.addActionListener(al);
	   }
	   public void jRadioBotonListener(ActionListener al) {
		   for(int i=0; i<jRadioBotonArray.size(); i++) {
			   this.jRadioBotonArray.get(i).addActionListener(al);
		   }
	   }

	   public void cambioImagen(URL icono) {
		   this.labelImg.setIcon(new ImageIcon(icono));
	   }
	   
	   
	   public void setDimension(int dim) {
		   this.dimension = dim;
	   }
	 
	   public void setRuta(String path) {
		   this.ruta = path;
	   }
	 
	   public int getDimension() {
		   return this.dimension;
	   }
	 
	   public String getRuta() {
		   return this.ruta;
	   }
	  public void cerrarVista() {
		  this.dispose();
	  }
	   private void iniciarComponentes() {
		    
		    JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 139));
			panel.setBounds(0, 0, 484, 361);
			this.getContentPane().add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(238, 232, 170));
			panel_1.setBounds(10, 11, 464, 339);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JLabel lblNewLabel = new JLabel("Slide Puzzle");
			lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 35));
			lblNewLabel.setForeground(new Color(72, 61, 139));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel_1.add(lblNewLabel, BorderLayout.NORTH);
			
			JLayeredPane layeredPane = new JLayeredPane();
			panel_1.add(layeredPane, BorderLayout.CENTER);
			layeredPane.setLayout(new BorderLayout(0, 0));
			
			JLayeredPane layeredPane_1 = new JLayeredPane();
			layeredPane.add(layeredPane_1, BorderLayout.NORTH);
			layeredPane_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
		    grupo = new ButtonGroup();
		    for(int i = 0; i<rutaRadio().size(); i++) {
		       	String [] splitTxt =rutaRadio().get(i).split(" "); 
		       	jRadioBotonArray.add(new JRadioButton(splitTxt[0],Boolean.parseBoolean(splitTxt[1])));
		       	jRadioBotonArray.get(i).setFont(new Font("Arial Black", Font.BOLD, 15));
		       	jRadioBotonArray.get(i).setBackground(new Color(238, 232, 170));
		       	jRadioBotonArray.get(i).setActionCommand(String.valueOf(i));
				grupo.add(jRadioBotonArray.get(i));
				layeredPane_1.add(jRadioBotonArray.get(i));
		       	
		       }
		
			
			labelImg = new JLabel(new ImageIcon(getClass().getResource("/recursos/radio3x3.png")));
			labelImg.setBorder(null);
			labelImg.setHorizontalAlignment(SwingConstants.CENTER);
			layeredPane.add(labelImg, BorderLayout.CENTER);
			
			jButtonJugar = new JButton("Jugar");
			jButtonJugar.setForeground(new Color(25, 25, 112));
			jButtonJugar.setFont(new Font("Monospaced", Font.BOLD, 25));
			jButtonJugar.setBackground(new Color(176, 196, 222));
			layeredPane.add(jButtonJugar, BorderLayout.SOUTH);
		  
	   }
	   
	   
	    private ArrayList<String> rutaRadio(){
	    	ArchivadorTexto ar = new ArchivadorTexto(getClass().getResource("/recursos/radioboton.txt").getFile());
	    	return ar.leerLineas();
	    }
	 
	    
}

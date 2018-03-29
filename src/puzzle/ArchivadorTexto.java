
package puzzle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivadorTexto 
{
    private File archivo;
    private FileReader archivoLectura;
    private FileWriter archivoEscritura;
    private BufferedReader bufferDeEntrada;
    private BufferedWriter bufferDeSalida;
    private boolean modoLectura;
    
    public ArchivadorTexto(String nombre)
    {
        this.modoLectura = true;
        crearArchivo(nombre);
    }
    
    private void crearArchivo(String nomArch)
    {
        try
        {
            this.archivo = new File(nomArch);
        } 
        catch(Exception e)
        {
            System.out.println("No se pudo crear el archivo");
        }
    }
    
    private void cerrarArchivo()
    {
        try 
        {
            if (modoLectura) archivoLectura.close();
            else archivoEscritura.close();
        }
        catch (IOException ex) 
        {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    private void prepararLectura()
    {
        try
        {
            this.modoLectura = true;
            this.archivoLectura = new FileReader(this.archivo);
            this.bufferDeEntrada = new BufferedReader(this.archivoLectura);
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    private void prepararEscritura()
    {
        try
        {
            this.modoLectura = false;
            this.archivoEscritura = new FileWriter(this.archivo, true);
            this.bufferDeSalida = new BufferedWriter(this.archivoEscritura);
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    /**
     * Obtiene la siguiente línea de un archivo de texto. 
     *
     * @return Cadena en formato String. Si no hay más lineas o falla, devuelve una cadena vacía.
     */
    public String leerLinea ()
    {
        try
        {
            String linea;
            prepararLectura();
            if ( (linea = bufferDeEntrada.readLine() ) != null ) return linea;
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
        finally
        {
            cerrarArchivo();
        }
        return "";
    }
    
    /**
     * Obtiene todas las líneas de un archivo de texto. 
     *
     * @return Una colección (ArrayList) de cadenas en formato String. Si no hay lineas o falla, devuelve null.
     */
    public ArrayList<String> leerLineas ()
    {
        try 
        {
            prepararLectura();
            String linea;
            ArrayList<String> lineas = new ArrayList<String>();
            while ( (linea = bufferDeEntrada.readLine() ) != null )
            {
                lineas.add( linea );
            }
            return lineas;
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
        finally
        {
            cerrarArchivo();
        }
        return null;
    }

    /**
     * Escribe una línea en un archivo de texto, en el renglón siguiente tras la última línea del archivo. 
     *
     * @return Una colección (ArrayList) de cadenas en formato String. Si no hay lineas o falla, devuelve null.
     */
    public void escribirLinea (String s)
    {
        try
        {
            prepararEscritura();
            this.bufferDeSalida.write(s + "\n");
            this.bufferDeSalida.flush();
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
        finally
        {
            cerrarArchivo();
        }
    }
    
    /**
     * Escribe líneas en un archivo de texto. 
     *
     * @param Una colección (ArrayList) de cadenas en formato String.
     */
    public void escribirLineas (ArrayList<String> lista)
    {
        try
        {
            prepararEscritura();
            for (int i = 0; i < lista.size(); i++)
            {
                this.bufferDeSalida.write(lista.get(i) + "\n");
            }
            this.bufferDeSalida.flush();
        }
        catch (IOException ex)
        {
            System.out.println("Error de entrada/salida.");
        }
        finally
        {
            cerrarArchivo();
        }
    }
    
}

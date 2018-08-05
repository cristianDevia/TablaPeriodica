package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tablaPeriodica.mundo.ElementoExisteException;
import tablaPeriodica.mundo.PersistenciaException;
import tablaPeriodica.mundo.TablaP;


public class InterfazTablaPeriodica extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private TablaP tabla;
    
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los botones
     */
    private PanelBotones panelBotones;
    
    /**
     * Panel con la imagen del titulo
     */
    private PanelBanner panelBanner;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye la interfaz de la aplicaci�n
     * @param mundo Es la tabla periodica que se va a mostrar - mundo!=null
     */
    public InterfazTablaPeriodica(TablaP mundo)
    {
        // Crea la clase principal
    	tabla = mundo;
    	
        // Construye la forma
    	setLayout(new BorderLayout());
    	setSize(600,600);
    	setTitle("Tabla Periodica de los Elmentos");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	 // Creaci�n de los paneles aqu�
        panelBanner = new PanelBanner( );
      
        add( panelBanner, BorderLayout.NORTH);
    	
    	
        panelBotones = new PanelBotones( this );
       
        add( panelBotones, BorderLayout.SOUTH);
        centrar( );

    	
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    
    /**
     * Muestra el di�logo para agregar un nuevo elemento 
     */
    public void mostrarDialogoAgregarElemento()
    {
    	DialogoAgregarElemento dialogo = new DialogoAgregarElemento(this);
    	dialogo.setVisible(true);
    }
    
    public boolean crearElemento(int numAtom, String simbolo, String nombre, String categoria) throws PersistenciaException
    {
        boolean ok = false;
		try {
			tabla.agregar(numAtom, simbolo, nombre, categoria);
			ok = true;
		} catch (ElementoExisteException e) 
		{
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
		}
        return ok;

    }
    
    
    /**
     * Este m�todo se encarga de salvar la informaci�n del mundial, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        try
        {
            tabla.salvarMundial( );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la informaci�n de la tabla periodica:\n" + e.getMessage( ) + "\n�Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }
    
    
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. No deben usarse
     */
    public static void main( String[] args )
    {
        TablaP tabla = null;

        try
        {
            tabla = new TablaP( "./data/tabla.elementos" );
        }
        catch(PersistenciaException e )
        {
            e.printStackTrace( );
            System.exit( 1 );
        }

        InterfazTablaPeriodica id = new InterfazTablaPeriodica( tabla );
        id.setVisible( true );
    }

    
    
}

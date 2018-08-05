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
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La ruta donde deben guardarse los reportes
     */
    private static final String RUTA_ELEMENTOS = "./data/elementos";
    
    public static final String ALCALINOS = "Metales alcalinos";
    
    public static final String ALCALINOTERREOS = "Metales alcalinoterreos";

    public static final String TRANSICION = "Metales de transicion";

    public static final String OTROS_MET = "Otros Metales";

    public static final String LANTANIDOS = "Lantanidos";

    public static final String ACTINIDOS = "Actinidos";

    public static final String METALOIDES = "Metaloides";

    public static final String NO_MET = "No metales";

    public static final String HALOGENOS = "Halogenos";

    public static final String GASES_NOBLES = "Gases nobles";



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
     * Construye la interfaz de la aplicación
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
    	
    	 // Creación de los paneles aquí
        panelBanner = new PanelBanner( );
      
        add( panelBanner, BorderLayout.NORTH);
    	
    	
        panelBotones = new PanelBotones( this );
       
        add( panelBotones, BorderLayout.SOUTH);
        centrar( );

    	
    }
    
    // -----------------------------------------------------------------
    // Métodos
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
     * Muestra el diálogo para agregar un nuevo elemento 
     */
    public void mostrarDialogoAgregarElemento()
    {
    	DialogoAgregarElemento dialogo = new DialogoAgregarElemento(this);
    	dialogo.setVisible(true);
    }
    
    public void crearElemento(String numAt, String simbolo, String nombre, String categoria)
    {
		int n = Integer.parseInt(numAt);
    	
    	if(n == 1 || n == 6 || n == 7 || n == 8 || n == 15 || n == 16 || n == 34)
		{
			categoria = NO_MET;
		}
		else if(n == 2 || n == 10 || n == 18 || n == 36 || n == 54 || n == 86 || n == 118 )
		{
			categoria = GASES_NOBLES;
		}
		else if(n == 3 || n == 11 || n == 19 || n == 37 || n == 55 || n == 87 )
		{
			categoria = ALCALINOS;
		}
		else if( n == 4 || n == 12 || n == 20 || n == 38 || n == 56 || n == 88 )
		{
			categoria = ALCALINOTERREOS;
		}
		else if( n == 5 || n ==  14 || n == 32 || n == 33 || n == 51 || n == 52 || n == 84 ) 
		{
			categoria = METALOIDES;
		}
		else if( n == 9 || n == 17 || n == 35 || n == 53 || n == 85 || n == 117)
		{
			categoria = HALOGENOS;
		}
		else if( n == 13 || n == 31 || n == 49 || n == 50 || (n >= 81 && n <= 83) || (n >= 113 && n <= 116) )
		{
			categoria = OTROS_MET;
		}
		else if( (n >= 21 && n <= 30) || (n >= 39 && n <= 48) || (n >= 72 && n <= 80) || (n >= 103 && n <= 112) )
		{
			categoria = TRANSICION;
		}
		else if(n >= 57 && n <= 71)
		{
			categoria = LANTANIDOS;
		}
		else
		{
			categoria = ACTINIDOS;
		}
    	
		try {
			tabla.agregar(numAt, simbolo, nombre, categoria);
		} catch (ElementoExisteException e) 
		{
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
		}
    	
    }
    
    
    /**
     * Este método se encarga de salvar la información del mundial, justo antes de cerrar la aplicación
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
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la información de la tabla periodica:\n" + e.getMessage( ) + "\n¿Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
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
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Argumentos para la ejecución de la aplicación. No deben usarse
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

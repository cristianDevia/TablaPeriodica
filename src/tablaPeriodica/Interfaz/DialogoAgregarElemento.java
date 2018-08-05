package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogoAgregarElemento extends JDialog
{
	
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
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazTablaPeriodica principal;
    
    
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Es el panel con la imagen de titulo
     */
    private PanelBannerCrear banner;
    
    /**
     * Es el panel con los datos para crear el nuevo elemento
     */
    private PanelCrearElemento panelInfo;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo
     * @param ventana Es una referencia a la clase principal de la interfaz - ventana!=null
     */
	public DialogoAgregarElemento(InterfazTablaPeriodica ventana)
	{
		principal = ventana;
		
		setTitle( "Crear Elemento" );
        setSize( 400, 400 );
        setResizable( false );
        setLocationRelativeTo(null);
        setModal(true);
        
        
        // Distribuidor grafico en los bordes
        setLayout( new BorderLayout( ) );        
        
		banner = new PanelBannerCrear();
		add(banner, BorderLayout.NORTH);
		
		panelInfo = new PanelCrearElemento(this);
		add(panelInfo, BorderLayout.CENTER);
		
	}
	
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Centra el diálogo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }
    
    /**
     * Adiciona el elemento a la tabla periodica
     */
    public void crearElemento( )
    {
        boolean datosOk = true;
        int numAtom = 0;
        String nombre = null;
        String simbolo = null;
        String categoria = null;
      
        nombre = panelInfo.darNombre();
        numAtom = Integer.parseInt( panelInfo.darNumeroAtomico() );
        simbolo = panelInfo.darSimbolo();
        
        if(numAtom < 0 && numAtom > 118)
        {
        	datosOk = false;
            JOptionPane.showMessageDialog( this, "El numero atomico ingresado no es un valor válido", "error", JOptionPane.ERROR_MESSAGE );
        
        }
        else 
        {
        	if(nombre.equals(""))
        	{
        		datosOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar el nombre del elemento", "error", JOptionPane.ERROR_MESSAGE );
            
        	}
        	else
        	{
        		if(simbolo.equals(""))
        		{
        			datosOk = false;
                    JOptionPane.showMessageDialog( this, "Debe ingresar el simbolo del elemento", "error", JOptionPane.ERROR_MESSAGE );
       
        		}
        		       			
        	}
        }
       
		if(numAtom == 1 || numAtom == 6 || numAtom == 7 || numAtom == 8 || numAtom == 15 || numAtom == 16 || numAtom == 34)
		{
			categoria = NO_MET;
		}
		else if(numAtom == 2 || numAtom == 10 || numAtom == 18 || numAtom == 36 || numAtom == 54 || numAtom == 86 || numAtom == 118 )
		{
			categoria = GASES_NOBLES;
		}
		else if(numAtom == 3 || numAtom == 11 || numAtom == 19 || numAtom == 37 || numAtom == 55 || numAtom == 87 )
		{
			categoria = ALCALINOS;
		}
		else if( numAtom == 4 || numAtom == 12 || numAtom == 20 || numAtom == 38 || numAtom == 56 || numAtom == 88 )
		{
			categoria = ALCALINOTERREOS;
		}
		else if( numAtom == 5 || numAtom ==  14 || numAtom == 32 || numAtom == 33 || numAtom == 51 || numAtom == 52 || numAtom == 84 ) 
		{
			categoria = METALOIDES;
		}
		else if( numAtom == 9 || numAtom == 17 || numAtom == 35 || numAtom == 53 || numAtom == 85 || numAtom == 117)
		{
			categoria = HALOGENOS;
		}
		else if( numAtom == 13 || numAtom == 31 || numAtom == 49 || numAtom == 50 || (numAtom >= 81 && numAtom <= 83) || (numAtom >= 113 && numAtom <= 116) )
		{
			categoria = OTROS_MET;
		}
		else if( (numAtom >= 21 && numAtom <= 30) || (numAtom >= 39 && numAtom <= 48) || (numAtom >= 72 && numAtom <= 80) || (numAtom >= 103 && numAtom <= 112) )
		{
			categoria = TRANSICION;
		}
		else if(numAtom >= 57 && numAtom <= 71)
		{
			categoria = LANTANIDOS;
		}
		else
		{
			categoria = ACTINIDOS;
		}
		
        
        if( datosOk )
        {
            boolean ok = principal.crearElemento(numAtom, simbolo, nombre, categoria);

            if( ok )
            {
                dispose( );
            }
        }
    	
    }

}

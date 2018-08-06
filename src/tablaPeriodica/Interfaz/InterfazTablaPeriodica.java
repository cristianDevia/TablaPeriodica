package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import tablaPeriodica.mundo.Elemento;
import tablaPeriodica.mundo.ElementoExisteException;
import tablaPeriodica.mundo.ElementoNoExisteException;
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
     * Panel con la imagen del titulo
     */
    private PanelBanner panelBanner;
    
    /**
     * Panel con la informacion de la tabla periodica
     */
    private PanelInformacion panelInfo;
    
    
    /**
     * Panel con los botones
     */
    private PanelBotones panelBotones;
    
   
    
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
    	
        
        panelInfo = new PanelInformacion(this);
        add(panelInfo, BorderLayout.CENTER);
    	
        panelBotones = new PanelBotones( this );
       
        add( panelBotones, BorderLayout.SOUTH);
        centrar( );
        
        try {
			tabla.cargarElementos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
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
         
    public boolean crearElemento(int numAtom, String simbolo, String nombre, String categoria) throws PersistenciaException
    {
        boolean ok = false;
		try {
			tabla.agregar(numAtom, simbolo, nombre, categoria);
            JOptionPane.showMessageDialog(this, "El elemento se ha agregado satisfactoriamente");

			
			ok = true;
		} catch (ElementoExisteException e) 
		{
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
		}
        return ok;

    }
    
    public void consultar() 
    {
    	String numero = JOptionPane.showInputDialog("¿Digita el numero atomico del elemento que desea consultar? ");
    	
   		Elemento elemento;
		try {
			elemento = tabla.consultar(Integer.parseInt(numero));
			if(elemento == null )
			{
				JOptionPane.showMessageDialog(this, "La operacion Consultar ha sido cancelada");
			}
			JOptionPane.showMessageDialog(this, "Numero Atomico: "+ elemento.getNumAtomico() + "\n" + "Nombre: " + elemento.getNombre() + "\n" + "Simbolo: " + elemento.getSimbolo() + "\n" + "Categoria: " + elemento.getCategoria());

		} catch (NumberFormatException | ElementoNoExisteException e) {
			e.printStackTrace();
		}

    }
    
    public void eliminar() 
    {
    	String numero = JOptionPane.showInputDialog("¿Digita el numero atomico del elemento que desea eliminar? ");
    	
			try {
				tabla.eliminar(numero);
				JOptionPane.showMessageDialog(this, "El elemento ha sido eliminado");

			} catch (ElementoNoExisteException | PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    }

    
    public void visualizar()
    {
    	
    	for(int i =0; i < tabla.getElementos().size(); i++)
    	{
    		Elemento aux = tabla.getElementos().get(i);
    		String cast = Integer.toString(aux.getNumAtomico());
    		String cast1 = aux.getNombre();
    		String cast2 = aux.getSimbolo();
    		String cast3 = aux.getCategoria();
    		
    		String visual = cast + " " + cast1 + " " + cast2 + " " + cast3;
    		
    		if(cast != null && cast == "" && cast1 != null && cast1 == "" && cast2 != null && cast2 == "" && cast3 != null && cast3 == "")
    		{
    			JTextArea resultado = panelInfo.getTxtResultado();
    			resultado.insert(visual, 0);
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

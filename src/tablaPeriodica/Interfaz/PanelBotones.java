package tablaPeriodica.Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelBotones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para agregar un elemento
     */
    private static final String AGREGAR_ELEMENTO = "Agregar Elemento";
    
    /**
     * Comando para consultar un elemento
     */
    private static final String CONSULTAR_ELEMENTO = "Consultar Elemento";
    
    /**
     * Comando para modificar un elemento
     */
    private static final String MODIFICAR_ELEMENTO = "Modificar Elemento";
    
    /**
     * Comando para eliminar un elemento
     */
    private static final String ELIMINAR_ELEMENTO = "Eliminar Elemento";
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazTablaPeriodica principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para agregar un elemento
     */
    private JButton butAgregar;

    /**
     * Es el botón para consultar un elemento
     */
    private JButton butConsultar;

    /**
     * Es el botón para modificar un elemento
     */
    private JButton butModificar;

    /**
     * Es el botón para eliminar un elemento
     */
    private JButton butEliminar;

    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal - ventana!=null
     */
    public PanelBotones( InterfazTablaPeriodica ventana )
    {
    	 principal = ventana;
         setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Opciones" ) ) );
         setLayout( new GridLayout( 2, 3) );

         // Botón agregar elementos
         butAgregar = new JButton( "Agregar Elemento" );
         butAgregar.setActionCommand( AGREGAR_ELEMENTO );
         butAgregar.addActionListener( this );
         add( butAgregar );
         
         // Botón consultar elementos
         butConsultar = new JButton( "Consultar Elemento" );
         butConsultar.setActionCommand( CONSULTAR_ELEMENTO );
         butConsultar.addActionListener( this );
         add( butConsultar );
         
         // Botón modificar elementos
         butModificar = new JButton( "Modificar Elemento" );
         butModificar.setActionCommand( MODIFICAR_ELEMENTO );
         butModificar.addActionListener( this );
         add( butModificar );
         
         // Botón eliminar elementos
         butEliminar = new JButton( "Eliminar Elemento" );
         butEliminar.setActionCommand( ELIMINAR_ELEMENTO );
         butEliminar.addActionListener( this );
         add( butEliminar );

        
    }
       
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento - evento!=null
     */
	public void actionPerformed(ActionEvent evento) 
	{
        String comando = evento.getActionCommand( );
        
        if(comando.equals(AGREGAR_ELEMENTO))
        {
        	principal.mostrarDialogoAgregarElemento();
        }

		if(comando.equals(ELIMINAR_ELEMENTO))
		{
			
		}
		
	}

	
	
}

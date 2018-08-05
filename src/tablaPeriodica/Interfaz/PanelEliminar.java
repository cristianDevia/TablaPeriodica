package tablaPeriodica.Interfaz;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelEliminar extends JPanel implements ActionListener
{
	
	 public final static String ELIMINAR = "Eliminar";
	 
	 public final static String CANCELAR = "Cancelar";
	 
	 /**
	 * Es una referencia a la clase principal de la interfaz
	 */
	 private InterfazTablaPeriodica principal;
	    
	    /**
	     * Es el bot�n para eliminar un elemento
	     */
	    private JButton butEliminar;

	    /**
	     * Es el bot�n para cancelar un elemento
	     */
	    private JButton butCancelar;
	    
		
	    private JComboBox jcbElementos;
	    
	    /**
	     * 
	     * 
	     */
	    public void cargarEquipos( ArrayList elementos )
	    {
	        jcbElementos.removeAllItems( );

	        for( int i = 0; i < elementos.size( ); i++ )
	        {
	            jcbElementos.addItem( elementos.get( i ) );
	        }
	    }
	    
	    /**
	     * Es la etiqueta "Nombre: "
	     */
	    private JLabel labNombre;
	    
	    
	    public PanelEliminar( InterfazTablaPeriodica ventana )
	    {
	    	 principal = ventana;
	         setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Opciones" ) ) );
	         setLayout( new GridLayout( 2, 3) );

	         labNombre = new JLabel("Elemento:");
	         
	         JPanel panelAuxiliar = new JPanel( );
	         panelAuxiliar.setLayout( new GridBagLayout( ) );
	         jcbElementos = new JComboBox( elementos.toArray( ) );
	         jcbElementos.setEditable( false );
	         jcbElementos.addActionListener( this );
	         jcbElementos.setActionCommand( CAMBIAR_EQUIPO );
	         
	         
	         // Bot�n agregar elementos
	         butEliminar = new JButton( "Eliminar elemento" );
	         butEliminar.setActionCommand( ELIMINAR );
	         butEliminar.addActionListener( this );
	         add( butEliminar );
	         
	         // Bot�n consultar elementos
	         butCancelar = new JButton( "Cancelar" );
	         butCancelar.setActionCommand( CANCELAR );
	         butCancelar.addActionListener( this );
	         add( butCancelar );
	    }

		@Override
		public void actionPerformed(ActionEvent evento) {
			
				String comando = evento.getActionCommand( );
		        
		        if(comando.equals(ELIMINAR))
		        {
		        	
		        }

				if(comando.equals(CANCELAR))
				{
					
				}
			
		}
}
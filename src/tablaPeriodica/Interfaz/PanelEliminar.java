package tablaPeriodica.Interfaz;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tablaPeriodica.mundo.Elemento;

public class PanelEliminar extends JPanel implements ActionListener
{
	
	 public final static String ELIMINAR = "Eliminar";
	 
	 public final static String CANCELAR = "Cancelar";
	 
	 /**
	 * Es una referencia a la clase principal de la interfaz
	 */
	 private DialogoEliminar principal;
	    
	 /**
	 * Es el botón para eliminar un elemento
	 */
	 private JButton butEliminar;

	    /**
	     * Es el botón para cancelar un elemento
	     */
	    private JButton butCancelar;
	    
		
	    private JTextField txtNumeroelemento;
   
	    /**
	     * Es la etiqueta "Nombre: "
	     */
	    private JLabel labNombre;

	    private Elemento elemento;
	    
	    public PanelEliminar( DialogoEliminar dialogoEliminar )
	    {
	    	 principal = dialogoEliminar;
	     
	         setLayout( new GridLayout( 1, 2) );
	         

	         labNombre = new JLabel("Elemento:");
	         
	         txtNumeroelemento = new JTextField();
	         txtNumeroelemento.setEditable(true);
	         txtNumeroelemento.setBackground(Color.WHITE);
	         
	         // Botón agregar elementos
	         butEliminar = new JButton( "Eliminar elemento" );
	         butEliminar.setActionCommand( ELIMINAR );
	         butEliminar.addActionListener( this );
	         add( butEliminar );
	         
	         // Botón consultar elementos
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

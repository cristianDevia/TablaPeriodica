package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoEliminar extends JDialog 
{
	
	 public final static String ELIMINAR = "Eliminar";
	 
	 public final static String CANCELAR = "Cancelar";
	 

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazTablaPeriodica principal;
    
    private JComboBox elemento;
    
    /**
     * Es la etiqueta "Nombre: "
     */
    private JLabel labNombre;
    
	public DialogoEliminar (InterfazTablaPeriodica ventana)
	{
		principal = ventana;
		
		setTitle( "Eliminar elemento" );
        setSize( 400, 400 );
        setResizable( false );
        setLocationRelativeTo(null);
        setModal(true);
        
        
        // Distribuidor grafico en los bordes
        setLayout( new BorderLayout( ) );        
     
        
	}
		

    
}

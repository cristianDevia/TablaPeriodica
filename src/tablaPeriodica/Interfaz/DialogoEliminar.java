package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoEliminar extends JDialog 
{

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazTablaPeriodica principal;
	
    /**
     * Es el panel con los datos para eliminar el elemento
     */
    private PanelEliminar panelEliminar;		

    /**
     * Construye el diálogo
     * @param ventana Es una referencia a la clase principal de la interfaz - ventana!=null
     */
    
    
	public DialogoEliminar(InterfazTablaPeriodica ventana)
	{
		principal = ventana;
		
		setTitle( "Eliminar" );
        setSize( 300, 300 );
        setResizable( false );
        setLocationRelativeTo(null);
        setModal(true);
        
        // Distribuidor grafico en los bordes
        setLayout( new BorderLayout( ) );        
        
		panelEliminar = new PanelEliminar(this);
		add(panelEliminar, BorderLayout.CENTER);
		
	}
	
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }
}

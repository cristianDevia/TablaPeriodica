package tablaPeriodica.Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelBannerCrear extends JPanel
{

	// -----------------------------------------------------
	// Atributos
	// -----------------------------------------------------
		
	/** Etiqueta para mostrar la imagen del banner.	 */
		private JLabel labImagen;
		
	// -----------------------------------------------------
	// Constructor
	// -----------------------------------------------------
		
    /** Crea el panel del banner. */
		
	public PanelBannerCrear()
	{
		FlowLayout layout = new FlowLayout( );
        layout.setHgap( 0 );
        layout.setVgap( 0 );
        setLayout( layout );
        //
        // Carga la imagen
        ImageIcon icono = new ImageIcon( "data/imagenes/bannerCrear.png" );

        // La agrega a la etiqueta
        labImagen = new JLabel( "" );
        labImagen.setIcon( icono );
        add( labImagen );
        //
        // Color de fondo blanco
        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.GRAY ) );
	}
	
}

package tablaPeriodica.Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelBanner extends JPanel
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
		
	public PanelBanner( )
	{
		// Se crea el objeto del panel
		labImagen = new JLabel(new ImageIcon(getClass().getResource("data/titulo.png")  ) );
			
		// Agregar el objeto al panel
		add( labImagen );
	}
	
	
}

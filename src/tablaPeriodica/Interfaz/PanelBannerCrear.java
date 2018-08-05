package tablaPeriodica.Interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		// Se crea el objeto del panel
		labImagen = new JLabel(new ImageIcon(getClass().getResource("data/bannerCrear.png")  ) );
			
		// Agregar el objeto al panel
		add( labImagen );
	}
	
}

package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;

public class DialogoAgregarElemento extends JDialog
{
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
	
	

}

package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelInformacion extends JPanel
{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazTablaPeriodica principal;
    
	/**
	 * Es el area de campo de texto donde se visualizaran los resultados
	 */
	private JTextArea txtResultado;
	
	/**
	 * Es la barra de desplazamiento del campo de texto donde se visualizaran los resultados
	 */
	private JScrollPane scroll;

	
	public PanelInformacion(InterfazTablaPeriodica princi) 
	{
		principal = princi;
		
		TitledBorder borde1 = BorderFactory.createTitledBorder("Visualizacion de los elementos");
		borde1.setTitleColor( Color.BLACK );
		setBorder( borde1 );
		
		txtResultado = new JTextArea();
		txtResultado.setBackground(Color.WHITE);
		txtResultado.setEditable(false);
		scroll = new JScrollPane(txtResultado);
				
		add(scroll,BorderLayout.CENTER);
	}


	public JTextArea getTxtResultado() {
		return txtResultado;
	}
	
	

}

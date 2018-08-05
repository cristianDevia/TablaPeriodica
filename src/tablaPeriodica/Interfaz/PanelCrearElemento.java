package tablaPeriodica.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class PanelCrearElemento extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el botón Crear
     */
    private static final String CREAR_ELEMENTO = "Crear Elemento";

    /**
     * El comando para el botón Cancelar
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo al que pertenece este panel
     */
    private DialogoAgregarElemento dialogo;

	
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Es la etiqueta "Numero Atomico: "
     */
    private JLabel labNumAt;

    /**
     * Es el campo de texto para ingresar el numero atomico del elemento
     */
    private JTextField txtNumAt;    
    
    /**
     * Es la etiqueta "Nombre: "
     */
    private JLabel labNombre;

    /**
     * Es el campo de texto para ingresar el nombre del elemento
     */
    private JTextField txtNombre;


    /**
     * Es la etiqueta "Simbolo: "
     */
    private JLabel labSimbolo;

    /**
     * Es el campo de texto para ingresar el simbolo del elemento
     */
    private JTextField txtSimbolo;
	
	
    public PanelCrearElemento(DialogoAgregarElemento dialog)
    {
    	dialogo = dialog;
    	
    	setLayout(new BorderLayout());
    	setBorder(new TitledBorder("Crear Elemento"));
    	
    	labNumAt = new JLabel("Numero Atomico: ");
    	
    	txtNumAt = new JTextField();
        txtNumAt.setPreferredSize( new Dimension( 80, 25 ) );

    	
    	labNombre = new JLabel("Nombre: ");
    	
    	txtNombre = new JTextField();
        txtNombre.setPreferredSize( new Dimension( 80, 25 ) );

    	labSimbolo = new JLabel("Simbolo ");
    	
    	txtSimbolo = new JTextField();
        txtSimbolo.setPreferredSize( new Dimension( 80, 25 ) );

    	
    	add(labNumAt);
    	add(txtNumAt);

    	add(labNombre);
    	add(txtNombre);

    	add(labSimbolo);
    	add(txtSimbolo);
    }

    /**
     * Da el valor del campo de texto con el nombre del elemento
     * @return Se retornó el nombre del elemento ingresado por el usuario
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }


    /**
     * Da el valor del campo de texto con el numero atomico del elemento
     * @return Se retornó el numero atomico ingresado por el usuario
     */
    public String darNumeroAtomico( )
    {
        return txtNumAt.getText( );
    }
    

    /**
     * Da el valor del campo de texto con el simbolo del elemento
     * @return Se retornó el simbolo del elemento ingresado por el usuario
     */
    public String darSimbolo( )
    {
        return txtSimbolo.getText( );
    }
	
    /**
     * Ejecuta una acción cuando se hace click sobre un botón
     * @param evento el evento del click sobre un botón - evento!=null
     */
	public void actionPerformed(ActionEvent evento) 
	{
        String comando = evento.getActionCommand( );

        if( CREAR_ELEMENTO.equals( comando ) )
        {
            
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
		
	}

}

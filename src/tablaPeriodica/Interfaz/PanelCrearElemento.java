package tablaPeriodica.Interfaz;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
    	
    	
    }



	public JLabel getLabNumAt() {
		return labNumAt;
	}

	public void setLabNumAt(JLabel labNumAt) {
		this.labNumAt = labNumAt;
	}

	public JTextField getTxtNumAt() {
		return txtNumAt;
	}

	public void setTxtNumAt(JTextField txtNumAt) {
		this.txtNumAt = txtNumAt;
	}

	public JLabel getLabNombre() {
		return labNombre;
	}

	public void setLabNombre(JLabel labNombre) {
		this.labNombre = labNombre;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JLabel getLabSimbolo() {
		return labSimbolo;
	}

	public void setLabSimbolo(JLabel labSimbolo) {
		this.labSimbolo = labSimbolo;
	}

	public JTextField getTxtSimbolo() {
		return txtSimbolo;
	}

	public void setTxtSimbolo(JTextField txtSimbolo) {
		this.txtSimbolo = txtSimbolo;
	}


	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

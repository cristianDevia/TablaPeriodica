package tablaPeriodica.mundo;

public class Elemento 
{
	/**
	 *Atributo que representa el numero Atomico de un elemento
	 */
	private int numAtomico;
	
	/**
	 * Atributo que representa el simbolo de un elemento
	 */
	private String simbolo;
	
	/**
	 * Atributo que representa el nombre de un elemento
	 */
	private String nombre;

	/**
	 * Atributo que representa la categoria de un elemento
	 */
	private String categoria;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	/**
	 * Inicializacion de los atributos de la clase Elemento
	 * @param numAtomico numero atomico del elemento
	 * @param simbolo simbolo del elemento
	 * @param nombre nombre del elemento
	 * @param categoria categoria del elemento
	 */
	public Elemento(int numAtomico, String simbolo, String nombre, String categoria)
	{
		this.numAtomico = numAtomico;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.categoria = categoria;
	}
	
	
	public int getNumAtomico() 
	{
		return numAtomico;
	}

	public String getSimbolo()
	{
		return simbolo;
	}

	public void setSimbolo(String simbolo)
	{
		this.simbolo = simbolo;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
}

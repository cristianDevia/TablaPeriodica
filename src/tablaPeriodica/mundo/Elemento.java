package tablaPeriodica.mundo;

public class Elemento 
{

	private int numAtomico;
	
	private String simbolo;
	
	private String nombre;

	private String categoria;
	
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

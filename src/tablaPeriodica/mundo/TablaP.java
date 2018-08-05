package tablaPeriodica.mundo;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class TablaP 
{
	
    /**
    * Nombre del archivo de registro de errores del programa
    */
    private static final String LOG_FILE = "./data/error.log";
    
    private static final String RUTA = "./data/elementos.log";
  
	private ArrayList<Elemento> elementos;
	
	public String getArchivoElementos() {
		return archivoElementos;
	}

	public void setArchivoElementos(String archivoElementos) {
		this.archivoElementos = archivoElementos;
	}

	public void setElementos(ArrayList<Elemento> elementos) {
		this.elementos = elementos;
	}

	private String archivoElementos;
	
	
	/**
	 * Constructor
	 */
	public TablaP(String nombreArchivoElemento) throws PersistenciaException
	{
		archivoElementos = nombreArchivoElemento;
		File archivo = new File(archivoElementos);
		
		if(archivo.exists())
		{
            // El archivo existe: se debe recuperar de all� el estado del modelo del mundo
			try
			{
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                elementos = ( ArrayList )ois.readObject( );
                ois.close( );
			}catch (Exception e) {
				
				registrarError(e);
                throw new PersistenciaException( "Error fatal: imposible restaurar el estado del programa (" + e.getMessage( ) + ")" );

			}
		}
		else
		{
			elementos = new ArrayList<>();
		}
		
	}	

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public Elemento consultar(int numAtomico)
	{
		Elemento buscado = null;
		for(int i =0; i< elementos.size(); i++)
		{
			Elemento e = elementos.get(i);
			
			if(e.getNumAtomico() == numAtomico)
			{
				buscado = e;
			}
		}
		return buscado;
	}
	
	public Elemento verificarElemento(String numAtom)
	{
		Elemento elementoBuscado = null;
		boolean esta = false;
		
		for (int i = 0; i < elementos.size() && !esta; i++) 
		{
		
			Elemento e = elementos.get(i);
			if(e.getNumAtomico() == Integer.parseInt(numAtom) )
			{
				elementoBuscado = e;
				esta = true;
				
			}
		}
		return elementoBuscado;		
	}
	
		
	public void agregar(int numAtom, String simbol, String nombre, String categoria) throws ElementoExisteException, PersistenciaException
	{
		String n = Integer.toString(numAtom);
		if(verificarElemento(n)!= null)
		{
			throw new ElementoExisteException("El elemento con numero atomico" + numAtom + " ya existe");
		}
	
		Elemento nuevo = new Elemento(numAtom, simbol, nombre, categoria);
		elementos.add(nuevo);
		guardar();
	}
	
	
	public void guardar() throws PersistenciaException
	{
		try 
		{
			FileWriter file = new FileWriter(RUTA,false);
			PrintWriter writer = new PrintWriter(file);
			for(int i = 0; i < elementos.size(); i++)
			{
				Elemento e = elementos.get(i);
				String out = e.getNumAtomico() +	 "-"	+	e.getSimbolo() +	"-"	+	e.getNombre() +	"-"	+	e.getCategoria();
				writer.println(out);
			}
			writer.close();
			file.close();
		}
		catch (IOException e)
		{
			throw new PersistenciaException("Existe un problema en el archivo");
		}
	}

	public void eliminar(String numAtom) throws ElementoNoExisteException
	{	
		if(verificarElemento(numAtom)== null)
		{
			throw new ElementoNoExisteException("El elemento con numero atomico " + numAtom + " no existe");
		}
		
		int n = Integer.parseInt(numAtom);
		
		for(int i = 0; i< elementos.size(); i++)
		{
			Elemento elemento = elementos.get(i);

			if(elemento.getNumAtomico() == n)
			{
				elementos.remove(i);
			}
		
		}	
	}
	
	public void modificar(String numAtom, String simbolo, String nombre) throws ElementoNoExisteException
	{
		if(verificarElemento(numAtom)== null)
		{
			throw new ElementoNoExisteException("El elemento con numero atomico " + numAtom + " no existe");
		}
		
		int n = Integer.parseInt(numAtom);
						
		for(int i = 0; i< elementos.size(); i++)
		{
			Elemento elemento = elementos.get(i);

			if(elemento.getNumAtomico() == n)
			{
				elemento.setSimbolo(simbolo);
				elemento.setNombre(nombre);
			}
		}
		
	}
	
	public void cargarElementos() throws IOException
	{
			File archivo = new File( RUTA );
			FileReader reader = new FileReader(archivo );
			BufferedReader lector = new BufferedReader( reader );
			String linea = lector.readLine();
			
			while(linea != null)
			{
				String[] partesElemento = linea.split("-");
				
				int numAtomico = Integer.parseInt(partesElemento[0]);
				String simbolo = partesElemento[1];
				String nombre = partesElemento[2];
				String categoria = partesElemento[3];
				
				Elemento e = new Elemento(numAtomico,simbolo,nombre, categoria);
				
				elementos.add(e);
				linea = lector.readLine();
			}
		lector.close();
		reader.close();
	}
	
    /**
     * Salva la tabla periodica en un archivo binario
     * @throws PersistenciaException Se lanza esta excepci�n si hay problemas guardando la informaci�n de los elementos en el archivo
     */
    public void salvar( ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoElementos ) );
            oos.writeObject( elementos );
            oos.close( );
        }
        catch( IOException e )
        {
            registrarError( e );
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
    }
	
    /**
     * Registra en el archivo de log del programa toda la informaci�n referente a una excepci�n, ocurrida durante el proceso de persistencia
     * @param excepcion Es la excepci�n que contiene la informaci�n del error
     */
    public void registrarError( Exception excepcion )
    {
        try
        {
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "Tabla Peridica.java :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( );
        }
        catch( IOException e )
        {
            excepcion.printStackTrace( );
            e.printStackTrace( );
        }
       
    }


}

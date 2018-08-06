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
    
    /**
     * Nombre del archivo de registro de los elementos
     */
    private static final String RUTA = "./data/elementos.log";
  
    /**
     * Arreglo de elementos quimicos
     */
	private ArrayList<Elemento> elementos;

	/**
	 * Referencia al archivo de elementos
	 */
	private String archivoElementos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Inicializacion de los atributos de la tabla periodica
	 * @param nombreArchivoElemento 
	 * @throws PersistenciaException error que puede presentar al abrir el archivo
	 */
	public TablaP(String nombreArchivoElemento) throws PersistenciaException
	{
		archivoElementos = nombreArchivoElemento;
		File archivo = new File(archivoElementos);
		
		if(archivo.exists())
		{
            // El archivo existe: se debe recuperar de allí el estado del modelo del mundo
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

	/**
	 * Metodo que representa el arreglo de los elementos
	 * @return Arreglo de elementos quimicos
	 */
	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public Elemento consultar(String numAtom)
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
		if(consultar(n)!= null)
		{
			throw new ElementoExisteException("El elemento con numero atomico" + numAtom + " ya existe");
		}
	
		Elemento nuevo = new Elemento(numAtom, simbol, nombre, categoria);
		elementos.add(nuevo);
		guardar();
	}
	
	
	
	public void eliminar(String numAtom) throws ElementoNoExisteException, PersistenciaException
	{	
		if(consultar(numAtom)== null)
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
				guardar();
			}
		
		}	
	}
	
	public void modificar(String numAtom, String simbolo, String nombre) throws ElementoNoExisteException, PersistenciaException
	{
		if(consultar(numAtom)== null)
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
				guardar();
			}
		}
		
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
     * @throws PersistenciaException Se lanza esta excepción si hay problemas guardando la información de los elementos en el archivo
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
     * Registra en el archivo de log del programa toda la información referente a una excepción, ocurrida durante el proceso de persistencia
     * @param excepcion Es la excepción que contiene la información del error
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

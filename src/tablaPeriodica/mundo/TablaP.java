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
	
    public static final String RUTA ="./data/elementos.txt";
    

	private ArrayList<Elemento> elementos;
	
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
	
		
	public void agregar(int numAtom, String simbol, String nombre, String categoria) throws ElementoExisteException
	{
		String n = Integer.toString(numAtom);
		if(verificarElemento(n)!= null)
		{
			throw new ElementoExisteException("El elemento con numero atomico" + numAtom + " ya existe");
		}
	
		Elemento nuevo = new Elemento(numAtom, simbol, nombre, categoria);
		elementos.add(nuevo);
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
	
    /**
     * Salva la tabla periodica en un archivo binario en un archivo binario
     * @throws PersistenciaException Se lanza esta excepción si hay problemas guardando la información de los elementos en el archivo
     */
    public void salvarMundial( ) throws PersistenciaException
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

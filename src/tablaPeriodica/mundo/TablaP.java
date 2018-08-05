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
    
    public static final String ALCALINOS = "Metales alcalinos";
    
    public static final String ALCALINOTERREOS = "Metales alcalinoterreos";

    public static final String TRANSICION = "Metales de transicion";

    public static final String OTROS_MET = "Otros Metales";

    public static final String LANTANIDOS = "Lantanidos";

    public static final String ACTINIDOS = "Actinidos";

    public static final String METALOIDES = "Metaloides";

    public static final String NO_MET = "No metales";

    public static final String HALOGENOS = "Halogenos";

    public static final String GASES_NOBLES = "Gases nobles";


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
	
		
	public void agregar(String numAt, String simbol, String nombre, String categoria) throws ElementoExisteException
	{
		if(verificarElemento(numAt)!= null)
		{
			throw new ElementoExisteException("El elemento con numero atomico" + numAt + " ya existe");
		}
	
		int n = Integer.parseInt(numAt);
		
		if(n == 1 || n == 6 || n == 7 || n == 8 || n == 15 || n == 16 || n == 34)
		{
			categoria = NO_MET;
		}
		else if(n == 2 || n == 10 || n == 18 || n == 36 || n == 54 || n == 86 || n == 118 )
		{
			categoria = GASES_NOBLES;
		}
		else if(n == 3 || n == 11 || n == 19 || n == 37 || n == 55 || n == 87 )
		{
			categoria = ALCALINOS;
		}
		else if( n == 4 || n == 12 || n == 20 || n == 38 || n == 56 || n == 88 )
		{
			categoria = ALCALINOTERREOS;
		}
		else if( n == 5 || n ==  14 || n == 32 || n == 33 || n == 51 || n == 52 || n == 84 ) 
		{
			categoria = METALOIDES;
		}
		else if( n == 9 || n == 17 || n == 35 || n == 53 || n == 85 || n == 117)
		{
			categoria = HALOGENOS;
		}
		else if( n == 13 || n == 31 || n == 49 || n == 50 || (n >= 81 && n <= 83) || (n >= 113 && n <= 116) )
		{
			categoria = OTROS_MET;
		}
		else if( (n >= 21 && n <= 30) || (n >= 39 && n <= 48) || (n >= 72 && n <= 80) || (n >= 103 && n <= 112) )
		{
			categoria = TRANSICION;
		}
		else if(n >= 57 && n <= 71)
		{
			categoria = LANTANIDOS;
		}
		else
		{
			categoria = ACTINIDOS;
		}
		
		Elemento nuevo = new Elemento(n, simbol, nombre, categoria);
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

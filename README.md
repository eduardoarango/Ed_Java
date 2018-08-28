Ed_Java
=======
public class Main {
	
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String JAVA_EXE = System.getProperty("java.home") + SEPARATOR + "bin" + SEPARATOR + "java.exe"; //Debe ser Java 7 o superior.

	public static void main(String[] args) {

		//Debe de ser obtenido del archivo "C:\Users\jperez\.refirma\refirma-pdf.properties" Siendo jperez la carpeta de usuario del sistema operativo.
		String refirmaPath = "C:/Users/pportugal/.refirma-pdf-jws/ReFirmaPDF-1.5.2.jar"; 

		//-f "C:\temp\cv.pdf" -r "Soy el autor del documento" -s "true" -e "C:\temp\iFirma.png" -a "0" -p "0" -x "20" -y "20" -z "7" -d ".*FIR.*|.*FAU.*" -t "true" -w "false" -o "C:\temp\cv[R].pdf"
		String arg = " "; 
		arg += "-f " + "\"" + "C:\\temp\\cv.pdf" + "\" ";
		arg += "-r " + "\"" + "Soy el autor del documento" + "\" ";
		arg += "-s " + "\"" + "true" + "\" ";
		arg += "-e " + "\"" + "C:\\temp\\iFirma.png" + "\" ";
		arg += "-a " + "\"" + "0" + "\" ";
		arg += "-p " + "\"" + "0" + "\" ";
		arg += "-x " + "\"" + "20" + "\" ";
		arg += "-y " + "\"" + "20" + "\" ";
		arg += "-z " + "\"" + "7" + "\" ";
		arg += "-d " + "\"" + ".*FIR.*|.*FAU.*" + "\" "; //Para que solo firme el que se autentico al sistema enviar el número de DNI ".*FIR.*|.*FAU.*|.*44627780.*"
		arg += "-t " + "\"" + "true" + "\" ";
		arg += "-w " + "\"" + "false" + "\" ";
		arg += "-o " + "\"" + "C:\\temp\\cv[R].pdf" + "\""; 

		String[] cmd = {"\"" + JAVA_EXE + "\"" + " -jar " + "\"" + refirmaPath + "\"" + arg};        
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssZ");
			System.out.println("INICIO REFIRMA PDF: " + format.format(new Date()));            
			Process p = Runtime.getRuntime().exec(cmd);    
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			@SuppressWarnings("UnusedAssignment")
			String line = null;
			while ((line = in.readLine()) != null) {    
				System.out.println("REFIRMA PDF: " + line); //Imprime registros log del ReFirma PDF, es opcional ponerlo. 		                  
			}
			System.out.println("FIN REFIRMA PDF: " + format.format(new Date()));  	
		} catch (IOException e) {  		                  				
			System.err.println("Ocurrió un error durante la ejecución del aplicativo ReFirma PDF.\n" + e.getMessage());
		}		
	}

}

Proyecto java

package agenda.io;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**@author Unai Pérez, Enrique Lafraya, Iñaki Tiraplegui
 * Utilidades para cargar la agenda
 */
public class AgendaIO {
	/**
	 * Método que al meter como @param una agenda, importa los datos predefinidos de
	 * esta. A su vez, introducimos la ruta del archivo txt que deseamos importar para coger sus datos.
	 * @return Devuelve un número entero con la cantidad de fallos originados a la hora de importar "X" líneas de datos
	 * 
	 */
	public static int importar(AgendaContactos agenda, String ruta) {
		int contador = 0;  
		int suma = 0;
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader(new FileReader(ruta));
			String linea = entrada.readLine();
			while (linea != null) {
				Contacto contacto = parsearLinea(linea);
				if(contacto != null) {
				agenda.añadirContacto(contacto);}
				else {linea = entrada.readLine();}			
				linea = entrada.readLine();
			}
			if(linea == null) {
				linea = entrada.readLine();
			}
		} catch (IOException e) {
			contador++;
			suma += contador;
			System.out.println("Error al leer " + ruta);
		} catch (NullPointerException e) {
			System.out.println("Documento vacío");
		} finally {
			if (entrada != null) { 
				try {
					entrada.close();
				} catch (NullPointerException e) {
					contador++;
					suma += contador;
					System.out.println(e.getMessage());
				} catch (IOException e) {
					contador++;
					suma += contador;
					System.out.println(e.getMessage());
				} 
			}
		}
		System.out.println(); //System out decorativo a la hora de sacar los datos en la consola.
		return suma;
	}

	/**
	 * Método que al meter como @param una linea con este
	 * estilo(tipo,nombre,apellidos,telefono,email, empresa o fecha de nacimiento
	 * dependiendo del tipo de Contacto,relacion si la hay) te la convierte en un
	 * Contacto, por tanto, como @return devolverá un Contacto creado mediante la información
	 * obtenido parseando la línea del parámetro.
	 * 
	 */
	private static Contacto parsearLinea(String linea){
		String[] separacion = linea.split(",");
		for(int i = 0; i < separacion.length; i++) {
			separacion[i] = separacion[i].trim();
		}
		String nombre ; 
		String apellidos; 
		String telefono;
		String email;
		int numClase = Integer.parseInt(separacion[0]);
		Relacion relac = Relacion.HIJO;
		String atributoNoComun1;
		
		try { //Creación de un contacto Profesional
			if(numClase == 1) {
				nombre = separacion[1]; 
				apellidos = separacion[2]; 
				telefono = separacion[3];
				email = separacion[4];;
				atributoNoComun1 = separacion[5];
				Profesional prof = new Profesional(nombre, apellidos, telefono, email, atributoNoComun1);
				Contacto con = (Contacto) prof;
				return con;
			}
		} catch (Exception e) {
			System.out.println("Error con un campo del contacto");
		}
		
		try { //Creación de un contacto Personal
			if(numClase == 2) {
				nombre = separacion[1]; 
				apellidos = separacion[2]; 
				telefono = separacion[3];
				email = separacion[4];
				atributoNoComun1 = separacion[5];
				
				separacion[6].toUpperCase();
				switch (separacion[6].toUpperCase()) {
				case "PADRE":
					relac = relac.PADRE;
					break;
				case "MADRE":
					relac = relac.MADRE;
					break;
				case "AMIGOS":
					relac = relac.AMIGOS;
					break;
				case "HIJO":
					relac = relac.HIJO;
					break;
				case "HIJA":
					relac = relac.HIJA;
					break;
				case "PAREJA":
					relac = relac.PAREJA;
					break;
				}
				
				Personal prof = new Personal(nombre, apellidos, telefono, email, atributoNoComun1, relac);
				Contacto con = (Contacto) prof;
				return con;
			}
		} catch (Exception e) {
			System.out.println("Error con un campo del contacto");
		}
		
		return null;
	}
	
	/**
	 * Método que exportará los contactos PERSONALES clasificados por tipo de relación en un archivo cuyo nombre
	 * será el introducido como parámetro.
	 * @param Introducimos como parámetros una ruta del archivo a crear con los contactos escritos en él. A su 
	 * vez, introducimos una agenda para obtener mediante ella, todos los contactos personales y clasificarlos
	 * por relación.
	 * 
	 */
	public static void exportarPersonales(AgendaContactos agenda, String ruta) {
		FileWriter salida = null;
        StringBuilder print = new StringBuilder();
        String dato = "";

        try {
            boolean acabado = true;
            salida = new FileWriter(new File(ruta));
            for(Relacion clave : agenda.personalesPorRelacion().keySet()) {
                    print.append(clave + "\n" + "-> ");
                    acabado = false;
                    dato = "" + print;
                for(String nombre : agenda.personalesPorRelacion().get(clave)) {
                    if(acabado == false) {
                        print.append(nombre);
                        dato = "" + print;
                        acabado = true;
                        }else {
                            print.append("," + nombre);
                            dato = "" + print;
                        }
            }
                print.append("\n");
                dato="" + print;}
            salida.write(dato);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                salida.close();
            } catch (IOException e) {
                System.out.println("No se puede cerrar");
            }
        }

    }
}


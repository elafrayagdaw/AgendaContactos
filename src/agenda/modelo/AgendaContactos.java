package agenda.modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

 /**
 *@Author - Iñaki T, Unai P, Enrique L 
 * */
public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;
	private Set<Contacto> contactos;

	/**
	 * Constructor de la Agenda de contactos
	 */
	public AgendaContactos() {
		agenda = new TreeMap<Character, Set<Contacto>>();
	}

	/**
	 * Método para añadir contactos
	 * @param Introducimos como parámetro un contacto
	 */
	public void añadirContacto(Contacto con) {
		this.contactos = new TreeSet<Contacto>();
		char c = con.getPrimeraLetra();
		if (agenda.isEmpty()) {
			contactos.add(con);
			agenda.put(c, contactos);
		} else {
			try {// Hemos hecho un Try Catch por si daba error a la hora de comprobar en el Test
				if (agenda.containsKey(c)) {
					contactos = agenda.get(c);
					contactos.add(con);
					agenda.put(c, contactos);
				} else {
					contactos.add(con);
					agenda.put(c, contactos);
				}
			} catch (Exception e) {
				System.out.println("Error al añadir contacto");
			}
		}

	}

	/**
	 * Método que nos devolverá el número de contactos que tiene una letra
	 * @param Introducimos un carácter 
	 * @return Devuelve un int con el numero de contactos de una letra
	 */
	public int contactosEnLetra(char letra) {
		int contador = 0;
		for (Character key : agenda.keySet()) {
			if (key.equals(letra)) {
				contador = agenda.get(key).size();
			}
		}
		return contador;
	}

	/**
	 * Método que nos devolverá el número de contactos de la agenda
	 * @return Devuelve un int con el numero de contactos de la agenda
	 */
	public int totalContactos() {
		int contador = 0;
		for (Set<Contacto> c : agenda.values()) {
			for (Contacto con : c) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Metodo para obtener la representación textual de la agenda
	 * @return Devuelve un String con la representación textual de la agenda
	 */
	@Override
	public String toString() {
		String salida = "";
		salida += "AGENDA DE CONTACTOS\n";
		for (Character clave : agenda.keySet()) {
			salida += clave + " (" + contactosEnLetra(clave) + ")\n---------------\n";
			Iterator<Contacto> it = agenda.get(clave).iterator();
			while (it.hasNext()) {
				salida += it.next().toString() + "\n";
			}
		}
		salida += "(" + totalContactos() + " contactos)";
		return salida;
	}

	/**
	 * Método que devuelve un List con los contactos encontrados tras hacer un filtrado de una cadena
	 * @param Introducimos un String para comparar con los contactos de la agenda
	 * @return Devuelve un List con los contactos
	 */
	public List<Contacto> buscarContactos(String texto) {
		texto = texto.toUpperCase();
		List<Contacto> salida = new ArrayList();
		Set<Contacto> posicion = new TreeSet();
		Set<Map.Entry<Character, Set<Contacto>>> evento = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = evento.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> entrada = it.next();
			posicion = entrada.getValue();
			for (Contacto contacto : posicion) {
				String nombre = contacto.getNombre();
				String apellido = contacto.getApellidos();
				int resultado = nombre.indexOf(texto);
				int resultadoo = apellido.indexOf(texto);
				if (resultado != -1 || resultadoo != -1) {
					salida.add(contacto);
				}
			}
		}
		return salida;

	}

	/**
	 * Devuelve los contactos que hay en una letra de la agenda
	 * @param Introducimos un caracter 
	 * @return Devuelve un List con los contactos en la letra que hemos introducido como parámetro
	 */
	public List<Personal> personalesEnLetra(char letra) {
		char comp = Character.toUpperCase(letra);
		List<Personal> personales = new ArrayList();
		Set<Contacto> contactos = new TreeSet();
		Set<Map.Entry<Character, Set<Contacto>>> evento = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = evento.iterator();
		for (int i = 0; i < agenda.size(); i++) {
			Map.Entry<Character, Set<Contacto>> entrada = it.next();
			if (entrada.getKey().equals(comp)) {
				contactos = agenda.get(comp);
				Iterator<Contacto> iit = contactos.iterator();
				while (iit.hasNext()) {
					Contacto contacto = iit.next();
					if (contacto instanceof Personal) {
						Personal si = (Personal) contacto;
						personales.add(si);
					}
				}

			}
		}

		return personales;
	}

	/**
	 * Felicitará a los contactos que coincida su fecha de nacimiento con la fecha actual
	 * @return Devuelve los contactos cumpleañeros
	 */
	public List<Personal> felicitar() {
		List<Personal> personales = new ArrayList();
		Set<Contacto> posicion = new TreeSet();
		Set<Map.Entry<Character, Set<Contacto>>> evento = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = evento.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> entrada = it.next();
			posicion = entrada.getValue();
			for (int j = 0; j < posicion.size(); j++) {
				Iterator<Contacto> iit = posicion.iterator();
				while (iit.hasNext()) {
					Contacto salida = iit.next();
					if (salida instanceof Personal) {
						Personal p = (Personal) salida;
						if ((p.esCumpleaños())) {
							personales.add(p);
						}
					}
				}
			}

		}
		return personales;

	}

	/**
	 * Devuelve la relación que tienen los contactos con un contacto
	 * @return Devuelve las relaciones de cada contacto
	 */
	public Map<Relacion, List<String>> personalesPorRelacion() {
		Set<Contacto> posicion = new TreeSet();
		TreeMap<Relacion, List<String>> cambio = new TreeMap();
		Set<Map.Entry<Character, Set<Contacto>>> evento = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = evento.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> entrada = it.next();
			posicion = entrada.getValue();
			Iterator<Contacto> iit = posicion.iterator();
			while (iit.hasNext()) {
				Contacto salida = iit.next();
				if (salida instanceof Personal) {
					List<String> valor = new ArrayList();
					Personal p = (Personal) salida;
					String cadena = p.getNombre() + " " + p.getApellidos();
					;
					Relacion relacion = p.getRelacion();
					valor = new ArrayList();
					;
					if (cambio.isEmpty()) {
						valor.add(cadena);
						cambio.put(relacion, valor);
					} else {
						if (cambio.containsKey(relacion)) {
							valor.clear();
							;
							valor = cambio.get(relacion);
							valor.add(cadena);
							cambio.put(relacion, valor);
						} else {
							valor.clear();
							valor.add(cadena);
							cambio.put(relacion, valor);
						}
					}
				}
			}
		}
		return cambio;

	}

	/**
	 * Método que ordena por fecha de nacimiento los contactos en una letra de la agenda
	 * @param Introducimos un caracter para realizar la busqueda en la agenda
	 * @return Devuelve un List de los contactos ordenados cuya letra de su apellido coincide con la letra que pasamos como parámetro
	 */
	public List<Personal> personalesOrdenadosPorFechaNacimiento(Character letra) {

		List<Personal> orden = new ArrayList<Personal>();
		orden = personalesEnLetra(letra);
		// Si no hay contactos en la letra elegida, devuelve el mensaje.
		if (personalesEnLetra(letra).size() == 0) {
			System.out.println(letra + " No está en la agenda o bien es contacto PROFESIONAL.");
		}

		Collections.sort(orden, new Comparator<Personal>() {
			@Override
			public int compare(Personal p1, Personal p2) {
				if (p1.getFecha().isBefore(p2.getFecha())) {
					return -1;
				}
				return 1;
			}
		});
		return orden;
	}

}

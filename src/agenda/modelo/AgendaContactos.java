package agenda.modelo;
import java.util.*;

/*
 *  @Author - Iñaki T, Unai P, Enrique L 
 * */
public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;
	private Set<Contacto> contactos; 
	public AgendaContactos() {
		agenda = new TreeMap<Character, Set<Contacto>>();
	}

	public void añadirContacto(Contacto con) {
		contactos = new TreeSet();
		char inicial = con.getPrimeraLetra();
		if(agenda.isEmpty()) {
			contactos.add(con);
			agenda.put(inicial, contactos);
		}else {
			if(agenda.containsKey(inicial)){
				contactos.clear();;
				contactos = agenda.get(inicial);
				contactos.add(con);
				agenda.put(inicial, contactos);
			}else {
				contactos.clear();
				contactos.add(con);
			agenda.put(inicial, contactos);}
		}

	}
	public int contactosEnLetra(char letra) {
		int contador = 0;
		for(Character key:agenda.keySet()) {
			if(key.equals(letra)) {
				contador = agenda.get(key).size();
			}
		}
		return contador;
	}

	public void totalContactos() {
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        while(it.hasNext()) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	int num =entrada.getValue().size();
        	int suma = 0;
        	suma = suma + num;
        }
	}

	@Override
	public String toString() {
		
		String salida ="";
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        while(it.hasNext()) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	int num = entrada.getValue().size();
        	salida = salida + entrada.getKey() +" ("+ num +") "+"\n"+ entrada.getValue() + "\n";
        }
        return salida;
	}

	public List<Contacto> buscarContactos(String texto) {
		texto = texto.toUpperCase();
		List<Contacto> salida = new ArrayList();
		Set<Contacto> posicion= new TreeSet();
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        while(it.hasNext()) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	posicion = entrada.getValue();
        	for(Contacto contacto : posicion) {
        		String nombre = contacto.getNombre();
        		String apellido = contacto.getApellidos();
        		int resultado = nombre.indexOf(texto);
        		int resultadoo = apellido.indexOf(texto);
        		if(resultado != -1 || resultadoo != -1) {
        			salida.add(contacto);
        		}
        		}
        	}       
		return salida;

	}

	public List<Personal> personalesEnLetra(char letra) {
		char comp = Character.toUpperCase(letra);		
		List<Personal> personales = new ArrayList();
		Set<Contacto> contactos = new TreeSet();
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        for(int i=0;i<agenda.size();i++) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	if(entrada.getKey().equals(comp)) {
        		contactos = agenda.get(comp);
        		Iterator<Contacto> iit = contactos.iterator();
        		while(iit.hasNext()) {
        			Contacto contacto = iit.next();
        			if(contacto instanceof Personal) {
        				Personal si = (Personal) contacto;
        				personales.add(si);
        			}
        		}
        		
        	}
        }


	
        return personales;
	}

	public List<Personal> felicitar() {
		List<Personal> personales = new ArrayList();
		Set<Contacto> posicion= new TreeSet();
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        while(it.hasNext()) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	posicion = entrada.getValue();
        		for(int j = 0;j<posicion.size();j++) {
            		Iterator<Contacto> iit = posicion.iterator();
            		while(iit.hasNext()) {
            			Contacto salida = iit.next();
            			if(salida instanceof Personal) {
            				Personal p = (Personal) salida;
            				if((p.esCumpleaños())) {
            					personales.add(p);
            					}
            				}
            			}
        	}
        	
        }
		return personales;
		
	}

	public Map<Relacion,List<String>> personalesPorRelacion() {
		Set<Contacto> posicion= new TreeSet();
		TreeMap<Relacion, List<String>> cambio  = new TreeMap();
		Set<Map.Entry<Character,Set<Contacto>>> evento = agenda.entrySet();
        Iterator<Map.Entry<Character,Set<Contacto>>> it = evento.iterator();
        while(it.hasNext()) {
        	Map.Entry<Character,Set<Contacto>> entrada = it.next();	
        	posicion = entrada.getValue();
        		Iterator<Contacto> iit = posicion.iterator();
        		while(iit.hasNext()) {
        			Contacto salida = iit.next();
        			if(salida instanceof Personal) {
        				List<String> valor = new ArrayList();
        				Personal p = (Personal) salida;
        				String cadena = p.getNombre() + " " +p.getApellidos();;
        				Relacion relacion = p.getRelacion();
        				valor = new ArrayList();;
        				if(cambio.isEmpty()) {
        					valor.add(cadena);
        					cambio.put(relacion, valor);
        				}else {
        					if(cambio.containsKey(relacion)){
        						valor.clear();;
        						valor = cambio.get(relacion);
        						valor.add(cadena);
        						cambio.put(relacion, valor);
        					}else {
        						valor.clear();
        						valor.add(cadena);
        					cambio.put(relacion, valor);}
        				}
        				}
        			}       	
        }
        return cambio;
		
	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(Character letra) {
		List<Personal> orden = new ArrayList<Personal>();
		orden = personalesEnLetra(letra);
		Collections.sort(orden);
		return orden;
	}

}

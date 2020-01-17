package DataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class HomeApplianceStore {
	
	String rutaFichero = System.getProperty("user.home") + "/Desktop/stock.dat";
	
	public void menu() {
		boolean menuStatus = false;
		
		Scanner sc = new Scanner(System.in);		
		
		while(!menuStatus) {
			System.out.println("========================================="
					+ "\n Bienvenido a la tienda el Tiendin, Que acci�n quiere realizar? \n 1- Guardar nuevo producto \n 2- Abrir un fichero stock.dat \n 3- Mostrar el registro \n 4- Vender producto \n 5- Salir \n"
					+ "=========================================");
			var respuesta = sc.nextInt();
			
			switch(respuesta) {
			case 1:
				modificarFichero();
				break;
				
			case 2:
				leerFichero();
				break;
				
			case 3:
				mostrarRegistro();
				break;
				
			case 4:
				venderProducto();
				break;
				
			case 5:
				menuStatus = true;
				break;
			
			default:
				System.out.println("El n�mero introducido es incorrecto, por favor vuelva a introducirlo\n");
			}
		}
	}
	
	public void modificarFichero() {
		try {
			crearFichero();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		Scanner strings = new Scanner(System.in);
		Scanner ints = new Scanner(System.in);	
		
		String nombreProducto;
		String tipoProducto;
		String fabricante;
		String precioUnitario;
		int cantidad = 0;
		
		System.out.println("Introduce la informaci�n del producto \n");
		
		System.out.println("Nombre del producto");
		nombreProducto = strings.nextLine();
		
		System.out.println("\nTipo del producto");
		tipoProducto = strings.nextLine();
		
		System.out.println("\nFabricante del producto");
		fabricante = strings.nextLine();
		
		System.out.println("\nQue cantidad del producto quiere a�adir ?");
		cantidad = ints.nextInt();
		
		System.out.println("\nCual es el precio unitario ?");
		precioUnitario = strings.nextLine();
		
		LocalDateTime fecha = LocalDateTime.now();
		
		try {
		
		BufferedWriter escribir = new BufferedWriter(new FileWriter(rutaFichero, true));
		escribir.write(nombreProducto + "," + tipoProducto + "," + fabricante + "," + precioUnitario + "," + cantidad + "," + fecha + "\n");
		escribir.close();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void leerFichero() {
		Scanner rt = new Scanner(System.in);
		
		System.out.println("Introduce la ruta del fichero que quieres leer");
		rutaFichero = rt.nextLine();
		boolean valido = comprobarEstructura(rutaFichero);
		if(!valido) {
			System.out.println("El fichero especificado no cumple con los parametros requeridos\n");
			rutaFichero = "D:\\DAM\\DA\\stock.dat";
		}
		else {
			System.out.println("El fichero se ha cargado correctamente\n");
		}
		
	}
	
	public void mostrarRegistro() {
		var separador = ",";
		String linea = "";
		String [] auxiliar = null;
		ArrayList<String> Articulos = new ArrayList<String>();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
			linea = br.readLine();
			
			while(linea != null) {
				linea = br.readLine();
				if(linea == null) {
					break;
				}
				auxiliar = linea.split(separador);
				Articulos.add(auxiliar[0] + ", " +  auxiliar[3] + ", " + auxiliar[4]);
			}		
			System.out.println("Nombre, Precio, Cantidad");
			Articulos.stream().forEach((p)->{
				System.out.println(p);
			});
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public void venderProducto(){ //Revisar la parte final ya que en el caso de que no quede stock y no queramos inroducir mas da un nullPointerExcdeption
		Scanner sc = new Scanner(System.in);
		String separador = ",";
		String producto = "";
		String cantidad = "";
		String cantidadPrevia = "";
		String linea = "";
		String respuesta = "";
		int stock = 0;
		String[] afirmativos = {"SI", "Si", "si", "s"};
		String [] auxiliar = null;
		List<String> lines = new ArrayList<String>();	
		
		System.out.print("Que producto va a vender\n");
		producto = sc.nextLine();
		
		boolean existe = productoExiste(producto); // Se comprueba si el peoducto existe en el stock.dat
		if(existe) {
			stock = Integer.parseInt(revisarStock(producto)); //Comprobamos el stock que queda del determinado producto
			
			if(stock == 0) {
				System.out.println("No queda stock de este producto, �quiere solicitarle mas al proveedeor ?");
				respuesta = sc.nextLine();
				for(int i = 0; i < afirmativos.length; i++) {
					if(respuesta.equals(afirmativos[i])) {
						añadirStock(producto);
					}
				}
				
			}
			else if(stock <= 5) {
				System.out.println("El stock de este producto es bajo, por favor solicite m�s al proveedor");
				System.out.println("�Quiere solicitar m�s stock al proveedor?");
				respuesta = sc.nextLine();
				for(int i = 0; i< afirmativos.length; i++) {
					if(respuesta.equals(afirmativos[i])) {
						añadirStock(producto);
					}
				}
			}
			else{				
				try {
				System.out.println("Cuantas unidades quiere vender ?");
				cantidad = sc.nextLine();
				}
				catch(Exception e) {
					System.out.println("El valor introducido es incorrecto");
				}
				
				try {
					cantidadPrevia = revisarStock(producto); //Obtenemos la cantidad que tenemos en stock en formato String para despues poder modificar el fichero
					int total = stock - Integer.parseInt(cantidad); //Calculamos el stock que nos quedar� despues de la venta. Para ello casteamos el valor que nos introduce el usuario de String a int para poder operar con el y con el valor que tenemos en el fichero que previamente hemos casteado a int
					String totalString = Integer.toString(total); // Casteamos el resultado a String para poder escribirlo en el fichero
					
					if(Integer.parseInt(totalString) >= 0) {
			            File fichero = new File(rutaFichero);
			            FileReader fr = new FileReader(fichero);
			            BufferedReader br = new BufferedReader(fr);
			            while ((linea = br.readLine()) != null) {
			                if (linea.contains(producto)) {
			                	auxiliar = linea.split(separador);
			                	auxiliar[4] = totalString;
			                	linea = (auxiliar[0] + "," + auxiliar[1] + "," + auxiliar[2]+ "," + auxiliar[3] + "," + auxiliar[4] + "," + auxiliar[5]);
			                }	                    
			                lines.add(linea);
			            }

			            FileWriter fw = new FileWriter(fichero);
			            BufferedWriter out = new BufferedWriter(fw);
			            for(String s : lines)
			                 out.write(s + "\n");
			            out.flush();
			            out.close();
					}	
					else {
						System.out.println("No queda stock suficiente como para realizar la venta solicitada, quiere solicitar m�s stock al proveedor ?");
						respuesta = sc.nextLine();
						for(int i = 0; i < afirmativos.length; i++) {
							if(respuesta.contains(afirmativos[i])) {
								añadirStock(producto);
							}
							else {
								System.out.println("Venta no realizada");
							}
						}
					}
					String totalFactura = calcularTotal(cantidad, auxiliar[3]);
					if(Integer.parseInt(cantidad) > 0) {
						crearFactura(producto, cantidad, auxiliar[3], totalFactura);
					}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		}
		else {
			System.out.println("El producto especificado no existe\n");
		}
	}
	
	public String revisarStock(String producto) {
		String linea = "";
		var separador = ",";
		String stock = "";
		String [] auxiliar = null;
		ArrayList<String> Articulos = new ArrayList<String>();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
			linea = br.readLine();
			
			while(linea != null) {
				linea = br.readLine();
				if(linea == null) {
					break;
				}
				if(linea.contains(producto)) {
					auxiliar = linea.split(separador);
					stock = auxiliar[4];
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return stock;
	}
	
	public void crearCarpeta() throws IOException { //Esto es para la creaci�n de las facturas, aqui se crear� la carpeta donde se guarden 

		String rutaCarpeta = System.getProperty("user.home") + "/Desktop/Facturas";//En esta ruta ya esta incluida la carpeta que queremos crear
		
		Path path = Paths.get(rutaCarpeta);  //Le pasamos a Path la ruta que queremos crear
		
		if(!Files.exists(path)) {
			Files.createDirectory(path);  //Mediante el metodo createDirectory de Files y la ruta que le pasamos se crea el directorio
		}
	}
	
	public void crearFichero() throws IOException{
		
		File fichero = new File(rutaFichero); //Le pasamos a File la ruta donde queremos crear el fichero
		
		if(!fichero.exists()) {
			System.out.println("Se ha generado el fichero\n");
			fichero.createNewFile(); // Esta linea no es necesaria ya que en la siguiente fila al intentar escribir en el fichero este se genera. Pero esta linea facilita la comprension del code
			BufferedWriter cabecera = new BufferedWriter(new FileWriter(rutaFichero));
			cabecera.write("ApplianceName,ApplianceType,Manufacturer,UnityPrice,Quantity,LastDayRegistry \n");
			cabecera.close();
		}	
	}
	
	public void crearFactura(String producto, String cantidad, String precio, String total) throws IOException{
		boolean valido = false;
		int i = 1;
		try {
			crearCarpeta();
		} catch (IOException e) {
			System.out.println(e);
		}	
		while(!valido) {			
			String nombreFactura = "Factura" + Integer.toString(i) + ".dat";
			String nuevaRuta = System.getProperty("user.home") + "/Desktop/Facturas" + File.separator + nombreFactura;
			
			File fichero = new File(nuevaRuta); //Le pasamos a File la ruta donde queremos crear el fichero
			
			boolean creado = fichero.createNewFile();
		       if (creado) {
		           System.out.println("\nSe ha generado el fichero " + nombreFactura);
		           BufferedWriter cabecera = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/Desktop/Facturas" + File.separator + nombreFactura));
		           cabecera.write("Tienda El Tiendin \n");
		           cabecera.close();
		           BufferedWriter escribir = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/Desktop/Facturas" + File.separator + nombreFactura, true));
		           escribir.write("Producto vendido => " + producto + "\n" + "Cantidad Vendida => " + cantidad + "\n" + "Precio unitario => " + precio + "\n" + "Total a pagar => " + total);
		           escribir.close();
		           valido = true;
		                   
		       } else {
		           i++;
		       }
		}
	}
	
	public void añadirStock(String producto) {
		Scanner sc = new Scanner(System.in);
		String separador = ",";
		String cantidad = "";
		String cantidadPrevia = "";
		String linea = "";
		String respuesta = "";
		int stock = Integer.parseInt(revisarStock(producto));
		String[] afirmativos = {"SI", "Si", "si", "s"};
		String[] auxiliar = null;
		List<String> lines = new ArrayList<String>();	
		
		try {
			System.out.println("Cuantas unidades quiere a�adir ?");
			cantidad = sc.nextLine();
			}
			catch(Exception e) {
				System.out.println("El valor introducido es incorrecto");
			}
			
			try {
				cantidadPrevia = revisarStock(producto); //Obtenemos la cantidad que tenemos en stock en formato String para despues poder modificar el fichero
				int total = stock + Integer.parseInt(cantidad); //Calculamos el stock que nos quedar� despues de la venta. Para ello casteamos el valor que nos introduce el usuario de String a int para poder operar con el y con el valor que tenemos en el fichero que previamente hemos casteado a int
				String totalString = Integer.toString(total); // Casteamos el resultado a String para poder escrivirlo en el fichero
				
	            File fichero = new File(rutaFichero);
	            FileReader fr = new FileReader(fichero);
	            BufferedReader br = new BufferedReader(fr);
	            while ((linea = br.readLine()) != null) {
	                if (linea.contains(producto)) {
	                	auxiliar = linea.split(separador);
	                	auxiliar[4] = totalString; //Modificamos el valor del stock
	                	linea = (auxiliar[0] + "," + auxiliar[1] + "," + auxiliar[2]+ "," + auxiliar[3] + "," + auxiliar[4] + "," + auxiliar[5]); //Reconstruimos la linea con el valor modificado
	                }	                    
	                lines.add(linea);
	            }

	            FileWriter fw = new FileWriter(fichero);
	            BufferedWriter out = new BufferedWriter(fw);
	            for(String s : lines)
	                 out.write(s + "\n");
	            out.flush();
	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }		
	}
	
	public String calcularTotal(String vendidos, String precioUnitario) {
		int unidadesVendidas = Integer.parseInt(vendidos);
		int precioUnidad = Integer.parseInt(precioUnitario);
		
		int total = precioUnidad * unidadesVendidas;
		String totalString = Integer.toString(total);
		
		return totalString;
	}
	
	public boolean productoExiste(String producto) {
		var separador = ",";
		String linea = "";
		boolean existe = false;
		String [] auxiliar = null;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
			linea = br.readLine();
			
			while(linea != null) {
				linea = br.readLine();
				if(linea == null) {
					break;
				}
				auxiliar = linea.split(separador);
			}
			if(auxiliar[0].contains(producto)) {
				existe = true;
			}
			else {
				existe = false;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return existe;
	}
	
	public boolean comprobarEstructura(String ruta) {
		String ejemplo = "ApplianceName,ApplianceType,Manufacturer,UnityPrice,Quantity,LastDayRegistry";
		boolean valido = false;
		
		try {
			BufferedReader leer = new BufferedReader(new FileReader(ruta));
			String linea = leer.readLine();
			String lineaSinEOL = linea.replace("\n", ""); // Se le quita el End Of Line a la linea que hemos leido para que conicida con la de ejemplo
			if(linea.equals(lineaSinEOL)) {
				valido = true;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return valido;
	}
	
	public static void main (String[] args) {
		HomeApplianceStore tienda = new HomeApplianceStore();
		tienda.menu();
	}
}
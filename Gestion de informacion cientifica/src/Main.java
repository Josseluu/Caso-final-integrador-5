import java.io.*;
import java.text.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        organizarDocumentos("documento.txt", "documento_ordenado.txt");
        buscarPalabra("documento.txt", "palabra");
        gestionarFechas();
    }


    public static void organizarDocumentos(String archivoEntrada, String archivoSalida) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            List<String> lineas = new ArrayList<>();
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
            lector.close();
            Collections.sort(lineas);
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida));
            for (String l : lineas) {
                escritor.write(l);
                escritor.newLine();
            }
            escritor.close();
            System.out.println("El archivo ha sido ordenado alfabéticamente correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void buscarPalabra(String archivo, String palabra) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;
            boolean encontrada = false;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(palabra)) {
                    System.out.println("Palabra encontrada en la línea " + numeroLinea + ": " + linea);
                    encontrada = true;
                }
                numeroLinea++;
            }
            lector.close();
            if (!encontrada) {
                System.out.println("La palabra no se encontró en el documento.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void buscarPalabraBinaria(String archivoOrdenado, String palabra) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrdenado));
            List<String> lineas = new ArrayList<>();
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
            lector.close();
            int inicio = 0;
            int fin = lineas.size() - 1;
            boolean encontrada = false;
            while (inicio <= fin) {
                int medio = inicio + (fin - inicio) / 2;
                if (lineas.get(medio).contains(palabra)) {
                    System.out.println("Palabra encontrada en la línea " + (medio + 1) + ": " + lineas.get(medio));
                    encontrada = true;
                    break;
                } else if (lineas.get(medio).compareTo(palabra) < 0) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            }
            if (!encontrada) {
                System.out.println("La palabra no se encontró en el documento.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void gestionarFechas() {
        List<Date> fechas = new ArrayList<>();
        agregarFecha(fechas, "2023-01-15");
        agregarFecha(fechas, "2022-12-25");
        agregarFecha(fechas, "2024-03-10");
        listarFechasOrdenadas(fechas);
    }

    public static void agregarFecha(List<Date> fechas, String fechaStr) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(fechaStr);
            fechas.add(fecha);
            System.out.println("Fecha agregada correctamente: " + fechaStr);
        } catch (ParseException e) {
            System.out.println("Error al agregar la fecha. Asegúrate de que esté en el formato yyyy-MM-dd.");
        }
    }


    public static void listarFechasOrdenadas(List<Date> fechas) {
        if (fechas.isEmpty()) {
            System.out.println("No hay fechas para listar.");
            return;
        }
        Collections.sort(fechas);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fechas listadas ordenadamente:");
        for (Date fecha : fechas) {
            System.out.println(formato.format(fecha));
        }
    }
}

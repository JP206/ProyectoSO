package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class ManejadorArchivosGenerico {

    /**
     * @param nombreCompletoArchivo
     * @param listaLineasArchivo lista con las lineas del archivo
     * @throws IOException
     */
    public static void escribirArchivo(String nombreCompletoArchivo,
            String[] listaLineasArchivo) {
            FileWriter fw;
            try {
                fw = new FileWriter(nombreCompletoArchivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < listaLineasArchivo.length; i++) {
                    String lineaActual = listaLineasArchivo[i];
                    
                    bw.write(lineaActual);
                    bw.newLine();
                }
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo "
                        + nombreCompletoArchivo);
                e.printStackTrace();
            }
    }

    public static String[] leerArchivo(String nombreCompletoArchivo){
		FileReader fr;
		ArrayList<String> listaLineasArchivo = new ArrayList<String>();
		try {
			fr = new FileReader(nombreCompletoArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual = br.readLine();
			while (lineaActual != null){
				listaLineasArchivo.add(lineaActual);
				lineaActual = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		}
		System.out.println("Archivo leido satisfactoriamente");

		return listaLineasArchivo.toArray (new String [0]);
	}
    public static void escribirArchivoCoded(String nombreCompletoArchivo,
            String[] listaLineasArchivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(nombreCompletoArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++) {
                String lineaActual = listaLineasArchivo[i];
                String encodedString = Base64.getEncoder().encodeToString(lineaActual.getBytes());
                bw.newLine();
                bw.write(encodedString);
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        }
    }

    public static String[] leerArchivoCoded(String nombreCompletoArchivo){
		FileReader fr;
		ArrayList<String> listaLineasArchivo = new ArrayList<String>();
		try {
			fr = new FileReader(nombreCompletoArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual = br.readLine();
			while (lineaActual != null){
                                byte[] decodedBytes = Base64.getDecoder().decode(lineaActual);
                                String decodedString = new String(decodedBytes);
				listaLineasArchivo.add(decodedString);
				lineaActual = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		}
		System.out.println("Archivo leido satisfactoriamente");

		return listaLineasArchivo.toArray (new String [0]);
	}
}

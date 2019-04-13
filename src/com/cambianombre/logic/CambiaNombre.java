package com.cambianombre.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cambianombre.domain.Archivo;

public class CambiaNombre {

	private static final char POINT_CHART = '.';
	final static Logger logger = LogManager.getLogger(CambiaNombre.class.getName());
	static CambiaNombre cambiaNombre = new CambiaNombre();

	public static void main(String[] args) {
		System.out.println("INICIO");
		String opcion = "subcarpetas";

		String origin = "C:\\Users\\Hernan\\Desktop\\CDs\\anime";
		String destiny = "C:\\Users\\Hernan\\Desktop\\CDs\\anime\\Nueva carpeta";
		String filters = "mp4,mpeg,mpg,avi,web,dat,wm,mp3,jpg,JPG";
		List<Archivo> lstArchivos = new ArrayList<Archivo>();

		System.out.println(lstArchivos);
		if (opcion.equalsIgnoreCase("subcarpetas")) {
			lstArchivos = cambiaNombre.readFile(origin, destiny, Boolean.TRUE);
			System.out.println("Son " + lstArchivos.size() + " archivos");
			int index = 0;
			for (Archivo archivo : lstArchivos) {
				if (!archivo.getExtension().equalsIgnoreCase("jpg")) {
					System.out.println("Copiando archivo numero: " + (index++) + "  , nombre: " + archivo.getEndName());
					cambiaNombre.copyFiles(archivo.getOriginSource() + "\\" + archivo.getInitialName(),
							archivo.getDestinySource() + "\\" + archivo.getEndName());
				}
			}
			System.out.println(lstArchivos);
			System.out.println("Archivos: " + lstArchivos.size());
		} else {
			lstArchivos = cambiaNombre.readFile(origin, destiny, Boolean.FALSE );
			Map<String, String> mapArchivos = new TreeMap<String, String>();
			for (Archivo a : lstArchivos) {
				mapArchivos.put(a.getEndName(), a.getLength() + "\t" + a.getExtension().toUpperCase());
			}
			printMap(mapArchivos);
		}

		System.out.println("TERMINO");
	}

	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " \t" + entry.getValue());
		}
	}

	/**
	 * 
	 * @param origin
	 * @param destiny
	 * @return
	 */
	public List<Archivo> readFile(String origin, String destiny, boolean searchSubDirectory) {
		List<Archivo> lstArchivo = new ArrayList<Archivo>();
		File lstFile = new File(origin);
		File[] arrFile = lstFile.listFiles();
		DecimalFormat df = new DecimalFormat("#.##");
		int season = 1;
		int episode = 1;
		for (File file : arrFile) {

			if (file.isDirectory() && searchSubDirectory) {
				lstArchivo.addAll(readFile(file.getPath(), destiny, Boolean.TRUE));
			} else {
				Archivo archivo = new Archivo();
				archivo.setInitialName(file.getName());
				archivo.setOriginSource(origin);
				archivo.setDestinySource(destiny);
				archivo.setEndName(obtainEndName(file.getName(), episode, season));
				archivo.setExtension(obtainExtension(file.getName()));
				archivo.setLength(df.format((file.length() / 1024000)));
				lstArchivo.add(archivo);
			}
			episode++;
		}
		return lstArchivo;
	}

	/**
	 * 
	 * @param nameFile
	 * @return
	 */
	private String obtainExtension(String nameFile) {
		String extension = "";
		int pointPosition = nameFile.lastIndexOf(POINT_CHART);
		if (pointPosition > -1) {
			extension = nameFile.substring(pointPosition + 1);
		} else {
			extension = "no-extension";
		}
		return extension;
	}

	/**
	 * 
	 * @param initialName
	 * @return
	 */
	private String obtainEndName(String initialName, int episode, int season) {

		String endName = "";
		int pointPosition = initialName.lastIndexOf(POINT_CHART);
		if (pointPosition > -1) {

//			endName = initialName.substring(0, pointPosition) + "[" + initialName.substring(pointPosition + 1)
//					+ "].txt";
			String newName = replaceName(initialName);
			endName = newName + "[" + season + "X" + episode + "][" + initialName.substring(pointPosition + 1)
					+ "].txt";
		} else {
			endName = initialName + "[no-extension].txt";
		}
		return endName;
	}

	private String replaceName(String initialName) {
		String newName = "";
		initialName.replaceAll("[a|A]", "4").replaceAll("e|E", "3").replaceAll("i|I", "1").replaceAll("o|O", "0");
		return newName;
	}

	/**
	 * 
	 * @param origin
	 * @param destiny
	 */
	public void copyFiles(String origin, String destiny) {
		try {
			Path originPath = Paths.get(origin);
			Path destinyPath = Paths.get(destiny);

			Files.copy(originPath, destinyPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

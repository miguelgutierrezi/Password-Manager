/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Cuenta;
import model.Usuario;

/**
 * @author migue
 *
 */
public class Utils {
	public static String getHash(String txt, String hashType) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<Usuario> readUsers() {
		String cadena;
		Usuario u = null;
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		String datos[];

		try {
			FileReader f = new FileReader(Paths.get("InfoUsuarios").toString());
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				if (cadena.equals("Usuario")) {

					cadena = b.readLine();
					datos = cadena.split(";");
					String user = datos[0];
					String pass = datos[1];

					u = new Usuario(user, pass);

				} else if (cadena.equals("Cuenta")) {
					cadena = b.readLine();
					System.out.println(cadena);
					while (!cadena.equals("#")) {

						datos = cadena.split(";");
						String nombre = datos[0];
						String url = datos[1];
						String user = datos[2];
						String pass = datos[3];
						Cuenta account = new Cuenta(nombre, url, user, pass, LocalDate.now());
						u.getCuentas().add(account);

						cadena = b.readLine();

					}

					users.add(u);
					u = null;
				}
			}
			b.close();
		} catch (FileNotFoundException ex) {

			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return users;
	}
}

/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Key;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import model.Cuenta;
import model.Usuario;

/**
 * @author migue
 *
 */
public class Utils {

	// Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
	private final static String alg = "AES";
	// Definición del modo de cifrado a utilizar
	private final static String cI = "AES/CBC/PKCS5Padding";

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

	public static String encrypt(String key, String iv, String cleartext) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(cleartext.getBytes());
		return new String(encodeBase64(encrypted));
	}

	public static String decrypt(String key, String iv, String encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance(cI);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		byte[] enc = decodeBase64(encrypted);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] decrypted = cipher.doFinal(enc);
		return new String(decrypted);
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

	public static ArrayList<Usuario> leerTodosUsuarios() {
		MongoConnection conn = new MongoConnection();
		Gson gson = new GsonBuilder().create();
		System.out.println("ServicioMongo.leerTodosUsuario()");
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		MongoCollection<Document> documents = conn.findCollection("Users");
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				users.add(gson.fromJson(cursor.next().toJson(), Usuario.class));
			}
		}
		return users;
	}

	public static Usuario crearUsuario(Usuario user) {
		MongoConnection conn = new MongoConnection();
		Gson gson = new GsonBuilder().create();
		System.out.println("ServicioMongo.crearUsuario()");
		// TODO Auto-generated method stub
		String temp = gson.toJson(user);
		Document doc = Document.parse(temp);
		conn.insertObject("Users", doc);
		return user;
	}

	public static Usuario actualizarUsuario(Usuario user) {
		System.out.println("ServicioMongo.actualizarUsuario()");
		MongoConnection conn = new MongoConnection();
		MongoCollection<Document> collection = conn.findCollection("Users");

		BasicDBObject doc = new BasicDBObject();
		doc.put("user", user.getUser());
		collection.deleteOne(doc);

		Gson gson = new GsonBuilder().create();
		String temp = gson.toJson(user);
		Document d = Document.parse(temp);
		conn.insertObject("Users", d);

		return user;
	}

	public static Boolean hasNumber(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (Character.isDigit(cadena.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static Boolean hasLowers(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (Character.isLowerCase(cadena.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static Boolean hasCapitals(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (Character.isUpperCase(cadena.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}

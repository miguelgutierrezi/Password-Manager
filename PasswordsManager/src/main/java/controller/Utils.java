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
import javax.crypto.spec.SecretKeySpec;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import model.Cuenta;
import model.Usuario;

/**
 * @author migue
 *
 */
public class Utils {

	private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();

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

	public static String encrypt(String strClearText, String strKey) throws Exception {
		String strData = "";

		try {
			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(strClearText.getBytes());
			strData = new String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}

	public static String decrypt(String strEncrypted, String strKey) throws Exception {
		String strData = "";

		try {
			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted = cipher.doFinal(strEncrypted.getBytes());
			strData = new String(decrypted);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
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
}

/**
 * 
 */
package model;

import java.time.LocalDate;

/**
 * @author migue
 *
 */
public class Cuenta {
	private String nombre;
	private String url;
	private String usuario;
	private String password;
	private LocalDate date;

	/**
	 * @param nombre
	 * @param url
	 * @param usuario
	 * @param password
	 * @param date
	 */
	public Cuenta(String nombre, String url, String usuario, String password, LocalDate date) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		this.date = date;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Cuenta [nombre=" + nombre + ", url=" + url + ", usuario=" + usuario + ", password=" + password
				+ ", date=" + date + "]";
	}
}

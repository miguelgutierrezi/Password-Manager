/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author migue
 *
 */
public class Usuario {
	private transient String _id;
	private String user;
	private String userPass;
	private ArrayList<Cuenta> cuentas;

	/**
	 * @param user
	 * @param userPass
	 */
	public Usuario(String user, String userPass) {
		super();
		this.user = user;
		this.userPass = userPass;
		this.cuentas = new ArrayList<Cuenta>();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**
	 * @return the cuentas
	 */
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", userPass=" + userPass + ", cuentas=" + cuentas + "]";
	}
}

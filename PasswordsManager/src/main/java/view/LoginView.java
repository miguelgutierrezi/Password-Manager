package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Utils;
import model.Usuario;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private ArrayList<Usuario> users = new ArrayList<Usuario>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Lato", Font.BOLD, 24));
		lblLogin.setBounds(5, 5, 424, 30);
		contentPane.add(lblLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(140, 71, 69, 14);
		contentPane.add(lblUsername);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(140, 107, 69, 14);
		contentPane.add(lblContrasea);

		textField = new JTextField();
		textField.setBounds(219, 68, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(219, 104, 86, 20);
		contentPane.add(passwordField);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean exito = false;
				users = Utils.leerTodosUsuarios();
				for (Usuario u : users) {
					if (u.getUser().equals(textField.getText())) {
						if (u.getUserPass().equals(Utils.getHash(new String(passwordField.getPassword()), "MD5"))) {
							System.out.println("Login Exitoso");
							exito = true;
							UserDetailView f = new UserDetailView(u);
							f.setVisible(true);
							setVisible(false);
						}
					}
				}
				
				if (!exito) {
					System.out.println("Usuario o contraseña incorrectos");
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
			}
		});
		btnIngresar.setBounds(163, 158, 97, 23);
		contentPane.add(btnIngresar);

		JButton btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateUserView frame = new CreateUserView();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnCrearUsuario.setBounds(163, 192, 97, 23);
		contentPane.add(btnCrearUsuario);
	}
}

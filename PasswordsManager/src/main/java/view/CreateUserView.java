package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Utils;
import model.Usuario;

public class CreateUserView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserView frame = new CreateUserView();
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
	public CreateUserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearUsuario.setFont(new Font("Lato", Font.BOLD, 24));
		lblCrearUsuario.setBounds(0, 11, 434, 30);
		contentPane.add(lblCrearUsuario);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 70, 138, 14);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a maestra");
		lblContrasea.setBounds(10, 112, 138, 14);
		contentPane.add(lblContrasea);

		textField = new JTextField();
		textField.setBounds(158, 67, 172, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblRepitaSuContrasea = new JLabel("Repita su contrase\u00F1a");
		lblRepitaSuContrasea.setBounds(10, 161, 138, 14);
		contentPane.add(lblRepitaSuContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(158, 109, 172, 20);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(158, 158, 172, 20);
		contentPane.add(passwordField_1);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario u;

				String nombre = textField.getText();
				String pass = "";
				if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
					System.out.println("Entra");
					pass = Utils.getHash(new String(passwordField.getPassword()), "MD5");
				}

				u = new Usuario(nombre, pass);
				Utils.crearUsuario(u);
			}
		});
		btnRegistrarse.setBounds(168, 209, 89, 23);
		contentPane.add(btnRegistrarse);
	}

}

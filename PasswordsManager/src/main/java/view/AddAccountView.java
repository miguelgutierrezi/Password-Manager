package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Utils;
import model.Cuenta;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddAccountView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JTextField textFieldURL;

	/**
	 * Create the frame.
	 */
	public AddAccountView(final Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAadirCuenta = new JLabel("A\u00F1adir cuenta");
		lblAadirCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAadirCuenta.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAadirCuenta.setBounds(10, 11, 414, 29);
		contentPane.add(lblAadirCuenta);
		
		JLabel lblNombreDeCuenta = new JLabel("Nombre de cuenta");
		lblNombreDeCuenta.setBounds(10, 62, 201, 14);
		contentPane.add(lblNombreDeCuenta);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(10, 87, 201, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 112, 201, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 137, 201, 14);
		contentPane.add(lblUrl);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(221, 59, 188, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(221, 84, 188, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(221, 109, 188, 20);
		contentPane.add(passwordField);
		
		textFieldURL = new JTextField();
		textFieldURL.setBounds(221, 134, 188, 20);
		contentPane.add(textFieldURL);
		textFieldURL.setColumns(10);
		
		JButton btnAadirCuenta = new JButton("A\u00F1adir cuenta");
		btnAadirCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((textFieldNombre.getText().length() != 0) && (textFieldUsuario.getText().length() != 0) && (textFieldURL.getText().length() != 0) && (passwordField.getPassword().length != 0)) {
					String nombre = textFieldNombre.getText();
					String url = textFieldURL.getText();
					String usuario = textFieldUsuario.getText();
					String password = "";
					try {
						password = Utils.encrypt(new String(passwordField.getPassword()), "Encrypt1234");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Cuenta c = new Cuenta(nombre, url, usuario, password, LocalDate.now());
					u.getCuentas().add(c);
					Utils.actualizarUsuario(u);
					UserDetailView f = null;
					try {
						f = new UserDetailView(u);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					f.setVisible(true);
					setVisible(false);
				}
				else {
					System.out.println("Faltan datos");
					JOptionPane.showMessageDialog(null, "Faltan datos");
				}
			}
		});
		btnAadirCuenta.setBounds(160, 195, 116, 23);
		contentPane.add(btnAadirCuenta);
	}

}

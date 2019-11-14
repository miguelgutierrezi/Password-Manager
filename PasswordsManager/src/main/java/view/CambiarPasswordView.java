package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Utils;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class CambiarPasswordView extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	String key = "92AE31A79FEEB2A3"; // llave
	String iv = "0123456789ABCDEF"; // vector de inicialización

	/**
	 * Create the frame.
	 */
	public CambiarPasswordView(final Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a");
		lblCambiarContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambiarContrasea.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCambiarContrasea.setBounds(10, 11, 414, 29);
		contentPane.add(lblCambiarContrasea);

		JLabel lblNewLabel = new JLabel("Ingrese nueva contrase\u00F1a");
		lblNewLabel.setBounds(10, 70, 214, 14);
		contentPane.add(lblNewLabel);

		JLabel lblRepitaNuevaContrasea = new JLabel("Repita nueva contrase\u00F1a");
		lblRepitaNuevaContrasea.setBounds(10, 131, 214, 14);
		contentPane.add(lblRepitaNuevaContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(234, 67, 150, 20);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(234, 128, 150, 20);
		contentPane.add(passwordField_1);

		JButton btnCambiarContrasea = new JButton("Cambiar contrase\u00F1a");
		btnCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((passwordField.getPassword().length < 8)
						|| !Utils.hasNumber(new String(passwordField.getPassword()))
						|| !Utils.hasLowers(new String(passwordField.getPassword()))
						|| !Utils.hasCapitals(new String(passwordField.getPassword()))) {
					System.out.println("Contraseña no valida");
					JOptionPane.showMessageDialog(null,
							"Contraseña no valida, debe tener números, mayúsculas y minusculas");
				} else {
					if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
						System.out.println("Entra");
						try {
							u.setUserPass(Utils.encrypt(key, iv, new String(passwordField.getPassword())));
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}

					Utils.actualizarUsuario(u);

					UserDetailView f = null;
					try {
						f = new UserDetailView(u);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					f.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnCambiarContrasea.setBounds(149, 190, 150, 23);
		contentPane.add(btnCambiarContrasea);
	}

}

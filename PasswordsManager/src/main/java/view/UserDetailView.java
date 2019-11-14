package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserDetailView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserDetailView(final Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido " + u.getUser());
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(10, 11, 414, 29);
		contentPane.add(lblBienvenido);
		
		JButton btnAgregarCuenta = new JButton("Agregar Cuenta");
		btnAgregarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAccountView f = new AddAccountView(u);
				f.setVisible(true);
				setVisible(false);
			}
		});
		btnAgregarCuenta.setBounds(10, 60, 109, 23);
		contentPane.add(btnAgregarCuenta);
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Utils;
import model.Cuenta;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserDetailView extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public UserDetailView(final Usuario u) throws Exception {
		System.out.println(u.get_id());
		setBounds(100, 100, 654, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 77, 578, 215);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + u.getUser());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 22, 618, 37);
		lblNewLabel.setFont(new Font("Microsoft Himalaya", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		table_1 = new JTable();
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Página", "Usuario", "Contraseña", "URL"
			}
		));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		scrollPane.setViewportView(table_1);
		
		for (Cuenta c: u.getCuentas()) {
			this.actualizarLog(c);
		}
		
		btnNewButton = new JButton("Agregar cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAccountView f = new AddAccountView(u);
				f.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(277, 303, 107, 23);
		contentPane.add(btnNewButton);
	}
	
	public void actualizarLog (Cuenta c) throws Exception {
		DefaultTableModel table = (DefaultTableModel) table_1.getModel();
		table.addRow( new Object[]{ c.getNombre(), c.getUsuario(), Utils.decrypt(c.getPassword(), "Encrypt1234"), c.getUrl()} );
	}

}

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JLabel label;
	private JPasswordField passwordField = new JPasswordField("ifpb");
	private JButton button;
	private JButton button_1;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("Senha");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(187, 46, 66, 19);
		frame.getContentPane().add(label);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(124, 80, 184, 32);
		frame.getContentPane().add(passwordField);
		
		button = new JButton("Ok");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String senhaForca=new String(passwordField.getPassword());
				if(senhaForca.equals("ifpb")) {
					label_1.setForeground(Color.GREEN);
					label_1.setText("Login com sucesso");
					AplicacaoGrafica forca=new AplicacaoGrafica();
					frame.dispose();
				}else {
					label_1.setForeground(Color.RED);
					label_1.setText("Tente novamente");
					passwordField.setText("");
					passwordField.requestFocus();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(75, 133, 121, 32);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(237, 133, 113, 32);
		frame.getContentPane().add(button_1);
		
		label_1 = new JLabel("");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(135, 196, 192, 26);
		frame.getContentPane().add(label_1);
	}
}

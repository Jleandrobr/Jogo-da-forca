import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AplicacaoGrafica {

	private JFrame frame;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JButton button;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button_1;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	
	private JogoDaForca jogo;
	ArrayList<Integer> posicoes;
	private String[] penalidades = {"1.png", "2.png","3.png","4.png","5.png","6.png","7.png","8.png"};
	String[] letrasAdivinhadas; 	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoGrafica window = new AplicacaoGrafica();
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
	public AplicacaoGrafica() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo da Forca");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("Digite uma letra:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(21, 31, 101, 25);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(132, 31, 39, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("Penalidade:  0");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(253, 214, 148, 25);
		frame.getContentPane().add(label_1);
		
		button = new JButton("Advinhar");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String letra = textField.getText().toUpperCase();
				
					letrasAdivinhadas = new String[jogo.getTamanho()];
					Arrays.fill(letrasAdivinhadas, "");
				
					posicoes = jogo.getPosicoes(letra); 
					if (posicoes.size()>0) {
						label_2.setText("Acertos: "+jogo.getAcertos());
						label_6.setText("posicoes encontradas: "+ posicoes);
						for(int i : posicoes)
							letrasAdivinhadas[i] = letra;
						label_6.setText(Arrays.toString(letrasAdivinhadas));	
					}
					else {
						label_1.setText("Penalidade: "+jogo.getPenalidade());
		
						ImageIcon icon =new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/"+ penalidades[jogo.getPenalidade()-1]));
						icon.setImage(icon.getImage().getScaledInstance(label_3.getWidth(), label_3.getHeight(), 1) );
						label_3.setIcon(icon);
					}
					
					if(jogo.terminou()) {
						label_6.setText(jogo.getResultado());
						button.setEnabled(false);
					}
					
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				if(textField.getText().isEmpty()) {
					label_6.setForeground(Color.RED);
					label_6.setText("Você não digitou nada");
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(119, 173, 114, 30);
		frame.getContentPane().add(button);
		
		label_2 = new JLabel("Acertos: 0");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(69, 214, 125, 25);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("");
		label_3.setBounds(278, 31, 148, 138);
		frame.getContentPane().add(label_3);
		ImageIcon icon =new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/0.png"));
		label_3.setIcon(icon);
		
		button_1 = new JButton("Iniciar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogo = new JogoDaForca("palavras.csv");
					textField.setText("");
					button.setEnabled(true);
					jogo.iniciar();
					label_4.setText("\nDica: " + jogo.getDica());
					label_5.setText("Tamanho: " + jogo.getTamanho()+" letras");
					label_6.setText("jogo iniciado");
					
				} catch(Exception erro) {
					label_6.setText(erro.getMessage());
				}
//				int tamanho=Integer.parseInt(label_5.getText());
//				label_5.setText(jogo.getTamanho(tamanho));
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(10, 173, 96, 30);
		frame.getContentPane().add(button_1);
		
		label_4 = new JLabel("Dica :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(21, 62, 234, 13);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("Tamanho:  ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(21, 85, 175, 13);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(21, 109, 259, 43);
		frame.getContentPane().add(label_6);
	}
}

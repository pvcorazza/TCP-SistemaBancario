package bank.ui;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class CadastroEventoPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public CadastroEventoPanel() {
		setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(73, 262, 89, 25);
		add(btnConfirmar);
		
		JLabel lblNewLabel = new JLabel("Verificar Dep\u00F3sitos");
		lblNewLabel.setBounds(136, 13, 125, 16);
		add(lblNewLabel);
		
		JButton btnRejeitar = new JButton("Rejeitar");
		btnRejeitar.setBounds(204, 262, 97, 25);
		add(btnRejeitar);
		
		textField = new JTextField();
		textField.setBounds(115, 50, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEnvelope = new JLabel("Envelope");
		lblEnvelope.setBounds(26, 53, 56, 16);
		add(lblEnvelope);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(254, 49, 97, 25);
		add(btnBuscar);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(26, 114, 56, 16);
		add(lblConta);
		
		JLabel lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setBounds(26, 143, 56, 16);
		add(lblAgencia);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(26, 172, 56, 16);
		add(lblValor);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(115, 111, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(115, 140, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(115, 169, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);

	}
}

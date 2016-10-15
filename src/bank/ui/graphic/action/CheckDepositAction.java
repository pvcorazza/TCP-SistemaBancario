package bank.ui.graphic.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import bank.business.AccountManagementService;
import bank.business.domain.Deposit;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;

/**
 * 
 **/

public class CheckDepositAction extends BankAction {

	
	private static final long serialVersionUID = 3464305499773865806L;
	
	private AccountManagementService accountManagementService;
	
	private JFormattedTextField textFieldEnvelope;
	private JTextField textFieldAccount;
	private JTextField textFieldBranch;
	private JTextField textFieldAmount;
	private Long envelope;
	private JPanel panel;


	public CheckDepositAction(BankGraphicInterface bankInterface, 
			TextManager textManager,
			AccountManagementService accountManagementService) {
		
		super(bankInterface, textManager);
		
		this.accountManagementService = accountManagementService;
		super.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME,
				textManager.getText("action.deposit.check"));
	}

	@Override
	public void execute() throws Exception {
		
		JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        
        
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(180,180,180,200));
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(73, 262, 89, 25);
		frame.add(btnConfirmar);
		
		JLabel lblNewLabel = new JLabel("Verificar Dep\u00F3sitos");
		lblNewLabel.setBounds(136, 13, 125, 16);
		frame.add(lblNewLabel);
		
		JButton btnRejeitar = new JButton("Rejeitar");
		btnRejeitar.setBounds(204, 262, 97, 25);
		frame.add(btnRejeitar);
		
		textFieldEnvelope = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		textFieldEnvelope.setBounds(115, 50, 116, 22);
		frame.add(textFieldEnvelope);
		textFieldEnvelope.setColumns(10);
		
		JLabel lblEnvelope = new JLabel("Envelope");
		lblEnvelope.setBounds(26, 53, 56, 16);
		frame.add(lblEnvelope);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(254, 49, 97, 25);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				envelope = (long) ((Number) textFieldEnvelope.getValue()).intValue();
				
				if(accountManagementService.isUsedEnvelope(envelope)){
					Deposit deposit = accountManagementService.getDeposit(envelope);
					if(deposit != null){
						System.out.println("Quantia = "+deposit.getAmount()+"\n");
						textFieldAccount.setText(""+deposit.getAccount().getId().getNumber());
						textFieldBranch.setText(""+deposit.getAccount().getId().getBranch().getNumber()+" - "+
								""+deposit.getAccount().getId().getBranch().getName());
						textFieldAmount.setText(""+deposit.getAmount());
					}
				}
				
				System.out.println("Envelope = "+envelope+"\n");
			}
		});
		frame.add(btnBuscar);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(26, 114, 56, 16);
		frame.add(lblConta);
		
		JLabel lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setBounds(26, 143, 56, 16);
		frame.add(lblAgencia);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(26, 172, 56, 16);
		frame.add(lblValor);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setEditable(false);
		textFieldAccount.setBounds(115, 111, 116, 22);
		frame.add(textFieldAccount);
		textFieldAccount.setColumns(10);
		
		textFieldBranch = new JTextField();
		textFieldBranch.setEditable(false);
		textFieldBranch.setBounds(115, 140, 116, 22);
		frame.add(textFieldBranch);
		textFieldBranch.setColumns(10);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(115, 169, 116, 22);
		frame.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		//panel.setVisible(true);
		
	

        frame.add (panel);

        //frame.setLocationRelativeTo (null);
        frame.pack ();
        frame.setVisible (true);
	}

}

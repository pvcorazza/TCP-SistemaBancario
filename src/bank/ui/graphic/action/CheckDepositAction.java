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

import bank.business.AccountOperationService;
import bank.business.domain.Deposit;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;

public class CheckDepositAction extends BankAction {

	private static final long serialVersionUID = 3464305499773865806L;
	private AccountOperationService accountOperationService;
	private JFormattedTextField textFieldEnvelope;
	private JTextField textFieldAccount;
	private JTextField textFieldBranch;
	private JTextField textFieldAmount;
	private Long envelope;
	private JPanel panel;
	private JLabel lblConfirmation;
	private JLabel lblConta;
	private JLabel lblAgencia;
	private JLabel lblValor;
	private JLabel lblEnvelope;

	Deposit deposit;

	public CheckDepositAction(BankGraphicInterface bankInterface, TextManager textManager,
			AccountOperationService accountOperationService) {

		super(bankInterface, textManager);

		deposit = null;

		this.accountOperationService = accountOperationService;
		super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME, textManager.getText("action.deposit.check"));
	}

	@Override
	public void execute() throws Exception {

		// Frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Verificar Depósito");

		// Panels
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(180, 180, 50, 200));

		// Labels

		lblEnvelope = new JLabel("Envelope");
		lblEnvelope.setBounds(26, 23, 56, 16);
		frame.add(lblEnvelope);

		lblConta = new JLabel("Conta");
		lblConta.setBounds(26, 53, 56, 16);
		frame.add(lblConta);

		lblAgencia = new JLabel("Agência");
		lblAgencia.setBounds(26, 83, 56, 16);
		frame.add(lblAgencia);

		lblValor = new JLabel("Valor");
		lblValor.setBounds(26, 113, 56, 16);
		frame.add(lblValor);

		lblConfirmation = new JLabel("");
		lblConfirmation.setBounds(73, 190, 207, 16);
		frame.add(lblConfirmation);

		// Buttons
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(254, 20, 97, 25);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					envelope = (long) ((Number) textFieldEnvelope.getValue()).intValue();
					if (accountOperationService.isUsedEnvelope(envelope)) {
						deposit = accountOperationService.getDeposit(envelope);
						if (deposit != null) {
							lblConfirmation.setText("Envelope encontrado!");
							textFieldAccount.setText("" + deposit.getAccount().getId().getNumber());
							textFieldBranch.setText("" + deposit.getAccount().getId().getBranch().getNumber() + " - "
									+ "" + deposit.getAccount().getId().getBranch().getName());
							textFieldAmount
									.setText("" + NumberFormat.getCurrencyInstance().format((deposit.getAmount())));
						} else {
							lblConfirmation.setText("Esse envelope já foi analisado!");
						}
					} else {
						lblConfirmation.setText("Envelope não encontrado!");
						textFieldAccount.setText("");
						textFieldAmount.setText("");
						textFieldBranch.setText("");
					}

				} catch (Exception e) {
					lblConfirmation.setText("Digite o número do envelope!");
				}
			}
		});
		frame.add(btnBuscar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(73, 150, 100, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (deposit == null) {
					lblConfirmation.setText("Clique em Buscar!");
				} else {
					deposit.setStatusConfirmed();
					lblConfirmation.setText("Depósito confirmado com sucesso!");
					accountOperationService.confirmDeposit(deposit);

					try {
						accountOperationService.updateDepositStatus(deposit);
						accountOperationService.deletePendingDeposit(deposit);
						textFieldAccount.setText("");
						textFieldAmount.setText("");
						textFieldBranch.setText("");
						textFieldEnvelope.setText("");
						deposit = null;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}
		});
		frame.add(btnConfirmar);

		JButton btnRejeitar = new JButton("Rejeitar");
		btnRejeitar.setBounds(204, 150, 100, 25);
		btnRejeitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (deposit == null) {
					lblConfirmation.setText("Clique em Buscar!");
				} else {
					accountOperationService.rejectDeposit(deposit);
					accountOperationService.deletePendingDeposit(deposit);
					lblConfirmation.setText("Depósito rejeitado com sucesso!");
					textFieldAccount.setText("");
					textFieldAmount.setText("");
					textFieldBranch.setText("");
					textFieldEnvelope.setText("");
					deposit = null;
				}

			}
		});
		frame.add(btnRejeitar);

		// TextFields
		textFieldEnvelope = new JFormattedTextField(NumberFormat.getIntegerInstance());
		textFieldEnvelope.setBounds(115, 20, 116, 22);
		frame.add(textFieldEnvelope);
		textFieldEnvelope.setColumns(10);

		textFieldAccount = new JTextField();
		textFieldAccount.setEditable(false);
		textFieldAccount.setBounds(115, 50, 116, 22);
		frame.add(textFieldAccount);
		textFieldAccount.setColumns(10);

		textFieldBranch = new JTextField();
		textFieldBranch.setEditable(false);
		textFieldBranch.setBounds(115, 80, 116, 22);
		frame.add(textFieldBranch);
		textFieldBranch.setColumns(10);

		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(115, 110, 116, 22);
		frame.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		// panel.setVisible(true);

		frame.add(panel);

		// frame.setLocationRelativeTo (null);
		frame.pack();
		frame.setVisible(true);
	}

}

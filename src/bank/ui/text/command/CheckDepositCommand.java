package bank.ui.text.command;

import java.util.Scanner;

import bank.business.AccountOperationService;
import bank.business.domain.Deposit;
import bank.ui.text.BankTextInterface;

public class CheckDepositCommand extends Command {

	private final AccountOperationService accountOperationService;
	private Long envelope;
	Deposit deposit;
	private Scanner readEnvelope;
	private Scanner readOption;

	public CheckDepositCommand(BankTextInterface bankInterface, AccountOperationService accountOperationService) {
		super(bankInterface);
		this.accountOperationService = accountOperationService;
	}

	@Override
	public void execute() throws Exception {

		readEnvelope = new Scanner(System.in);
		readOption = new Scanner(System.in);

		try {
			System.out.println("Digite o n�mero do envelope: ");
			envelope = readEnvelope.nextLong();
			if (accountOperationService.isUsedEnvelope(envelope)) {
				deposit = accountOperationService.getDeposit(envelope);
				if (deposit != null) {
					System.out.println("Envelope encontrado!");
					System.out.println("Quantia = " + deposit.getAmount() + "\n");
					System.out.println("Conta: " + deposit.getAccount().getId().getNumber());
					System.out.println("Ag�ncia: " + deposit.getAccount().getId().getBranch().getNumber() + " - " + ""
							+ deposit.getAccount().getId().getBranch().getName());
					System.out.println("Valor: " + deposit.getAmount());
					System.out.println("Op��es (ou E para sair):");
					System.out.println("C - Confirmar Dep�sito");
					System.out.println("R - Rejeitar Dep�sito");
					switch (readOption.nextLine().charAt(0)) {

					case 'C':
						System.out.println("Temos deposito\n");
						deposit.setStatusConfirmed();
						System.out.println("Dep�sito confirmado com sucesso!");
						accountOperationService.confirmDeposit(deposit);
						accountOperationService.updateDepositStatus(deposit);
						accountOperationService.deletePendingDeposit(deposit);
						deposit = null;
						break;
					case 'R':
						System.out.println("Temos deposito\n");
						accountOperationService.rejectDeposit(deposit);
						accountOperationService.deletePendingDeposit(deposit);
						System.out.println("Dep�sito rejeitado com sucesso!");
						deposit = null;
						break;
					case 'E':
						break;
					default:
						System.out.println("Op��o inv�lida");
					}
				} else {
					System.out.println("Esse envelope j� foi analisado!");
				}
			} else {
				System.out.println("Envelope n�o encontrado!");
			}

		} catch (Exception e) {
			System.out.println("Digite o n�mero do envelope!");
		}
		;

		// Long branch = bankInterface.readBranchId();
		// Long accountNumber = bankInterface.readCurrentAccountNumber();
		// Long envelope = UIUtils.INSTANCE.readLong("envelope");
		// Double amount = UIUtils.INSTANCE.readDouble("amount");
		//
		// Deposit deposit =
		// accountOperationService.deposit(bankInterface.getOperationLocation().getNumber(),
		// branch,
		// accountNumber, envelope, amount);
		//
		// System.out.println(getTextManager().getText("message.operation.succesfull"));
		// System.out.println(getTextManager().getText("deposit") + ": " +
		// deposit.getAmount());
	}

}
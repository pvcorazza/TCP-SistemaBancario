package bank.ui.text.command;

import java.text.NumberFormat;
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
			System.out.print("Digite o número do envelope: ");
			envelope = readEnvelope.nextLong();
			if (accountOperationService.isUsedEnvelope(envelope)) {
				deposit = accountOperationService.getDeposit(envelope);
				if (deposit != null) {
					System.out.println("Envelope encontrado!");
					System.out.println("Conta: " + deposit.getAccount().getId().getNumber());
					System.out.println("Agência: " + deposit.getAccount().getId().getBranch().getNumber() + " - " + ""
							+ deposit.getAccount().getId().getBranch().getName());
					System.out.println("Valor: " + NumberFormat.getCurrencyInstance().format((deposit.getAmount())));
					System.out.println("Opções (ou E para sair):");
					System.out.println("C - Confirmar Depósito");
					System.out.println("R - Rejeitar Depósito");
					System.out.print("Escolha uma opção: ");
					switch (readOption.nextLine().charAt(0)) {

					case 'C':
						deposit.setStatusConfirmed();
						System.out.println("Depósito confirmado com sucesso!");
						accountOperationService.confirmDeposit(deposit);
						accountOperationService.updateDepositStatus(deposit);
						accountOperationService.deletePendingDeposit(deposit);
						deposit = null;
						break;
					case 'R':
						accountOperationService.rejectDeposit(deposit);
						accountOperationService.deletePendingDeposit(deposit);
						System.out.println("Depósito rejeitado com sucesso!");
						deposit = null;
						break;
					case 'E':
						break;
					default:
						System.out.println("Opção inválida");
					}
				} else {
					System.out.println("Esse envelope já foi analisado!");
				}
			} else {
				System.out.println("Envelope não encontrado!");
			}

		} catch (Exception e) {
			System.out.println("Digite o número do envelope!");
		}
		;

	}

}
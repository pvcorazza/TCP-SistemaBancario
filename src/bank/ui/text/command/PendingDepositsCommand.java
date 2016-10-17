package bank.ui.text.command;

import java.text.NumberFormat;
import java.util.List;

import bank.business.AccountManagementService;
import bank.business.AccountOperationService;
import bank.business.domain.Deposit;
import bank.ui.text.BranchInterface;
import bank.ui.text.UIUtils;

public class PendingDepositsCommand extends Command {

	private final AccountManagementService accountManagementService;

	public PendingDepositsCommand(BranchInterface branchInterface, AccountOperationService accountOperationService,
			AccountManagementService accountManagementService) {
		super(branchInterface);
		this.accountManagementService = accountManagementService;
	}

	@Override
	public void execute() throws Exception {


		if (accountManagementService.hasPendingDeposits()) {

			List<Deposit> deposits = accountManagementService.getAllPendingDeposits();
			System.out.println("------------------------------- Depósitos Pendentes -----------------------------");
			StringBuffer sb = new StringBuffer();
			sb.append(getTextManager().getText("date")).append("\t\t\t");
			sb.append(getTextManager().getText("details")).append("\t");
			sb.append(getTextManager().getText("location")).append("\t\t");
			sb.append(getTextManager().getText("amount")).append("\n");
			sb.append("---------------------------------------------------------------------------------\n");
			for (Deposit deposit : deposits) {
				sb.append(UIUtils.INSTANCE.formatDateTime(deposit.getDate())).append("\t");

				sb.append(deposit.getEnvelope()).append("\t\t");
				sb.append(deposit.getLocation()).append("\t\t");
				sb.append(NumberFormat.getCurrencyInstance().format(deposit.getAmount())).append("\t");
				sb.append("\n");
			}
			sb.append("---------------------------------------------------------------------------------");
			System.out.println(sb);

		}

	}

}

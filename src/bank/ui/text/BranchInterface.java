package bank.ui.text;

import bank.business.AccountManagementService;
import bank.business.AccountOperationService;
import bank.business.domain.Branch;
import bank.ui.text.command.BalanceCommand;
import bank.ui.text.command.CheckDepositCommand;
import bank.ui.text.command.CreateAccountCommand;
import bank.ui.text.command.DepositCommand;
import bank.ui.text.command.EmployeeLoginCommand;
import bank.ui.text.command.LogoutCommand;
import bank.ui.text.command.PendingDepositsCommand;
import bank.ui.text.command.StatementCommand;
import bank.ui.text.command.TransferCommand;
import bank.ui.text.command.WithdrawalCommand;

/**
 * @author Ingrid Nunes
 * 
 */
public class BranchInterface extends BankTextInterface {
	
	private PendingDepositsCommand pendingDepositsCommand;

	public BranchInterface(Branch branch,
			AccountManagementService accountManagementService,
			AccountOperationService accountOperationService) {
		super(branch);
		this.addAction("L", new EmployeeLoginCommand(this,
				accountManagementService));
		this.addAction("C", new CreateAccountCommand(this,
				accountManagementService));
		this.addAction("B", new BalanceCommand(this, accountOperationService));
		this.addAction("S", new StatementCommand(this, accountOperationService));
		this.addAction("D", new DepositCommand(this, accountOperationService));
		this.addAction("W",
				new WithdrawalCommand(this, accountOperationService));
		this.addAction("T", new TransferCommand(this, accountOperationService));
		this.addAction("V", new CheckDepositCommand(this, accountOperationService));
		this.addAction("O", new LogoutCommand(this));
		pendingDepositsCommand = new PendingDepositsCommand(this, accountOperationService,accountManagementService);
	}

	@Override
	public Long readBranchId() {
		return UIUtils.INSTANCE.readLong("branch");
	}

	@Override
	public Long readCurrentAccountNumber() {
		return UIUtils.INSTANCE.readLong("account.number");
	}

	@Override
	public void showPendingDepositMessage() {
		try {	
			pendingDepositsCommand.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}

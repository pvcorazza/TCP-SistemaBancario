package bank.business.domain;

/**
 * @author Ingrid Nunes
 * 
 */
public class Withdrawal extends Transaction {
	
	private double amountItf;

	public Withdrawal(OperationLocation location, CurrentAccount account,
			double amount) {
		super(location, account, amount);
	}

	public double getAmountItf() {
		return amountItf;
	}

	public void setAmountItf(double amountWithItf) {
		this.amountItf = amountWithItf;
	}

	
}

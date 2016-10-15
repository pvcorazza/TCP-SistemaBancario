package bank.business.domain;

/**
 * @author Ingrid Nunes
 * 
 */
public class Transfer extends Transaction {

	private CurrentAccount destinationAccount;
	private double amounItf;

	public Transfer(OperationLocation location, CurrentAccount account,
			CurrentAccount destinationAccount, double amount) {
		super(location, account, amount);
		this.destinationAccount = destinationAccount;
	}

	/**
	 * @return the destinationAccount
	 */
	public CurrentAccount getDestinationAccount() {
		return destinationAccount;
	}

	public double getAmountItf() {
		return amounItf;
	}

	public void setAmountItf(double amountWithItf) {
		this.amounItf = amountWithItf;
	}

}

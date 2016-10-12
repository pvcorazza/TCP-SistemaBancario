package bank.business.domain;


/**
 * @author Ingrid Nunes
 * 
 */


//Testando

public class Deposit extends Transaction {

	private long envelope;
	private DepositStatus status;
	
	public enum DepositStatus{
		PENDING(1),CONFIRMED(2),REJECTED(3);
		
		private int status;
		
		DepositStatus(int status){
			this.status = status;
		}
		
		public int getStatus(){
			return this.status;
		}
		
	}

	public Deposit(OperationLocation location, CurrentAccount account,
			long envelope, double amount) {
		super(location, account, amount);
		this.envelope = envelope;
		this.status = DepositStatus.PENDING;
	}

	/**
	 * @return the envelope
	 */
	public long getEnvelope() {
		return envelope;
	}
	
	public DepositStatus getStatus(){
		return status;
	}

}

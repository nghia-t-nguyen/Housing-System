
public class RentIncreaseClause extends ClauseDecorator {
	private Lease lease;
	private int daysNotice;
	
	public RentIncreaseClause(Lease lease, int days) {
		this.lease = lease;
		this.daysNotice = days;
	}
	
	public String toString() {
		return "";
	}

}

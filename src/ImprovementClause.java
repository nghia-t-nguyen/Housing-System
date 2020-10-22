
public class ImprovementClause extends ClauseDecorator {
	private Lease lease;
	
	public ImprovementClause(Lease lease) {
		this.lease = lease;
	}
	
	public String toString() {
		return "";
	}
}

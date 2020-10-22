
public class EnvironmentalDangersClause extends ClauseDecorator {
	private Lease lease;
	
	public EnvironmentalDangersClause(Lease lease, boolean asbestos, boolean lead) {
		this.lease = lease;
		
	}
	
	public String addAbestos() {
		return "There is abestos!";
	}
	
	public String addLead() {
		return "There is lead!";
	}
	
	public String toString() {
		return "";
	}
	
}


import java.util.ArrayList;

public abstract class ClauseDecorator extends Lease{
    public ClauseDecorator(ArrayList<StudentAccounts> tenants, HostAccount landlord, Listing listing) {
        super(tenants, landlord, listing);
    }
    @Override
    public abstract String toString();
}

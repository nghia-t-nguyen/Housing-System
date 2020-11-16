import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Lease {
    ArrayList<StudentAccount> tenants;
    HostAccount landlord;
    Listing listing;
    private String Lease;

    /**
     * Class Lease is meant to generate the text of the lease in a .txt file. It imports BufferedWriter, FileWriter,
     * IOException, and ArrayList to do so.
     */
    
    public Lease(ArrayList<StudentAccount> tenants, HostAccount landlord, Listing listing){
    	this.tenants = tenants;
    	this.landlord = landlord;
    	this.listing = listing;
        Lease = "Tenants: ";
        for(StudentAccount temp : tenants ) {
            Lease += temp.getFirstName() + " " + temp.getLastName() + "\n";
        }
        Lease += "Landlord: " + landlord.getFirstName() + " " + landlord.getLastName() + "\nListing: " +
                listing.getName() + " at the address of " + listing.getAddress() +".\n";
    }

    /**
     * This is the constructor for the Lease class
     * @param start
     * @param tenants
     * @param host
     */
    public void addDuration(){
    	String tenantList = "";
    	tenantList += tenants.get(0).getFirstName() + " " + tenants.get(0).getLastName();
    	for (int i = 1; i < tenants.size(); ++i) {
    		if (i == tenants.size()-1) {
    			tenantList += " and " + tenants.get(i).getFirstName() + " " + tenants.get(i).getLastName();
    		} else {
    			tenantList += ", "+ tenants.get(i).getFirstName() + " " + tenants.get(i).getLastName();
    		}
    	}
    	
    	DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	Calendar cal = Calendar.getInstance();
    	String date = dateFormatter.format(cal.getTime());
    	Lease+="This lease agreement is made and entered on "+date+" by and between " +
    	landlord.getFirstName() + " " + landlord.getLastName() + " and "+ tenantList +".\n";
    }

    /**
     * addDuration displays the durations of the lease, using String start, String tenants, and String host
     */

    public void addTenantAct()
    {

          Lease+="1. Landlord Tenant Act. This Rental Agreement is governed by the South Carolina Residential Landlord and Tenant Act.\n";
    }

    /**
     * Add tenant adds a line regarding the LandLord tenant act. 
     */
    
    public void addProperty()
    {
            Lease+="2. Property. Landlord, in consideration of the lease payments provided in this"
            		+ " agreement, leases to Tenant a house with "+ listing.getBedrooms()+
            		" bedrooms and "+listing.getBathrooms()+ "bathrooms, located at "+
            		listing.getAddress()+". No other portion of the building wherein the"
            				+ " Property is located is included unless expressly provided for in this agreement.\n";
    }

    /**
     * Property displays a line regarding property in the listing, using int bedrooms, int bathrooms, String address, and int zip.
     */
    
    public void addPaymentTerm(String beg, String end){
        Lease+="3. Term. The Tenant will coninue to pay rent from "+beg+ " to "+end+".\n";
    }
    
 
    public void addPaymentTerm() {
        Lease+="3. Term. The Tenant will coninue to pay rent from 08/01/2021 to 06/01/2022.\n";
    }

    /**
     * PaymentTerm displays a line regarding when the tenant should pay using double beg and double end
     */
    
    public void addPayment()
    {
        Lease+="4. Rent. The Tenant will pay "+listing.getRent()+" each month on the first of the month.\n";
    }

    /**
     * Payment displays the amount to be payed each month using double payment
     */
    
    public void addPaymentAddress(String address)
    {
        Lease += "5. Payment should be sent to " + address + ".\n";
    }
    
    /**
     * adds a payment address if the payment 
     */
    public void addPaymentAddress() {
    	Lease += "5. Payment should be sent to " + listing.getAddress() + ".\n";
    }

    /**
     * PaymentAddress displays a line regarding where payments should be sent using String address
     */
    
    public void addDamages(double cost){
        Lease+="6. Damages. Charges will be billed to the client for damaged property, up to "+cost+".\n";
    }

    /**
     * Damages displays a line regarding bills for damages using double cost
     */
    
    public void addSignatures()
    {
    	for (StudentAccount tenant : tenants) {
    		Lease += tenant.getFirstName() + " " + tenant.getLastName() +"\n------------------\n";
    	}
        Lease += landlord.getFirstName() + " " + landlord.getLastName( )+"\n------------------";
    }

    /**
     * Signatures displays the signatures of the tenant and host, using String tenant and String host
     */
    
    public String toString(){
        return Lease;
    }

    /**
     * toString returns Lease of type String
     */
    
    public void printToFile(String fileName) {
      try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(Lease);

        writer.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

    /**
     * printToFile makes use of BufferedWriter, FIleWriter, and IOException to write the lines from Lease to a file titled Lease.txt
     */

}
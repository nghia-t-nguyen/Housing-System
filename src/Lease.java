import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lease {
    
    private String Lease;

    /**
     * Class Lease is meant to generate the text of the lease in a .txt file. It imports BufferedWriter, FileWriter,
     * IOException, and ArrayList to do so.
     */
    
    public Lease(ArrayList<StudentAccount> tenants, HostAccount landlord, Listing listing){

        Lease = "Tenants: ";
        for(StudentAccount temp : tenants ) {
            Lease += temp.getFirstName() + " " + temp.getLastName() + "\n";
        }
        Lease += "\n Landlord: " + landlord.getFirstName() + " " + landlord.getLastName() + "\n Listing: " +
                listing.toString();
    }

    /**
     * This is the constructor for the Lease class
     * @param start
     * @param tenants
     * @param host
     */
    public void addDuration(String start, String tenants, String host){
            Lease+="This lease agreement is made and entered on "+start+" by and between" + host + "and "+tenants+"\n";
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
    
    public void Property(int bedrooms, int bathrooms, String address, int zip)
    {

            Lease+="2. Property. Landlord, in consideration of the lease payments provided in this agreement, leases to Tenant a house with "+bedrooms+" bedrooms and "+bathrooms+ "bathrooms, located at "+address+", South Carolina "+zip+". No other portion of the building wherein the Property is located is included unless expressly provided for in this agreement.\n";

    }

    /**
     * Property displays a line regarding property in the listing, using int bedrooms, int bathrooms, String address, and int zip.
     */
    
    public void PaymentTerm(double beg, double end){
        Lease+="3. Term. The Tenant will coninue to pay rent from"+beg+ "to "+end+".\n";
    }

    /**
     * PaymentTerm displays a line regarding when the tenant should pay using double beg and double end
     */
    
    public void Payment(double payment)
    {
        Lease+="4. Rent. The Tenant will pay "+payment+" each month on the first of the month.\n";
    }

    /**
     * Payment displays the amount to be payed each month using double payment
     */
    
    public void PaymentAddress(String address)
    {
        Lease += "5. Payment should be sent to" + address + ".\n";
    }

    /**
     * PaymentAddress displays a line regarding where payments should be sent using String address
     */
    
    public void Damages(double cost){
        Lease+="6. Damages. Charges will be billed to the client for damaged property, up to "+cost+".\n";
    }

    /**
     * Damages displays a line regarding bills for damages using double cost
     */
    
    public void Signatures(String tenant, String host)
    {
        Lease+=""+tenant+"\n------------------"+"\n"+host+"\n------------------";
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
    
    public void printToFile(String fileName)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lease.txt"));
        writer.write(Lease);

        writer.close();
    }

    /**
     * printToFile makes use of BufferedWriter, FIleWriter, and IOException to write the lines from Lease to a file titled Lease.txt
     */

}
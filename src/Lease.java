import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Lease {

    private String Lease;

    public Lease(ArrayList<StudentAccount> tenants, HostAccount landlord, Listing listing){

    	Lease = "Tenants: ";
		for(StudentAccount temp : tenants ) {
			Lease += temp.getFirstName() + " " + temp.getLastName() + "\n";
		}
		Lease += "\n Landlord: " + landlord.getFirstName() + " " + landlord.getLastName() + "\n Listing: " +
		listing.toString();
    }

    public void addDuration(String start, String end){
        Lease += "\nLease Duration: " + start + " to " + end;
    }

    public void PaymentDay(int days){
        Lease += "\nPayment Day: " + days;
    }

    public void PaymentsAllowed(String types){
    	Lease += "\nPayment Types: " + types;
    }

    public void addOccupancyClause(boolean occupancy, String list){
    	 if(occupancy == true) {
             occupancy = true;
         Lease += "\nOccupancy: Occupied";
         }else
             occupancy = false;
         Lease += "\nOccupancy: Empty";
     }

    public void addParkingClause(boolean parking){
        if(parking == true) {
            parking = true;
            Lease += "\nParking: Yes";
            
        } else {
            parking = false;
            Lease += "\nParking: No";
        }
    }

    public void SmokingClause(boolean smoking){
        if(smoking == true) {
            smoking = true;
        Lease += "\nSmoking: Smoking is allowed.";
        } else {
            smoking = false;
        Lease += "\nSmoking: Smoking isn't allowed.";
        }
    }

    public void VapingClause(boolean vaping){
        if(vaping == true) {
            vaping = true;
        Lease += "\nVaping: Vaping isn't allowed.";
        } else {
            vaping = false;
        Lease += "\nVaping: Vaping isn't allowed.";
        }
    }

    public void PetClause(boolean pets, String type){
        if(pets == true) {
        	pets = true;
        	Lease += "\nPets: Pets are allowed.\nPet Type: " + type;
        } else {
            pets = false;
            Lease += "\nPets: Pets aren't allowed.\nPet Type: " + type;
        }
    }

    public void addUtilities(String text){
    	Lease += "\nUltilities: " + text;
    }

    public void insuranceClause(String text){
    	Lease += "\nInsurance Clause: " + text;
    }

    public void attorneyFeesClause(String text){
    	Lease += "\nAttorney Fees Clause: " + text;
    }

    public String toString(){
        return Lease;
    }

    public void printToFile(String fileName)
    		  throws IOException {
    		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    		    writer.write(Lease);
    		    
    		    writer.close();
    		}
}


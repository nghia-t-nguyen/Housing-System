import java.util.ArrayList;

public abstract class Lease {

    private String Lease;

    public Lease(ArrayList<StudentAccounts> tenants, HostAccount landlord, Listing listing){

    }

    public String addDuration(String start, String end){
        return start+end;
    }

    public int PaymentDay(int days){
        return 0;
    }

    public void PaymentsAllowed(String types){

    }

    public void addOccupancyClause(boolean occupancy, String list){
        if(occupancy == true)
            occupancy = true;
        else
            occupancy = false;
    }

    public void addParkingClause(boolean parking){
        if(parking == true)
            parking = true;
        else
            parking = false;
    }

    public void SmokingClause(boolean smoking){
        if(smoking == true)
            smoking = true;
        else
            smoking = false;
    }

    public void VapingClause(boolean vaping){
        if(vaping == true)
            vaping = true;
        else
            vaping = false;
    }

    public void PetClause(boolean pets, String type){
        if(pets == true)
            pets = true;
        else
            pets = false;
    }

    public void addUtilities(String text){

    }

    public void insuranceClause(String text){

    }

    public void attorneyFeesClause(String text){

    }

    public String toString(){
        return "null";
    }

    public void printToFile(String fileName){

    }
}

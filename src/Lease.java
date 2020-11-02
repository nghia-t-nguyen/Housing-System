import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The abstract class Lease contains the private String lease which is adjusted
 * throughout the entire class and then returned. 
 * Created by Team QUEBEC
 */
public abstract class Lease {
	private String Lease;

	/**
	 * The Lease constructor takes in an ArrayList of StudentAccount objects, a
	 * HostAccount object, and a Listing object. It takes the lease String private
	 * variable and adds the first and last name Strings of the StudentAccounts
	 * within the ArrayList, it then adds the same from the HostAccount and finally,
	 * it adds to toString for the Listing. 
	 * Created by Team QUEBEC
	 */
	public Lease(ArrayList<StudentAccount> tenants, HostAccount landlord, Listing listing) {

		Lease = "Tenants: ";
		for (StudentAccount temp : tenants) {
			Lease += temp.getFirstName() + " " + temp.getLastName() + "\n";
		}
		Lease += "\n Landlord: " + landlord.getFirstName() + " " + landlord.getLastName() + "\n Listing: "
				+ listing.toString();
	}

	/**
	 * The addDuration method takes in several Strings and uses Filewriter to write
	 * a String with the passed Strings in it to a txt file. 
	 * Created by Team QUEBEC
	 */
	public void addDuration(String start, String tenants, String host) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write("This lease agreement is made and enterd on " + start + " by and between" + host + "and "
					+ tenants + "\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The addTenantAct method uses FileWriter to write a String to a txt file.
	 * Created by Team QUEBEC
	 */
	public void addTenantAct() {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write(
					"1. Landloard Tenant Act. This Rental Agreement is governed by the South Carolina Residential Landlord and Tenant Act.\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The Property method uses FileWriter to write a String to a txt file while
	 * taking in 3 ints to be part of it. 
	 * Created by Team QUEBEC
	 */
	public void Property(int bedrooms, int bathrooms, String address, int zip) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write(
					"2. Property. Landlord, in consideration of the lease payments provided in this agreement, leases to Tenant a house with "
							+ bedrooms + " bedrooms and " + bathrooms + "bathrooms, located at " + address
							+ ", South Carolina " + zip
							+ ". No other portion of the building wherein the Property is located is included unless expressly provided for in this agreement.\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The PaymentTerm method uses FileWriter to write a String to a txt file while
	 * taking in two doubles to be part of it. 
	 * Created by Team QUEBEC
	 */
	public void PaymentTerm(double beg, double end) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write("3. Term. The Tenant will coninue to pay rent from" + beg + "to " + end + ".\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The Payment method uses FileWriter to write a String to a txt file while
	 * taking in a double to be part of it. 
	 * Created by Team QUEBEC
	 */
	public void Payment(double payment) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write("4. Rent. The Tenant will pay " + payment + " each month on the first of the month.\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The PaymentAddress method uses FileWriter to write a String to a txt file
	 * while taking in a String to be part of it. 
	 * Created by Team QUEBEC
	 */
	public void PaymentAddress(String address) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write("5. Payment should be sent to" + address + ".\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The Damages method uses FileWriter to write a String to a txt file while
	 * taking in a double to be part of it. 
	 * Created by Team QUEBEC
	 */
	public void Damages(double cost) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write("6. Damages. Charges will be billed to the client for damaged property, up to " + cost + ".\n");
			file.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * The Signatures method takes in two Strings and uses them to create a String
	 * and write them to a file using FileWriter. 
	 * Created by Team QUEBEC
	 */
	public void Signatures(String tenant, String host) {
		try (FileWriter file = new FileWriter("src/lease.txt")) {
			file.write(tenant + "\n------------------" + "\n" + host + "\n------------------");
			file.flush();
		} catch (IOException e) {

		}
	}
	/*
	 * public void printToFile(String fileName) throws IOException { BufferedWriter
	 * writer = new BufferedWriter(new FileWriter("Lease.txt"));
	 * writer.write(Lease); writer.close(); } We don't need this part anymore,
	 * unless I'm mistaken - dylan
	 */
}

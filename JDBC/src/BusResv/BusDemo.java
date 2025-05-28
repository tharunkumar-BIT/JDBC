package BusResv;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class BusDemo {
	public static void main(String[] args) throws SQLException {

		BusDAO busdao = new BusDAO();
		busdao.displayBusInfo();

		int userOpt = 1;
		Scanner scanner = new Scanner(System.in);

		while(userOpt == 1) {
			System.out.println("Enter 1 to Book amd 2 to exit");
			userOpt = scanner.nextInt();
			if(userOpt == 1) {
				Booking booking = new Booking();
				if(booking.isAvailable()) {
					BookingDAO bookingdao = new BookingDAO();
					bookingdao.addBooking(booking);
					System.out.println("Your booking is Confirmed");
				}
				else {
					System.out.println("Sorry. Bus is full. Try another bus or date");
				}
			}
		}

		scanner.close();
	}
}

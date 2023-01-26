package cinema;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Cinema {

    private static String[][] cinema;
    private static int ticketsSold = 0;
    private static int income = 0;
    public static void printCinema() {
        System.out.print("\nCinema:\n");

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initializeCinema() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = in.nextInt();

        cinema = new String[rows+1][seats+1];
        cinema[0][0] = " ";

        for (int i = 1; i < cinema.length; i++) {
            cinema[i][0] = String.valueOf(i);
        }
        for (int i = 1; i < cinema[0].length; i++) {
            cinema[0][i] = String.valueOf(i);
        }

        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    public static void buyTicket() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter a row number:");
            int rowNum = in.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seatNum = in.nextInt();

            if (rowNum > cinema.length - 1 || seatNum > cinema[0].length - 1) {
                System.out.println("Wrong input!");
            } else if (cinema[rowNum][seatNum].equals("B") ) {
                System.out.println("That ticket has already been purchased!");
            } else {
                cinema[rowNum][seatNum] = "B";
                ticketsSold++;

                int ticketPrice = 0;
                int total = (cinema.length - 1) * (cinema[0].length - 1);

                if (total <= 60) {
                    ticketPrice = 10;
                } else {
                    if (rowNum <= ((cinema.length - 1) / 2)) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                }
                income += ticketPrice;

                System.out.println("Ticket Price: $" + ticketPrice);
                break;
            }
        }
    }

    public static void cinemaStats() {
        DecimalFormat df=new DecimalFormat("0.00");

        int rows = cinema.length - 1;
        int seats = cinema[0].length - 1;

        int numSeats = rows * seats;
        double percentSold = ((double)ticketsSold / numSeats) * 100.0;

        int totalIncome = 0;
        if (numSeats <= 60) {
            totalIncome = rows*seats*10;
        } else {
            totalIncome = ((rows / 2) * seats * 10) + ((rows - rows / 2) * seats * 8);
        }

        System.out.println("\nNumber of purchased tickets: " + ticketsSold);
        System.out.println("Percentage: " + df.format(percentSold) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        initializeCinema();

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            int menuOption = in.nextInt();

            if (menuOption == 1) {
                printCinema();
            } else if (menuOption == 2) {
                buyTicket();
            } else if (menuOption == 3) {
                cinemaStats();
            } else if (menuOption == 0) {
                break;
            } else {
                System.out.println("That is not an option. Try again.");
            }
        }

    }
}
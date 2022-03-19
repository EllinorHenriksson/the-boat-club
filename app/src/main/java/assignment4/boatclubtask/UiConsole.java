package assignment4.boatclubtask;

import java.util.Scanner;

/**
 * Represents a console user interface.
 */
public class UiConsole {
  private Scanner scan = new Scanner(System.in, "utf-8");

  /**
   * Prints the main menu and handles input from the user.
   *
   * @return The menu choice of the user.
   */
  public int mainMenu() {
    String menu = "--- Main meny ---\n1. Create new member\n2. List all members\n3. Select member\n" 
        + "4. Search for members\n5. Quit application\nWhat would you like to do? (1-5) ";
    System.out.println(menu);
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Provides the user with a form to create a new member.
   *
   * @return The member data.
   */
  public String[] createMember() {
    String[] data = new String[3];
    System.out.println("--- Create new member ---\nEnter name (required): ");
    data[0] = scan.nextLine();
    System.out.println("Enter email (optional): ");
    data[1] = scan.nextLine();
    return data;
  }

  /**
   * Prints a list of all members.
   *
   * @return User input to view the main menu.
   */
  public String memberList() {
    System.out.println("Member list");
    System.out.print("Press Enter to go back to the main menu");
    return scan.nextLine();
  }

  /**
   * Closes the scanner.
   */
  public void closeScanner() {
    scan.close();
  }
}

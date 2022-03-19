package assignment4.boatclubtask;

import java.util.ArrayList;
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
    String menu = "\n--- Main meny ---\n1. Create new member\n2. List all members\n3. Select member\n" 
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
    System.out.println("\n--- Create new member ---\nEnter name (required): ");
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
  public String memberList(ArrayList<Member> members) {
    System.out.println("\n--- List of members ---");
    for (Member m : members) {
      System.out.println(m);
    }
    System.out.println("Press Enter to go back to the main menu");
    return scan.nextLine();
  }

  /**
   * Lets the user select a member to view detailed info about the member.
   *
   * @return The member id.
   */
  public String selectMember() {
    System.out.println("\n--- Select member---\nEnter the member id: ");
    return scan.nextLine();
  }

  /**
   * Prints detailed info about a member and provides a member menu.
   *
   * @param member The member.
   * @return The menu choice.
   */
  public int memberInfo(Member member) {
    System.out.println("\n--- " + member.getName() + " ---");
    System.out.println(member);
    for (Boat b : member.getBoats()) {
      System.out.println(b);
    }
    String menu = "\n--- Member meny ---\n1. Edit member\n2. Delete member\n3. Add boat\n" 
        + "4. Select boat\n5. Go back to main meny\nWhat would you like to do? (1-5)";
    System.out.println(menu);
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Lets the user edit the current member.
   *
   * @param member The member.
   * @return The new member data.
   */
  public String[] editMember(Member member) {
    String[] data = new String[2];
    System.out.println("--- Edit member---\nOld name: " + member.getName() + "\nEnter the new name: ");
    data[0] = scan.nextLine();
    System.out.println("Old email: " + member.getEmail() + "Enter the new email: ");
    data[1] = scan.nextLine();
    return data;
  }

  /**
   * Closes the scanner.
   */
  public void closeScanner() {
    scan.close();
  }
}

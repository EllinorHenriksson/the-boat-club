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
    do {
      try {
        String menu = "\n--- Main meny ---\n1. Create new member\n2. List all members\n3. Select member\n" 
            + "4. Search for members\n5. Quit application\nWhat would you like to do? (1-5) ";
        System.out.println(menu);
        int choice = Integer.parseInt(scan.nextLine());
        if (choice < 1 || choice > 5) {
          throw new Exception("The entered value must be 1-5.");
        }
        return choice;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } while (true);
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
  public String memberList(ArrayList<Member> members, String header) {
    System.out.println(header);
    for (Member m : members) {
      String email = m.getEmail();
      if (email == null) {
        email = "-";
      }
      System.out.println(m.getName() + "\n\tEmail: " + email + "\n\tID: " + m.getId());
    }
    System.out.println("\nPress Enter to go back to the main menu");
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
    System.out.println("\n--- Member info ---");
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("ID: " + member.getId());
    System.out.println("Boats: ");
    ArrayList<Boat> boats = member.getBoats();
    if (boats.size() > 0) {
      for (Boat b : boats) {
        System.out.println("\t" + b.getName());
      }
    } else {
      System.out.println("\t-");
    }
    
    String menu = "\n--- Member meny ---\n1. Edit member\n2. Delete member\n3. Add boat\n" 
        + "4. Select boat\n5. Go back to main meny\nWhat would you like to do? (1-5)";
    System.out.println(menu);
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Lets the user edit the current member.
   *
   * @return The new member data.
   */
  public String[] editMember() {
    String[] data = new String[2];
    System.out.println("\n--- Edit member---\nEnter the new name: ");
    data[0] = scan.nextLine();
    System.out.println("Enter the new email: ");
    data[1] = scan.nextLine();
    return data;
  }

  /**
   * Lets the user add or edit a boat for the current member (by creating a new boat).
   *
   * @return The boat.
   */
  public Boat createBoat(String header) throws Exception {
    Boat boat = null;
    System.out.println(header + "\n1. Canoe\n2. Sailboat\n3. Motorboat\n4. Motorsailer"
        + "\nChoose type of boat (1-4): ");
    int choice = Integer.parseInt(scan.nextLine());
    if (choice < 1 || choice > 4) {
      throw new Exception("The entered value must be 1-4.");
    }
    System.out.println("Enter the name of the boat: ");
    String name = scan.nextLine();
    System.out.println("Enter the length (in meter): ");
    int length = Integer.parseInt(scan.nextLine());
    if (choice == 1) {
      boat = new Canoe(name, length);    
    } else if (choice == 2) {
      System.out.println("Enter the depth (in meter): ");
      int depth = Integer.parseInt(scan.nextLine());
      boat = new Sailboat(name, length, depth);    
    } else if (choice == 3) {
      System.out.println("Enter the engine power (in horse powers): ");
      int power = Integer.parseInt(scan.nextLine());
      boat = new Motorboat(name, length, power);     
    } else if (choice == 4) {
      System.out.println("Enter the depth (in meter): ");
      int depth = Integer.parseInt(scan.nextLine());
      System.out.println("Enter the engine power (in horse powers): ");
      int power = Integer.parseInt(scan.nextLine());
      boat = new Motorsailer(name, length, depth, power);
    }
    return boat;
  }

  /**
   * Lets the user select a boat to view detailed info about it.
   *
   * @return The name of the boat.
   */
  public String selectBoat() {
    System.out.println("\n--- Select boat---\nEnter the name of the boat: ");
    return scan.nextLine();
  }

  /**
   * Prints detailed info about a boat and provides a boat menu.
   *
   * @param boat The boat.
   * @return The menu choice.
   */
  public int boatInfo(Boat boat) {
    System.out.println("\n--- Boat info ---");
    System.out.println("Name: " + boat.getName());
    String type = null;
    int depth = 0;
    int power = 0;
    if (boat instanceof Canoe) {
      type = "canoe";
    } else if (boat instanceof Sailboat) {
      type = "sailboat";
      depth = ((Sailboat) boat).getDepth();
    } else if (boat instanceof Motorboat) {
      type = "motorboat";
      power = ((Motorboat) boat).getPower();
    } else if (boat instanceof Motorsailer) {
      type = "motorsailer";
      depth = ((Motorsailer) boat).getDepth();
      power = ((Motorsailer) boat).getPower();
    }
    System.out.println("Type: " + type);
    System.out.println("Length: " + boat.getLength() + " m");
    if (depth != 0) {
      System.out.println("Depth: " + depth + " m");
    }
    if (power != 0) {
      System.out.println("Engine power: " + power + " horse powers");
    }

    String menu = "\n--- Boat meny ---\n1. Edit boat\n2. Delete boat\n" 
        + "3. Go back to main meny\nWhat would you like to do? (1-3)";
    System.out.println(menu);
    return Integer.parseInt(scan.nextLine());  
  }

  /**
   * Lets the user choose search strategy and enter a search phrase.
   *
   * @return The entered data.
   */
  public String[] searchForMembers() throws Exception {
    String[] data = new String[2];
    System.out.println("\n--- Search for members ---\n1. By member ID\n2. By member name" 
        + "\n3. By boat type\n4. By minimum boat length\nChoose a search strategy (1-4): ");
    data[0] = scan.nextLine();
    if (Integer.parseInt(data[0]) < 1 || Integer.parseInt(data[0]) > 4) {
      throw new Exception("The entered value must be 1-4.");
    }
    if (data[0].equals("1")) {
      System.out.println("Enter the ID: ");
    } else if (data[0].equals("2")) {
      System.out.println("Enter the name: ");
    } else if (data[0].equals("3")) {
      System.out.println("Enter the type (canoe/sailboat/motorboat/motorsailer): ");
    } else if (data[0].equals("4")) {
      System.out.println("Enter the length (in meter): ");
    }
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

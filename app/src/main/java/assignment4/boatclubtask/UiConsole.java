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
      String email = m.getEmail();
      if (email == null) {
        email = "-";
      }
      System.out.println(m.getName() + "\n\tEmail: " + email + "\n\tID: " + m.getId());
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
    System.out.println("--- Edit member---\nEnter the new name: ");
    data[0] = scan.nextLine();
    System.out.println("Enter the new email: ");
    data[1] = scan.nextLine();
    return data;
  }

  /**
   * Lets the user add a boat for the current member.
   *
   * @return The boat.
   */
  public Boat addBoat() {
    Boat boat = null;
    System.out.println("--- Add boat ---\n1. Canoe\n2. Sailboat\n3. Motorboat\n4. Motorsailer"
        + "\nChoose type of boat (1-4): ");
    int choice = Integer.parseInt(scan.nextLine());
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
   * Lets the user edit the current boat.

   * @return The edited (new) boat. 
   */
  public Boat editBoat() {
    Boat newBoat = null;
    System.out.println("--- Edit boat ---\nChoose type (1 = canoe; 2 = sailboat; 3 = motorboat; 4 = motorsailer): ");
    int type = Integer.parseInt(scan.nextLine());
    System.out.println("Enter the new name: ");
    String name = scan.nextLine();
    System.out.println("Enter the new length (in meter): ");
    int length = Integer.parseInt(scan.nextLine());

    if (type == 1) {
      newBoat = new Canoe(name, length);
    } else if (type == 2) {
      System.out.println("Enter the new depth (in meter): ");
      int depth = Integer.parseInt(scan.nextLine());
      newBoat = new Sailboat(name, length, depth);
    } else if (type == 3) {
      System.out.println("Enter the new engine power (in horse powers): ");
      int power = Integer.parseInt(scan.nextLine());
      newBoat = new Motorboat(name, length, power);
    } else if (type == 4) {
      System.out.println("Enter the new depth (in meter): ");
      int depth = Integer.parseInt(scan.nextLine());
      System.out.println("Enter the new engine power (in horse powers): ");
      int power = Integer.parseInt(scan.nextLine());
      newBoat = new Motorsailer(name, length, depth, power);
    }
    return newBoat;
  }

  /**
   * Closes the scanner.
   */
  public void closeScanner() {
    scan.close();
  }
}

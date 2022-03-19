package assignment4.boatclubtask;

import java.io.File;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

/**
 * Represents an application for assignment 4 - The Boat Club.
 */
public class Application {
  private File file;
  private Registry registry = new Registry();
  private UiConsole console = new UiConsole();

  /**
   * Initializing constructor.
   *
   * @param file A file object to read from and write to.
   */
  public Application(File file) {
    this.file = file;
  }

  /**
   * Runs the application.
   */
  public void run() {
    String textFromFile = readFile().toString();
    populateRegistry(textFromFile);

    handleMainMenu();
  }

  /**
   * Reads from the registry file and returns its content.
   *
   * @return {StringBuilder} The text content of the file.
   */
  private StringBuilder readFile() {
    StringBuilder text = new StringBuilder();
    try {
      Scanner scan = new Scanner(file, "utf-8");
      while (scan.hasNext()) {
        text.append(scan.nextLine() + "\n");
      }
      scan.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }

  /**
   * Populates the regitry with the members in the registry file.
   *
   * @param text The file text.
   */
  private void populateRegistry(String text) {
    text = text.replace("\n", "");
    String[] members = text.split("MEMBER:");
    ArrayList<String> memberlist = new ArrayList<>(Arrays.asList(members));
    memberlist.remove(0);
    for (int i = 0; i < memberlist.size(); i++) {
      String[] memberInfo = memberlist.get(i).split("BOAT:");

      String[] personalData = memberInfo[0].split(":");
      Member newMember = createMember(personalData);
      
      ArrayList<String[]> boats = new ArrayList<>();
      for (int j = 1; j < memberInfo.length; j++) {
        boats.add(memberInfo[j].split(":"));
      }
      
      for (int j = 0; j < boats.size(); j++) {
        Boat newBoat = createBoat(boats.get(j));
        newMember.addBoat(newBoat);
      }

      registry.addMember(newMember);
    }
  }

  /**
   * Handles the main menu actions forwarded from the console ui. 
   */
  private void handleMainMenu() {
    int choiceMainMenu = console.mainMenu(); 
    
    // If "Create member"...
    if (choiceMainMenu == 1) {
      handleCreateMember();
    }

    // If "List all members"...
    if (choiceMainMenu == 2) {
      handleMemberList();
    }

    /*
    // If "Select member"...
    if (choiceMainMenu == 3) {
      int choiceMemberInfo = console.memberInfo(); 
      // If "Edit member"...
      if (choiceMemberInfo == 1) {
        // Edit member
      }
      // If "Delete member"...
      if (choiceMemberInfo == 2) {
        // Delete member
      }
      // If "Add boat"...
      if (choiceMemberInfo == 3) {
        // Add boat
      }
      // If "Select boat"...
      if (choiceMemberInfo == 4) {
        int choiceBoatInfo = console.boatInfo();
        // If "Edit boat"...
        if (choiceBoatInfo == 1) {
          // Edit boat
        }
        // If "Deletet boat"...
        if (choiceBoatInfo == 2) {
          // Delete boat
        }
        // If "Exit to main meny"...
        if (choiceBoatInfo == 3) {
          // Exit to main meny
        }
      }
      // If "Exit to main meny"...
      if (choiceMemberInfo == 5) {
        // Exit to main meny
      }
    }

    // If "Search"...
    if (choiceMainMenu == 4) {
      // Search for members
    }
    */

    // If "Quit application"...
    if (choiceMainMenu == 5) {
      exit();
    }
  }

  /**
   * Handles the create member action forwarded from the ui.
   */
  private void handleCreateMember() {
    String[] data = console.createMember();
    String id = null;
    Boolean unique = false;
    while (!unique) {
      id = generateId();
      unique = isUnique(id);
    }
    data[2] = id;
    Member newMember = createMember(data);
    registry.addMember(newMember);
    handleMainMenu();
  }

  /**
   * Handles the member list action forwarded from the console ui.
   */
  private void handleMemberList() {
    // OBS! Fortsätt här! Fundera på hur göra med meny val.
    String choice = null;
    choice = console.memberList();
    if (choice != null) {
      handleMainMenu();
    }
  }

  /**
   * Generates a random alphanumeric id of 6 characters.
   *
   * @return The id.
   */
  private String generateId() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replaceAll("-", "").substring(0, 6);
  }

  /**
   * Checks if the id is unique among the members.
   *
   * @param id The id.
   * @return True if unique, else false.
   */
  private Boolean isUnique(String id) {
    ArrayList<Member> members = registry.getMembers();
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }


  /**
   * Creates a new member with the provided personal data.
   *
   * @param data Personal data (name, email address and id).
   * @return A new member.
   */
  private Member createMember(String[] data) {
    String name = data[0];
    String email = data[1];
    String id = data[2];

    Member newMember = null;
    if (email.equals("")) {
      newMember = new Member(name, id);
    } else {
      newMember = new Member(name, email, id);
    }
    return newMember;
  }

  /**
   * Creates a new boat with the provided attributes.
   *
   * @param boat The attributes (name, type, length, depth and power).
   * @return A new boat.
   */
  private Boat createBoat(String[] boat) {
    Boat newBoat = null;
    String boatName = boat[0];
    int length = Integer.parseInt(boat[2]);
    if (boat[1].equals("canoe")) {
      newBoat = new Canoe(boatName, length);
    } else if (boat[1].equals("sailboat")) {
      int depth = Integer.parseInt(boat[3]);
      newBoat = new Sailboat(boatName, length, depth);
    } else if (boat[1].equals("motorboat")) {
      int power = Integer.parseInt(boat[3]);
      newBoat = new Motorboat(boatName, length, power);
    } else if (boat[1].equals("motorsailer")) {
      int depth = Integer.parseInt(boat[3]);
      int power = Integer.parseInt(boat[4]);
      newBoat = new Motorsailer(boatName, length, depth, power);
    }
    return newBoat;
  }

  /**
   * Writes the registry's members to the registry file and exits the application.
   */
  private void exit() {
    String textToFile = registryToText(registry.getMembers());
    writeFile(textToFile);
    console.closeScanner();
    System.exit(1);
  }

  /**
   * Transfoms the member registry to a formatted text.
   *
   * @param members The members in the registry.
   * @return A formatted text.
   */
  private String registryToText(ArrayList<Member> members) {
    StringBuilder text = new StringBuilder();
    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      text.append(member.toString() + "\n");

      ArrayList<Boat> boats = member.getBoats();
      for (int j = 0; j < boats.size(); j++) {
        Boat boat = boats.get(j);
        text.append(boat.toString() + "\n");
      }
    }
    return text.toString();
  }

  /**
   * Writes text to the registry file.
   *
   * @param text The text to write.
   */
  private void writeFile(String text) {
    try {
      PrintWriter printer = new PrintWriter(file, "utf-8");
      printer.print(text);
      printer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The main method of the program.
   */
  public static void main(String[] args) {
    String currentPath = System.getProperty("user.dir");
    String registryPath = currentPath + File.separator + "data" + File.separator + "registry.data";
    File file = new File(registryPath);
    Application application = new Application(file);
    application.run();
  }
}

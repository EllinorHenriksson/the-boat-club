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
  private Console console = new Console();

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

    String choiceMainMeny = console.mainMeny(); 
    // If "Create member"...
    if (choiceMainMeny.equals("1")) {
      String[] data = console.newMember();
      String id = null;
      Boolean unique = false;
      while (!unique) {
        id = generateId();
        unique = isUnique(id);
      }
      data[2] = id;
      createMember(data);
      // Exit to main meny
    }

    // If "List all members"...
    if (choiceMainMeny.equals("2")) {
      String choiceMemberList = console.memberList();
      // If "Exit to main meny"...
      if (choiceMemberList.equals("1")) {
        // Exit to main meny
      }
    }

    // If "Select member"...
    if (choiceMainMeny.equals("3")) {
      String choiceMemberInfo = console.memberInfo(); 
      // If "Edit member"...
      if (choiceMemberInfo.equals("1")) {
        // Edit member
      }
      // If "Delete member"...
      if (choiceMemberInfo.equals("2")) {
        // Delete member
      }
      // If "Add boat"...
      if (choiceMemberInfo.equals("3")) {
        // Add boat
      }
      // If "Select boat"...
      if (choiceMemberInfo.equals("4")) {
        String choiceBoatInfo = console.boatInfo();
        // If "Edit boat"...
        if (choiceBoatInfo.equals("1")) {
          // Edit boat
        }
        // If "Deletet boat"...
        if (choiceBoatInfo.equals("2")) {
          // Delete boat
        }
        // If "Exit to main meny"...
        if (choiceBoatInfo.equals("3")) {
          // Exit to main meny
        }
      }
      // If "Exit to main meny"...
      if (choiceMemberInfo.equals("5")) {
        // Exit to main meny
      }
    }

    // If "Search"...
    if (choiceMainMeny.equals("4")) {
      // Search for members
    }

    // If "Quit application"...
    if (choiceMainMeny.equals("5")) {
      // Quit application
      String textToFile = registryToText(registry.getMembers());
      writeFile(textToFile);
      // Exit application
    }
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

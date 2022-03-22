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
   * Handles the "main menu" actions forwarded from the console ui. 
   */
  private void handleMainMenu() {
    int choiceMainMenu = console.mainMenu(); 
    
    // If "Create member"...
    if (choiceMainMenu == 1) {
      handleCreateMember();
    }

    // If "List all members"...
    if (choiceMainMenu == 2) {
      handleMemberList(registry.getMembers(), "\n--- List of members ---");
    }

    // If "Select member"...
    if (choiceMainMenu == 3) {
      handleSelectMember();
    }

    // If "Search"...
    if (choiceMainMenu == 4) {
      handleSearch();
    }

    // If "Quit application"...
    if (choiceMainMenu == 5) {
      exit();
    }
  }

  /**
   * Handles the "create member" action forwarded from the console ui.
   */
  private void handleCreateMember() {
    String[] data = console.createMember();
    if (!data[1].equals("")) {
      if (!uniqueEmail(data[1])) {
        // OBS! Meddela användaren att mailadressen är upptagen
        System.out.println("Email must be unique."); // OBS! Kom ihåg att ändra
      }
    }

    String id = null;
    Boolean unique = false;
    while (!unique) {
      id = generateId();
      unique = uniqueId(id);
    }
    data[2] = id;

    Member newMember = createMember(data);
    registry.addMember(newMember);
    handleMainMenu();
  }

  /**
   * Handles the "member list" actions forwarded from the console ui.
   */
  private void handleMemberList(ArrayList<Member> members, String header) {
    console.memberList(members, header);
    handleMainMenu();
  }

  /**
   * Handles the "select member" actions forwarded fron the console ui.
   */
  private void handleSelectMember() {
    String id = console.selectMember();
    Member member = null;
    for (Member m : registry.getMembers()) {
      if (m.getId().equals(id)) {
        member = m;
      }
    }
    handleMemberInfo(member);
  }

  /**
   * Handles the "search for members" actions forwarded fron the console ui.
   */
  private void handleSearch() {
    String[] data = console.searchForMembers();
    int choice = Integer.parseInt(data[0]);
    String phrase = data[1];

    if (choice == 1) {
      registry.setSearchStrategy(new MemberIdStrategy(phrase)); 
    } else if (choice == 2) {
      registry.setSearchStrategy(new MemberNameStrategy(phrase));
    } else if (choice == 3) {
      registry.setSearchStrategy(new BoatTypeStrategy(phrase));
    } else if (choice == 4) {
      registry.setSearchStrategy(new BoatLengthStrategy(phrase));
    }

    ArrayList<Member> results = registry.searchForMembers();
    handleMemberList(results, "\n--- Search results ---");
  }

  /**
   * Handles the member actions forwarded from the console ui.
   *
   * @param member The current member.
   */
  private void handleMemberInfo(Member member) {
    String id = member.getId();
    int choice = console.memberInfo(member); 
  
    // If "Edit member"...
    if (choice == 1) {
      String[] data = console.editMember();
      registry.editMember(id, data[0], data[1]);
      handleMainMenu();
    }
    
    // If "Delete member"...
    if (choice == 2) {
      registry.deleteMember(id);
      handleMainMenu();
    }
    
    // If "Add boat"...
    if (choice == 3) {
      Boat boat = console.addBoat();
      registry.addBoatToMember(id, boat);
      handleMainMenu();
    }
    
    // If "Select boat"...
    if (choice == 4) {
      handleSelectBoat(member);
    }
    
    // If "Go back to main meny"...
    if (choice == 5) {
      handleMainMenu();
    }
  }

  /**
   * Handles the "select boat" actions forwarded fron the console ui.
   *
   * @param member The current member.
   */
  private void handleSelectBoat(Member member) {
    String name = console.selectBoat();
    Boat boat = null;
    for (Boat b : member.getBoats()) {
      if (b.getName().equals(name)) {
        boat = b;
      }
    }
    handleBoatInfo(member.getId(), boat);
  }

  /**
   * Handles the boat actions forwarded from the console ui.
   *
   * @param id The id of the member that owns the boat.
   * @param boat The current boat.
   */
  private void handleBoatInfo(String id, Boat boat) {
    int choice = console.boatInfo(boat);
    // If "Edit boat"...
    if (choice == 1) {
      Boat newBoat = console.editBoat();
      registry.editBoat(id, boat.getName(), newBoat);
      handleMainMenu();
    }
    
    // If "Deletet boat"...
    if (choice == 2) {
      registry.deleteBoatFromMember(id, boat.getName());
      handleMainMenu();
    }
    
    // If "Go back to main meny"...
    if (choice == 3) {
      handleMainMenu();
    }
  }

  /**
   * Checks if the email is unique among the members.
   *
   * @param email The email.
   * @return True if unique, else false.
   */
  private boolean uniqueEmail(String email) {
    for (Member m : registry.getMembers()) {
      if (m.getEmail() != null) {
        if (m.getEmail().equals(email)) {
          return false;
        }
      }
    }
    return true;
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
  private Boolean uniqueId(String id) {
    for (Member m : registry.getMembers()) {
      if (m.getId().equals(id)) {
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
   * @param data Boat data (name, type, length, depth and power).
   * @return A new boat.
   */
  private Boat createBoat(String[] data) {
    Boat newBoat = null;
    String boatName = data[0];
    int length = Integer.parseInt(data[2]);
    if (data[1].equals("canoe")) {
      newBoat = new Canoe(boatName, length);
    } else if (data[1].equals("sailboat")) {
      int depth = Integer.parseInt(data[3]);
      newBoat = new Sailboat(boatName, length, depth);
    } else if (data[1].equals("motorboat")) {
      int power = Integer.parseInt(data[3]);
      newBoat = new Motorboat(boatName, length, power);
    } else if (data[1].equals("motorsailer")) {
      int depth = Integer.parseInt(data[3]);
      int power = Integer.parseInt(data[4]);
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
    System.exit(0);
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
      text.append(member + "\n");

      ArrayList<Boat> boats = member.getBoats();
      for (int j = 0; j < boats.size(); j++) {
        Boat boat = boats.get(j);
        text.append(boat + "\n");
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

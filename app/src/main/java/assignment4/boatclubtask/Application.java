package assignment4.boatclubtask;

import java.io.File;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents an application for assignment 4 - The Boat Club.
 */
public class Application {
  private File file;
  private BoatClub boatClub = new BoatClub();

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
    String text = readFile().toString();
    populateRegistry(text);
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
    ArrayList<Member> allNewMembers = new ArrayList<>(); // OBS
    for (int i = 0; i < memberlist.size(); i++) {
      String[] memberInfo = memberlist.get(i).split("BOAT:");

      String[] personalData = memberInfo[0].split(":");
      String name = personalData[0];
      String email = personalData[1];
      String id = personalData[2];

      Member newMember = null;
      if (email.equals("")) {
        newMember = new Member(name, id);
      } else {
        newMember = new Member(name, email, id);
      }

      ArrayList<String[]> boats = new ArrayList<>();
      for (int j = 1; j < memberInfo.length; j++) {
        boats.add(memberInfo[j].split(":"));
      }
      
      for (int j = 0; j < boats.size(); j++) {
        Boat newBoat = createBoat(boats.get(j));
        newMember.addBoat(newBoat);
      }

      boatClub.addMember(newMember);
      allNewMembers.add(newMember); // OBS
    }

    ArrayList<Member> membersCopy = boatClub.getMembers(); // OBS
    System.out.println(allNewMembers); // OBS
  }

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
   * Writes text to the registry file.
   *
   * @param text The text to write.
   */
  private void writeFile(StringBuilder text) {
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

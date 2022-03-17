package assignment4.boatclubtask;

import java.io.File;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * Represents an application for assignment 4 - The Boat Club.
 */
public class Application {
  private File file;
  private StringBuilder registry;

  /**
   * Initializing constructor.
   *
   * @param file A file object to read from and write to.
   */
  public Application(File file) {
    this.file = file;
    registry = readFile();
  }

  /**
   * Runs the application.
   */
  public void run() {
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
   * Saves the registry to the file.
   */
  private void saveFile() {
    try {
      PrintWriter printer = new PrintWriter(file, "utf-8");
      printer.print(registry);
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

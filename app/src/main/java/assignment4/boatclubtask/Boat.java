package assignment4.boatclubtask;

/**
 * Represents a boat.
 */
public abstract class Boat {
  private String name;
  private int length;

  /**
   * Initializing constructor.
   *
   * @param name The name of the boat.
   * @param length The length (in meter).
   */
  public Boat(String name, int length) {
    setName(name);
    setLength(length);
  }

  /**
   * Gets the name of the boat.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the boat.
   *
   * @param name The name (must not be null or shorter than 2 characters, or an exception will be thrown).
   */
  public void setName(String name) {
    if (name == null || name.length() < 2) {
      throw new IllegalArgumentException("The name of the boat must be at least 2 characters long.");
    } else {
      this.name = name;
    }
  }

  /**
   * Gets the length of the boat (in meter). 
   *
   * @return The length.
   */
  public int getLength() {
    return length;
  }

  /**
   * Sets the length of the boat (in meter).
   *
   * @param length The length (must be greater than 0, or an exception will be thrown).
   */
  public void setLength(int length) {
    if (length > 0) {
      this.length = length;
    } else {
      throw new IllegalArgumentException("The length of the boat must be greater than 0.");
    }
  }

  public abstract String toString();
}

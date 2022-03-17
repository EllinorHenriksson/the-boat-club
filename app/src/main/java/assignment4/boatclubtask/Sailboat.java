package assignment4.boatclubtask;

/**
 * Represents a sailboat.
 */
public class Sailboat extends Boat {
  private int depth;

  /**
   * Initializing constructor.
   *
   * @param name The name of the sailboat.
   * @param length The length (in meter).
   * @param depth The depth (in meter).
   */
  public Sailboat(String name, int length, int depth) {
    super(name, length);
    setDepth(depth);
  }

  /**
   * Gets the depth of the sailboat (in meter).
   *
   * @return The depth.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Sets the depth of the sailboat (in meter).
   *
   * @param depth The depth (must be greater than 0 or an exception will be thrown).
   */
  public void setDepth(int depth) {
    if (depth > 0) {
      this.depth = depth;
    } else {
      throw new IllegalArgumentException("The depth of the boat must be greater than 0.");
    }
  }
}

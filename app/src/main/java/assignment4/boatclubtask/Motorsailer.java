package assignment4.boatclubtask;

/**
 * Represents a motorsailer.
 */
public class Motorsailer extends Boat {
  private int depth;
  private int power;

  /**
   * Initializing constructor.
   *
   * @param name The name of the motorsailer.
   * @param length The length (in meter).
   * @param depth The depth (in meter).
   * @param power The horse power.
   */
  public Motorsailer(String name, int length, int depth, int power) {
    super(name, length);
    setDepth(depth);
    setPower(power);
  }

  /**
   * Gets the depth of the motorsailer (in meter).
   *
   * @return The depth.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Sets the depth of the motorsailer (in meter).
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

  /**
   * Gets the horse power of the motorsailer.
   *
   * @return The horse power.
   */
  public int getPower() {
    return power;
  }

  /**
   * Sets the horse power of the motorsailer.
   *
   * @param power The horse power.
   */
  public void setPower(int power) {
    if (power > 0) {
      this.power = power;
    } else {
      throw new IllegalArgumentException("The horse power of the boat must be greater than 0.");
    }
  }

  /**
   * Returns a string representation of the motorsailer.
   *
   * @return A string.
   */
  @Override
  public String toString() {
    return "BOAT:" + this.getName() + ":" + "motorsailer" + ":" + this.getLength() + ":" + this.getDepth() + ":" + this.getPower();
  }
}

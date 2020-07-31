package toDoApplication.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An Options object represents the collection of Options used for a program, which each describes
 * a possible command line argument for the program.
 */
public class Options {
  /**
   * A map of all the Options, with the key being their long-String representation,
   * and the value being the corresponding Option object.
   */
  private Map<String, Option> longOpts;

  /** A list of all the Options (their long-String form) that are required to run the given program. */
  private List<String> requiredOpts;

  /**
   * Constructor: creates a new Options object with its an empty map and list
   */
  public Options(){
    this.longOpts = new LinkedHashMap<>();
    this.requiredOpts = new ArrayList<>();
  }

  /**
   * Gets the map: longname as key and Option as value
   *
   * @return the map:  longname as key and Option as value
   */
  public Map<String, Option> getLongOpts() {
    return this.longOpts;
  }

  /**
   * Gets every Option that is required.
   *
   * @return Read-only List of all Option required (long-String representation)
   */
  public List<String> getRequiredOptions() {
    return this.requiredOpts;
  }

  /**
   * This method adds a new Option to the existing set of Options
   *
   * @param opt The new Option to be added
   * @return The updated Options object
   */
  public Options addOption(Option opt) {
    String key = opt.getLongOpt();

    // Add it to the map.
    this.longOpts.put(Utility.stripLeadingHyphens(key), opt);

    // If the option is required, add it to the required list as well.
    if (opt.isRequired()) {
      this.requiredOpts.add(key);
    }
    return this;
  }

  /**
   * Given a long-String name of an Option, retrieves the corresponding Option.
   * Before retrieving, the leading hyphens in the long name are removed (up to 2).
   *
   * @param opt short or long name of the Option we want to look for
   * @return the corresponding Option, and null if the Option is not in the map
   */
  public Option getOption(String opt) {
    opt = Utility.stripLeadingHyphens(opt);
    if (this.longOpts.containsKey(opt)) {
      return this.longOpts.get(opt);
    }
    return null;
  }

  /**
   * Returns all the Option objects having a long name starting with the specified string.
   *
   * @param opt The prefix/complete name of an Option
   * @return A list of all Option objects (list of their long names) matching the prefix/complete
   * name specified, or an empty list if none matches.
   */
  public List<String> getMatchingOptions(String opt) {
    opt = Utility.stripLeadingHyphens(opt);
    List<String> matchingOpts = new ArrayList<>();

    // If we have a perfect match, return that Option only
    if (this.longOpts.keySet().contains(opt)) {
      return Collections.singletonList(opt);
    }

    for (String longOpt : this.longOpts.keySet()) {
      if (longOpt.startsWith(opt)) {
        matchingOpts.add(longOpt);
      }
    }
    return matchingOpts;
  }

  /**
   * Given the long name of an Option, check whether the corresponding Option object is in this
   * Options set.
   *
   * @param opt long name of the Option
   * @return true if the corresponding Option is in this Options set, false if it is not.
   */
  public boolean hasOption(String opt) {
    opt = Utility.stripLeadingHyphens(opt);
    return this.longOpts.containsKey(opt);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Options)) {
      return false;
    }
    Options options = (Options) o;
    return Objects.equals(longOpts, options.longOpts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longOpts);
  }

  @Override
  public String toString() {
    return "Options{" +
        "longOpts=" + this.longOpts +
        ", requiredOpts=" + this.requiredOpts +
        '}';
  }
}

/* This class contains various constant values that will be
 * referenced multiple times throughout the project's code
 */
package autocomplete;

public class CONSTANTS {
	public static final int NUMNODES = 27; // the number of child nodes each trie node has
	public static final int UNUSED = -999; // each trieNode's "usedIndexes" array will be filled with this value initially
	public static final int ASCII_START = 97; // ASCII encoding of lowercase letters starts here
	
	// menu constants
	public static final int TRAIN = 1;
	public static final int TEST = 2;
	public static final int EXIT = 3;
}

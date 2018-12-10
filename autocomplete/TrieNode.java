/* This class represents the nodes in the Trie, or prefix tree data structure
 * Each node contains a count of how many times it has been branched to by the parent node,
 * a list of 27 child nodes (26 for alphabet letters and one to mark the end of the word)
 * and a list that contains indexes of child nodes that have been populated so far so that
 * the search algorithm does not have to check every letter to see if it has been used
 */
package autocomplete;

import java.util.Arrays;

public class TrieNode {
	public int count; // the number of times this node has been referenced by the parent node
	public TrieNode children[]; // the node's array of child nodes
	public int usedIndexes[]; // keeps track of which child indexes are in use to limit iteration
	public int numChildren; // the number of children that branch of of this node
	
	// the default constructor for a node with no arguments
	public TrieNode() { 
		count = 0;
		children = new TrieNode[CONSTANTS.NUMNODES]; // declare array of children
		usedIndexes = new int[CONSTANTS.NUMNODES];
		Arrays.fill(usedIndexes, CONSTANTS.UNUSED); // set all child indexes to initially unused
		numChildren = 0;
	}
	
	/* returns the index of the first entry in the "usedIndexes" array
	 * that contains an unused marker
	 */
	public int getFirstUnused()  {
		for (int i = 0; i < usedIndexes.length; i++) {
			if (usedIndexes[i] == CONSTANTS.UNUSED) { // we have found the entry
				return i;
			}
		}
		return -1;
	}
}

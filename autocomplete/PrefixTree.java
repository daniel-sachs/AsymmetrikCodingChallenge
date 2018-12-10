/* This class is an implementation of the Trie, or prefix tree data structure.
 * Each node of the Trie represents one character in a word and can have 
 * up to 27 child nodes (26 alphabet letters + 1 end of word marker)
 * Following a chain of letters from the root of the tree to an end of word marker
 * will result in one full word that the Trie has learned.
 */
package autocomplete;

import java.util.ArrayList;
import java.util.List;

public class PrefixTree {
	private TrieNode root; // the root of the Trie. All words stem from here
	
	// default constructor of the data structure with no constructors
	public PrefixTree() {
		root = new TrieNode(); // initialize the root
	}
	
	/* inserts a word into the data structure
	 * word should be comprised of all lowercase characters
	 */
	public void insert(String word) {
		TrieNode current = root; // node we are currently on
		
		for (int i = 0; i < word.length(); i++) { // iterate over the word
			int index = ASCIIToIndex(word.charAt(i));
			
			if (current.children[index] == null) { // this character has not been declared from this node yet
				current.usedIndexes[current.getFirstUnused()] = index;
				current.children[index] = new TrieNode();
				current.children[index].count = 1;
				current.numChildren += 1;
				
				current = current.children[index];
			}
			else { // the node has already been declared, we just have to add to its count
				current.children[index].count += 1;
				current = current.children[index];
			}
		}
		// after the last char in the word, we must mark the end of the word
		if (current.children[CONSTANTS.NUMNODES - 1] == null) { // a word has not ended here yet
			current.usedIndexes[current.getFirstUnused()] = CONSTANTS.NUMNODES - 1;
			current.children[CONSTANTS.NUMNODES - 1] = new TrieNode();
			current.children[CONSTANTS.NUMNODES - 1].count = 1;
			current.numChildren += 1;
		}
		else { // add to the number of times a word has ended here
			current.children[CONSTANTS.NUMNODES - 1].count += 1;
		}
	}
	
	// bootstrap function to initialize a list to return and begin recursively searching the tree
	public List<Candidate> retrieve(String prefix) {
		List<Candidate> candidates = new ArrayList<Candidate>();
		TrieNode current = root;
		
		// iterate over the given prefix
		for (int i = 0; i < prefix.length(); i++) {
			int index = ASCIIToIndex(prefix.charAt(i));
			if (current.children[index] == null) { // no word with this prefix is found in the tree, return empty list
				return candidates;
			}
			else {
				current = current.children[index];
			}
		}
		
		// call the recursive function to populate the list with possible candidates
		candidates = retrieve(prefix, current, candidates);
		return candidates;
	}
	
	/* recursive retrieve function
	 * begins at the node represented by the prefix string,
	 * populates the candidates list with all possible paths from that node
	 */
	public List<Candidate> retrieve(String prefix, TrieNode current, List<Candidate> candidates) {
		// iterate over all of the used indexes at this node
		for (int i = 0; i < current.numChildren; i++) {
			if (current.usedIndexes[i] == CONSTANTS.NUMNODES - 1) { // there is an end of word marker here. add the word to our list
				Candidate candidate = new Candidate(prefix, current.children[CONSTANTS.NUMNODES - 1].count);
				candidates.add(candidate);
			}
			else { // keep building the current word and iterating
				candidates = retrieve(prefix + (char)IndexToASCII(current.usedIndexes[i]), current.children[current.usedIndexes[i]], candidates);
			}
		}
		return candidates;
	}
	
	// converts the ASCII value of a character to an index for a TrieNode
	public int ASCIIToIndex(char c) {
		return (int)c - CONSTANTS.ASCII_START;
	}
	
	// converts an array index from 0-26 to lowercase ASCII
	public int IndexToASCII(int i) {
		return (char)(i + CONSTANTS.ASCII_START);
	}
}

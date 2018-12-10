/* Implementation of the AutocompleteProvider class as specified by the challenge requirements
 * Maintains an instance of the prefix tree data structure and interfaces with it on behalf of the user
 */

package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutocompleteProvider {
	private static PrefixTree P;
	
	public AutocompleteProvider() { // default constructor with no arguments
		P = new PrefixTree(); // initialize the data structure
	}

	public List<Candidate> getWords(String fragment) { // returns list of candidates ordered by confidence
		List<Candidate> candidates = P.retrieve(fragment); // get the list of candidates
		Collections.sort(candidates, Collections.reverseOrder()); // sort by confidence, descending
		return candidates;
	}

	public void train(String passage) { // trains the algorithm with the provided passage
		passage = passage.toLowerCase(); // Trie only takes lowercase into account
		List<String> words = new ArrayList<String>(); // list of words in the passage
		Matcher m = Pattern.compile("[a-z]+").matcher(passage); // match the passage with this regex. one or more characters from a-z
		while (m.find()) {
			words.add(m.group()); // populate the list
		}

		for (int i = 0; i < words.size(); i++) { // insert each word into the prefix tree
			P.insert(words.get(i));
		}

	}

}

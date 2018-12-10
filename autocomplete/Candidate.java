/* Implementation of the candidate class as specified by the challenge requirements
 * Represents a candidate word to autocomplete a prefix.
 * Higher confidence means word is more likely to be a correct autocompletion
 */

package autocomplete;

public class Candidate implements Comparable {

	private String word; // the autocompleted word
	private int confidence; // algorithm's confidence in this candidate
	
	// constructor for a candidate, with the word and confidence as arguments
	public Candidate(String w, int c) {
		word = w;
		confidence = c; // confidence will be represented by the number of times a particular word has been entered
		// higher frequency of entries = higher confidence
	}
	
	public String getWord() { // returns the autocomplete candidate
		return word;
	}
	
	public int getConfidence() { // returns the confidence for the candidate
		return confidence;
	}

	// allows candidates to be compared by their confidences for easier sorting
	public int compareTo(Object other) { 
		return Integer.compare(confidence, ((Candidate) other).getConfidence());
	}

}

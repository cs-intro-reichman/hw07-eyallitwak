
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	/* returns the given string without the first character */
	public static String tail(String str) {
		return str.substring(1, str.length());
	}

	/* calculates levenshtein distance between two strings */
	public static int levenshtein(String word1, String word2) {
		word1.toLowerCase();
		word2.toLowerCase();

		if (word2.length() == 0)
			return word1.length();

		if (word1.length() == 0)
			return word1.length();

		if (word1.charAt(0) == word2.charAt(0))
			return levenshtein(tail(word1), tail(word2));

		return 1 + Math.min(levenshtein(tail(word1), word2),
				Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2))));

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	/*
	 * returns the word from the dictionary with the closest lev-distance to the
	 * given argument "word", within the given threshold
	 */
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = threshold + 1;
		String result = word;
		for (int i = 0; i < dictionary.length; i++) {
			int currentDistance = levenshtein(word, dictionary[i]);

			if (currentDistance < minDistance) {
				minDistance = currentDistance;
				result = dictionary[i];
			}
		}
		return result;
	}

}

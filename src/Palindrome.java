import java.text.BreakIterator;
import java.util.*;

/**
 * This class checks if there is any palindromes or palindromic sentences in your text.
 *
 * @author MasterFlomaster1
 * @version 2.0
 */
public class Palindrome {

    private ArrayList<String> inputSentencesList = new ArrayList<>();
    private ArrayList<String> sentenceList = new ArrayList<>();
    private ArrayList<String> outputSentencesList = new ArrayList<>();
    private ArrayList<String> inputWordsList = new ArrayList<>();
    private ArrayList<String> outputWordsList = new ArrayList<>();
    private boolean sentence;
    private String text;

    public ArrayList<String> getOutputWordsList() {
        return outputWordsList;
    }

    public ArrayList<String> getOutputSentencesList() {
        return outputSentencesList;
    }

    /**
     * Main class constructor.
     *
     * @param text here goes your text.
     * @param checkForSentences if true, the I will look for palindromic sentences in your text.
     */
    public Palindrome(String text, boolean checkForSentences) {
        this.text = text;
        sentence = checkForSentences;
        if (sentence) sentenceSeparation();
        wordSeparation();
    }


    /**
     * This method fills the <code>{@link #inputSentencesList}</code>.
     */
    private void sentenceSeparation() {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            inputSentencesList.add(text.substring(start,end));
        }
        sentenceAdaptation();
    }


    /**
     * This method fills the <code>{@link #inputWordsList}</code> and deletes extra characters.
     */
    private void wordSeparation() {
        String[] subStr = text.split(":|;|\\?|”|“|\\(|\\)|!|\"|\\.|,| +");
        inputWordsList = new ArrayList<>(Arrays.asList(subStr));
        inputWordsList.removeAll(Arrays.asList("", null));
        checkPalindrome(inputWordsList);
    }


    /**
     * This method fills the <code>{@link #sentenceList}</code> and deletes extra characters.
     */
    private void sentenceAdaptation() {
        for (String s : inputSentencesList) {
            String removeChar = s.toLowerCase().replaceAll("[,-.\'!?();: +]", "");
            sentenceList.add(removeChar);
        }
        checkPalindrome(sentenceList);
    }


    /**
     * Primary method which receives ArrayList to check for palindromes and palindromic sentences.
     * @param list ArrayList to check.
     */
    private void checkPalindrome(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            boolean isPalindrome = true;
            String word = list.get(i);
            if (word.length()==1) continue;
            for (int q = 0; q < word.length(); q++) {
                if (word.charAt(q)!=word.charAt(word.length()-q-1)) {
                    isPalindrome = false;
                }
            }

            if (isPalindrome) {
                if (sentence) outputSentencesList.add(inputSentencesList.get(i));
                else outputWordsList.add(inputWordsList.get(i));
            }

        }
    }
}


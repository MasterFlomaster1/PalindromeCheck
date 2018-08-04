import java.io.*;
import java.text.BreakIterator;
import java.util.*;


public class Palindrome {

    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your text here: ");
        String inputText = reader.readLine();


        System.out.println("#####Palindromic sentences found in the text: " + sentenceCheck(inputText).size()+ "#####");
        for (Object m: sentenceCheck(inputText)){
            System.out.println(m);
        }

        System.out.println("#####Palindromes found in the text: " + wordCheck(inputText).size()+ "#####");
        for (Object m: wordCheck(inputText)){
            System.out.println(m);
        }
    }


    public static ArrayList<String> sentenceCheck(String inputText){
        ArrayList<String> inputSentencesArrayList = new ArrayList<>();
        ArrayList<String> sentenceArrayList = new ArrayList<>();
        ArrayList<String> outputSentencesArrayList = new ArrayList<>();


        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(inputText);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            inputSentencesArrayList.add(inputText.substring(start,end));
        }


        for (int i = 0; i < inputSentencesArrayList.size(); i++){
            StringBuilder sentenceStringBuilder = new StringBuilder(inputSentencesArrayList.get(i).toLowerCase());
            String sentenceString = "-";
            int sentenceLength = sentenceStringBuilder.length();
            sentenceArrayList.add(inputSentencesArrayList.get(i));
            int count = 0;

            for (int q = 0; q < sentenceLength; q++){
                count++;
                if (sentenceStringBuilder.charAt(q) == ',' || sentenceStringBuilder.charAt(q) == ' '
                        || sentenceStringBuilder.charAt(q) == '-' || sentenceStringBuilder.charAt(q) == '\''
                        || sentenceStringBuilder.charAt(q) == '.' || sentenceStringBuilder.charAt(q) == '!'
                        || sentenceStringBuilder.charAt(q) == '?' || sentenceStringBuilder.charAt(q) == ';'
                        || sentenceStringBuilder.charAt(q) == ':'){
                    sentenceStringBuilder.deleteCharAt(q);
                    sentenceLength--;
                    q--;
                    count--;
                    sentenceString = sentenceStringBuilder.toString();

                    if (count == sentenceLength){
                        sentenceArrayList.remove(i);
                    }
                }
            }

            if (!sentenceString.equals("-")){
                sentenceArrayList.add(sentenceString);
            }
        }

        for (int i = 0; i < sentenceArrayList.size(); i++){
            StringBuilder sentenceForCheck = new StringBuilder(sentenceArrayList.get(i));
            String sentence;

            if (inputSentencesArrayList.size() > i){
                sentence = inputSentencesArrayList.get(i);
            } else {
                break;
            }

            int count = 0;

            for (int q = 0; q < sentenceForCheck.length(); q++){

                if (sentenceForCheck.length() == 1){
                    break;
                }

                if (sentenceForCheck.length() == sentence.length()){
                    break;
                }


                if (sentenceForCheck.charAt(q) == sentenceForCheck.charAt(sentenceForCheck.length() - 1 - q)){
                    count++;
                } else {
                    sentence = " ";
                    break;
                }

            }

            if (count == sentenceForCheck.length()){
                if (!sentence.equals(" ")) {
                    outputSentencesArrayList.add(sentence);
                }
            }
        }
        return outputSentencesArrayList;
    }


    public static ArrayList<String> wordCheck(String inputText){
        String[] subStr = inputText.split(":|;|\\?|”|“|!|\"|\\.|,| +");
        ArrayList<String> inputWordsArrayList = new ArrayList<>(Arrays.asList(subStr));
        inputWordsArrayList.removeAll(Arrays.asList("", null));
        ArrayList<String> outputWordsArrayList = new ArrayList<>();
        ArrayList<String> wordsArrayList = new ArrayList<>();


        for (int m = 0; m < inputWordsArrayList.size(); m++){
            StringBuilder wordStringBuilder = new StringBuilder(inputWordsArrayList.get(m).toLowerCase());
            int wordLength = wordStringBuilder.length();
            String wordString = "-";
            int count = 0;
            for (int i = 0; i < wordLength; i++){
                count++;
                if (wordStringBuilder.charAt(i) == '\''){
                    wordStringBuilder.deleteCharAt(i);
                    wordLength--;
                    i--;
                    count--;
                    wordString = wordStringBuilder.toString();
                    if (count == wordLength-1){
                        wordString = "-";
                    }
                }
            }
            wordsArrayList.add(wordStringBuilder.toString());

            if (!wordString.equals("-")){
                wordsArrayList.add(wordString);
            }
        }


        for (int i = 0; i < wordsArrayList.size(); i++) {
            String wordForCheck = wordsArrayList.get(i);
            String word = inputWordsArrayList.get(i);
            int count = 0;

            for (int q = 0; q < wordForCheck.length(); q++) {

                if (wordForCheck.length() == 1) {
                    break;
                }


                if (wordForCheck.charAt(q) == wordForCheck.charAt(wordForCheck.length() - 1 - q)) {
                    count++;
                } else {
                    word = " ";
                    break;
                }
            }
            if (count == wordForCheck.length()) {
                if (!word.equals(" ")) {
                    outputWordsArrayList.add(word);
                }
            }
        }
        return outputWordsArrayList;
    }

}


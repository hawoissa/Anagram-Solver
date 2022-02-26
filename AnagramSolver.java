// Hawo Issa
// CSE 143 EG with Khushi Chaudhari
// Homework 6
// Anagram Solver is a class that prints all anagram phrases of a 
// given word or phrase using a dictionary.

import java.util.*;
public class AnagramSolver {
   
   private List<String> wordList;
   private Map<String, LetterInventory> wordInventory;
   
   // post: constructs a new object that stores each word in dictionary
   // param: List<String> dictionary - dictionary containing words
   public AnagramSolver(List<String> dictionary) {
      wordInventory = new HashMap<String, LetterInventory>();
      for (String word : dictionary) {
         wordInventory.put(word, new LetterInventory(word));
      }
      wordList = dictionary;
   }
   
   // pre: throws new IllegalArgumentException if max is negative
   // post: prunes the list of words to only compatibles words then
   //       finds combination of words that fit the given string. Number of 
   //       words will be determined by the max value.
   // param: Strig text - phrase used for anagram, int max - number of words in combination
   public void print(String text, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory phrase = new LetterInventory(text);
      List<String> listOfWords = new ArrayList<String>();
      for (String word : wordList) {
         if (phrase.subtract(wordInventory.get(word)) != null) {
            listOfWords.add(word);
         }
      }
      Stack<String> result = new Stack<String>();
      print(phrase, max, listOfWords, result);
   }
   
   // post: prints out every anagram of given word
   // param: LetterInventory phrase - phrase anagram will be made of, 
   //        int max - number of words in anagram,
   //        List<String> listOfWords - list of available words
   private void print(LetterInventory phrase, int max, List<String> listOfWords, 
         Stack<String> result) {
      if (phrase.isEmpty()) {
         System.out.println(result);
      } else if (max == 0 || result.size() < max ) {
         for (String word : listOfWords) {
            LetterInventory text = phrase.subtract(wordInventory.get(word));
            if (text != null) {
               result.push(word);
               print(text, max, listOfWords, result);
               result.pop();
            }
         }
      }
   }
    
}
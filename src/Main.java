import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.*;
/*
 The program reads a file, which in this case is the novel "Pride and Prejudice", and calculates
 the words average length, the longest word and the most used word in the book in a multi-threaded way,
 one thread for each task mentioned above.
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {
        File file = new File("Pride and Prejudice.txt");
        Scanner input = new Scanner(file, "UTF-8");

        LargestWord largestWord = new LargestWord();
        MostUsedWord mostUsedWord = new MostUsedWord();

        Book book = new Book(file, input, largestWord, mostUsedWord);

        ExecutorService executor = Executors.newCachedThreadPool();


        Future<Integer> avgWordLength = executor.submit(book::averageWordLength);
        Future<LargestWord> largestWordFuture = executor.submit(book::largestWord);
        Future<MostUsedWord> mostUsedWordFuture = executor.submit(book::mostUsedWord);

        executor.shutdown();


        System.out.println("The average length of the words is: " + avgWordLength.get());
        System.out.println("The largest word is: " + largestWordFuture.get().getLargestWords() + " and has the size of " + largestWordFuture.get().getLength() + " characters");
        System.out.println("The most used word is: " + mostUsedWordFuture.get().getMostUsedWord() + " and is used " + mostUsedWordFuture.get().getFrequency() + " times");

        System.out.println("The book has " + book.getListOfWords().size() + " words");
    }
}

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TypingSpeedTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueTest = true;

        // Main loop to control the entire typing test
        while (continueTest) {
            // Load words from file
            String[] words = loadWordsFromFile("words.txt");
            if (words.length == 0) { // Check if file was loaded successfully
                continue;
            }

            // Display instructions and start the test
            System.out.println("Welcome to the Typing Speed Test!");
            System.out.println("Type the following words as fast as you can:");
            System.out.println("Press ENTER to start the test...");
            scanner.nextLine();

            // Start the timer
            long startTime = System.currentTimeMillis();

            // Loop to display and prompt user for each word
            int wordCount = 0;
            int correctCount = 0;
            while (wordCount < words.length) {
                System.out.print(words[wordCount] + " ");
                String userInput = scanner.next();
                if (userInput.equals(words[wordCount])) { // Check if user input matches the word
                    correctCount++;
                }
                wordCount++;
            }

            // Calculate typing speed and accuracy
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            int wordsPerMinute = (int) ((wordCount * 60000) / totalTime); //  1 word = 1 character
            double accuracy = (double) correctCount / wordCount * 100;

            // Display test results
            System.out.println("Typing Speed: " + wordsPerMinute + " words per minute");
            System.out.println("Accuracy: " + accuracy + "%");

            // Ask if user wants to take the test again
            System.out.println("Do you want to take the test again? (Y/N)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("Y")) {
                continueTest = false;
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Load words from a file
    private static String[] loadWordsFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            StringBuilder sb = new StringBuilder();
            while (fileScanner.hasNext()) { // Read words from file
                sb.append(fileScanner.next()).append(" ");
            }
            fileScanner.close();
            return sb.toString().split("\\s+"); // Split words into an array
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            return new String[0]; // Return an empty array if file is not found
        }
    }
}

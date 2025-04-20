import java.io.*;
import java.util.Scanner;

public class JavaNotesManager {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String file1 = "JavaFile1.txt";
        String file2 = "JavaFile2.txt";

        System.out.println("Enter content for JavaFile1.txt (type 'exit' in a new line to finish):");
        StringBuilder text1 = new StringBuilder();
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            text1.append(input).append("\n");
        }
        writeToFile(file1, text1.toString());
        System.out.println("JavaFile1.txt created successfully.");

        System.out.println("\n--- Content of JavaFile1.txt ---");
        readFile(file1);

        System.out.println("\nEnter content for JavaFile2.txt (type 'exit' in a new line to finish):");
        StringBuilder text2 = new StringBuilder();
        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            text2.append(input).append("\n");
        }
        writeToFile(file2, text2.toString());
        System.out.println("JavaFile2.txt created successfully.");

        appendFileContent(file1, file2);
        System.out.println("JavaFile1.txt content appended to JavaFile2.txt.");

        analyzeFile(file1);

        searchWordInFile(file1, "polymorphism");
    }

    public static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }

    public static void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void appendFileContent(String sourceFile, String targetFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, true));
        String line;
        writer.newLine();
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    public static void analyzeFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int charCount = 0, wordCount = 0, lineCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            lineCount++;
            charCount += line.length();
            wordCount += line.split("\\s+").length;
        }
        reader.close();
        System.out.println("\n--- Analysis of JavaFile1.txt ---");
        System.out.println("Total Characters: " + charCount);
        System.out.println("Total Lines: " + lineCount);
        System.out.println("Total Words: " + wordCount);
    }

    public static void searchWordInFile(String filename, String wordToFind) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int lineNumber = 0;
        int occurrences = 0;
        System.out.println("\n--- Searching for word: \"" + wordToFind + "\" ---");
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (line.toLowerCase().contains(wordToFind.toLowerCase())) {
                System.out.println("Found at line: " + lineNumber);
                String[] words = line.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (word.equals(wordToFind.toLowerCase())) {
                        occurrences++;
                    }
                }
            }
        }
        reader.close();
        System.out.println("Total occurrences of \"" + wordToFind + "\": " + occurrences);
    }
}

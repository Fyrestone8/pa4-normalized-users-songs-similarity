import java.util.stream.Collectors;
import java.io.*;
import java.util.*;

public class SimilarityCalculator {

    public static void main(String[] args) {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 4) {
            System.err.println("Usage: java SimilarityCalculator <songs_file> <rankings_file> <song_similarity_output_file> <user_similarity_output_file>");
            System.exit(1);
        }

        // Parse command-line arguments
        String songsFile = args[0];
        String rankingsFile = args[1];
        String songSimilarityOutputFile = args[2];
        String userSimilarityOutputFile = args[3];

        try {
            // Read songs and rankings data from input files
            List<String> songs = readLinesFromFile(songsFile);
            List<List<Integer>> rankings = readRankingsFromFile(rankingsFile);

            // Calculate song similarity and write to output file
            calculateAndWriteSongSimilarity(songs, rankings, songSimilarityOutputFile);

            // Calculate user similarity and write to output file
            calculateAndWriteUserSimilarity(rankings, userSimilarityOutputFile);

            System.out.println("Similarity calculation completed successfully.");
        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to read lines from a file and return as a list of strings
    private static List<String> readLinesFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    // Method to read rankings from a file and return as a list of lists of integers
    private static List<List<Integer>> readRankingsFromFile(String filename) throws IOException {
        List<List<Integer>> rankings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> userRankings = Arrays.stream(line.split(" "))
                                              .map(Integer::parseInt)
                                              .collect(Collectors.toList());
                rankings.add(userRankings);
            }
        }
        return rankings;
    }

    // Method to calculate song similarity and write to output file
    private static void calculateAndWriteSongSimilarity(List<String> songs, List<List<Integer>> rankings, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < songs.size(); i++) {
                for (int j = i + 1; j < songs.size(); j++) {
                    double similarity = calculateSimilarity(rankings, i, j);
                    writer.write(songs.get(i) + "," + songs.get(j) + "," + similarity + "\n");
                }
            }
        }
    }

    // Method to calculate user similarity and write to output file
    private static void calculateAndWriteUserSimilarity(List<List<Integer>> rankings, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < rankings.size(); i++) {
                for (int j = i + 1; j < rankings.size(); j++) {
                    double similarity = calculateSimilarity(rankings.get(i), rankings.get(j));
                    writer.write(i + "," + j + "," + similarity + "\n");
                }
            }
        }
    }

    // Method to calculate similarity between two lists of rankings
    private static double calculateSimilarity(List<Integer> rankings1, List<Integer> rankings2) {
        double similarity = 0.0;
        for (int i = 0; i < rankings1.size(); i++) {
            if (rankings1.get(i) != -1 && rankings2.get(i) != -1) { // Exclude signal value (-1)
                similarity += rankings1.get(i) * rankings2.get(i);
            }
        }
        return similarity;
    }

    // Method to calculate similarity between two songs (using rankings data)
    private static double calculateSimilarity(List<List<Integer>> rankings, int songIndex1, int songIndex2) {
        double similarity = 0.0;
        for (List<Integer> userRankings : rankings) {
            if (userRankings.get(songIndex1) != -1 && userRankings.get(songIndex2) != -1) { // Exclude signal value (-1)
                similarity += userRankings.get(songIndex1) * userRankings.get(songIndex2);
            }
        }
        return similarity;
    }
}
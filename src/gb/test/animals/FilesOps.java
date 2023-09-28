package gb.test.animals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesOps {

    public static void WriteFile(String fileName, String saveIt) {
        Path filePath = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(saveIt, 0, saveIt.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static String ReadFile(String fileName) {
        String line = null;
        File file = new File(fileName);
        if (file.exists()) {
            Path filePath = Path.of(fileName);
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                line = reader.readLine();
                return line;
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        return line;
    }
}

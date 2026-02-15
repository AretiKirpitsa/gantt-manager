package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class FileHandler {
    private final String filename;
    private FileWriter fileWriter;
    public FileHandler(String filename) {
        this.filename = filename;
    }
    public void writeToFile(String data) throws IOException {
        try (FileWriter writer = new FileWriter(this.filename)) {
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + this.filename);
            throw e; // Rethrow the exception
        }
    }

    public FileWriter createWriterFD() throws IOException {
        try {
            fileWriter = new FileWriter(this.filename);
            return fileWriter;
        } catch (IOException e) {
            System.err.println("Error creating writer for file: " + this.filename);
            throw e;
        }
    }

    public void closeFD() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing file: " + this.filename);
        }
    }

  

    public String readFromFile() throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + this.filename, e);
        }
        return data.toString();
    }
}
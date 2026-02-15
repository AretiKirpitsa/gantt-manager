package parser;

import dom2app.SimpleTableModel;
import dom.Task;
import java.io.*;
import java.util.*;

public class TaskReader{
	private SimpleTableModel s; 
	
	public TaskReader() {
			
	} 
	private FileReader file_reader;
    private BufferedReader buffered_reader;
    private String filepath;

    /**
     * @message file_reader
     * @brief Getter for file_reader
     * @return file_reader
     */
    private FileReader file_reader() {
        return this.file_reader;
    }

    /**
     * @message buffered_reader
     * @brief Getter for buffered_reader
     * @return buffered_reader
     */
    private BufferedReader buffered_reader() {
        return this.buffered_reader;
    }

    /**
     * @message filepath
     * @brief Getter for filepath
     * @return filepath
     */
    private String filepath() {
        return this.filepath;
    }

    /**
     * @message file_does_not_exist
     * @brief Check if the path file in the filesystem
     * @return true if it does not exist
     */
    private boolean file_does_not_exist() {
        /* Check beforehand if the file file_does_not_exist */
        File file = new File(filepath());
        return !file.exists();
    }

    /**
     * @message open
     * @brief Open a file in read mode
     * @param filepath -> The path to open
     * @return -> a boolean signaling if the opening was successful
     */
    public boolean open(String filepath) {
        this.filepath = filepath;

        if(file_does_not_exist()) return false;

        try {
            this.file_reader = new FileReader(filepath());
            this.buffered_reader = new BufferedReader(file_reader());
            return true;
        }
        catch(FileNotFoundException e) {
            System.out.println("There was an error with creating the input file. | `" + filepath() + '`');
            return false;
        }
    }

    /**
     * @message read_line
     * @brief Read a new line from the file descriptor
     * @return The line read
     */
    public String read_line() {
        try {
            return buffered_reader().readLine();
        }
        catch(IOException e) {
            System.out.println("There was an error with reading a line from the input file. | `" + filepath() + '`');
            return null;
        }
    }

    /**
     * @message close
     * @brief Attempts to close the buffers to avoid memory overflows
     */
    public void close() {
        try {
            file_reader().close();
            buffered_reader().close();
        }
        catch(IOException e) {
            System.out.println("There was an error with closing the file descriptor. | `" + filepath() + '`');
        }
    }
}
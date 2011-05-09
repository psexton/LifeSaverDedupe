/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.psexton.lifesaverdedupe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PSexton
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length < 1)
            System.err.println("No input file provided");
        else
            new Main(args[0]);
    }
    
    /**
     * 
     * @param filePath Path to original MessageLog file
     */
    public Main(String filePath) {
        try {
            // Read in the old file, dedupe and write out to a new file
            //     MessageLog ===> MessageLogNew
            File original = new File(filePath);
            File copy = new File(filePath + "New");
            
            ArrayList<String> entries = readOriginal(original);
            dedupe(entries);
            
            // If that succeeded, rename the files
            //     MessageLogNew -> MessageLog
            //     MessageLogNew -> MessageLog
            
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Reads in MessageLog file into a list of entries
     * @param original MessageLog file
     * @return ArrayList of entries
     * @throws FileNotFoundException if file couldn't be found
     * @throws IOException rethrown from BufferedReader.readLine
     */
    private ArrayList<String> readOriginal(File original) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(original));
        
        // First line is the message count
        int numEntries = Integer.parseInt(reader.readLine());
        
        // Read rest of file into List, one entry per line
        ArrayList<String> entries = new ArrayList<String>(numEntries); // might as well preallocate space
        String line;
        while((line = reader.readLine()) != null) {
            entries.add(line);
        }
        reader.close();
        if(entries.size() != numEntries) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, "numEntries and length of array do not match (" 
                    + String.valueOf(numEntries) + ", " + String.valueOf(entries.size()) + ")");
        }
        
        return entries;
    }
    
    private void dedupe(ArrayList<String> entries) {
        for(String entry : entries) {
            System.out.println(entry);
        }
    }
}

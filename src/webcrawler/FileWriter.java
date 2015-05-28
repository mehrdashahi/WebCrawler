/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Represents a file writer object responsible for handling functionality related 
 * to files and directories.
 * @author mehrdad
 */
public class FileWriter {
    
    
    /* the content to be written to a file, **/
    private String content;
    /* the name of the file to be written to**/
    private File fileName; 
    
    
    /*
    Constructs a filewriter given a filename, a search word, a success status(
    whether or not the search phrase was found and the content of the html to be
    written to the file.
    */
    public FileWriter(String content, String fileName, String searchWord, Boolean success) throws FileNotFoundException, UnsupportedEncodingException { 
        
        
        this.content = content;
       
        File fDir = new File("C:\\Pages with " + searchWord);
        File nDir = new File("C:\\Pages without " + searchWord);
        
        fDir.mkdir();
        nDir.mkdir();
        
        if (success) { 
            File file1 = new File(fDir, fileName + ".txt");

            PrintWriter writer = new PrintWriter(file1);
            writer.println(content);
            writer.close();
        } else { 
            
            File file2 = new File(nDir, fileName + ".txt");
            PrintWriter writer = new PrintWriter(file2);
            writer.println(content);
            writer.close();
            
        }
          
        
    }
    
    

 
    
}

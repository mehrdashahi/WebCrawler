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
 *
 * @author mehrdad
 */
public class FileWriter {
    
    
    
    private String content;
    private File fileName; 
    
    
    
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

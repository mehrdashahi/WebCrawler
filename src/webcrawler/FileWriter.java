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
    
    public FileWriter(String content, String fileName) throws FileNotFoundException, UnsupportedEncodingException { 
        
        this.content = content;
        PrintWriter writer = new PrintWriter(fileName + ".txt", "UTF-8");
        writer.println(content);
        writer.close();
        
    }

 
    
}

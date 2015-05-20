package webcrawler;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SpiderTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        
      Crawler crawler = new Crawler();
        
      String URLAddress;
      String keyWord;
 
      Scanner in = new Scanner(System.in);
 
      System.out.println("Enter the URLAddress: ");
      URLAddress = in.nextLine();
     
      System.out.println("Enter a keyword to search for:");
      keyWord = in.nextLine();
        
      crawler.search(URLAddress, keyWord);
      
    }
}

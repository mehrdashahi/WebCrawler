package webcrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Crawler
{
  private static final int MAX_PAGES_TO_SEARCH = 10;
  private Set<String> pagesVisited = new HashSet<String>();
  private Queue<String> pagesToVisit = new LinkedList<String>();

  
  /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for
   */
  public void search(String url, String searchWord) throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
      String currentUrl;
      CrawlerLeg leg = new CrawlerLeg();
      int pageNumber = 1;
      currentUrl = url;
      this.pagesToVisit.add(currentUrl);
      //leg.crawl(currentUrl);
      this.pagesVisited.add(currentUrl);
      
      //HTMLProcessor processor = new HTMLProcessor();
      
      
      while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH & !this.pagesToVisit.isEmpty())
          
      {
          
          
        currentUrl = this.pagesToVisit.remove();
        
        
        leg.crawl(currentUrl);
        boolean success = leg.searchForWord(searchWord);  
        this.pagesVisited.add(currentUrl);
        
        
        // Search the HTML document for the keyword and save in a file if found. 
        leg.processDoc(success, searchWord, currentUrl);
                
        for (String link: leg.getLinks()) { 
            
            

           if (!this.pagesVisited.contains(link)) { 
               
                this.pagesToVisit.add(link);       
            } 
        }
        continue;
        
      }
      }
      
    }
  
   
 
          
      

  


 

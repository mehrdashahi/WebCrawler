package webcrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/** Represents a crawler which receives a URL address and a search phrase, opens HTML pages linked to the URL 
 * and stores HTML pages in separate directories depending on whether or not they contain the search phrase. 
 * 
 * @author mehrdad 
 */
public class Crawler
{
  /* Maximum number of pages the crawler will search for **/
  private static final int MAX_PAGES_TO_SEARCH = 10;
  /* Stores the HTML pages that have been visited **/ 
  private Set<String> pagesVisited = new HashSet<String>();
  /* Stores the pages that must be visited by the crawler **/
  private Queue<String> pagesToVisit = new LinkedList<String>();

  
  /**
   * Starts the search for the search phrase (represented by "searchWord) in the
   * web site starting from the given URL (represented by "url"). 
   * @param url
   *            - starting point of the search
   * @param searchWord
   *            - the word being searched 
   *   
   */
  public void search(String url, String searchWord) throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
      String currentUrl;
      CrawlerLeg leg = new CrawlerLeg();
      int pageNumber = 1;
      currentUrl = url;
      this.pagesToVisit.add(currentUrl);
      this.pagesVisited.add(currentUrl);
      
      // As long as there are pages to visit and the maximum number of pages have not been reached continue 
      // Exploring urls. 
      while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH & !this.pagesToVisit.isEmpty())
          
      {
          
          
        currentUrl = this.pagesToVisit.remove();
        
        
        leg.crawl(currentUrl);
        boolean success = leg.FindWord(searchWord);  
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
  
   
 
          
      

  


 

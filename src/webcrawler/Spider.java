package webcrawler;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Spider
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
  public void search(String url, String searchWord) throws FileNotFoundException, UnsupportedEncodingException
  {
      String currentUrl;
      SpiderLeg leg = new SpiderLeg();
      int pageNumber = 1;
      currentUrl = url;
      this.pagesToVisit.add(currentUrl);
      //leg.crawl(currentUrl);
      this.pagesVisited.add(currentUrl);
      int pageNum = 1; 
      
      while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH & !this.pagesToVisit.isEmpty())
      {
          
          
        currentUrl = this.pagesToVisit.remove();
        
        
        leg.crawl(currentUrl);
        boolean success = leg.searchForWord(searchWord);  
        this.pagesVisited.add(currentUrl);
        
        if (success)
            {
                System.out.println("Found " + searchWord + " at " + currentUrl);
                leg.searchResult("Page" + Integer.toString(pageNum) + ".txt");
                pageNum++;
            }
        else 
            { 
                System.out.println("Did not find it at" + currentUrl);
            }
        for (String link: leg.getLinks()) { 
            

           if (!this.pagesVisited.contains(link)) { 
                
                 
                    this.pagesToVisit.add(link);
       
                  }
            
        }
        continue;
       
        
      }
      
      
      

    }
  
   /**
   * Returns the next URL to visit (in the order that they were found). We also do a check to make
   * sure this method doesn't return a URL that has already been visited.
   * 
   * @return
   */
  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove();
      } while(this.pagesVisited.contains(nextUrl)); 
      this.pagesVisited.add(nextUrl);
      return nextUrl;
  }
          
      }
      //System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
  


 
package webcrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Represents a crawler's main component. Responsible for making HTTP requests, 
 * checking weather the url is HTML and retrieving the contents of the web
 * @author mehrdad
 */
public class CrawlerLeg
{
    /* Mimicing a browser's behaviour **/
    public static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    /* keeps the count of files storing pages that contain the search phrase **/
    private int successNumber = 1; 
    /* keeps the count of files storing pages that do not contain the search phrase **/
    private int failNumber = 1;
    

    /**
     * Returns a boolean value indicating whether or not the URL represented by
     * "url" was successfully crawled. A successful crawl means the page was HTML 
     * and its contents can be read by the crawler. The method also gathers up all
     * the links from the url to be later crawled. 
     * @param url
     * @return 
     */
    
    public boolean crawl(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            
            if(connection.response().statusCode() == 200) //  HTTP OK status
                                                         // Webpage can be retrieved
                                                          
            {
                System.out.println("\n**Retrieved** web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Non-HTML webpage");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for (Element link : linksOnPage)
            {
                
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(IOException e)
            // Unsuccessful 
        {
            // We were not successful in our HTTP request
            return false;
        }
    }


    /**
     * Searches the body of the HTML document that is retrieved. This method must
     * only be called after a successful crawl has been performed. 
     * 
     * @param searchWord
     *            - The string to search for
     * @return whether or not the string was found
     */
    public boolean FindWord(String searchWord)
    {
        // Defensive coding. This method should only be used after a successful crawl.
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! crawl method should be called first ");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }
    
    /**
     * Creates a file and stores the information of the url. 
     * @param fileName
     * @param searchWord
     * @param success
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public void searchResult(String fileName, String searchWord, Boolean success) throws FileNotFoundException, UnsupportedEncodingException, IOException { 
        
        String bodyText = this.htmlDocument.body().text(); 
        FileWriter writer = new FileWriter(bodyText, fileName, searchWord, success);
        
      
        
    }
    /**
     * Creates text files according to whether the url contains the search phrase
     * or not. 
     * @param success
     * @param searchWord
     * @param currentUrl
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
   
    public void processDoc(Boolean success, String searchWord, String currentUrl) throws UnsupportedEncodingException, IOException { 
        
        if (success) { 
            System.out.println("Found " + "\""+searchWord+"\"" + " at " + currentUrl);
            this.searchResult("Page" + Integer.toString(successNumber) + ".txt", searchWord, success);
        
            this.successNumber++;
        } else { 
            System.out.println("Did not find " + "\""+searchWord+"\"" + " at " + currentUrl);
            this.searchResult("Page" + Integer.toString(failNumber) + ".txt", searchWord, success);
            
        }
        
        
    }
    
    /**
     * Returns a list containing all the links of this crawler leg.
     * @return 
     */
    public List<String> getLinks()
    {
        return this.links;
    }

}
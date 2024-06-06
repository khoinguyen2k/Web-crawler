package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookScraper {
   public static String getThumbnailUrl(Document doc) {
      Elements imgContainers = doc.select("div[class=thumbnail]");
      Element imgContainer = imgContainers.first();
      String url = imgContainer.select("img").attr("src");
      return url;
   }

   public static String fixedUrl(String url) {
      String res = null;
      if (url.charAt(1) == '/') //      //asdasd.com
         res = "https:" + url;
      else res = "https://tiki.vn" + url; //    /asdasd.com
      return res;
   }

   public static int getRandomNumber(int lowbound, int upperbound) {
      Random random = new Random();
      return lowbound + random.nextInt(upperbound - lowbound);
   }

   public static String getDescription(Document doc) {
      Elements descriptionContainers = doc.select("div[class=content]");
      Element descriptionContainer = descriptionContainers.first();
      Elements descriptionParagraphs = descriptionContainer.select("p");
      String res = descriptionParagraphs.first().text();
      return res;
   }

   public static String[] categoryList = {"văn học", "kinh tế", "kỹ năng sống", "kiến thức tổng hợp", "chính trị - pháp lý"};

   public static String getCategory() {
      return categoryList[getRandomNumber(0, categoryList.length)];
   }

   public static String getAuthor(Document document) {
      String author = document.select("a[data-view-id=pdp_details_view_author]").text();
      if (author.length() <= 2) return "Unknown";
      else return author;
   }

   public static List<Book> getListBook(int pageNumber) throws IOException {
      List<Book> bookList = new ArrayList<>();
      String url = "https://tiki.vn/search?q=manga&page=" + pageNumber;
      Document doc = Jsoup.connect(url).get();
      Elements bookElements = doc.select("a[class=product-item]");
      int bookAmount = bookElements.size();

      for (int i = 0; i < bookAmount; i++) {
         Element bookElement = bookElements.get(i);
         String bookUrl = bookElement.attr("href");
         bookUrl = fixedUrl(bookUrl);
//         System.out.println(bookUrl);
         Document bookDoc = Jsoup.connect(bookUrl).get();

         String name = bookDoc.select("h1[class=title]").text();
         int price = getRandomNumber(50, 200) * 1000;
         String author = getAuthor(bookDoc);
         String description = getDescription(bookDoc);
         String thumbnailUrl = getThumbnailUrl(bookDoc);
         double rating = getRandomNumber(37, 49) / 10.0;
         String category = getCategory();
         int quantity = getRandomNumber(1, 30);

         Book book = new Book(name, thumbnailUrl, price, description, author);
         bookList.add(book);
         System.out.println("get book " + i);

      }
      return bookList;
   }

   public static void main(String[] args) throws IOException {
      List<Book> bookList = getListBook(1);
   }
}


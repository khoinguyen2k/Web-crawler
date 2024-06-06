package crawler;

public class Book {
   public String name;
   public String thumbnailUrl;
   public int price;
   public String description;
   public String author;
   public int categoryId;

   public Book(String name, String thumbnailUrl, int price, String description, String author) {
      this.name = name.replace("'", "_");
      this.thumbnailUrl = thumbnailUrl;
      this.price = price;
      this.description = description.length() <= 2?
              "This is default book description, it says books are made from wood and has content, that s it!"
              : description.replace("'", "_");
      this.author = author.replace("'", "_");
      this.categoryId = BookScraper.getRandomNumber(4,9);
   }

   @Override
   public String toString() {
      return "crawler.Book{" +
              "name='" + name + '\'' +
              ", thumbnailUrl='" + thumbnailUrl + '\'' + '\n' +
              ", price=" + price +
              ", description='" + description + '\'' +
              ", author='" + author + '\'' +
              '}';
   }
}

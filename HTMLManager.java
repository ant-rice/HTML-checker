import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags;
  
   public HTMLManager(Queue<HTMLTag> html) {
      if(html == null) { 
         throw new IllegalArgumentException(); 
      }
      
      tags = new LinkedList<>();
      while (!html.isEmpty()) {
            tags.add(html.poll());
      }
   }
  
   public Queue<HTMLTag> getTags() {
      return tags;
   }
   
   public String toString() {
      return tags.toString();
   
   }
   
   public void fixHTML() {
      Stack<HTMLTag> fixThis = new Stack<>();
      // add fix logic here
   }
}

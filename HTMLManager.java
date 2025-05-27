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
      String result = "";
      int size = tags.size();
      
      for (int i = 0; i < size; i++) {
         HTMLTag tag = tags.poll();
         result += tag.toString().trim() + " ";
         tags.add(tag);
      }
      return result.trim();
   }
   
   public void fixHTML() {
      Stack<HTMLTag> openTags = new Stack<>();
      Queue<HTMLTag> fixed = new LinkedList<>();
      
      while(!tags.isEmpty()) {
         HTMLTag current = tags.poll();
         if(current.isSelfClosing()) {
            fixed.add(current);
         }
         else if(current.isOpening()) {
            openTags.push(current);
            fixed.add(current);
            
         }
         else if(current.isClosing()) {
            if(!openTags.isEmpty() && openTags.peek().matches(current)) {
               fixed.add(current);
               openTags.pop();
            }
            else if(!openTags.isEmpty() && !openTags.peek().matches(current)) {
               fixed.add(openTags.pop().getMatching());
            }  
         }
      }
      
      while (!openTags.isEmpty()) {
         fixed.add(openTags.pop().getMatching());
      }
      
      tags = fixed;
   }
}
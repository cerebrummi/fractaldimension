package fa.walksets;

import java.util.ArrayList;
import java.util.StringJoiner;

public class An implements Walkset
{
   private ArrayList<Integer> list = new ArrayList<>();

   public void addElement(int n)
   {
      list.add(n);
   }

   public boolean isEmpty()
   {
      return list.isEmpty();
   }

   @Override
   public String toString()
   {
      String start = "An < ";
      StringJoiner joiner = new StringJoiner(",");
      for (int element : list)
      {
         joiner.add(String.valueOf(element));
      }
      String end = " >";
      return start + joiner.toString() + end;
   }
}

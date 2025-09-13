package fa.walksets;

import java.util.ArrayList;
import java.util.StringJoiner;

import enums.Symbol;

public class APn implements Walkset
{
   private ArrayList<Symbol> list = new ArrayList<>();

   public void addElement(Symbol n)
   {
      list.add(n);
   }

   public ArrayList<Symbol> getList()
   {
      return list;
   }

   @Override
   public String toString()
   {
      String start = "APn < ";
      StringJoiner joiner = new StringJoiner(",");
      for (Symbol element : list)
      {
         joiner.add(element.name());
      }
      String end = " >";
      return start + joiner.toString() + end;
   }
}

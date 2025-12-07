package fa.walksets;

import java.util.Arrays;
import java.util.LinkedList;

import enums.Symbol;

public class CPn implements Walkset
{
   private LinkedList<Symbol> list = new LinkedList<>();

   public CPn()
   {
      list.add(Symbol.L);
   }

   public void fractalProcedureMove()
   {
      Symbol element = list.pollFirst();
      list.add(element);
   }

   public void fractalProcedureCopy(int n)
   {
      LinkedList<Symbol> copiedList = new LinkedList<>();

      for (int i = 0; i < n; i++)
      {
         for (Symbol element : list)
         {
            copiedList.add(element);
         }
      }

      list = copiedList;
   }

   public void fractalProcedureChange(int n)
   {
      LinkedList<Symbol> copy = new LinkedList<>();
      for (Symbol element : list)
      {
         copy.add(element);
      }

      for (int i = n-1; i < copy.size(); i+=n)
      {
         if (list.get(i).equals(Symbol.L))
         {
            list.set(i, Symbol.M);
         }
      }
   }

   public Symbol getLeftmostElement()
   {
      return list.getFirst();
   }

   @Override
   public String toString()
   {
      return "CPn = < " + Arrays.toString(list.toArray()) + " >";
   }

   public LinkedList<Symbol> getPattern()
   {
      return new LinkedList<Symbol>(list);
   }

   public String toPlainString()
   {
      StringBuilder builder = new StringBuilder(list.size());
      for (Symbol element : list)
      {
         builder.append(element.name());
      }
      return builder.toString();
   }
}

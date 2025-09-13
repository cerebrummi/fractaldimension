package fa.walksets;

public class Cn implements Walkset
{
   private int leftmostElement = 1;

   public int cutLeftmostElement()
   {
      return leftmostElement++;
   }
   
   public int getLeftmostElement()
   {
      return leftmostElement;
   }

   @Override
   public String toString()
   {
      return "Cn = < " + leftmostElement + ", ... infinity >";
   }
}
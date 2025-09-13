package fa.walksets;

public class Bn implements Walkset
{
   private Integer n;

   public Integer cutN()
   {
      Integer copyOfN = n;
      n = null;
      return copyOfN;
   }

   public Integer getN()
   {
      return n;
   }

   public void setN(int n)
   {
      this.n = n;
   }

   public boolean isEmpty()
   {
      return n == null;
   }

   @Override
   public String toString()
   {
      return "Bn = < " + (n == null ? "" : n) + " >";
   }
}

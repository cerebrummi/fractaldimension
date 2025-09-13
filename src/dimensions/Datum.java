package dimensions;

public class Datum
{
   private long boxSizeEpsilon;
   private long numberOfFilledWindows;

   public Datum(long boxSizeEpsilon, long numberOfFilledWindows)
   {
      this.boxSizeEpsilon = boxSizeEpsilon;
      this.numberOfFilledWindows = numberOfFilledWindows;
   }

   public long getBoxSizeEpsilon()
   {
      return boxSizeEpsilon;
   }

   public void setBoxSizeEpsilon(long boxSizeEpsilon)
   {
      this.boxSizeEpsilon = boxSizeEpsilon;
   }

   public long getNumberOfFilledWindows()
   {
      return numberOfFilledWindows;
   }

   public void setNumberOfFilledWindows(long numberOfFilledWindows)
   {
      this.numberOfFilledWindows = numberOfFilledWindows;
   }

   @Override
   public String toString()
   {
      return "Datum [boxSizeEpsilon=" + boxSizeEpsilon
            + ", numberOfFilledWindows=" + numberOfFilledWindows + "]";
   }
}
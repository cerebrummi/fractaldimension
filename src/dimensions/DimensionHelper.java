package dimensions;

import java.util.LinkedList;

public class DimensionHelper
{

   public double calculateDimension(LinkedList<Datum> data)
   {
      double sumLnNumberOfFilledWindows = 0D;
      double sumLn1divBoxSize = 0D;

      for (int i = 0; i < data.size(); i++)
      {
         // for calculating mean
         sumLnNumberOfFilledWindows += Math
               .log((double)data.get(i).getNumberOfFilledWindows());

         sumLn1divBoxSize += Math.log(1D / (double)data.get(i).getBoxSizeEpsilon());
      }

      double meanLnNumberOfFilledWindows = sumLnNumberOfFilledWindows
            / data.size();
      double meanLn1divBoxSize = sumLn1divBoxSize / data.size();

      double dimensionTop = 0D;
      double dimensionBottom = 0D;

      for (int i = 0; i < data.size(); i++)
      {
         double lnOfFilledWindows = Math
               .log((double)data.get(i).getNumberOfFilledWindows());
         double deltaLnOfFilledWindows = lnOfFilledWindows
               - meanLnNumberOfFilledWindows;

         double lnOf1divEpsilon = Math
               .log(1D / (double)data.get(i).getBoxSizeEpsilon());
         double deltaLnOf1divEpsilon = lnOf1divEpsilon - meanLn1divBoxSize;

         dimensionTop += (deltaLnOfFilledWindows * deltaLnOf1divEpsilon);
         dimensionBottom += Math.pow(deltaLnOf1divEpsilon, 2D);      
      }

      return dimensionTop / dimensionBottom;
   }

   
}

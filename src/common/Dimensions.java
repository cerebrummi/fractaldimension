package common;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import dimensions.CountCP;
import dimensions.Datum;
import dimensions.DimensionHelper;
import enums.Symbol;

/**
 * Dimensions operates with SFA
 */
public class Dimensions
{
   private SFA sfa = new SFA();
   private CountCP countCP;
   private DimensionHelper dimensionHelper;

   public Dimensions() throws UnsupportedEncodingException
   {
      //sfa.step();
      countCP = new CountCP();
      dimensionHelper = new DimensionHelper();
   }

   public double step()
   {
      sfa.step();
      LinkedList<Symbol> pattern = sfa.getPattern();
      LinkedList<Datum> data = countCP.countLs(pattern);
      return dimensionHelper.calculateDimension(data);
   }

   public int getStepNumber()
   {
      return sfa.getStepNumber();
   }

}

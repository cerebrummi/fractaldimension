package common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import blox29prime.Blox29PrimeResource;
import cp17partial.PartialSieves17;
import dimensions.CountCP;
import dimensions.Datum;
import dimensions.DimensionHelper;

/**
 * Dimensions3 operiert auf Basis früher erstellter Datein. Diese wurden zu
 * großen Partialsieben erweitert.
 */
public class Dimensions3
{
   private Blox29PrimeResource blox29prime;
   private CountCP countCP;
   private DimensionHelper dimensionHelper;
   private BigDecimal currentPrime;

   public Dimensions3() throws UnsupportedEncodingException
   {
      countCP = new CountCP();
      dimensionHelper = new DimensionHelper();
      blox29prime = new Blox29PrimeResource(17);
   }

   public double step(int i)
   {
      currentPrime = blox29prime.nextPrime();

      LinkedList<Datum> data = countCP
            .countLs("CP_17partial_" + currentPrime + ".txt", PartialSieves17.class);

      return dimensionHelper.calculateDimension(data);
   }

   public String getStepNumber()
   {
      return currentPrime.toPlainString();
   }
}

package common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import blox29prime.Blox29PrimeResource;
import dimensions.CountCP;
import dimensions.Datum;
import dimensions.DimensionHelper;
import resources.FullSieves;

/**
 * Dimensions2 operiert auf Basis früher erstellter Datein, vollständige Siebe
 */
public class Dimensions2
{

   private Blox29PrimeResource blox29prime;
   private CountCP countCP;
   private DimensionHelper dimensionHelper;
   private BigDecimal currentPrime;

   public Dimensions2() throws UnsupportedEncodingException
   {
      countCP = new CountCP();
      dimensionHelper = new DimensionHelper();
      blox29prime = new Blox29PrimeResource(2);
   }

   public double step(int i)
   {
      currentPrime = blox29prime.nextPrime();

      LinkedList<Datum> data = countCP
            .countLs("CP_" + currentPrime + ".txt", FullSieves.class);

      return dimensionHelper.calculateDimension(data);
   }

   public String getStepNumber()
   {
      return currentPrime.toPlainString();
   }

}
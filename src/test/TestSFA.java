package test;

import common.SFA;

public class TestSFA
{
   static String[] patterns = { "L", "LM", "MLMLMM", "LMLMMM",
         "MLMMMLMLMMMLMLMMMLMMMMMLMLMMMM" };

   public static void main(String[] args)
   {
      SFA sfa = new SFA();

      for (int i = 0; i < patterns.length; i++)
      {
         sfa.step();
         String wanted = patterns[i];
         String aktual = String.valueOf(sfa.getPattern()).replace("[", "")
               .replace("]", "").replace(",", "").replace(" ", "");
         if (!wanted.equals(aktual))
         {
            System.err.println("pattern i=" + i + " should be " + wanted
                  + " but was " + sfa.getPattern());
         }
      }

      System.out.println("Test SFA done");
   }

}

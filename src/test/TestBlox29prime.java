package test;

import blox29prime.Blox29PrimeResource;

public class TestBlox29prime
{
   private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
         41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

   public static void main(String[] args)
   {
      Blox29PrimeResource blox29prime = new Blox29PrimeResource(2);

      for (int i = 0; i < primes.length; i++)
      {
         int wanted = primes[i];
         int aktual = blox29prime.nextPrime().intValue();
         if(wanted != aktual)
         {
            System.err.println("i=" + i + " should be " + wanted
                  + " but was " + aktual);
         }
      }
      System.out.println("Test Blox29prime done");
   }

}

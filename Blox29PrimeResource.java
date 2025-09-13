package blox29prime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class Blox29PrimeResource
{
   private int[] missingPrimes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
   private LinkedList<String> lNumbers;
   private int pointer = 0;
   private int filePointer = 0;

   public Blox29PrimeResource(int startPrimeNumber)
   {
      lNumbers = new LinkedList<>();
      for (int prime : missingPrimes)
      {
         if(prime >= startPrimeNumber)
         {
            lNumbers.add(String.valueOf(prime));
         } 
      }

      loadNextFile();
   }

   public BigDecimal nextPrime()
   {
      if (pointer >= lNumbers.size())
      {
         if(!loadNextFile())
         {
            return null;
         }
         pointer = 0;
      }

      BigDecimal nextPrime = new BigDecimal(lNumbers.get(pointer));
      pointer++;
      
      return nextPrime;
   }

   private boolean loadNextFile()
   {
      String filename = "blox_" + filePointer + ".txt";

      URL path = Blox29PrimeResource.class.getResource(filename);
      File file = new File(path.getFile());

      try (FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis, "UTF-8");)
      {
         while (sc.hasNextLine())
         {
            lNumbers.add(sc.nextLine());
         }
      }
      catch (IOException e)
      {
         return false;
      }
      catch (Exception e)
      {
         return false;
      }
      
      filePointer++;
      return true;
   }
}

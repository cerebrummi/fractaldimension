package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import blox29prime.Blox29PrimeResource;

public class MakeSievesCP17partial
{
   private static byte[] letterM;
   private static byte[] dot;

   public static void main(String[] args)
   {
      try
      {
         letterM = "M".getBytes("UTF-8");
         dot = ".".getBytes("UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }

      Blox29PrimeResource primesFrom = new Blox29PrimeResource(17);
      Blox29PrimeResource primesTo = new Blox29PrimeResource(19);

      for (int f = 0; f < 100; f++)
      {
         String currentPrimeFrom = primesFrom.nextPrime().toPlainString();
         String currentPrimeTo = primesTo.nextPrime().toPlainString();

         long currentPrimeFromLong = new BigDecimal(currentPrimeFrom)
               .longValue();
         long currentPrimeToLong = new BigDecimal(currentPrimeTo).longValue();

         String fileFrom = "CP_17partial_" + currentPrimeFrom + ".txt";
         String fileTo = "./CP_17partial_" + currentPrimeTo + ".txt";

         try
         {
            Files.copy(new File(fileFrom).toPath(), new File(fileTo).toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }

         try (RandomAccessFile accesser = new RandomAccessFile(fileTo, "rw"))
         {
            long position = 0;
            long end = accesser.length();

            FileChannel channel = accesser.getChannel();

            byte[] b = new byte[1];
            for (; position < end; position++)
            {
               channel.position(position);
               b[0] = accesser.readByte();
               if (Byte.compare(b[0], dot[0]) != 0)
               {
                  break;
               }
            }
            if (currentPrimeFromLong != position)
            {
               System.err.println("position should be " + currentPrimeFrom
                     + " but was " + position);
            }

            for (long i = position; i < currentPrimeToLong; i++, position++)
            {
               channel.position(position);
               accesser.writeByte(dot[0]);
            }

            position--;
            position += currentPrimeToLong;

            for (long i = position; i < end; i += currentPrimeToLong, position += currentPrimeToLong)
            {
               channel.position(position);
               accesser.writeByte(letterM[0]);
            }

            accesser.close();
         }
         catch (FileNotFoundException e)
         {
            e.printStackTrace();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

}

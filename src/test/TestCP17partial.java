package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.LinkedList;

import common.SFA;
import cp17partial.PartialSieves17;
import enums.Symbol;

public class TestCP17partial
{
   private static byte[] letterL;
   private static byte[] letterM;
   private static byte[] dot;
   private static final long TEST = 37L;

   public static void main(String[] args)
   {

      try
      {
         letterL = "L".getBytes("UTF-8");
         letterM = "M".getBytes("UTF-8");
         dot = ".".getBytes("UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }

      SFA sfa = new SFA();
      do
      {
         sfa.step();
      } while (sfa.getStepNumber() < 17);

      String filename = "./CP_17partial_" + TEST + ".txt";
      File file = new File(filename);

      try (RandomAccessFile accesser = new RandomAccessFile(file, "r"))
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

         LinkedList<Symbol> pattern = sfa.getPattern();

         for (int j = ((int) TEST - 17); position < end; position++, j++)
         {
            channel.position(position);
            b[0] = accesser.readByte();
            if (Byte.compare(b[0], letterL[0]) == 0)
            {
               if (!pattern.get(j).equals(Symbol.L))
               {
                  System.out.println("pattern j=" + j + " should be L but was "
                        + pattern.get(j).name());
               }
            }
            else if (Byte.compare(b[0], letterM[0]) == 0)
            {
               if ((position + 1L) % 19L == 0)
               {
                  // okay new M
               }
               else if ((position + 1L) % 23L == 0)
               {
                  // okay new M
               }
               else if ((position + 1L) % 29L == 0)
               {
                  // okay new M
               }
               else if ((position + 1L) % 31L == 0)
               {
                  // okay new M
               }
               else if ((position + 1L) % TEST == 0)
               {
                  // okay new M
               }
               else if (!pattern.get(j).equals(Symbol.M))
               {
                  System.out.println("pattern j=" + j + " should be M but was "
                        + pattern.get(j).name());
               }
            }
            else
            {
               System.out.println("Jo mei woa sind wir den doa? " + b[0]);
            }
         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      System.out.println("Test CP17partial done");
   }
}

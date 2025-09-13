package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.LinkedList;

import common.SFA;
import enums.Symbol;
import resources.FullSieves;

public class TestFullSieves
{
   private static byte[] letterL;
   private static byte[] letterM;
   private static byte[] dot;
   
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
      sfa.step();
      
      for (int i = 0; i < 17; i++)
      {
         sfa.step();
         URL path = FullSieves.class.getResource("CP_"+sfa.getStepNumber()+".txt");
         File file = new File(path.getFile());

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
            long patternSize = end - position;
            long wanted = sfa.getPattern().size();
            if(wanted != patternSize)
            {
               System.err.println("pattern i=" + i + " should be size " + wanted
                     + " but was " + patternSize);
            }
            LinkedList<Symbol>pattern = sfa.getPattern();
            for (int j = 0; position < end; position++, j++)
            {
               channel.position(position);
               b[0] = accesser.readByte();
               if (Byte.compare(b[0], letterL[0]) == 0)
               {
                  if(!pattern.get(j).equals(Symbol.L))
                  {
                     System.err.println("pattern j=" + j + " should be L but was not.");
                  }
               }
               else if (Byte.compare(b[0], letterM[0]) == 0)
               {
                  if(!pattern.get(j).equals(Symbol.M))
                  {
                     System.err.println("pattern j=" + j + " should be M but was not.");
                  }
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
      }

      System.out.println("Test FullSieves done");
   }
}

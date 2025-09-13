package dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.Symbol;

public class CountCP
{
   private final byte[] letterL;
   // private final byte[] letterM;
   private final byte[] dot;
   private int[] prime = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
   private ArrayList<BigDecimal> boxSizeEpsilon = new ArrayList<>();

   public CountCP() throws UnsupportedEncodingException
   {
      letterL = "L".getBytes("UTF-8");
      // letterM = "M".getBytes("UTF-8");
      dot = ".".getBytes("UTF-8");
      BigDecimal remember = BigDecimal.ONE;
      for (int number : prime)
      {
         remember = remember.multiply(new BigDecimal(number));
         boxSizeEpsilon.add(remember);
      }
   }
   
   /** variant that reads from sfa pattern
    * 
    * @param pattern
    * @return
    */
   public LinkedList<Datum> countLs(LinkedList<Symbol> pattern)
   {
      LinkedList<Datum> data = new LinkedList<>();
      ArrayList<BigDecimal> windows = new ArrayList<>();
      
      long patternSize = pattern.size();

      findWindowBoxes(windows, patternSize);

      for (BigDecimal window : windows)
      {
         data.add(new Datum(window.longValue(),
               countLs(pattern, window)));
      }

      return data;
   }
   
   private long countLs(LinkedList<Symbol> pattern,
         BigDecimal window)
   {
      long numberOfFilledWindows = 0L;
      for (int i = 0; i < pattern.size() - window.intValue() + 1; i += window.intValue())
      {
         if(countLs(pattern.subList(i, i + window.intValue())))
               {
            numberOfFilledWindows++;
               }
      }
      return numberOfFilledWindows;
   }

   private boolean countLs(List<Symbol> subList)
   {
      for (Symbol symbol : subList)
      {
         if (Symbol.L.equals(symbol))
         {
            return true;
         }
      }
      return false;
   }

   /** variant that reads from files
    * 
    * @param fileAsString
    * @param clazz
    * @return
    */
   public LinkedList<Datum> countLs(String fileAsString, Class<?> clazz)
   {
      URL path = clazz.getResource(fileAsString);
      File file = new File(path.getFile());

      try (RandomAccessFile accesser = new RandomAccessFile(file, "r"))
      {
         return countLs(accesser, accesser.length());
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   private LinkedList<Datum> countLs(RandomAccessFile accesser, long end)
         throws IOException
   {
      LinkedList<Datum> data = new LinkedList<>();
      ArrayList<BigDecimal> windows = new ArrayList<>();

      long position = 0;
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

      findWindowBoxes(windows, patternSize);

      for (BigDecimal window : windows)
      {
         data.add(new Datum(window.longValue(),
               countWindowsWithLs(accesser, window, patternSize, position)));
      }

      return data;
   }

   private void findWindowBoxes(ArrayList<BigDecimal> windows, long patternSize)
   {
      for (BigDecimal window : boxSizeEpsilon)
      {
         if (window.compareTo(new BigDecimal(patternSize)) == -1 ||window.compareTo(new BigDecimal(patternSize)) == 0)
         {
            windows.add(window);
         }
      }
   }

   private long countWindowsWithLs(RandomAccessFile accesser, BigDecimal window,
         long patternSize, long start) throws IOException
   {
      long counter = 0;
      for (long i = start; i < accesser.length(); i += window.longValue())
      {
         if (readSubPattern(i, window.longValue(), accesser))
         {
            counter++;
         }
      }
      return counter;
   }

   private boolean readSubPattern(long i, long windowSize,
         RandomAccessFile accesser) throws IOException
   {
      long position = i;
      FileChannel channel = accesser.getChannel();

      byte[] b = new byte[1];
      for (; position < i + windowSize; position++)
      {
         if(position >= accesser.length())
         {
            return false;
         }
         channel.position(position);
         b[0] = accesser.readByte();
         if (Byte.compare(b[0], letterL[0]) == 0)
         {
            return true;
         }
      }

      return false;
   }
}

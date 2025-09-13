package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;


public class StartDimensions3
{
   final static int FILES = 100;
   
   public static void main(String[] args)
   {   
      try
      {
         File file = new File("result3.txt");
            
         FileOutputStream stream = new FileOutputStream(file);
         OutputStreamWriter writer = new OutputStreamWriter(stream,
               StandardCharsets.UTF_8);
         StringJoiner joiner = new StringJoiner("\n");
         
         Dimensions3 dimensions = new Dimensions3();
         for (int i = 0; i < FILES; i++)
         {
            double dimension = dimensions.step(i);
            joiner.add( dimensions.getStepNumber()+ "\t" + dimension );
            System.out.println(dimensions.getStepNumber()+ "\t" + dimension );
         }
         
         writer.write(joiner.toString());
         writer.flush();
         writer.close();
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}

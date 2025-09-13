package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class StartDimensions
{
   final static int NUMBER_OF_STEPS = 18;

   public static void main(String[] args)
   {
      try
      {
         File file = new File("result.txt");

         FileOutputStream stream = new FileOutputStream(file);
         OutputStreamWriter writer = new OutputStreamWriter(stream,
               StandardCharsets.UTF_8);
         StringJoiner joiner = new StringJoiner("\n");

         Dimensions dimensions = new Dimensions();

         for (int i = 1; i < NUMBER_OF_STEPS; i++)
         {
            double dimension = dimensions.step();
            joiner.add(dimensions.getStepNumber() + "\t" + dimension);
            System.out.println("StepNumber n = " + dimensions.getStepNumber()
                  + " Dimension = " + dimension);
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

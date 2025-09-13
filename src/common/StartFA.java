package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class StartFA
{
   final static int NUMBER_OF_STEPS = 19;

   public static void main(String[] args)
   {
      SFA sfa = new SFA();

      try
      {
         File file = new File("resultFA_19only.txt");

         FileOutputStream stream = new FileOutputStream(file);
         OutputStreamWriter writer = new OutputStreamWriter(stream,
               StandardCharsets.UTF_8);
         StringJoiner joiner = new StringJoiner("\n");

         for (int i = 0; i < NUMBER_OF_STEPS; i++)
         {
            sfa.step();
            if(i == 19)
            {
               joiner.add("=============== step number = " + sfa.getStepNumber() +" ==========================");
               joiner.merge(sfa.toStringJoiner());
            }
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

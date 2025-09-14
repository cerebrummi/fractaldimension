package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class StartFA
{
   final static int NUMBER_OF_STEPS = 17;

   public static void main(String[] args)
   {
      SFA sfa = new SFA();

      for (int i = 0; i < NUMBER_OF_STEPS; i++)
      {
         sfa.step();
         try
         {
            File file = new File("src/fa/FA_" + sfa.getStepNumber() + ".txt");
            
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream,
                  StandardCharsets.UTF_8);
            writer.write(sfa.toPlainString());
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
}

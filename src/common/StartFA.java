package common;

public class StartFA
{
  final static int NUMBER_OF_STEPS = 17;
  
   public static void main(String[] args)
   {
      SFA sfa = new SFA();
      sfa.printWalksets();
      
      for( int i = 0 ; i < NUMBER_OF_STEPS; i++)
      {
         System.out.println("========== step start ==========");
         sfa.step();
         //sfa.printWalksets();
         System.out.println(sfa.getStepNumber());
         System.out.println("========== step end ===========");
      }
   }
}


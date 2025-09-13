package common;

import java.util.LinkedList;

import enums.Symbol;
import fa.walksets.APn;
import fa.walksets.An;
import fa.walksets.BPn;
import fa.walksets.Bn;
import fa.walksets.CPn;
import fa.walksets.Cn;

/** A fractal algorithm shows prime number patterning.
 * 
 * @author heeren Heeren, B. (2025). A fractal algorithm shows prime number
 *         patterning. Zenodo. https://doi.org/10.5281/zenodo.16829092
 * 
 *         Lemma: The equivalence of cutting in C and copy- pasting in CP:
 * 
 *         Step number n = 3: <‾MLMLMM> This means: <MLMLMM,MLMLMM, MLMLMM,
 *         M...infinity
 * 
 *         Removing the first letter leaves: <LMLMM, MLMLMM, MLMLMM, M...
 *         infinity
 * 
 *         This can be rewritten as: <LMLMMM, LMLMMM, LMLMMM, ... infinity
 * 
 *         Thus step number n = 4 is: <‾LMLMMM>
 */

public class SFA
{
   // production line one
   private An walksetAn = new An();
   private Bn walksetBn = new Bn();
   private Cn walksetCn = new Cn();

   // production line two
   private APn walksetAPn = new APn();
   private BPn walksetBPn = new BPn();
   private CPn walksetCPn = new CPn();

   public SFA()
   {
      // starting state
      validateStartingState();
   }

   public void step()
   {
      /**
       * Update of walkset An-1 to An
       * 
       * The only element of Bn-1 is cut from Bn-1 and pasted into An-1 on the
       * right side. Thus set An-1 becomes An . At the start Bstart = <>. Since
       * there is no element in Bstart to cut A1 is an empty walkset.
       */
      if (walksetBn.getN() != null)
      {
         walksetAn.addElement(walksetBn.cutN());
      }

      /**
       * Update of walkset Bn-1 to Bn
       * 
       * The leftmost element of Cn-1 is cut and pasted into Bn-1 left empty be
       * the update of A. Thus Bn-1 again contains one element that is the step
       * number and thus becomes Bn. At the start Bstart = <> therefore as B is
       * filled for the first time it becomes B1.
       */
      walksetBn.setN(walksetCn.cutLeftmostElement());

      /**
       * Update of walkset Cn-1 to Cn
       * 
       * This has already taken place during the update of B by cutting the
       * leftmost element of Cn-1. Thus Cn-1 has become Cn.
       */
      // update walkset Cn
      // has already taken place during update Bn

      /**
       * Update of pattern walkset APn-1 to APn
       * 
       * The only element of BPn-1 is cut from BPn-1 and pasted into APn-1. The
       * new element has to become the rightmost element to keep AP in match
       * with A. Thus APn-1 becomes APn. At the start BPstart = <> therefore AP1
       * is an empty set, since there is no element in BPstart to cut.
       */
      if (walksetBPn.getN() != null)
      {
         walksetAPn.addElement(walksetBPn.cutN());
      }

      /**
       * Update of pattern walkset BPn-1 to BPn
       * 
       * The leftmost element of CPn-1 is copied (not cut ! see lemma) from
       * CPn-1 and pasted into BPn-1 left empty by the update of AP. Thus BPn-1
       * again contains one element that is the type of the step number and thus
       * becomes BPn. At the start BPstart = <>, therefore as BP n-1 is filled
       * for the first time it becomes BP1.
       */
      walksetBPn.setN(walksetCPn.getLeftmostElement());

      /**
       * Find whether n is prime
       * 
       * The element n, that is the step number is contained in Bn. Its type
       * information is contained in BPn. If BPn contains the type element M
       * then the current step number is not prime. If BPn contains the type
       * element L then the current step number is prime. This is denoted by
       * changing L into P in BPn (with exception of number one which turns L
       * into symbol 1)
       */
      if (walksetBn.getN() == 1)
      {
         walksetBPn.setN(Symbol.ONE);
      }
      else
      {
         if (walksetBPn.getN().equals(Symbol.L))
         {
            walksetBPn.setN(Symbol.P);
         }
      }

      /**
       * Update pattern walkset CPn-1 to CPn when n is not prime
       * 
       * Fractal procedure: move As can be seen in the description "Update of
       * pattern walkset BPn-1 to BPn" the type information of the step number n
       * is not cut from CPn-1. Instead this leftmost type information is moved
       * to the rightmost place of the periodic term. Thus CPn-1 becomes CPn
       * (see lemma).
       * 
       * Update pattern walkset CPn-1 to CPn when n is prime
       * 
       * Fractal procedure: move The first procedure is the same as for "n is
       * not prime"
       */
      walksetCPn.fractalProcedureMove();

      if (walksetBPn.getN().equals(Symbol.P))
      {
         /**
          * Fractal procedure: copy
          * 
          * The sieve size is increased by copying the pattern and pasting it
          * n-1 times to the right of itself
          */
         walksetCPn.fractalProcedureCopy(walksetBn.getN());

         /**
          * Fractal procedure: change
          * 
          * The types of all numbers x for which applies x·n with (x isElementOf
          * N AND x > 1 AND x·n lessThan sievesizeAtN + n) in CPn-1 are turned
          * from undetermined L types into M types, unless they are already of
          * type M. Thus CPn-1 becomes CPn
          */
         walksetCPn.fractalProcedureChange(walksetBn.getN());
      }
   }

   public void printWalksets()
   {
      System.out.println(walksetAn);
      System.out.println(walksetAPn);

      System.out.println(walksetBn);
      System.out.println(walksetBPn);

      System.out.println(walksetCn);
      System.out.println(walksetCPn);
   }

   private boolean validateStartingState()
   {
      if (!walksetAn.isEmpty())
      {
         System.out.println("Error in starting state of An");
         return false;
      }

      if (!walksetBn.isEmpty())
      {
         System.out.println("Error in starting state of Bn");
         return false;
      }

      if (walksetCn.getLeftmostElement() != 1)
      {
         System.out.println("Error in starting state of Cn");
         return false;
      }

      if (!walksetAPn.getList().isEmpty())
      {
         System.out.println("Error in starting state of APn");
         return false;
      }

      if (!walksetBPn.isEmpty())
      {
         System.out.println("Error in starting state of BPn");
         return false;
      }

      if (!walksetCPn.getLeftmostElement().equals(Symbol.L))
      {
         System.out.println("Error in starting state of CPn");
         return false;
      }

      return true;
   }

   public LinkedList<Symbol> getPattern()
   {
      return this.walksetCPn.getPattern();
   }

   public int getStepNumber()
   {
      return walksetBn.getN();
   }

}

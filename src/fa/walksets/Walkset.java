package fa.walksets;

/** Walksets
 * 
 * For the purpose of this sieve algorithm it was necessary to define sorted
 * sets, which were coined “walksets” (W) as a distinction to sets which are
 * always unsorted. Walksets can be thought of as a walk on the number ray and
 * are different from intervals as they only can contain whole numbers or
 * symbols. Walksets have direction, they can be empty, they are written with
 * angle brackets. Infinity is only possible either at the start or end of a
 * walkset or otherwise as a periodic term that encompasses the whole walkset.
 * 
 * Wempty := <> (1) 
 * Wnatural numbers := <1, 2, … infinity> (2) 
 * Wsymbol pattern 1 := <L,… infinity> = < ‾ L> (3) 
 * Wsymbol pattern 2 := < ‾ LM> (4) 
 * Wsymbol pattern 3 := < ‾ MLMLMM> (5) 
 * Wsymbol pattern 4 := < ‾ LMLMMM> (6)
 * 
 */

public interface Walkset
{
   public String toString();
}

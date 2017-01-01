/********************************************************************* 
 * 										SUMARY:
	The following program contains the method "difference", this method is an algorithm that works with
lists of comparable items. The method "difference" computes the set-theoretic difference 
(which is also known as the relative complement), between the two lists and saves the result into a third
 list provided through the method signature.

	In this case the algorithm uses the fact that both lists are sorted and that the elements in 
them are comparable to its advantage.
	The algorithm uses two iterators that point to the elements of the first and second list 
respectively and iterate through them, checking for elements that are present in list 1 but are not in list 2
 and saves such elements into a list which will contain the new set of numbers (List of numbers that belong 
 to L1 but do not belong to L2, AKA set difference). 
	Here follows a brief summary of the method's implementation as well as some explanation about their
run time complexity:

 *DIFFERENCE*:
	This algorithm is extremely similar to disjoint, the main difference is that while "disjoint" would 
stop and return when a common item is found, this algorithm will save those items which belong to list1 
but do not belong to list2. The program starts a procedure that will only stop once one of the iterators for
list1 and list2 respectively, become null. Similarly to "disjoint" this procedure starts by checking if 
the current element from list1 is greater than the current element from list 2, if it is, it then advances
the iterator for list 2. 
	The second possibility checks if the current element of list 1 is smaller than the current element 
on list 2, if it is it then adds that element to the result set list as it is obvious at this point that this
element will not be found on list 2 (since both lists are sorted, and as explained before, mathematical induction
proves that if x<y, then follows that x <(y+1), x <(y+2)... x <(y+n)  ). 
	Last check is to verify if the current elements are equal and the same, it thats the case the algorithm 
then advances both iterators, this process continues until one of the iterators become null.
	After that the program checks to see if there are still remaining elements that have not been visited 
in list 1 that were not verified because list 2 was searched in full before all items from list 1 could be 
visited, in this case the algorithm just appends the remaining elements to the result set list and concludes. 
(It is clear that if there were unchecked remaining items on list 1 then they are not present on list 2, and by
the definition of relative complement they are part of the "difference" resulting set. Assume that the first item
left on list 1 is n, if n> (last element on list 2), then n is not on list 2, since both lists are ordered it then 
follows that for every subsequent element (n+x) after n on list 1, where n <( n+x), then n+x will also not be 
present in list 2, this is true fore every item left in list 1.

	It is worth noting that this whole process takes place in just one whole simultaneous iteration of both lists,
making this algorithm very efficient. Even in the worst case in which list 1 still has remaining elements after 
the while loop stops, the algorithm continues to append the remaining items on list1 to the result set list.
In that case the program checks every element on both lists only once, which makes this program's Running time 
complexity O(N+M)(with N and M being the number of items on lists one and two respectively) in the worst case 
in which the program may have to traverse list 1 on its entirety after traversing list 2, as it happens in the 
following example:

		list1 = {10,15,20,25}; list2 = {1,2,3,4,5,6,7,8};

		Here the iterators are updated as follows:
				a = {10, 10  10 10  10  10  10  10}
				b = {1,   2,  3, 4,  5,  6,  7,  8, null}

After b is assigned null the loop stops and the algorithm appends any leftover items in list 1 to the 
result set, in this case it seems to be the entirety of list 1, The running time complexity for this case
situations will be O(N+M), where N and M are the sizes of list 1 and list 2 respectively.

Author’s Panther ID: <5446178>
Certification:
I hereby certify that this work is my own and none of it is the work of any other person.
 ********************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Problem1b {

	public static void main(String[] args) {
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,9,10);
		List<Integer> list2 =Arrays.asList(5,6,7);
		List<Integer> listR1 = new ArrayList<>();
		
		difference(list1, list2, listR1);
		printSet(listR1);
		
		List <Integer> listR2 = new ArrayList<Integer>();
		difference(list2, list1, listR2);
		printSet(listR2);
	}
	
	
	
	
	
	/**
	 * This method determines the difference between two lists of comparable elements
	 *  and saves the results if any into the list <b>Difference</b>.
	 * <br>The difference between them is computed as a set difference: <br> <b>A ∖ B = { x ∈ A ∣ x ∉ B }</b> 
	 * 
	 * @param L1
	 * @param L2
	 * @param Difference
	 */
	public static <AnyType extends Comparable<? super AnyType>> 
	void difference(List<AnyType> L1, List<AnyType> L2, List<AnyType> Difference){

		if(L1 == null){//if list 1 is null then the difference is null
			Difference = null;
		}
		else if(L2 == null || L1.isEmpty() || L2.isEmpty()){// if list 2 is null or empty, or list 1 is empty
			Difference = L1;								// then l1\l2 is just l1.
		}
		else{
			Iterator<AnyType> iterator1 =L1.listIterator();
			Iterator<AnyType> iterator2 =L2.listIterator();

			AnyType a = iterator1.next();
			AnyType b = iterator2.next();

			while((a!=null) && (b!=null)){//stop when one of the lists is traveled through completely
				int comp = a.compareTo(b);
				if(comp > 0)//if elem of list 1 is greater than elem of list 2, advance on list 2
					b =(iterator2.hasNext()) ? iterator2.next(): null;

					else if(comp < 0){    //if elem of list 1 is smaller than elem of list 2, advance on list 1  
						Difference.add(a);//and also save elem of list 1 to the result set.
						a = (iterator1.hasNext())? iterator1.next(): null;
					}
					else {//if both elements are equal, then advance on both list 1 and list 2.
						a = (iterator1.hasNext())? iterator1.next() : null;
						b = (iterator2.hasNext())? iterator2.next() : null;
					}
			}
			if(b==null){		//This final loop appends any left over elements from list 1 to the result set as 
				while(a!=null){ //they are in list1 but not on list 2 and are part of the result set.
					Difference.add(a);
					a = (iterator1.hasNext())? iterator1.next() : null;
				}
			}
		}
	}
	public static <AnyType extends Comparable<? super AnyType> >void printSet(List<AnyType> set){

		//--------Simple print to test Results-------
		/*ListIterator<AnyType> itD = set.listIterator();
		AnyType print = (itD.hasNext()) ? itD.next() : null;
		
		String answer = "[ ";
		
		while(print!=null){
			answer += print +", ";
			print = (itD.hasNext()) ? itD.next() : null;
		}
		answer +="]";
		System.out.println("Difference Set: " + answer);
		*/
		
		//---------------More elegant but less efficient print-----------------
		System.out.println("Difference Set: " + Arrays.toString(set.toArray()));
		//Its is less efficient because converts to array and then the array to string.
	}

}
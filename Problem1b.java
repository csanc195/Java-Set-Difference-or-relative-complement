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

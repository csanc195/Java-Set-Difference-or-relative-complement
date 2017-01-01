# Java-Set-Difference-or-relative-complement
<h2>SUMARY:</h2>
The following program contains the method "difference", this method is an algorithm that works with
lists of comparable items. The method "difference" computes the set-theoretic difference 
(which is also known as the relative complement), between the two lists and saves the result into a third
list provided through the method signature.
<br>
In this case the algorithm uses the fact that both lists are sorted and that the elements in 
them are comparable to its advantage.
The algorithm uses two iterators that point to the elements of the first and second list 
respectively and iterate through them, checking for elements that are present in list 1 but are not in list 2
and saves such elements into a list which will contain the new set of numbers (List of numbers that belong 
to L1 but do not belong to L2, AKA set difference). 
Here follows a brief summary of the method's implementation as well as some explanation about their
run time complexity:
<h3>DIFFERENCE:</h3>
This algorithm is extremely similar to disjoint, the main difference is that while "disjoint" would 
stop and return when a common item is found, this algorithm will save those items which belong to list1 
but do not belong to list2. The program starts a procedure that will only stop once one of the iterators for
list1 and list2 respectively, become null. Similarly to "disjoint" this procedure starts by checking if 
the current element from list1 is greater than the current element from list 2, if it is, it then advances
the iterator for list 2. 
<br><br>
The second possibility checks if the current element of list 1 is smaller than the current element 
on list 2, if it is it then adds that element to the result set list as it is obvious at this point that this
element will not be found on list 2 (since both lists are sorted, and as explained before, mathematical induction
proves that if x&lt;y, then follows that x&lt;(y+1), x &lt;(y+2)... x &lt;(y+n)  ). 
<br><br>
Last check is to verify if the current elements are equal and the same, it thats the case the algorithm 
then advances both iterators, this process continues until one of the iterators become null.
After that the program checks to see if there are still remaining elements that have not been visited 
in list 1 that were not verified because list 2 was searched in full before all items from list 1 could be 
visited, in this case the algorithm just appends the remaining elements to the result set list and concludes. 
(It is clear that if there were unchecked remaining items on list 1 then they are not present on list 2, and by
the definition of relative complement they are part of the "difference" resulting set. Assume that the first item
left on list 1 is n, if n> (last element on list 2), then n is not on list 2, since both lists are ordered it then 
follows that for every subsequent element (n+x) after n on list 1, where n&lt;( n+x), then n+x will also not be 
present in list 2, this is true fore every item left in list 1.
<br><br>
It is worth noting that this whole process takes place in just one whole simultaneous iteration of both lists,
making this algorithm very efficient. Even in the worst case in which list 1 still has remaining elements after 
the while loop stops, the algorithm continues to append the remaining items on list1 to the result set list.
In that case the program checks every element on both lists only once, which makes this program's Running time 
complexity O(N+M)(with N and M being the number of items on lists one and two respectively) in the worst case 
in which the program may have to traverse list 1 on its entirety after traversing list 2, as it happens in the 
following example:
<br>
<b> list1 = {10,15,20,25}; <br>list2 = {1,2,3,4,5,6,7,8};</b>

 Here the iterators are updated as follows:<br>
<b>   a = {10, 10  10 10  10  10  10  10}<br>
   b = {1,   2,  3, 4,  5,  6,  7,  8, null}</b><br>

After b is assigned null the loop stops and the algorithm appends any leftover items in list 1 to the 
result set, in this case it seems to be the entirety of list 1, The running time complexity for this case
situations will be <b>O(N+M)</b>, where N and M are the sizes of list 1 and list 2 respectively.


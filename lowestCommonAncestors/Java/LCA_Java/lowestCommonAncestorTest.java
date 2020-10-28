import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class lowestCommonAncestorTest {
	
	@Test
    public void testGet() {
		lowestCommonAncestor<Integer, Integer> bst = new lowestCommonAncestor<Integer, Integer>();
        assertEquals("Checking in get on an empty tree", null, bst.get(7));

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);	 //        \
                         //         5
        
        System.out.println("test1");

        assertEquals("Getting the root", "7", bst.get(7).toString());
        //System.out.println(bst.get(7).toString());
        assertEquals("Getting a leaf", "2", bst.get(2).toString());
        //System.out.println(bst.get(2).toString());
        assertEquals("Getting root of a sub-tree", "3", bst.get(3).toString());
        //System.out.println(bst.get(3).toString());
    }

    @Test
    public void testPrintInOrder() {
    	lowestCommonAncestor<Integer, Integer> bst = new lowestCommonAncestor<Integer, Integer>();
        assertEquals("Checking in order printing of empty tree","()", bst.printKeysInOrder());

        //	 2
        //	/ \
        //   1   3
        //        \
        //           4

        bst.put(2, 7);
        bst.put(1, 9);
        bst.put(3, 7);
        bst.put(4, 9);

        //output: "((()A())B(()C(()D())))"
        
        System.out.println("\n" + "test2");

        String result = "((()1())2(()3(()4())))";
        assertEquals("Checking in order printing of non-empty tree", result, bst.printKeysInOrder());
        //System.out.println(bst.printKeysInOrder());
    }

    @Test
    public void testFindLCA() {
    	lowestCommonAncestor<Integer, Integer> bst = new lowestCommonAncestor<Integer, Integer>();
        assertNull("Checking in get on an empty tree", bst.findLCA(7,8));

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //        5
        
        System.out.println("\n" + "test3");

        assertEquals("Getting the root with root", "7", bst.findLCA(7,8).toString());
        assertEquals("Getting the root from branches", "7", bst.findLCA(8,5).toString());
        assertEquals("Getting the root of a sub branch", "3", bst.findLCA(2,6).toString());
        assertEquals("Getting the root of a sub branch using said root", "3", bst.findLCA(3,1).toString());

        assertNull("Testing value one not in tree", bst.findLCA(9,7));
        assertNull("Testing value two not in tree", bst.findLCA(7,9));
        assertNull("Testing both values not in tree", bst.findLCA(9,10));

        assertNull("Testing same value", bst.findLCA(7,7));

    }

}

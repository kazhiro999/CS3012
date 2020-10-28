import java.util.ArrayList; 
import java.util.List; 
import java.util.NoSuchElementException;
@SuppressWarnings("unused")

public class lowestCommonAncestor<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private treeNode root;
	
	private class treeNode {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private treeNode left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public treeNode(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() { return size(root); }

    private int size(treeNode x) {
        if (x == null) return 0;
        else           return x.N;
    }

    public Key get(Value val) { return get(root, val); }

    private Key get(treeNode x, Value val) {
        if (x == null)    return null;
        int cmp = val.compareTo(x.val);
        if      (cmp < 0) return get(x.left, val);
        else if (cmp > 0) return get(x.right, val);
        else              return x.key;
    }

    public void put(Key key, Value val) { root = put(root, key, val);}

    private treeNode put(treeNode x, Key key, Value val) {
        if (x == null) return new treeNode(key, val, 1);
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val   = val;
        
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public String printKeysInOrder() {
        String toPrint = "(";
        if (!isEmpty()) toPrint = print(root, toPrint);
        return toPrint + ")";
    }

    public String print(treeNode node, String str) {
        str += "(";
        if(node.left!=null)  str =  print(node.left, str);
        str += ")";
        str += node.key;
        str += "(";
        if (node.right!=null) str =  print(node.right, str);
        str += ")";
        return str;

    }

    public Value findLCA(Value valA, Value valB) {
        if (valA != valB && this.get(valA) != null && this.get(valB) != null && !this.isEmpty()){
            System.out.println(valA + " " + valB);
            treeNode tmp = LCA(root, valA, valB);
            if (tmp != null) {
                return tmp.val;
            }
        }
        return null;
    }

    private treeNode LCA(treeNode node, Value valA, Value valB) {
        
        if (node == null) return null;
        if (node.val == valA || node.val == valB) return node;

        treeNode left_lca = LCA(node.left, valA, valB);
        treeNode right_lca = LCA(node.right, valA, valB);

        if (left_lca!=null && right_lca!=null) return node;
        
        return (left_lca != null) ? left_lca : right_lca;
    }

}

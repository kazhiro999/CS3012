test('test LCA', () => {

    // Node class 
    class Node {
        constructor(val) {
            this.val = val; this.left = null; this.right = null;
        }
    }

    // Binary Search tree class 


    class BinarySearchTree {
        constructor() { this.root = null }

        create(val) {
            // Creating a node and initailising with value  
            var newNode = new Node(val);

            if (this.root === null) { this.root = newNode; }
            else { this.createNode(this.root, newNode); }
        }

        // Method to insert a node in a tree 
        // It moves over the tree to find the location to insert a node with a given data  
        createNode(node, newNode) {
            // if the data is less than the node data move left of the tree  
            if (newNode.val < node.val) {
                // if left is null insert node here 
                if (node.left === null) { node.left = newNode; }
                else { this.createNode(node.left, newNode); }
            }

            // if the data is more than the node 
            // data move right of the tree  
            else {
                // if right is null insert node here 
                if (node.right === null) { node.right = newNode; }
                else { this.createNode(node.right, newNode); }
            }
        }

        // returns root of the tree 
        getRootNode() { return this.root; }

        // search for a node with given data 
        lca(node, val1, val2) {
            if (node === null) return null;
            else if (node.val > val1 && node.val > val2) return this.lca(node.left, val1, val2);
            else if (node.val < val1 && node.val < val2) return this.lca(node.right, val1, val2);
            else return node.val;
        }
    }

    
    
    // create an object for the BinarySearchTree 
    var BST = new BinarySearchTree();
      
    // Inserting nodes to the BinarySearchTree 
    BST.create(15); 
    BST.create(25); 
    BST.create(10); 
    BST.create(7); 
    BST.create(22); 
    BST.create(17); 
    BST.create(13); 
    BST.create(5); 
    BST.create(9); 
    BST.create(27); 
                              
    //          15 
    //         /  \ 
    //        10   25 
    //       / \   / \ 
    //      7  13 22  27 
    //     / \    / 
    //    5   9  17  
                  
    var root = BST.getRootNode();
    
    var answer = 0;
    var value1 = 5, value2 = 9;
    answer = BST.lca(root, value1, value2);
    console.log("The lca of " + value1 + " and " +  value2 + " is " + answer);
    expect(answer).toBe(7);
    
    value1 = 17, value2 = 25;
    answer = BST.lca(root, value1, value2);
    console.log("The lca of " + value1 + " and " +  value2 + " is " + answer);
    expect(answer).toBe(25);
    
    value1 = 13, value2 = 22;
    answer = BST.lca(root, value1, value2);
    console.log("The lca of " + value1 + " and " +  value2 + " is " + answer);
    expect(answer).toBe(15);
    
})
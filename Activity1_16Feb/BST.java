package Activity1_16Feb;

class BST {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    Node root;

    Node insert(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.key)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        return root;
    }

    boolean search(Node root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;

        if (key < root.key)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.key + " ");
        }
    }

    public static void main(String[] args) {

        BST tree = new BST();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int v : values)
            tree.root = tree.insert(tree.root, v);

        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);

        System.out.println("\nPreorder Traversal:");
        tree.preorder(tree.root);

        System.out.println("\nPostorder Traversal:");
        tree.postorder(tree.root);

        System.out.println("\n\nSearch Results:");
        System.out.println("Search 40: " + tree.search(tree.root, 40));
        System.out.println("Search 100: " + tree.search(tree.root, 100));
    }
}


// //Output:
// Inorder Traversal:
// 20 30 40 50 60 70 80 
// Preorder Traversal:
// 50 30 20 40 70 60 80 
// Postorder Traversal:
// 20 40 30 60 80 70 50 

// Search Results:
// Search 40: true
// Search 100: false
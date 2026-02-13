package Tree_13_Feb;

// import java.util.*;

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    Node root;

    Node insert(Node root,int data ){
        if(root == null){
            root = new Node(data);
            return root;
        }

        if(data < root.data){
            root.left = insert(root.left, data);
        }else if(data > root.data){
            root.right = insert(root.right, data);
        }

        return root;
    }

    void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    void preorder(Node root){
        if(root != null){
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

     void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }
    public static void main(String[] args) {
        
        BinaryTree tree = new BinaryTree();
        tree.root = tree.insert(tree.root, 10);
        tree.insert(tree.root, 5);
        tree.insert(tree.root, 15);
        tree.insert(tree.root, 3);
        tree.insert(tree.root, 7);
        tree.insert(tree.root, 12);
        tree.insert(tree.root, 18);

        //print tree in order
        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);

        System.out.println("\nPreorder Traversal:");
        tree.preorder(tree.root);

        System.out.println("\nPostorder Traversal:");
        tree.postorder(tree.root);


    }
}

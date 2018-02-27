package Project7;

import com.sun.glass.ui.Size;
import sun.reflect.generics.tree.Tree;

/**
 * Created by Admin on 10/12/2015.
 */

//class is called hashTree
public class SizeLimitedBBSTree<T extends Comparable> {

    //create an array to store the tree make up of the wrapper class
    public TreeNode<T>[] treeArray;

    //create a static field to store a classwide counter
    public static int inputCounter;

    //create a final int k to store the array max value
    public int maxSize;

    //create a dataWrapper called currentMax
    public TreeNode<T> currentMax;

    //create a dataWrapper called oldest

    public SizeLimitedBBSTree() {
        //creates object but no set size yet
    }

    //Constructor sets the input value (INITIALIZE(k))
    //if k is greater than or equal to 0
    //create an array equal to 2k + 1 of type hashTreeWrapper
    //else
    //throw an exception
    public void initialize(int k) throws Exception {
        if (k >= 0) {
            this.maxSize = k;
            treeArray = new TreeNode[2 * k + 1];
        } else
            throw new Exception("Number is less than 0");
    }

    //void Offer (nodeX)


    public void Offer(TreeNode node) {
        //counter++
        node.setInputNumber(this.inputCounter);
        this.inputCounter++;

        //make a boolean called max that is true if nodeX is greater than everything
        boolean max = true;
        boolean done = false;

        //start at first node
        int place = 0;
        TreeNode current = treeArray[place];
        TreeNode previous = current;
        //while boolean done is false
        while (done == false) {
            //if the value of the current node is greater than the nodeX (unless its empty)
            if (current.getComparableObject().compareTo(node.getComparableObject()) > -1) {
                //nodeX gets the right child
                previous = current;
                current = current.getRightChild();
                place = 2 * place + 2;
            }
            //else if the value is less than the nodeX (unless its empty set that as null or 0 and catch it or something)
            else if (treeArray[place].getComparableObject().compareTo(node.getComparableObject()) < 0) {
                //nodeX gets the left child
                previous = current;
                current = current.getLeftChild();
                place = 2 * place + 1;
                //max equals false
                max = false;
            }
            //else
            //set nodeX to become right child
            //change the parents value so it has the correct child
            //done is true
            else {
                node.setParent(previous);
                node.getParent().setRightChild(node);
                this.treeArray[place] = node;
                done = true;
            }
        }
        //balance the tree
        //if max is true
        //set currentMax to this object
        this.balance(treeArray[0]);
        if (max == true) {
            this.currentMax = node;
        }
        //set oldest equal to this object
    }


    //balance()
    //call delete()
    //call hashBalance()
    public void balance(TreeNode node) {
        this.deleteIfNeeded();
        this.balanceHelper(node);
    }

    //hashBalance()
    // allowed depth is log(base2k+2) 2 = depth (not needed as it can't be balanced and also be too deep)
    //balance the tree calling rotateRight() and rotateLeft() as needed (basic AVL rotation)
    //make sure to update the balance of the nodes
    private void balanceHelper(TreeNode node) {
        if (node.getBalance() < -1) {
            if (node.getRightChild().getBalance() > 1) {
                this.doubleLeftRotation(node);
            } else {
                this.rotateLeft(node);
            }
        } else if (node.getBalance() > 1) {
            if (node.getRightChild().getBalance() > 1) {
                this.doubleRightRotation(node);
            } else {
                this.rotateRight(node);
            }
        } else {

        }

    }
    public void add(TreeNode input){

    }
    //rotation methods
    private TreeNode rotateLeft(TreeNode node) {
        TreeNode rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());
        rightChild.setLeftChild(node);
        node.setHeight(Math.max(node.getLeftChild().getHeight(), node.getRightChild().getHeight()) + 1);
        rightChild.setHeight(Math.max(rightChild.getLeftChild().getHeight(), rightChild.getRightChild().getHeight()) + 1);
        return rightChild;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());
        leftChild.setRightChild(node);
        node.setHeight(Math.max(node.getLeftChild().getHeight(), node.getRightChild().getHeight()) + 1);
        leftChild.setHeight(Math.max(leftChild.getLeftChild().getHeight(), leftChild.getRightChild().getHeight()) + 1);
        return leftChild;
    }

    private TreeNode doubleRightRotation(TreeNode node) {
        node.setRightChild(rotateRight(node.getRightChild()));
        return rotateLeft(node);
    }

    private TreeNode doubleLeftRotation(TreeNode node) {
        node.setLeftChild(node.getLeftChild());
        return rotateRight(node);
    }

    //delete()
    // search for the oldest value
    //if the value is less than the counter - k
    //remove the node by setting the right child's parent to the node's parent
    // and set the left child to the right child's left most child
    //else
    //move to the next node
    private void deleteIfNeeded() {
        for (TreeNode node : this.treeArray) {
            if (node == treeArray[0]) {
                TreeNode head = node.getLeftChild().getRightChild();
                head.setParent(null);
                head.setParent(node.getLeftChild());
                head.getLeftChild().setParent(node.getLeftChild());
                head.setRightChild(node.getRightChild());
                head.setLeftChild(node.getLeftChild());
                node.getRightChild().setParent(head);
                node.getLeftChild().setParent(head);
            }
            if (node.inputNumber < this.inputCounter - maxSize) {
                this.leftOrRightChildDeletion(node);
            } else {
            }
        }
    }

    //Deletion helped methods to reduce complexity
    private void leftOrRightChildDeletion(TreeNode node) {
        if (node.getComparableObject().compareTo(node.getParent().getComparableObject()) > -1) {
            this.rightChildDeletion(node);
        } else if (node.getComparableObject().compareTo(node.getParent().getComparableObject()) < 0) {
            this.leftChildDeletion(node);
        }
    }

    private void rightChildDeletion(TreeNode node) {
        if (node.getRightChild() != null && node.getLeftChild() != null) {
            node.getRightChild().setParent(node.getParent());
            node.getLeftChild().setParent(node.getRightChild());
        } else if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node.getParent());
            node.getParent().setRightChild(node.getLeftChild());
        } else if (node.getRightChild() != null) {
            node.getRightChild().setParent(node.getParent());
            node.getParent().setRightChild(node.getRightChild());
        } else {

        }
    }

    private void leftChildDeletion(TreeNode node) {
        if (node.getRightChild() != null && node.getLeftChild() != null) {
            node.getLeftChild().setParent(node.getParent());
            node.getRightChild().setParent(node.getLeftChild());
        } else if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node.getParent());
            node.getParent().setRightChild(node.getLeftChild());
        } else if (node.getRightChild() != null) {
            node.getRightChild().setParent(node.getParent());
            node.getParent().setRightChild(node.getRightChild());
        } else {

        }
    }
    public TreeNode<T> head = null;
    //T Max()
    //simply return the currentMax field
    public T Max() throws Exception{
        if (this.maxSize > 0) {
            return this.currentMax.getComparableObject();
        }
        else{
            throw new Exception("error lol");
        }
    }

    public class InnerTree extends SizeLimitedBBSTree{
        public TreeNode rotateLeftTest(TreeNode node) {
            return SizeLimitedBBSTree.this.rotateLeft(node);
        }

        public TreeNode rotateRightTest(TreeNode node) {
            return SizeLimitedBBSTree.this.rotateRight(node);

        }

        public TreeNode doubleRightRotationTest(TreeNode node) {
            return SizeLimitedBBSTree.this.doubleRightRotation(node);

        }

        public TreeNode doubleLeftRotationTest(TreeNode node) {
            return SizeLimitedBBSTree.this.doubleRightRotation(node);
        }
        public void deleteIfNeededTest() {
            SizeLimitedBBSTree.this.deleteIfNeeded();
        }
        public void leftOrRightChildDeletionTest(TreeNode node) {
            SizeLimitedBBSTree.this.leftOrRightChildDeletion(node);
        }

        public void rightChildDeletionTest(TreeNode node) {
            SizeLimitedBBSTree.this.rightChildDeletion(node);

        }
        public void leftChildDeletionTest(TreeNode node) {
            SizeLimitedBBSTree.this.rightChildDeletion(node);
        }
        public void balanceHelperTest(TreeNode node) {
            SizeLimitedBBSTree.this.balanceHelper(node);
        }
        public void balanceTest(TreeNode node){
            SizeLimitedBBSTree.this.balance(node);
        }
}
}
package Project7;

//class is a data wrapper class called hashTreeWrapper
public class TreeNode<T extends Comparable> {

    //create a field for an positionInput
    public int inputNumber;

    //create a field for a generic object
    public T comparableComparableObject;

    // create an integer field called depth
    public int depth;

    //create a hashTreeWrapper field for parent
    public TreeNode<T> parent;

    //create a hashTreeWrapper field for right child
    public TreeNode<T> rightChild;

    //create a hashTreeWrapper field for left child
    public TreeNode<T> leftChild;

    //create integers called rightHeight, leftHeight, and height
    public int height;

    //create an integer called balance
    public int balance;


    //constructor takes an integer for position input and a object
    //sets them equal to the respective fields
    public TreeNode(T comparableComparableObject, int inputNumber, TreeNode<T> parent, TreeNode<T> rightChild, TreeNode<T> leftChild){
        this.comparableComparableObject = comparableComparableObject;
        this.inputNumber = inputNumber;
        this.parent = parent;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
        this.height = this.findHeight(this);
    }

    //create getters and setters for the fields
    public int getBalance() {
        return balance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth(){return this.depth;}

    public T getComparableObject() {
        return comparableComparableObject;
    }

    public void setComparableObject(T comparableComparableObject) {
        this.comparableComparableObject = comparableComparableObject;
    }

    public int getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(int inputNumber) {
        this.inputNumber = inputNumber;
    }

    //compareTo() method that compares generic comparable object
    public int compareTo(TreeNode input){
        return this.getComparableObject().compareTo(input.getComparableObject());
    }

    //getDepth() loops by getting the parent class until it hits the top note
    //It increments the depth field each time it calls getParent without an error
    public int findDepth(){
        int counter = 0;
        while(this.parent.getParent() != null){
            counter++;
        }
        return counter;
    }

    //getHeight(Node input)
    //rightHeight equals getHeight on the right child
    //leftHeight equals getHeight on the left child
    //height equals the greater of rightHeight and leftHeight
    //balance equals leftheight minus rightHeight
    public int findHeight(TreeNode node){

        if(node == null)
            return -1;

        int leftHeight = findHeight(node.leftChild);
        int rightHeight = findHeight(node.rightChild);

        if(leftHeight > rightHeight)
            return leftHeight + 1;
        else
            return rightHeight +1;
    }
}

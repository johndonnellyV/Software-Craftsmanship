package Project7;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import Project7.SizeLimitedBBSTree.InnerTree;
import static org.junit.Assert.*;
/**
 * Created by Admin on 10/12/2015.
 */
public class SizeLimitedBBSTreeTest {

    SizeLimitedBBSTree<Integer>.InnerTree testTree1 = new SizeLimitedBBSTree<Integer>().new InnerTree();
    SizeLimitedBBSTree<Integer>.InnerTree testTree2 = new SizeLimitedBBSTree<Integer>().new InnerTree();
    SizeLimitedBBSTree<Integer>.InnerTree testOfferedTree = new SizeLimitedBBSTree<Integer>().new InnerTree();
    SizeLimitedBBSTree<Integer>.InnerTree treeInitializeTest = new SizeLimitedBBSTree<Integer>().new InnerTree();
    SizeLimitedBBSTree<Integer>.InnerTree treeRotations = new SizeLimitedBBSTree<Integer>().new InnerTree();
    SizeLimitedBBSTree<Integer>.InnerTree treeRandom = new SizeLimitedBBSTree<Integer>().new InnerTree();

    TreeNode<Integer> node14 = new TreeNode<>(14, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node28 = new TreeNode<>(28, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node37 = new TreeNode<>(37, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node24 = new TreeNode<>(24, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node57 = new TreeNode<>(57, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node98 = new TreeNode<>(98, testTree1.inputCounter, null, null, null);
    TreeNode<Integer> node87 = new TreeNode<>(87, testTree1.inputCounter, null, null, null);




    @Before
    public void setUp() throws Exception{
        testTree1.initialize(30);
        testTree2.initialize(60);
        testOfferedTree.initialize(10);
        testOfferedTree.Offer(node14);
        treeRotations.Offer(node28);
        treeRotations.Offer(node37);
        treeRotations.Offer(node24);
        treeRotations.Offer(node57);
        treeRotations.Offer(node98);
        treeRotations.Offer(node87);
        for (int i = 0; i < 100; i++){
            treeRandom.Offer(new TreeNode<Integer>(1 + (int)(Math.random()*50000), treeRandom.inputCounter, null, null, null));
        }
    }

    @After
    public void tearDown(){
        testTree1 = null;
        testTree2 = null;
        testOfferedTree = null;
        treeRotations = null;
    }

    /**
     * tests the possible routes the method could go in
     * @throws Exception
     */
    @Test
    public void testOfferandMaxStructuredBasis() throws Exception {

        testTree1.Offer(node14); //initial insertion, immediately ends the loop
        assertTrue(testTree1.Max().equals(14));

        testTree1.Offer(node28); //now a nominal case uses first if and should be a max
        assertTrue(testTree1.Max().equals(28));

        testTree1.Offer(node37); //still uses max
        assertTrue(testTree1.Max().equals(37));

        testTree1.Offer(node24); //uses the second if statement this time and max is false
        assertTrue(testTree1.Max().equals(37));

        testTree1.Offer(node57); //makes sure max still works afterwords
        assertTrue(testTree1.Max().equals(57));

        node87.setComparableObject(9000);
        treeRandom.Offer(node87);// data flow test for changed data, unchanged data is all other cases
        assertTrue(treeRandom.Max().equals(9000));


        //all routes have been covered
    }

    /**
     * tests the possible routes the method could go in
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testOfferandMaxBadData() throws Exception{

        testTree1.Offer(new TreeNode(null, 23, null, null, null)); //data is null no object to compare
        assertTrue(testTree1.Max().equals(23));

        testTree2.Offer(new TreeNode<Integer>(-1, testTree1.inputCounter, null, null, null));
    }

    @Test(expected = NullPointerException.class)
    public void testOfferandMaxBadData2(){

        testTree2.Offer(new TreeNode<Integer>(-1, testTree1.inputCounter, null, null, null));
    }
    /**
     * tests the possible routes the method could go in
     * @throws Exception
     */
    @Test
    public void testOfferandMaxGoodData() throws Exception {

        testTree1.Offer(node14); //initial insertion, immediately ends the loop
        assertTrue(testTree1.Max().equals(14));

        treeRandom.Offer(node57); //nominal case good data
        assertTrue(treeRandom.Max().equals(14));

    }
    /**
     * tests the possible routes the method could go in
     * @throws Exception
     */
    @Test
    public void testOfferandMaxBoundary() throws Exception {

        for (int i = 0; i < 30; i++) {
            testTree1.Offer(new TreeNode<Integer>(14, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks if the minimum comparable value is acceptable and functions
        for (int i = 0; i < 30; i++) {
            testTree1.Offer(new TreeNode<Integer>(1, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(1));
        }
        this.tearDown();
        this.setUp();

        assertTrue(testTree1.Max().equals(null));
        //checks if input is one less than the limit (boundary)
        for (int i = 0; i < 29; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks if input hits the limit (boundary)
        for (int i = 0; i < 30; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks if the input is 1 more than the limit (boundary)
        for (int i = 0; i < 31; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }

        this.tearDown();
        this.setUp();
        //checks if the input is the true array input limit (boundary)
        for (int i = 0; i < 61; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks if the input is 1 less than the true array input limit (boundary)
        for (int i = 0; i < 60; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks if the input is 1 more than the true array input limit (boundary)
        for (int i = 0; i < 62; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(14));
        }
        this.tearDown();
        this.setUp();
        //checks for huge amounts of data being input
        for (int i = 0; i < 2390480; i++) {
            testTree1.Offer(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
            assertTrue(testTree1.Max().equals(i));
        }
    }

    @Test
    public void testInitialize() throws Exception {
        treeInitializeTest.initialize(0);//too low testing bad data/bounds
        treeInitializeTest.initialize(-10);//more bad data
        treeInitializeTest.initialize(1);//smallest allowed boundary test
        treeInitializeTest.initialize(10);//nominal cases
        treeInitializeTest.initialize(13);//good data
        treeInitializeTest.initialize(1342345);//good data
        treeInitializeTest.initialize(123465); //good data
        treeInitializeTest.initialize(2147483647);//max int possible good data
        int x = 4;
        x = 4 * 2;
        treeInitializeTest.initialize(x);//dataflow test
        treeInitializeTest.initialize(5);//dataflow test initialized
        //bad data cannot be input
    }

    @Test
    public void testRotateLeft() throws Exception {
        //NO IFs or anything else so structured basis is covered by any test
        //tests for the head (testing boundaries)
        treeRotations.rotateLeftTest(node37);
        assertTrue(node14.getParent().getComparableObject().equals(57));

        //tests for the maximum (testing boundaries)
        treeRotations.rotateLeftTest(node98);
        assertTrue(node14.getParent().getComparableObject().equals(89));

        //tests for minimum (testing boundaries)
        treeRotations.rotateLeftTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(24));

        //checks for huge amounts of data being input
        for (int i = 0; i < 2390480; i++) {
            testTree1.add(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.currentMax.getParent().equals(2390478));

        //good data test nominal case
        treeRotations.rotateLeftTest(node57);
        assertTrue(node57.getParent().getComparableObject().equals(57));

        //good data test nominal case
        treeRotations.rotateLeftTest(node98);
        assertTrue(node98.getParent().getComparableObject().equals(37));

        //good data test nominal case
        treeRotations.rotateLeftTest(node28);
        assertTrue(node98.getParent().getComparableObject().equals(87));

        this.tearDown();
        this.setUp();
        //tests for bad data
        node14 = null;
        treeRotations.rotateLeftTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(57));
        //stress tests not really applicable as it doesn't matter how big the tree is

        //dataflow test for modified data
        node87.setComparableObject(9000);
        treeRotations.Offer(node87);
        treeRotations.rotateLeftTest(node87);
        assertTrue(node87.getParent().getComparableObject() < 1000);

    }

    @Test
    public void testRotateRight() throws Exception {
        //NO IFs or anything else so structured basis is covered by any test
        //tests for the head (testing boundaries)
        treeRotations.rotateRightTest(node37);
        assertTrue(node14.getParent().getComparableObject().equals(89));

        //tests for the maximum (testing boundaries)
        treeRotations.rotateRightTest(node98);
        assertTrue(node14.getParent().getComparableObject().equals(57));

        //tests for minimum (testing boundaries)
        treeRotations.rotateRightTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(37));

        //checks for huge amounts of data being input
        for (int i = 0; i < 2390480; i++) {
            testTree1.add(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.currentMax.getParent().equals(2390478));

        //good data test nominal case
        treeRotations.rotateRightTest(node57);
        assertTrue(node57.getParent().getComparableObject().equals(57));

        //good data test nominal case
        treeRotations.rotateRightTest(node98);
        assertTrue(node98.getParent().getComparableObject().equals(37));

        //good data test nominal case
        treeRotations.rotateRightTest(node28);
        assertTrue(node98.getParent().getComparableObject().equals(87));

        this.tearDown();
        this.setUp();
        //tests for bad data
        node14 = null;
        treeRotations.rotateRightTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(57));
        //stress tests not really applicable as it doesn't matter how big the tree is

        //dataflow test for modified data
        node87.setComparableObject(9000);
        treeRotations.Offer(node87);
        treeRotations.rotateRightTest(node87);
        assertTrue(node87.getParent().getComparableObject() < 1000);

        //dataflow test for modified data
        node98.setComparableObject(9000);
        treeRotations.Offer(node98);
        treeRotations.rotateRightTest(node98);
        assertTrue(node98.getParent().getComparableObject() < 1000);
    }

    @Test
    public void testDoubleLeftRotation() throws Exception {
        //NO IFs or anything else so structured basis is covered by any test
        //tests for the head (testing boundaries)
        treeRotations.doubleLeftRotationTest(node37);
        assertTrue(node14.getParent().getComparableObject().equals(89));

        //tests for the maximum (testing boundaries)
        treeRotations.doubleLeftRotationTest(node98);
        assertTrue(node14.getParent().getComparableObject().equals(57));

        //tests for minimum (testing boundaries)
        treeRotations.doubleLeftRotationTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(14));

        //checks for huge amounts of data being input
        for (int i = 0; i < 2390480; i++) {
            testTree1.add(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.currentMax.getParent().equals(2390478));

        //good data test nominal case
        treeRotations.doubleLeftRotationTest(node57);
        assertTrue(node57.getParent().getComparableObject().equals(57));

        //good data test nominal case
        treeRotations.doubleLeftRotationTest(node98);
        assertTrue(node98.getParent().getComparableObject().equals(37));

        //good data test nominal case
        treeRotations.doubleLeftRotationTest(node28);
        assertTrue(node98.getParent().getComparableObject().equals(87));

        this.tearDown();
        this.setUp();
        //tests for bad data
        node14 = null;
        treeRotations.doubleLeftRotationTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(57));
        //stress tests not really applicable as it doesn't matter how big the tree is

        //dataflow test for modified data
        node87.setComparableObject(9000);
        treeRotations.Offer(node87);
        treeRotations.doubleLeftRotationTest(node87);
        assertTrue(node87.getParent().getComparableObject() < 1000);

    }

    @Test
    public void testDoubleRightRotation() throws Exception {
        //NO IFs or anything else so structured basis is covered by any test
        //tests for the head (testing boundaries)
        treeRotations.doubleRightRotationTest(node37);
        assertTrue(node14.getParent().getComparableObject().equals(57));

        //tests for the maximum (testing boundaries)
        treeRotations.doubleRightRotationTest(node98);
        assertTrue(node14.getParent().getComparableObject().equals(89));

        //tests for minimum (testing boundaries)
        treeRotations.doubleRightRotationTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(24));

        //checks for huge amounts of data being input
        for (int i = 0; i < 2390480; i++) {
            testTree1.add(new TreeNode<Integer>(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.currentMax.getParent().equals(2390478));

        //good data test nominal case
        treeRotations.doubleRightRotationTest(node57);
        assertTrue(node57.getParent().getComparableObject().equals(57));

        //good data test nominal case
        treeRotations.doubleRightRotationTest(node98);
        assertTrue(node98.getParent().getComparableObject().equals(37));

        //good data test nominal case
        treeRotations.doubleRightRotationTest(node28);
        assertTrue(node98.getParent().getComparableObject().equals(87));

        this.tearDown();
        this.setUp();
        //tests for bad data
        node14 = null;
        treeRotations.doubleRightRotationTest(node14);
        assertTrue(node14.getParent().getComparableObject().equals(57));
        //stress tests not really applicable as it doesn't matter how big the tree is

        //dataflow test for modified data
        node98.setComparableObject(9000);
        treeRotations.Offer(node98);
        treeRotations.doubleLeftRotationTest(node98);
        assertTrue(node98.getParent().getComparableObject() < 1000);
    }
    @Test
    public void testDeleteIfNeeded() throws Exception {
        //boundary test of a tree with just a head also Structured basis test case 1 (head)
        testTree1.add(node14);
        testTree1.deleteIfNeededTest();
        assertTrue(testTree1.currentMax.getDepth() == 1);

        //boundary test of a tree with nothing also a bad data test   Structured basis test case 2 (no deletion)
        testTree2.deleteIfNeededTest();
        assertTrue(testTree2.currentMax.getDepth() == 1);

        //tests case where balance is -1 boundary as well 1 more than head
        testTree1.add(node28);
        testTree1.deleteIfNeededTest();
        assertTrue(testTree1.currentMax.getDepth() == 1);

        //tests case where balance is 1 also a boundary
        testTree2.add(node28);
        testTree2.add(node37);
        testTree2.deleteIfNeededTest();
        assertTrue(testTree2.currentMax.getDepth() == 1);

        //good data test Structured basis test case 3 (deletion needed)
        treeRandom.deleteIfNeededTest();
        assertTrue(treeRandom.currentMax.getDepth() == 15);

        //dataflow test for modified data
        node87.setComparableObject(9000);
        treeRandom.Offer(node87);
        treeRandom.deleteIfNeededTest();
        assertTrue(treeRandom.currentMax.getDepth() == 16);

    }
    @Test
    public void testRightChildDeletion() throws Exception {
        //structured base cases are 1: right and left child 2: left child 3: right child 4: no children

        //bad data/there is no right child structured basis case 1
        //also serves as a boundary
        testTree1.add(node24);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getRightChild().equals(null));

        //right child is deleted good data structured basis case 3
        //also serves as a boundary
        testTree1.add(node28);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getRightChild().equals(null));

        //only a left child so it remains null structured basis case 2
        //also serves as a boundary
        testTree1.add(node14);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getRightChild().equals(null));

        //deletes node 5 and 6 is the new right child
        testTree1.add(node57);
        testTree1.add(node98);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getRightChild().equals(node98));

        //good data and structured basis case 4
        treeRandom.rightChildDeletionTest(treeRandom.currentMax.getParent().getParent().getLeftChild());
        assertFalse(node24.getRightChild().equals(null));

        //dataflow test for modified data
        node87.setComparableObject(9000);
        testTree1.Offer(node87);
        testTree1.rightChildDeletionTest(node87);
        assertTrue(node87.getRightChild().equals(null));

    }
    @Test
    public void testLeftChildDeletion() throws Exception {
        //structured base cases are 1: right and left child 2: left child 3: right child 4: no children

        //bad data/there is no right child structured basis case 1
        //also serves as a boundary
        testTree1.add(node24);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getLeftChild().equals(null));

        //right child is deleted good data structured basis case 3
        //also serves as a boundary
        testTree1.add(node28);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getLeftChild().equals(null));

        //only a left child so it remains null structured basis case 2
        //also serves as a boundary
        testTree1.add(node14);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getLeftChild().equals(null));

        //deletes node 5 and 6 is the new right child
        testTree1.add(node37);
        testTree1.add(node28);
        testTree1.rightChildDeletionTest(node24);
        assertTrue(node24.getLeftChild().equals(node28));

        //good data and structured basis case 4
        treeRandom.rightChildDeletionTest(treeRandom.currentMax.getParent().getParent().getLeftChild());
        assertFalse(node24.getLeftChild().equals(null));

        //dataflow test for modified data
        node87.setComparableObject(9000);
        testTree1.Offer(node87);
        testTree1.leftChildDeletionTest(node87);
        assertTrue(node87.getLeftChild().equals(null));
    }
    @Test
    public void testBalanceHelper() throws Exception {
        //structures basis test -2 balance calls rotate left
        testTree1.add(node98);
        testTree1.add(node57);
        testTree1.add(node24);
        testTree1.balanceHelperTest(node98);
        assertTrue(node98.getBalance() == 0);

        //structures basis test -2 balance calls double rotation left
        testTree2.add(node98);
        testTree2.add(node24);
        testTree2.add(node57);
        testTree2.add(node57);
        testTree1.balanceHelperTest(node98);
        assertTrue(node98.getBalance() == 0);
        
        this.tearDown();
        this.setUp();
        //structured basis test 2 balance that calls right rotation
        testTree1.add(node14);
        testTree1.add(node28);
        testTree1.add(node37);
        testTree1.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //structured basis test 2 balance that calls left rotation
        testTree2.add(node14);
        testTree2.add(node98);
        testTree2.add(node37);
        testTree2.add(node24);
        testTree2.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test as the balance is already 0
        this.tearDown();
        this.setUp();
        testTree1.add(node14);
        testTree1.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test of a value of 1
        testTree1.add(node28);
        testTree1.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test with a value of -1 for balance
        testTree2.add(node28);
        testTree2.add(node14);
        testTree2.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //final boundary where the balance is -2 but the child balance is not enough for double rotation
        testTree2.add(node28);
        testTree2.add(node14);
        testTree2.add(node24);
        testTree2.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //final boundary where the balance is -2 but the child balance is not enough for double rotation
        testTree1.add(node14);
        testTree1.add(node28);
        testTree1.add(node24);
        testTree1.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //good data
        treeRandom.balanceHelperTest(treeRandom.head);
        assertTrue(treeRandom.head.getBalance() == 0);
        this.tearDown();
        this.setUp();

        //bad data null tree
        testTree2.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //bad data null node
        testTree2.add(node14);
        testTree2.balanceHelperTest(node24);
        assertTrue(node14.getBalance() == 0);

        //dataflow test for modified data
        node87.setComparableObject(9000);
        testTree1.Offer(node87);
        testTree1.balanceHelperTest(node87);
        assertTrue(node87.getBalance() == 0);
    }
    @Test
    public void testBalance() throws Exception {
        //structures basis test -2 balance calls rotate left
        testTree1.add(node98);
        testTree1.add(node57);
        testTree1.add(node24);
        testTree1.balanceTest(node98);
        assertTrue(node98.getBalance() == 0);

        //structures basis test -2 balance calls double rotation left
        testTree2.add(node98);
        testTree2.add(node24);
        testTree2.add(node57);
        testTree2.add(node57);
        testTree1.balanceTest(node98);
        assertTrue(node98.getBalance() == 0);

        this.tearDown();
        this.setUp();

        //structured basis test 2 balance that calls right rotation
        testTree1.add(node14);
        testTree1.add(node28);
        testTree1.add(node37);
        testTree1.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //structured basis test 2 balance that calls left rotation
        testTree2.add(node14);
        testTree2.add(node98);
        testTree2.add(node37);
        testTree2.add(node24);
        testTree2.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test as the balance is already 0
        this.tearDown();
        this.setUp();
        testTree1.add(node14);
        testTree1.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test of a value of 1
        testTree1.add(node28);
        testTree1.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //boundary test with a value of -1 for balance
        testTree2.add(node28);
        testTree2.add(node14);
        testTree2.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //final boundary where the balance is -2 but the child balance is not enough for double rotation
        testTree2.add(node28);
        testTree2.add(node14);
        testTree2.add(node24);
        testTree2.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);

        //final boundary where the balance is -2 but the child balance is not enough for double rotation
        testTree1.add(node14);
        testTree1.add(node28);
        testTree1.add(node24);
        testTree1.balanceTest(node14);
        assertTrue(node14.getBalance() == 0);
        this.tearDown();
        this.setUp();

        //good data
        treeRandom.balanceHelperTest(treeRandom.head);
        assertTrue(treeRandom.head.getBalance() == 0);
        this.tearDown();
        this.setUp();

        //bad data null tree
        testTree2.balanceHelperTest(node14);
        assertTrue(node14.getBalance() == 0);

        //bad data null node
        testTree2.add(node14);
        testTree2.balanceHelperTest(node24);
        assertTrue(node14.getBalance() == 0);
        assertTrue(treeRandom.head.getBalance() == 0);
        this.tearDown();
        this.setUp();

        //boundary test for balance near the size limit
        for (int i = 0; i < 30; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);
        this.tearDown();
        this.setUp();
        //boundary test for balance one under the size limit
        for (int i = 0; i < 29; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);
        this.tearDown();
        this.setUp();
        //boundary test for one over the size limit, delete is called now
        for (int i = 0; i < 31; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);
        this.tearDown();
        this.setUp();
        //boundary test for one over the size limit, delete is called now
        for (int i = 0; i < 31; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);
        //good data
        this.tearDown();
        this.setUp();
        //boundary test for one over the size limit, delete is called now
        for (int i = 0; i < 67; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);
        //good data
        this.tearDown();
        this.setUp();
        //boundary test for one over the size limit, delete is called now
        for (int i = 0; i < 12343; i++){
            testTree1.add(new TreeNode(i, testTree1.inputCounter, null, null, null));
        }
        assertTrue(testTree1.head.getBalance() == 0);

        //dataflow test for modified data
        node87.setComparableObject(9000);
        testTree1.Offer(node87);
        testTree1.balanceTest(node87);
        assertTrue(node87.getBalance() == 0);
    }

    //technically a new method would be needed to check if the tree is valid
    //the problem is I would need to write a new method for it that validates the tree
}
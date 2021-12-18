package BstTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BSTWorksTest {
    @Test
    void testReconstruct() {
        ArrayList<Object> preorderValues = new ArrayList<Object>();
        assertNull(BSTWorks.<Object>reconstruct(preorderValues, new ArrayList<Object>()));
    }

    @Test
    void testReconstruct2() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        assertThrows(IllegalStateException.class, () -> BSTWorks.<Object>reconstruct(objectList, new ArrayList<Object>()));
    }

    @Test
    void testReconstruct3() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<Object>();
        objectList1.add("42");
        assertEquals("42", BSTWorks.<Object>reconstruct(objectList, objectList1).tree);
    }

    @Test
    void testReconstruct4() {
        BSTNodes<Integer> actualReconstructResult = BSTWorks.reconstruct(new int[]{42, 42, 42, 42});
        assertEquals(42, actualReconstructResult.tree.intValue());
        BSTNodes<Integer> bstNodes = actualReconstructResult.left;
        assertNull(bstNodes.right);
        Integer integer = actualReconstructResult.tree;
        BSTNodes<Integer> bstNodes1 = actualReconstructResult.right;
        assertSame(integer, bstNodes1.tree);
        assertNull(bstNodes1.right);
        assertSame(integer, bstNodes.tree);
        BSTNodes<Integer> bstNodes2 = bstNodes.left;
        assertSame(integer, bstNodes2.tree);
        assertNull(bstNodes2.right);
    }

    @Test
    void testReconstructClearer() {
        ArrayList<Object> preorderValues = new ArrayList<Object>();
        assertNull(BSTWorks.<Object>reconstructClearer(preorderValues, new ArrayList<Object>()));
    }

    @Test
    void testReconstructClearer2() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        assertNull(BSTWorks.<Object>reconstructClearer(objectList, new ArrayList<Object>()));
    }

    @Test
    void testReconstructClearer3() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<Object>();
        objectList1.add("42");
        assertEquals("42", BSTWorks.<Object>reconstructClearer(objectList, objectList1).tree);
    }

    @Test
    void testReconstructClearer4() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<Object>();
        objectList1.add("42");
        objectList1.add("42");
        assertThrows(IllegalStateException.class, () -> BSTWorks.<Object>reconstructClearer(objectList, objectList1));
    }

    @Test
    void testReconstructClearer5() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add("42");
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<Object>();
        objectList1.add("42");
        assertThrows(IllegalStateException.class, () -> BSTWorks.<Object>reconstructClearer(objectList, objectList1));
    }

    @Test
    void testDuplicates() {
        assertTrue(BSTWorks.duplicates(new int[]{1, 1, 1, 1}));
        assertFalse(BSTWorks.duplicates(new int[]{}));
    }
}


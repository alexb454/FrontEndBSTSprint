package BstTree;

import java.util.*;

public class BSTWorks {
    static BSTNodes<Integer> reconstruct(final int[] values) {
        if (values.length == 0) {
            return null;
        }

        final int midIdx = values.length / 2;

        final int midValue = values[midIdx];
        final BSTNodes<Integer> newNode = new BSTNodes<>(midValue);

        if (values.length == 1) {
            return newNode;
        }

        final int[] leftPart = Arrays.copyOfRange(values, 0, midIdx);
        final int[] rightPart = Arrays.copyOfRange(values, midIdx + 1,
                values.length);

        newNode.left = reconstruct(leftPart);
        newNode.right = reconstruct(rightPart);

        return newNode;
    }

    static <B> BSTNodes<B> reconstruct(final List<B> preorderValues, final List<B> inorderValues) {
        if (preorderValues.size() != inorderValues.size())
            throw new IllegalStateException("inputs differ in length");

        if (preorderValues.size() == 0 || inorderValues.size() == 0)
            return null;

        final B rootValue = preorderValues.get(0);
        final BSTNodes<B> root = new BSTNodes<>(rootValue);

        if (preorderValues.size() == 1 && inorderValues.size() == 1) {
            return root;
        }

        final int index = inorderValues.indexOf(rootValue);

        root.left = reconstruct(preorderValues.subList(1, index + 1), inorderValues.subList(0, index));
        root.right = reconstruct(preorderValues.subList(index + 1, preorderValues.size()),
                inorderValues.subList(index + 1, inorderValues.size()));

        return root;
    }

    static <B> BSTNodes<B> reconstructClearer(final List<B> preorderValues, final List<B> inorderValues) {
        if (preorderValues.size() == 0 || inorderValues.size() == 0) {
            return null;
        }

        final B rootValue = preorderValues.get(0);
        final BSTNodes<B> root = new BSTNodes<>(rootValue);

        if (preorderValues.size() == 1 && inorderValues.size() == 1) {
            return root;
        }

        final int index = inorderValues.indexOf(rootValue);

        final List<B> leftInoder = inorderValues.subList(0, index);
        final List<B> rightInoder = inorderValues.subList(index + 1, inorderValues.size());

        final List<B> leftPreorder = preorderValues.subList(1, 1 + index);
        final List<B> rightPreorder = preorderValues.subList(index + 1, preorderValues.size());

        root.left = reconstruct(leftPreorder, leftInoder);
        root.right = reconstruct(rightPreorder, rightInoder);

        return root;
    }

    private static void printInfo(final BSTNodes<Integer> root) {
        BSTPrinter.nicePrint(root);
        System.out.println("Root:  " + root);
        System.out.println("Left:  " + root.left);
        System.out.println("Right: " + root.right);
        System.out.println();
    }

    static boolean duplicates(final int[] integers) {
        Set<Integer> lump = new HashSet<Integer>();
        for (int i : integers) {
            if (lump.contains(i)) return true;
            lump.add(i);
        }
        return false;
    }

    public static void main(final String[] args) throws Exception {
        System.out.println("Hello, lets make Happy little Trees!");
        Thread.sleep(500);
        System.out.println("Lets make a binary search tree together!");
        Thread.sleep(500);
        while (true) {
            int arrayLength = 0;
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enter the total number of integers (nodes) in this happy tree: ");
                if (!sc.hasNextInt()) {
                    System.out.println("You have to enter an integer ding dong!");
                    sc.next();
                } else {
                    arrayLength = sc.nextInt();
                    break;
                }
            }
            int[] inputArray = new int[arrayLength];
            System.out.println("Now enter the integers (one at a time  and press return(or enter for pc): ");
            for (int i = 0; i < arrayLength; i++) {
                while (true) {
                    if (!sc.hasNextInt()) {
                        System.out.println("You have to enter an integer ding dong!");
                        sc.next();
                    } else {
                        inputArray[i] = sc.nextInt();
                        break;
                    }
                }
            }
            if (!duplicates(inputArray)) {
                BSTRestClient.postedInputToDatabase(inputArray);
                Arrays.sort(inputArray);
                final int[][] inputs = {inputArray};
                System.out.println("Here is your happy lil tree, bob ross proud:");
                for (int[] values : inputs)
                {
                    final BSTNodes<Integer> root = reconstruct(values);
                    printInfo(root);
                }
                break;
            }
            else {
                System.out.println("Please don't have no duplicate integers ding dong.");
                Thread.sleep(500);
            }
            }
        }
    }
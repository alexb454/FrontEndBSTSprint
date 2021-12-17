package BstTree;

public class BSTHeight {
    static <B> int getHeight(final BSTNodes<B> parent)
    {
        if (parent == null)
            return 0;

        final int leftHeight = getHeight(parent.left);
        final int rightHeight = getHeight(parent.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}

package BstTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTPrinter {
    static int subtreeWidth(final int height)
    {
        if (height <= 0)
            return 0;

        final int leafWidth = 3;
        final int spacing = 3;

        final int maxNumOfLeaves = (int) Math.pow(2, height - 1);
        final int widthOfTree = maxNumOfLeaves * leafWidth + (maxNumOfLeaves - 1) * spacing;
        final int widthOfSubtree = (widthOfTree - spacing) / 2;

        return widthOfSubtree;
    }


    static <B> String drawNode(final BSTNodes<B> currentNode, final int lineLength)
    {
        String strNode = "   ";
        strNode += spacing(lineLength);
        strNode += stringifyNodeValue(currentNode);
        strNode += spacing(lineLength);

        return strNode;
    }

    static <B> String stringifyNodeValue(final BSTNodes<B> node)
    {
        if (node == null || node.tree == null)
            return "   ";

        final String nodeValue = "" + node.tree;
        if (nodeValue.length() == 1)
            return " " + nodeValue + " ";
        if (nodeValue.length() == 2)
            return nodeValue + " ";

        return nodeValue.substring(0, 3);
    }

    static String spacing(final int lineLength)
    {
        return " ".repeat(lineLength);
    }

    static <B> String drawConnections(final BSTNodes<B> currentNode,
                                      final int lineLength)
    {
        if (currentNode == null)
            return "   " + spacing(lineLength) + "   " + spacing(lineLength) + "   ";

        String connection = drawLeftConnectionPart(currentNode, lineLength);
        connection += drawJunction(currentNode);
        connection += drawRightConnectionPart(currentNode, lineLength);
        return connection;
    }

    static <B> String drawLeftConnectionPart(final BSTNodes<B> currentNode,
                                             final int lineLength)
    {
        if (currentNode.left == null)
            return "   " + spacing(lineLength);

        return " |-" + drawLine(lineLength);
    }

    static <B> String drawJunction(final BSTNodes<B> currentNode)
    {
        if (currentNode.left == null && currentNode.right == null)
            return "   ";
        else if (currentNode.left == null)
            return " +-";
        else if (currentNode.right == null)
            return "-+ ";

        return "-+-";
    }

    static <B> String drawRightConnectionPart(final BSTNodes<B> currentNode,
                                              final int lineLength)
    {
        if (currentNode.right == null)
            return spacing(lineLength) + "   ";

        return drawLine(lineLength) + "-| ";
    }

    static String drawLine(final int lineLength)
    {
        return "-".repeat(lineLength);
    }

    static String spacingBetweenNodes(final int treeHeight, final int level)
    {
        final int spacingLength = subtreeWidth(treeHeight - level);
        String spacing = " ".repeat(spacingLength);
        if (spacingLength > 0)
            spacing += "   ";
        return spacing;
    }

    static String spacingBetweenConnections(final int treeHeight, final int level)
    {
        final int spacingLength = subtreeWidth(treeHeight - level);
        return " ".repeat(spacingLength);
    }

    static class Pair<B1, B2>
    {
        final B1 first;

        final B2 second;

        public Pair(final B1 left, final B2 right)
        {
            this.first = left;
            this.second = right;
        }
    }

    public static <B> void nicePrint(final BSTNodes<B> startNode)
    {
        if (startNode == null)
            return;

        final int treeHeight = BSTHeight.getHeight(startNode);
        final List<String> lines = new ArrayList<>();

        int level = 0;
        String nodeLine = "";
        String connectionLine = "";

        final Queue<Pair<BSTNodes<B>, Integer>> toProcess = new LinkedList<>();
        toProcess.offer(new Pair<>(startNode, 0));

        while (!toProcess.isEmpty() && level < treeHeight)
        {
            final Pair<BSTNodes<B>, Integer> current = toProcess.poll();
            final BSTNodes<B> currentNode = current.first;
            final int nodelevel = current.second;

            int lineLength = subtreeWidth(treeHeight - 1 - level);

            if (level != nodelevel)
            {
                level = nodelevel;
                lineLength = subtreeWidth(treeHeight - 1 - level);

                lines.add(nodeLine.stripTrailing());
                lines.add(connectionLine.stripTrailing());
                nodeLine = "";
                connectionLine = "";

                for (int i = 0; i < lines.size(); i++)
                {
                    lines.set(i, "   " + spacing(lineLength) + lines.get(i));
                }
            }

            nodeLine += drawNode(currentNode, lineLength);
            nodeLine += spacingBetweenNodes(treeHeight, level);
            connectionLine += drawConnections(currentNode, lineLength);
            connectionLine += spacingBetweenConnections(treeHeight, level);

            if (currentNode != null)
            {
                toProcess.offer(new Pair<>(currentNode.left, level + 1));
                toProcess.offer(new Pair<>(currentNode.right, level + 1));
            }
            else
            {
                toProcess.offer(new Pair<>(null, level + 1));
                toProcess.offer(new Pair<>(null, level + 1));
            }
        }

        lines.forEach(System.out::println);
    }
}

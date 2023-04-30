import java.util.*;

public class Huffman {

    private HuffmanNode root = null;
    private HashMap<String, String> codeMap = new HashMap<>();

    private class HuffmanNode {
        int p;
        String c;

        HuffmanNode left;
        HuffmanNode right;

        static int order = 0;
        int myOrder;


        public HuffmanNode(String c, int p) {
            this.c = c;
            this.p = p;
            left = null;
            right = null;
            myOrder = order++;
        }
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void encode(String[] symbols, int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(symbols.length, (x, y) -> {
            if (x.p == y.p) return x.myOrder - y.myOrder;
            else return x.p - y.p;
        });

        HuffmanNode.order = 0;
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] != null) {
                HuffmanNode node = new HuffmanNode(symbols[i], frequencies[i]);
                queue.add(node);
            }
        }

        while (queue.size() > 1) {
            HuffmanNode x = queue.poll();
            HuffmanNode y = queue.poll();

            HuffmanNode node = new HuffmanNode("", x.p + y.p);
            node.left = x;
            node.right = y;

            root = node;
            queue.add(node);
        }
    }

    public HashMap<String, String> generateCodeMap() {
        generateCodeMapHelper(root, "");
        return codeMap;
    }

    private void generateCodeMapHelper(HuffmanNode node, String s) {
        if (node.left == null && node.right == null && !node.c.equals("")) {
            codeMap.put(node.c, s);
            return;
        }
        generateCodeMapHelper(node.left, s + "0");
        generateCodeMapHelper(node.right, s + "1");
    }

    public void printMap() {
        System.out.println("Symbol map: ");
        TreeMap<String, String> sorted = new TreeMap<>();
        sorted.putAll(codeMap);
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }

    public double[] calculateEfficiency(String[] symbols, double[] symbolsProbability, double H) {
        double Lsr = 0;
        int ldq = symbols[0].length();
        for (int i = 0; i < symbols.length; i++) {
            if (symbolsProbability[i] == 0)
                continue;
            Lsr += symbolsProbability[i] * codeMap.get(symbols[i]).length();
        }

        double codingEfficiency = H / Lsr * 100;
        double compressionRatio = ldq / Lsr;

        System.out.println("\nH(S) = " + H);
        System.out.println("Lsr = " + Lsr);
        System.out.println("Coding efficiency: " + codingEfficiency);
        System.out.println("Compression ratio: " + compressionRatio);

        return new double[]{Lsr, codingEfficiency, compressionRatio};
    }
}

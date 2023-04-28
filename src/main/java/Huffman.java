import java.util.*;

public class Huffman {

    private HuffmanNode root = null;
    private HashMap<String, String> codeMap = new HashMap<>();

    private class HuffmanNode {
        int p;
        String c;

        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(String c, int p) {
            this.c = c;
            this.p = p;
            left = null;
            right = null;
        }
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void encode(String[] symbols, int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(symbols.length, (x, y) -> {
            return x.p - y.p;
        });

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

    public void generateCodeMap(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && !root.c.equals("")) {
            codeMap.put(root.c, s);
            return;
        }
        generateCodeMap(root.left, s + "0");
        generateCodeMap(root.right, s + "1");
    }

    public void printMap() {
        System.out.println("Symbol map: ");
        TreeMap<String, String> sorted = new TreeMap<>();
        sorted.putAll(codeMap);
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }

    public void calculateEfficiency(String[] symbols, double[] symbolsProbability, double H) {
        double Lsr = 0;
        for (int i = 0; i < symbols.length; i++) {
            if (symbolsProbability[i] == 0)
                continue;
            Lsr += symbolsProbability[i] * codeMap.get(symbols[i]).length();
        }
        System.out.println("\nH(S) = " + H);
        System.out.println("Lsr = " + Lsr);
        System.out.println("Coding efficiency: " + H / Lsr * 100);
        int ldq = symbols[0].length();
        System.out.println("Compression ratio: " + ldq / Lsr);
    }
}

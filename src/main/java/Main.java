import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the sequence: ");
        int n = sc.nextInt();
        System.out.println("Enter p01: ");
        double p01 = sc.nextDouble();
        System.out.println("Enter p10: ");
        double p10 = sc.nextDouble();

        Source source = new Source(n, p01, p10);
        source.generateSymbols();
        source.printSymbols();

        HashMap<String, Integer> symbolCounter = null;

        for (int expansion = 1; expansion <= 5; expansion++) {
            System.out.println("\n" + expansion + ". expansion:");
            symbolCounter = source.calculateSymbolsProbability(expansion);
            System.out.println();

            int numberOfSymbols = 2 << (expansion - 1);
            String[] symbols = new String[numberOfSymbols];
            int[] symbolsFreq = new int[numberOfSymbols];
            double[] symbolsProbability = new double[numberOfSymbols];

            int i = 0;
            for (Map.Entry<String, Integer> set : symbolCounter.entrySet()) {
                symbols[i] = set.getKey();
                symbolsFreq[i] = set.getValue();
                i++;
            }
            for (i = 0; i < symbolsFreq.length; i++) {
                symbolsProbability[i] = symbolsFreq[i] * 1.0 / n * expansion;
            }

            Huffman huffman = new Huffman();
            huffman.encode(symbols, symbolsFreq);
            huffman.generateCodeMap(huffman.getRoot(), "");

            huffman.printMap();

            huffman.calculateEfficiency(symbols, symbolsProbability, source.getH() * expansion);
        }
    }
}

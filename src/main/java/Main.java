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

        for (int extension = 1; extension <= 5; extension++) {
            System.out.println("\n" + extension + ". extension:");
            symbolCounter = source.countSymbols(extension);
            System.out.println();

            int numberOfSymbols = 2 << (extension - 1);
            String[] symbols = new String[numberOfSymbols];
            int[] symbolsFreq = new int[numberOfSymbols];

            int i = 0;
            for (Map.Entry<String, Integer> set : symbolCounter.entrySet()) {
                symbols[i] = set.getKey();
                symbolsFreq[i] = set.getValue();
                i++;
            }
            double[] symbolProbabilities = source.calculateSymbolProbabilities(symbolsFreq, extension);
            source.printProbabilitiesOrderedBySymbols(symbolCounter, extension);

            Huffman huffman = new Huffman();
            huffman.encode(symbols, symbolsFreq);
            huffman.generateCodeMap();

            huffman.printMap();

            huffman.calculateEfficiency(symbols, symbolProbabilities, source.getH() * extension);
        }
    }
}

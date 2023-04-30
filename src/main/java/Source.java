import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Source {
    private char[] symbols;
    private double p01, p10, p00, p11;
    private double H;

    public Source(int n, double p01, double p10) {
        this.symbols = new char[n];
        this.p01 = p01;
        this.p10 = p10;
        this.p00 = 1 - p01;
        this.p11 = 1 - p10;

        this.H = calculateEntropy();
    }

    public double getH() {
        return H;
    }

    public double calculateEntropy() {
        double p0 = p10 / (p01 + p10);
        double p1 = 1 - p0;

        double H1 = 0, H2 = 0, H3 = 0, H4 = 0;
        if (p00 != 0) H1 = p0 * p00 * Math.log(1 / p00) / Math.log(2);
        if (p10 != 0) H2 = p0 * p10 * Math.log(1 / p10) / Math.log(2);
        if (p01 != 0) H3 = p1 * p01 * Math.log(1 / p01) / Math.log(2);
        if (p11 != 0) H4 = p1 * p11 * Math.log(1 / p11) / Math.log(2);

        return H1 + H2 + H3 + H4;
    }

    public void setSymbols(char[] symbols) {
        this.symbols = symbols;
    }

    public void generateSymbols() {
        if (Math.random() < 0.5)
            symbols[0] = '0';
        else
            symbols[0] = '1';
        for (int i = 1; i < symbols.length; i++) {
            double p = Math.random();
            if (symbols[i - 1] == '0') {
                if (p < p01)
                    symbols[i] = '1';
                else
                    symbols[i] = '0';
            } else {
                if (p < p10)
                    symbols[i] = '0';
                else
                    symbols[i] = '1';
            }
        }
    }

    public HashMap<String, Integer> countSymbols(int extension) {
        HashMap<String, Integer> symbolCounter = new HashMap<>();
        String s;
        for (int i = 0; i < symbols.length; i += extension) {
            try {
                s = new String(symbols, i, extension);
            } catch (Exception e) {
                break;
            }
            if (symbolCounter.containsKey(s)) {
                symbolCounter.replace(s, symbolCounter.get(s) + 1);
            } else {
                symbolCounter.put(s, 1);
            }
        }

        return symbolCounter;
    }

    public double[] calculateSymbolProbabilities(int[] symbolsFreq, int extension) {
        int n = symbolsFreq.length;
        double[] symbolsProbability = new double[n];
        int sequenceLength = symbols.length / extension;
        for (int i = 0; i < symbolsProbability.length; i++) {
            symbolsProbability[i] = symbolsFreq[i] * 1.0 / sequenceLength;
        }

        return symbolsProbability;
    }

    public void printSymbols() {
        for (int i = 0; i < symbols.length; i++) {
            System.out.print(symbols[i]);
            if ((i + 1) % 100 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printProbabilitiesOrderedBySymbols(HashMap<String, Integer> symbolCounter, int extension) {
        int sequenceLength = symbols.length / extension;
        System.out.println("Symbol probabilities: ");
        TreeMap<String, Integer> sorted = new TreeMap<>();
        sorted.putAll(symbolCounter);
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue() * 1.0 / sequenceLength);
        }
        System.out.println();
    }

    public void analyzeSequence() {
        int totalZeros = 0;
        int totalOnes = 0;
        int zeroAfterZero = 0;
        int zeroAfterOne = 0;
        int oneAfterZero = 0;
        int oneAfterOne = 0;

        for (int i = 1; i < symbols.length; i++) {
            if (symbols[i] == '0' && symbols[i - 1] == '0')
                zeroAfterZero++;
            if (symbols[i] == '0' && symbols[i - 1] == '1')
                zeroAfterOne++;
            if (symbols[i] == '1' && symbols[i - 1] == '0')
                oneAfterZero++;
            if (symbols[i] == '1' && symbols[i - 1] == '1')
                oneAfterOne++;
        }

        double a = oneAfterZero * 1.0 / (oneAfterZero + zeroAfterZero);
        double b = zeroAfterOne * 1.0 / (zeroAfterOne + oneAfterOne);

        System.out.println("One after zero: " + a);
        System.out.println("Zero after one: " + b);
    }
}

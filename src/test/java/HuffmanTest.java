import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {

    Huffman huffman = new Huffman();

    @Test
    void testGenerateCodeMapNoExtension() {
        String[] symbols = new String[]{"0", "1"};
        int[] symbolsFreq = new int[]{50, 50};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("0", "0");
        expectedMap.put("1", "1");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testGenerateCodeMapSecondExtension() {
        String[] symbols = new String[]{"00", "01", "10", "11"};
        int[] symbolsFreq = new int[]{5, 55, 30, 10};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("00", "000");
        expectedMap.put("01", "1");
        expectedMap.put("10", "01");
        expectedMap.put("11", "001");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testGenerateCodeMapSecondExtension2() {
        String[] symbols = new String[]{"00", "01", "10", "11"};
        int[] symbolsFreq = new int[]{20, 10, 50, 20};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("00", "111");
        expectedMap.put("01", "110");
        expectedMap.put("10", "0");
        expectedMap.put("11", "10");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testGenerateCodeMapSecondExtensionWithEqualProbabilities() {
        String[] symbols = new String[]{"00", "01", "10", "11"};
        int[] symbolsFreq = new int[]{50, 50, 50, 50};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("00", "00");
        expectedMap.put("01", "01");
        expectedMap.put("10", "10");
        expectedMap.put("11", "11");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testGenerateCodeMapThirdExtension() {
        String[] symbols = new String[]{"000", "001", "010", "011", "100", "101", "110", "111"};
        int[] symbolsFreq = new int[]{6, 8, 10, 10, 12, 13, 18, 23};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("000", "1100");
        expectedMap.put("001", "1101");
        expectedMap.put("010", "000");
        expectedMap.put("011", "001");
        expectedMap.put("100", "100");
        expectedMap.put("101", "101");
        expectedMap.put("110", "111");
        expectedMap.put("111", "01");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testGenerateCodeMapThirdExtension2() {
        String[] symbols = new String[]{"000", "001", "010", "011", "100"};
        int[] symbolsFreq = new int[]{10, 8, 12, 50, 15};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("000", "001");
        expectedMap.put("001", "000");
        expectedMap.put("010", "010");
        expectedMap.put("011", "1");
        expectedMap.put("100", "011");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void testEfficiencyCalculation() {
        String[] symbols = {"0", "1"};
        double[] symbolsProbability = {0.5, 0.5};
        int[] symbolsFreq = {10, 10};
        double H = 1;
        double expectedLsr = 1;
        double expectedCodingEfficiency = 100;
        double expectedCompressionRatio = 1;

        huffman.encode(symbols, symbolsFreq);
        huffman.generateCodeMap();

        double[] results = huffman.calculateEfficiency(symbols, symbolsProbability, H);
        assertEquals(expectedLsr, results[0]);
        assertEquals(expectedCodingEfficiency, results[1]);
        assertEquals(expectedCompressionRatio, results[2]);
    }

    @Test
    void testEfficiencyCalculation2() {
        String[] symbols = {"00", "01", "10", "11"};
        double[] symbolsProbability = {0.2, 0.1, 0.5, 0.2};
        int[] symbolsFreq = {20, 10, 50, 20};
        double H = 1.76096;
        double expectedLsr = 1.8;
        double expectedCodingEfficiency = 97.83111;
        double expectedCompressionRatio = 1.11111;

        huffman.encode(symbols, symbolsFreq);
        huffman.generateCodeMap();

        double[] results = huffman.calculateEfficiency(symbols, symbolsProbability, H);
        assertEquals(expectedLsr, DoubleRounder.round(results[0], 5));
        assertEquals(expectedCodingEfficiency, DoubleRounder.round(results[1], 5));
        assertEquals(expectedCompressionRatio, DoubleRounder.round(results[2], 5));
    }

}
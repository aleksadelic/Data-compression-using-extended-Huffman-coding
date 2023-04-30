import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

}
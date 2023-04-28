import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {

    @Test
    void testGenerateCodeMapNoExtension() {
        Huffman huffman = new Huffman();
        String[] symbols = new String[]{"0", "1"};
        int[] symbolsFreq = new int[]{50, 50};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("0", "0");
        expectedMap.put("1", "1");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap(huffman.getRoot(), "");
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            String key = entry.getKey();
            assertEquals(entry.getValue().length(), actualMap.get(key).length());
        }
    }

    @Test
    void testGenerateCodeMapSecondExtension() {
        Huffman huffman = new Huffman();
        String[] symbols = new String[]{"00", "01", "10", "11"};
        int[] symbolsFreq = new int[]{5, 55, 30, 10};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("00", "000");
        expectedMap.put("01", "1");
        expectedMap.put("10", "01");
        expectedMap.put("11", "001");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap(huffman.getRoot(), "");
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            String key = entry.getKey();
            assertEquals(entry.getValue().length(), actualMap.get(key).length());
        }
    }

    @Test
    void testGenerateCodeMapSecondExtensionWithEqualProbabilities() {
        Huffman huffman = new Huffman();
        String[] symbols = new String[]{"00", "01", "10", "11"};
        int[] symbolsFreq = new int[]{50, 50, 50, 50};
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("00", "00");
        expectedMap.put("01", "01");
        expectedMap.put("10", "10");
        expectedMap.put("11", "11");
        huffman.encode(symbols, symbolsFreq);
        HashMap<String, String> actualMap = huffman.generateCodeMap(huffman.getRoot(), "");
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            String key = entry.getKey();
            assertEquals(entry.getValue().length(), actualMap.get(key).length());
        }
    }
}
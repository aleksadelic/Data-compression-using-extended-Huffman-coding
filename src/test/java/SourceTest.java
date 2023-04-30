import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SourceTest {

    @Test
    void testEntropyOfDeterministicSequence11() {
        Source source = new Source(1000, 1, 1);
        source.calculateEntropy();
        double expected = 0;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testEntropyOfDeterministicSequence01() {
        Source source = new Source(1000, 0, 1);
        source.calculateEntropy();
        double expected = 0;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testEntropyOf05And05() {
        Source source = new Source(1000, 0.5, 0.5);
        source.calculateEntropy();
        double expected = 1;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testEntropyOf099And099() {
        Source source = new Source(1000, 0.99, 0.99);
        source.calculateEntropy();
        double expected = 0.08079;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testEntropyOf02And01() {
        Source source = new Source(1000, 0.2, 0.1);
        source.calculateEntropy();
        double expected = 0.59737;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testSymbolCounterNoExtension() {
        Source source = new Source(10, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '0', '1', '0', '1', '0', '1', '0', '1'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("0", 5);
        expectedMap.put("1", 5);
        assertEquals(expectedMap, source.countSymbols(1));
    }

    @Test
    void testSymbolCounterOfSecondExtension() {
        Source source = new Source(10, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '0', '1', '0', '1', '0', '1', '0', '1'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("01", 5);
        assertEquals(expectedMap, source.countSymbols(2));
    }

    @Test
    void testSymbolCounterOfThirdExtension() {
        Source source = new Source(10, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '0', '1', '0', '1', '0', '1', '0', '1'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("010", 2);
        expectedMap.put("101", 1);
        assertEquals(expectedMap, source.countSymbols(3));
    }

    @Test
    void testSymbolCounterOfFourthExtension() {
        Source source = new Source(10, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '0', '1', '0', '1', '0', '1', '0', '1'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("0101", 2);
        assertEquals(expectedMap, source.countSymbols(4));
    }

    @Test
    void testSymbolCounterOfFifthExtension() {
        Source source = new Source(10, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '0', '1', '0', '1', '0', '1', '0', '1'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("01010", 1);
        expectedMap.put("10101", 1);
        assertEquals(expectedMap, source.countSymbols(5));
    }

    @Test
    void testSymbolCounterNoExtension2() {
        Source source = new Source(20, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '1', '1', '0', '1', '0', '1', '1', '1',
                '1', '0', '1', '1', '0', '1', '1', '0', '0', '0'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("0", 8);
        expectedMap.put("1", 12);
        assertEquals(expectedMap, source.countSymbols(1));
    }

    @Test
    void testSymbolCounterOfSecondExtension2() {
        Source source = new Source(20, 0.5, 0.5);
        source.setSymbols(new char[]{'0', '1', '1', '1', '0', '1', '0', '1', '1', '1',
                '1', '0', '1', '1', '0', '1', '1', '0', '0', '0'});
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("00", 1);
        expectedMap.put("01", 4);
        expectedMap.put("10", 2);
        expectedMap.put("11", 3);
        assertEquals(expectedMap, source.countSymbols(2));
    }

}
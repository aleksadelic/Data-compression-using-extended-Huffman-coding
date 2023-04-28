import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.Test;

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
    void testEntropy099And099() {
        Source source = new Source(1000, 0.99, 0.99);
        source.calculateEntropy();
        double expected = 0.08079;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

    @Test
    void testEntropy02And01() {
        Source source = new Source(1000, 0.2, 0.1);
        source.calculateEntropy();
        double expected = 0.59737;
        assertEquals(expected, DoubleRounder.round(source.getH(), 5));
    }

}
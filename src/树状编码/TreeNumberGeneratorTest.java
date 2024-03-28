import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreeNumberGeneratorTest {

    @Test
    void testGenerateNextNumberSimple() {
        assertEquals("AAAB", TreeNumberGenerator.generateNextNumber("AAAA"));
    }

    @Test
    void testGenerateNextNumberWithDash() {
        assertEquals("AAAA-AAAB", TreeNumberGenerator.generateNextNumber("AAAA-AAAA"));
    }

    @Test
    void testGenerateNextNumberWithMultipleParts() {
        assertEquals("AAA-BBB-CCD", TreeNumberGenerator.generateNextNumber("AAA-BBB-CCC"));
    }


}

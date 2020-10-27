package io.prestosql.string.functions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static io.airlift.slice.Slices.utf8Slice;
import org.testng.annotations.Test;



public class TrimFunctionsTest {
    @Test
    public void testltrimSlice() {
        // Test null input
        assertEquals(TrimFunctions.leftTrim(null, 2), null);

        // Test trim more than existing tokens
        assertEquals(TrimFunctions.leftTrim(utf8Slice(""), 2), utf8Slice(""));
        assertEquals(TrimFunctions.leftTrim(utf8Slice("a"), 2), utf8Slice(""));

        // Test trim exactly the same of string
        assertEquals(TrimFunctions.leftTrim(utf8Slice("ab"), 2), utf8Slice(""));

        // Test trim less than existing tokens
        assertEquals(TrimFunctions.leftTrim(utf8Slice("abc"), 2).toStringUtf8(), "c");

        // Test trim size is 0
        assertEquals(TrimFunctions.leftTrim(utf8Slice("abc"), 0).toStringUtf8(), "abc");
    }

    @Test
    public void testltrimLong() {
        // Test null input
        assertNull(TrimFunctions.leftTrim(0, true, 2));

        // Test trim more than existing length
        assertNull(TrimFunctions.leftTrim(1, false, 2));

        // Test trim exactly the same of string
        assertNull(TrimFunctions.leftTrim(12, false, 2));

        // Test trim less than existing tokens
        long res = TrimFunctions.leftTrim(12345, false, 2);
        assertEquals(res, 345);
    }

    @Test
    public void testltrimDefaultTrim() {
        // Test numeric input
        long res = TrimFunctions.leftTrim(12345, false);
        assertEquals(res, 5);

        // Test string input
        assertEquals(TrimFunctions.leftTrim(utf8Slice("12345")).toStringUtf8(), "5");
    }
}
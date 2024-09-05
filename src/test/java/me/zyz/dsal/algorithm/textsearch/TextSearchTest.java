package me.zyz.dsal.algorithm.textsearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextSearchTest {

    @Test
    public void roughSearch() {
        final SubStringSearch searchMethod = new RoughSubStringSearch();

        final String text = "121231234";

        assertEquals(2, searchMethod.findFirst(text, "123"));
        assertEquals(0, searchMethod.findFirst(text, "12"));
        assertEquals(6, searchMethod.findFirst(text, "234"));
        assertEquals(4, searchMethod.findFirst(text, "31"));
        assertEquals(5, searchMethod.findFirst(text, "1234"));
    }

    @Test
    public void kmpSearch() {
        final SubStringSearch searchMethod = new KmpSubStringSearch();

        final String text = "121231234";

        assertEquals(2, searchMethod.findFirst(text, "123"));
        assertEquals(0, searchMethod.findFirst(text, "12"));
        assertEquals(6, searchMethod.findFirst(text, "234"));
        assertEquals(4, searchMethod.findFirst(text, "31"));
        assertEquals(5, searchMethod.findFirst(text, "1234"));
    }
}

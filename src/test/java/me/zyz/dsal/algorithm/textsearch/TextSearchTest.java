package me.zyz.dsal.algorithm.textsearch;

import org.junit.jupiter.api.Test;

class TextSearchTest {

    @Test
    public void roughSearch() {
        String text = "121231234";

        assertEquals(2, TextSearch.roughSearch(text, "123"));
        assertEquals(0, TextSearch.roughSearch(text, "12"));
        assertEquals(6, TextSearch.roughSearch(text, "234"));
        assertEquals(4, TextSearch.roughSearch(text, "31"));
        assertEquals(5, TextSearch.roughSearch(text, "1234"));
    }

    @Test
    public void kmpSearch() {
        String text = "121231234";

        assertEquals(2, TextSearch.kmpSearch(text, "123"));
        assertEquals(0, TextSearch.kmpSearch(text, "12"));
        assertEquals(6, TextSearch.kmpSearch(text, "234"));
        assertEquals(4, TextSearch.kmpSearch(text, "31"));
        assertEquals(5, TextSearch.kmpSearch(text, "1234"));
    }
}

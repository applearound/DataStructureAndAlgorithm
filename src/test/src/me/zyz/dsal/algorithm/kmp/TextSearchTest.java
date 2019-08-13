package me.zyz.dsal.algorithm.kmp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(2, TextSearch.betterRoughSearch(text, "123"));
        assertEquals(0, TextSearch.betterRoughSearch(text, "12"));
        assertEquals(6, TextSearch.betterRoughSearch(text, "234"));
        assertEquals(4, TextSearch.betterRoughSearch(text, "31"));
        assertEquals(5, TextSearch.betterRoughSearch(text, "1234"));
    }
}

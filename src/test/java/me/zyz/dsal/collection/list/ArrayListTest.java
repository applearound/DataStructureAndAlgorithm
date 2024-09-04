package me.zyz.dsal.collection.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest {
    private ArrayList<Object> arrayList;

    @BeforeEach
    void init() {
        arrayList = new ArrayList<>();
    }

    @AfterEach
    void destroy() {
        arrayList = null;
    }

    @Test
    void testAdd() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertEquals(3, arrayList.size());

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }
}
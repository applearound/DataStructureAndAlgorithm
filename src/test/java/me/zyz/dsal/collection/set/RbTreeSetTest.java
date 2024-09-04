package me.zyz.dsal.collection.set;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RbTreeSetTest {

    static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("filename is null or words is null");
            return false;
        }

        // 文件读取
        Scanner scanner;

        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), StandardCharsets.UTF_8);
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("Cannot open " + filename);
            return false;
        }

        // 简单分词
        // 这个分词方式相对简陋, 没有考虑很多文本处理中的特殊问题
        // 在这里只做demo展示用
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
        }

        return true;
    }

    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++)
            if (Character.isLetter(s.charAt(i)))
                return i;
        return s.length();
    }

    @Test
    void testAdd() {
        final RbTreeSet<Integer> rbTreeSet = new RbTreeSet<>();

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.add(i);
        }

        assertEquals(1000, rbTreeSet.size());
    }

    @Test
    void testAddThenRemove() {
        final RbTreeSet<Integer> rbTreeSet = new RbTreeSet<>();

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.add(i);
        }

        assertEquals(1000, rbTreeSet.size());

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.remove(i);
        }

        assertEquals(0, rbTreeSet.size());
    }
}

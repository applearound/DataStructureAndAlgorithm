package me.zyz.dsal.algorithm.textsearch;

import java.util.Objects;

/**
 * @author yezhou
 */
public class TextSearch {
    public static int roughSearch(final CharSequence text, final CharSequence words) {
        // 判断 null
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(words, "words");

        final int textLength = text.length();
        final int wordsLength = words.length();

        // 单词长度长于文本直接返回 -1
        if (wordsLength > textLength) {
            return -1;
        }

        final int over = textLength - wordsLength;
        for (int i = 0; i <= over; i++) {
            int possibleIdx = 0;
            for (; possibleIdx < wordsLength; possibleIdx++) {
                if (text.charAt(i + possibleIdx) != words.charAt(possibleIdx)) {
                    break;
                }
            }
            if (possibleIdx == words.length()) {
                return i;
            }
        }
        return -1;
    }

    public static int kmpSearch(CharSequence text, CharSequence words) {
        int n = words.length();
        if (n == 0) {
            throw new IllegalArgumentException("words length must greater than 0");
        }

        int m = text.length();

        if (m >= n) {
            int[] supportArray = buildSupportArray(words);

            int i = 0;
            int j = 0;
            while (i < m) {
                if (text.charAt(i) != words.charAt(j)) {
                    if (supportArray[j] == -1) {
                        ++i;
                    } else {
                        j = supportArray[j];
                    }
                } else if (j < n - 1) {
                    ++i;
                    ++j;
                } else {
                    return i - j;
                }
            }
        }

        return -1;
    }

    private static int[] buildSupportArray(CharSequence seq) {
        int[] supportArray = new int[seq.length()];
        supportArray[0] = 0;

        int i = 1;
        int j = 0;
        while (i < seq.length()) {
            if (seq.charAt(i) == seq.charAt(j)) {
                supportArray[i++] = ++j;
            } else if (j != 0) {
                j = supportArray[j - 1];
            } else {
                supportArray[i++] = 0;
            }
        }

        System.arraycopy(supportArray, 0, supportArray, 1, supportArray.length - 1);
        supportArray[0] = -1;

        return supportArray;
    }
}

package me.zyz.dsal.algorithm.textsearch;

import lombok.NonNull;

public class KmpSubStringSearch implements SubStringSearch {
    @Override
    public int findFirst(final @NonNull CharSequence text, final @NonNull CharSequence pattern) {
        final int textLength  = text.length();
        final int wordsLength = pattern.length();

        if (wordsLength == 0) {
            throw new IllegalArgumentException("所查询的子串长度为0");
        }

        // 需要查找的子串长度大于文本长度，不需要查找
        if (textLength < wordsLength) {
            return -1;
        }

        final int[] supportArray = buildSupportArray(pattern);

        int i = 0;
        int j = 0;
        while (i < textLength) {
            if (text.charAt(i) != pattern.charAt(j)) {
                if (supportArray[j] == -1) {
                    i++;
                } else {
                    j = supportArray[j];
                }
            } else if (j < wordsLength - 1) {
                i++;
                j++;
            } else {
                return i - j;
            }
        }

        return -1;
    }

    private static int[] buildSupportArray(final CharSequence seq) {
        final int[] supportArray = new int[seq.length()];
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

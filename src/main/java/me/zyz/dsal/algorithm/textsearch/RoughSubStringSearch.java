package me.zyz.dsal.algorithm.textsearch;

import lombok.NonNull;

public class RoughSubStringSearch implements SubStringSearch {
    @Override
    public int findFirst(final @NonNull CharSequence text, final @NonNull CharSequence pattern) {
        final int textLength  = text.length();
        final int wordsLength = pattern.length();

        if (wordsLength == 0) {
            throw new IllegalArgumentException("所查询的子串长度为0");
        }

        // 需要查找的子串长度大于文本长度，不需要查找
        if (wordsLength > textLength) {
            return -1;
        }

        final int rightLimit = textLength - wordsLength;
        for (int i = 0; i <= rightLimit; i++) {
            int possibleIdx = 0;
            for (; possibleIdx < wordsLength; possibleIdx++) {
                if (text.charAt(i + possibleIdx) != pattern.charAt(possibleIdx)) {
                    break;
                }
            }
            if (possibleIdx == pattern.length()) {
                return i;
            }
        }
        return -1;
    }
}

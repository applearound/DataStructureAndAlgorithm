package me.zyz.dsal.algorithm.textsearch;

import lombok.NonNull;

public interface SubStringSearch {
    default boolean contains(@NonNull final CharSequence text, @NonNull final CharSequence pattern) {
        return findFirst(text, pattern) != -1;
    }

    int findFirst(@NonNull final CharSequence text, @NonNull final CharSequence pattern);
}

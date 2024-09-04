package me.zyz.dsal.algorithm.hanoi;

import lombok.NonNull;

public final class FrameUtils {
    private FrameUtils() {
    }

    public static Frame newFrame(
            final int n,
            @NonNull final String from,
            @NonNull final String to,
            @NonNull final String aux
    ) {
        final Frame frame = new Frame();

        frame.setPc(0);
        frame.setN(n);
        frame.setFrom(from);
        frame.setTo(to);
        frame.setAux(aux);

        return frame;
    }
}

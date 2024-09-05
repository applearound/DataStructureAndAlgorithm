package me.zyz.dsal.algorithm.hanoi;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HanoiMachine implements Hanoi {
    // 栈
    private final Frame[] stack;

    // 栈指针
    private int sp;
    // 移动次数寄存器
    private int reg0;

    public HanoiMachine(int n, String from, String to, String aux) {
        // 初始化栈和栈指针，栈指针指向栈底
        this.stack = new Frame[1024];
        this.sp    = stack.length - 1;

        // 将初始栈帧压入栈
        this.stack[sp] = FrameUtils.newFrame(n, from, to, aux);
    }

    private void push(@NonNull final Frame frame) {
        if (sp == 0) {
            throw new RuntimeException("栈已满");
        }

        stack[--sp] = frame;
    }

    private Frame pop() {
        if (sp == stack.length) {
            throw new RuntimeException("栈已空");
        }

        final Frame discardFrame = stack[sp];

        stack[sp++] = null;

        return discardFrame;
    }

    private Frame peekTop() {
        if (sp < 0 || sp >= stack.length) {
            throw new RuntimeException("栈指针越界");
        }
        return stack[sp];
    }

    private boolean isEmpty() {
        return sp == stack.length;
    }

    private boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public int move() {
        while (isNotEmpty()) {
            final Frame topFrame = peekTop();
            switch (topFrame.getPc()) {
                case 0 -> {
                    if (topFrame.getN() == 1) {
                        log.info("从 {} 移动盘片 1 至 {}", topFrame.getFrom(), topFrame.getTo());
                        reg0 = 1;
                        pop();
                    } else {
                        topFrame.setPc(1);
                    }
                }
                case 1 -> {
                    final Frame newFrame =
                            FrameUtils.newFrame(topFrame.getN() - 1, topFrame.getFrom(), topFrame.getAux(), topFrame.getTo());
                    push(newFrame);
                    topFrame.setPc(2);
                }
                case 2 -> {
                    topFrame.setL(reg0);
                    topFrame.setPc(3);
                }
                case 3 -> {
                    log.info("从 {} 移动盘片 {} 至 {}", topFrame.getFrom(), topFrame.getN(), topFrame.getTo());
                    topFrame.setPc(4);
                }
                case 4 -> {
                    final Frame newFrame =
                            FrameUtils.newFrame(topFrame.getN() - 1, topFrame.getAux(), topFrame.getTo(), topFrame.getFrom());
                    push(newFrame);
                    topFrame.setPc(5);
                }
                case 5 -> {
                    topFrame.setR(reg0);
                    topFrame.setPc(6);
                }
                case 6 -> {
                    reg0 = topFrame.getL() + topFrame.getR() + 1;
                    pop();
                }
                default -> throw new RuntimeException("非法的PC指向：" + topFrame.getPc());
            }
        }

        return reg0;
    }
}

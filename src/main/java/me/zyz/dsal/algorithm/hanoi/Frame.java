package me.zyz.dsal.algorithm.hanoi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Frame {
    /**
     * 需要移动的盘片数量
     */
    private int n;

    /**
     * 起始柱
     */
    private String from;
    /**
     * 目标柱
     */
    private String to;
    /**
     * 辅助柱
     */
    private String aux;

    private int pc;
    private int l;
    private int r;
}

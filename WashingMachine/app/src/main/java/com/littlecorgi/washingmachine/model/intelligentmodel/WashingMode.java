package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * 洗涤模式选择
 * 有三种模式供选择：
 * 1. 柔和
 * 2. 标准
 * 3. 强力
 *
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum WashingMode {
    /**
     * 柔和模式
     */
    SOFT("柔和", 1),

    /**
     * 标准模式
     */
    STANDARD("标准", 2),

    /**
     * 强力模式
     */
    POEWRFUL("强力", 3);

    private String name;
    private int index;

    WashingMode(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

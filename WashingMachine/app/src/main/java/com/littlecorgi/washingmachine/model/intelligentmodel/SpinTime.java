package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * 脱水时间
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum SpinTime {
    /**
     * 脱水时间
     */
    THREE(3, 1),
    FIVE(5, 2),
    TEN(10, 3),
    FIFTEEN(15, 4),
    TWENTY(20, 5),
    TWENTY_FIVE(25, 6),
    THIRTY(30, 7);

    private int time;
    private int index;

    SpinTime(int time, int index) {
        this.time = time;
        this.index = index;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum WashingTime {
    /**
     * 洗涤时间
     */
    TEN(10, 1),
    FIFTEEN(15, 2),
    TWENTY(20, 3),
    TWENTY_FIVE(25, 4),
    THIRTY(30, 5),
    THIRTY_FIVE(35, 6),
    FORTY(40, 7),
    FORTY_FIVE(45, 8),
    FIFTY(50, 9),
    FIFTY_FIVE(55, 10),
    SIXTY(60, 11);

    private int time;
    private int index;

    WashingTime(int time, int index) {
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

package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * 漂洗次数
 *
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum RinsingTimes {
    /**
     * 洗涤一次
     */
    ONE(1, 1),
    /**
     * 洗涤两次
     */
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5);


    private int times;
    private int index;

    RinsingTimes(int times, int index) {
        this.times = times;
        this.index = index;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

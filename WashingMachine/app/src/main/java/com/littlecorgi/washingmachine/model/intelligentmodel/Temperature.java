package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum Temperature {
    /**
     * 水温控制
     */
    COLD(0, 1),
    TWENTY(20, 2),
    FORTY(40, 3),
    SIXTY(60, 4),
    NINETY_FIVE(95, 5);

    /**
     * 温度
     */
    private int temperature;
    private int index;

    Temperature(int temperature, int index) {
        this.temperature = temperature;
        this.index = index;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

package com.littlecorgi.washingmachine.model.standardmodel;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum Water {
    /**
     * 水位设置
     */
    LOW("低", 1),
    MEDIUM_LOW("中低", 2),
    MEDIUM("中", 3),
    MEDIUM_HIGH("中高", 4),
    HIGH("高", 5);

    private String water;
    private int index;

    Water(String water, int index) {
        this.water = water;
        this.index = index;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

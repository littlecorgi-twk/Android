package com.littlecorgi.washingmachine.model.intelligentmodel;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum RPM {
    /**
     * 转速设置
     */
    RPM_400(400, 1),
    RPM_600(600, 2),
    RPM_800(800, 3),
    RPM_1000(1000, 4),
    RPM_1200(1200, 5),
    RPM_1400(1400, 6);

    private int rpm;
    private int index;

    RPM(int rpm, int index) {
        this.rpm = rpm;
        this.index = index;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

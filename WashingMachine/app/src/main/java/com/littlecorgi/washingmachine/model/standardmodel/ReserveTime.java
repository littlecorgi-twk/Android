package com.littlecorgi.washingmachine.model.standardmodel;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum ReserveTime {
    /**
     * 预约时间，以分钟为单位
     */
    ZERO(0, 1),
    HALF_AN_HOUR(30, 2),
    HOUR_1(60, 3),
    HOUR_2(120, 4),
    HOUR_3(180, 5),
    HOUR_4(240, 6),
    HOUR_5(300, 7),
    HOUR_6(360, 8),
    HOUR_7(420, 9),
    HOUR_8(480, 10),
    HOUR_9(540, 11),
    HOUR_10(600, 12),
    HOUR_11(660, 13),
    HOUR_12(720, 14),
    HOUR_13(780, 15),
    HOUR_14(840, 16),
    HOUR_15(900, 17),
    HOUR_16(960, 18),
    HOUR_17(1020, 19),
    HOUR_18(1080, 20),
    HOUR_19(1140, 21),
    HOUR_20(1200, 22),
    HOUR_21(1260, 23),
    HOUR_22(1320, 24),
    HOUR_23(1380, 25);

    private int reserveTime;
    private int index;

    ReserveTime(int reserveTime, int index) {
        this.reserveTime = reserveTime;
        this.index = index;
    }

    public int getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(int reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

package com.littlecorgi.washingmachine.model.models;

import com.littlecorgi.washingmachine.model.intelligentmodel.RPM;
import com.littlecorgi.washingmachine.model.intelligentmodel.RinsingTimes;
import com.littlecorgi.washingmachine.model.intelligentmodel.SpinTime;
import com.littlecorgi.washingmachine.model.intelligentmodel.Temperature;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingMode;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingTime;

/**
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public enum BaseModelEnum {
    /**
     * 自定义
     */
    Customize("自定义", "自定义洗衣设置", WashingMode.STANDARD, RinsingTimes.ONE, WashingTime.TEN, SpinTime.THREE, Temperature.COLD, RPM.RPM_400),
    /**
     * 棉麻
     */
    Cotton("棉麻", "洗涤日常耐洗衣物", WashingMode.STANDARD, RinsingTimes.TWO, WashingTime.THIRTY, SpinTime.TEN, Temperature.TWENTY, RPM.RPM_800),

    /**
     * 混合洗
     */
    Mix("混合洗", "日常耐洗且不会褪色的衣物", WashingMode.STANDARD, RinsingTimes.TWO, WashingTime.THIRTY, SpinTime.TEN, Temperature.TWENTY, RPM.RPM_1000),

    /**
     * 羊毛
     */
    Woo("羊毛", "洗涤标记有“可机器洗涤”的羊毛织物", WashingMode.SOFT, RinsingTimes.TWO, WashingTime.TWENTY, SpinTime.THREE, Temperature.TWENTY, RPM.RPM_400),

    /**
     * 羽绒服
     */
    DownJacket("羽绒服", "洗涤羽绒服", WashingMode.STANDARD, RinsingTimes.TWO, WashingTime.FORTY, SpinTime.FIFTEEN, Temperature.TWENTY, RPM.RPM_1200),

    /**
     * 大件
     */
    Big("大件", "洗涤厚重的衣服，如外套、毛巾等等", WashingMode.POEWRFUL, RinsingTimes.TWO, WashingTime.THIRTY, SpinTime.TWENTY, Temperature.FORTY, RPM.RPM_1200),

    /**
     * 节能
     */
    ECO("节能", "在节能的状态下进行洗涤", WashingMode.SOFT, RinsingTimes.TWO, WashingTime.THIRTY, SpinTime.FIVE, Temperature.COLD, RPM.RPM_800),

    /**
     * 单脱水
     */
    SpinOnly("单脱水", "只进行脱水", null, null, null, SpinTime.TEN, Temperature.COLD, RPM.RPM_1200),

    /**
     * 童装
     */
    Kid("童装", "洗涤儿童类衣服，这种模式下衣物更洁净", WashingMode.STANDARD, RinsingTimes.TWO, WashingTime.THIRTY, SpinTime.FIFTEEN, Temperature.FORTY, RPM.RPM_1200),

    /**
     * 桶自洁
     */
    SelfClean("桶自洁", "采用95℃高温清洗内、外桶", WashingMode.POEWRFUL, RinsingTimes.TWO, WashingTime.TEN, SpinTime.FIVE, Temperature.NINETY_FIVE, RPM.RPM_400),

    /**
     * 内衣
     */
    UnderWear("内衣", "增加漂洗次数，充分洗掉衣物上的残余洗涤剂", WashingMode.STANDARD, RinsingTimes.FOUR, WashingTime.TWENTY, SpinTime.FIFTEEN, Temperature.TWENTY, RPM.RPM_600),

    /**
     * 快洗15'
     */
    Fast15Min("快洗15'", "适合洗少量不太脏的衣物", WashingMode.POEWRFUL, RinsingTimes.ONE, WashingTime.TEN, SpinTime.FIVE, Temperature.COLD, RPM.RPM_1400),

    /**
     * 快洗30'
     */
    Fast30Min("快洗30'", "适合洗少量不太脏的衣物", WashingMode.POEWRFUL, RinsingTimes.TWO, WashingTime.TEN, SpinTime.TEN, Temperature.COLD, RPM.RPM_1400);


    private String name;
    private String des;
    private WashingMode washingMode;
    private RinsingTimes rinsingTimes;
    private WashingTime washingTime;
    private SpinTime spinTime;
    private Temperature temperature;
    private RPM rpm;

    BaseModelEnum(String name, String des, WashingMode washingMode, RinsingTimes rinsingTimes, WashingTime washingTime, SpinTime spinTime, Temperature temperature, RPM rpm) {
        this.name = name;
        this.des = des;
        this.washingMode = washingMode;
        this.rinsingTimes = rinsingTimes;
        this.washingTime = washingTime;
        this.spinTime = spinTime;
        this.temperature = temperature;
        this.rpm = rpm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public WashingMode getWashingMode() {
        return washingMode;
    }

    public void setWashingMode(WashingMode washingMode) {
        this.washingMode = washingMode;
    }

    public RinsingTimes getRinsingTimes() {
        return rinsingTimes;
    }

    public void setRinsingTimes(RinsingTimes rinsingTimes) {
        this.rinsingTimes = rinsingTimes;
    }

    public WashingTime getWashingTime() {
        return washingTime;
    }

    public void setWashingTime(WashingTime washingTime) {
        this.washingTime = washingTime;
    }

    public SpinTime getSpinTime() {
        return spinTime;
    }

    public void setSpinTime(SpinTime spinTime) {
        this.spinTime = spinTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public RPM getRpm() {
        return rpm;
    }

    public void setRpm(RPM rpm) {
        this.rpm = rpm;
    }
}

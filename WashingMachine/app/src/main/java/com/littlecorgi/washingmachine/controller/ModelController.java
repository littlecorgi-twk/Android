package com.littlecorgi.washingmachine.controller;

import com.littlecorgi.washingmachine.model.models.BaseModelEnum;
import com.littlecorgi.washingmachine.model.intelligentmodel.RPM;
import com.littlecorgi.washingmachine.model.intelligentmodel.RinsingTimes;
import com.littlecorgi.washingmachine.model.intelligentmodel.SpinTime;
import com.littlecorgi.washingmachine.model.intelligentmodel.Temperature;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingMode;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingTime;
import com.littlecorgi.washingmachine.model.standardmodel.ReserveTime;
import com.littlecorgi.washingmachine.model.standardmodel.Water;

/**
 * 洗衣机的模式控制
 *
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public interface ModelController {

    /**
     * 设置洗衣模式
     *
     * @param baseModelEnum 洗衣的模式
     */
    public void setModel(BaseModelEnum baseModelEnum);

    /**
     * 设置水位
     *
     * @param water 水位
     */
    public void setWater(Water water);

    /**
     * 设置预约时间
     *
     * @param reserveTime 预约时间
     */
    public void setReserveTime(ReserveTime reserveTime);

    /**
     * 设置洗涤模式
     *
     * @param washingMode 洗涤模式
     */
    public void setWashingMode(WashingMode washingMode);

    /**
     * 设置漂洗次数
     *
     * @param rinsingTimes 漂洗次数
     */
    public void setRinsingTimes(RinsingTimes rinsingTimes);

    /**
     * 设置洗涤时间
     *
     * @param washingTime 洗涤时间
     */
    public void setWashingTime(WashingTime washingTime);

    /**
     * 设置脱水时间
     *
     * @param spinTime 脱水时间
     */
    public void setSpinTime(SpinTime spinTime);

    /**
     * 设置水温
     *
     * @param temperature 水温
     */
    public void setTemperature(Temperature temperature);

    /**
     * 设置洗衣转速
     *
     * @param rpm 转速
     */
    public void setRpm(RPM rpm);

}

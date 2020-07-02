package com.littlecorgi.washingmachine;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.littlecorgi.washingmachine.controller.DoorController;
import com.littlecorgi.washingmachine.controller.ModelController;
import com.littlecorgi.washingmachine.controller.WashingController;
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
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public class WashingMachine implements DoorController, ModelController, WashingController {

    private static final String TAG = "WashingMachine";

    private Context context;

    private TimerTickListener timerTickListener;

    /**
     * 门是否关上
     */
    private boolean doorClosed;
    /**
     * 门是否锁上
     */
    private boolean doorLocked;
    /**
     * 洗衣机是否正在运转
     */
    private boolean isRunning;
    /**
     * 倒计时的当前时间
     */
    private Long currentTime = 0L;
    /**
     * 选择的模式
     */
    private BaseModelEnum model;

    /**
     * 洗衣机的设置参数
     */
    private WashingMode washingMode;
    private RinsingTimes rinsingTimes;
    private WashingTime washingTime;
    private SpinTime spinTime;
    private Temperature temperature;
    private RPM rpm;
    private ReserveTime reserveTime;
    private Water water;

    public WashingMachine(BaseModelEnum baseModelEnum, Context context, TimerTickListener listener) {
        this.doorClosed = false;
        this.doorLocked = false;
        this.isRunning = false;
        this.model = baseModelEnum;
        this.washingMode = baseModelEnum.getWashingMode();
        this.rinsingTimes = baseModelEnum.getRinsingTimes();
        this.washingTime = baseModelEnum.getWashingTime();
        this.spinTime = baseModelEnum.getSpinTime();
        this.temperature = baseModelEnum.getTemperature();
        this.rpm = baseModelEnum.getRpm();
        this.reserveTime = ReserveTime.ZERO;
        this.water = Water.MEDIUM;
        this.context = context;
        this.timerTickListener = listener;
    }

    /**
     * 通过Timer倒计时模拟洗衣机运行
     */
    private CountDownTimer timer;

    @Override
    public boolean openDoor() {
        if (!isDoorLocked() && !isRunning && isDoorClosed()) {
            doorClosed = false;
            return true;
        } else if (isRunning) {
            Toast.makeText(context, "开门时发生错误：正在运行中，不许开门", Toast.LENGTH_SHORT).show();
        } else if (!isDoorClosed()) {
            Toast.makeText(context, "开门时发生错误：门开着，不需要再开门", Toast.LENGTH_SHORT).show();
        } else if (isDoorLocked()) {
            Toast.makeText(context, "开门时发生错误：门锁着，请先解锁", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean closeDoor() {
        if (!isDoorClosed()) {
            doorClosed = true;
            doorLocked = false;
            return true;
        } else if (isDoorClosed()) {
            Toast.makeText(context, "关门时发生错误：门关着，不需要再关了", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean lockDoor() {
        if (isDoorClosed() && !isDoorLocked()) {
            doorLocked = true;
            return true;
        } else if (!isDoorClosed()) {
            Toast.makeText(context, "锁门时发生错误：门没关上", Toast.LENGTH_SHORT).show();
        } else if (isDoorLocked()) {
            Toast.makeText(context, "锁门时发生错误：门锁着，不需要再锁了", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean unLockDoor() {
        if (isDoorLocked()) {
            doorLocked = false;
            return true;
        } else if (!isDoorLocked() && isDoorClosed()) {
            Toast.makeText(context, "解锁门时发生错误：门没有锁", Toast.LENGTH_SHORT).show();
        } else if (!isDoorClosed()) {
            Toast.makeText(context, "解锁门时发生错误：门开着", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean isDoorClosed() {
        return doorClosed;
    }

    @Override
    public boolean isDoorLocked() {
        return doorLocked;
    }

    @Override
    public boolean start() {
        if (isDoorClosed() && isDoorLocked()) {
            timer = new CountDownTimer((currentTime == 0) ? getAllTime() * 1000 : currentTime - 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d(TAG, "seconds remaining: " + millisUntilFinished / 1000);
                    timerTickListener.onTick(millisUntilFinished);
                    currentTime = millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    Log.d(TAG, "down");
                    timerTickListener.onFinish();
                    timer = null;
                    currentTime = 0L;
                    isRunning = false;
                }
            }.start();
            isRunning = true;
            return true;
        } else {
            if (isDoorClosed()) {
                Toast.makeText(context, "门没关上", Toast.LENGTH_SHORT).show();
            }
            if (isDoorLocked()) {
                Toast.makeText(context, "门没上锁", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    @Override
    public boolean pause() {
        if (isRunning) {
            timer.cancel();
            isRunning = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isPaused() {
        return !isRunning;
    }

    @Override
    public boolean isFinished() {
        return currentTime == 0;
    }

    @Override
    public int getAllTime() {
        // 返回的是秒数
        return spinTime.getTime() + washingTime.getTime() * rinsingTimes.getTimes();
    }

    @Override
    public void forceStop() {
        if (isRunning) {
            timerTickListener.onFinish();
            timer.cancel();
            timer = null;
            currentTime = 0L;
            isRunning = false;
        }
    }

    @Override
    public void setModel(BaseModelEnum baseModelEnum) {
        this.model = baseModelEnum;
        this.washingMode = baseModelEnum.getWashingMode();
        this.rinsingTimes = baseModelEnum.getRinsingTimes();
        this.washingTime = baseModelEnum.getWashingTime();
        this.spinTime = baseModelEnum.getSpinTime();
        this.temperature = baseModelEnum.getTemperature();
        this.rpm = baseModelEnum.getRpm();
    }

    @Override
    public void setWater(Water water) {
        this.water = water;
    }

    @Override
    public void setReserveTime(ReserveTime reserveTime) {
        this.reserveTime = reserveTime;
    }

    @Override
    public void setWashingMode(WashingMode washingMode) {
        this.washingMode = washingMode;
    }

    @Override
    public void setRinsingTimes(RinsingTimes rinsingTimes) {
        this.rinsingTimes = rinsingTimes;
    }

    @Override
    public void setWashingTime(WashingTime washingTime) {
        this.washingTime = washingTime;
    }

    @Override
    public void setSpinTime(SpinTime spinTime) {
        this.spinTime = spinTime;
    }

    @Override
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void setRpm(RPM rpm) {
        this.rpm = rpm;
    }
}

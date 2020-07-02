package com.littlecorgi.washingmachine;

interface TimerTickListener {
    /**
     * Timer tick一次
     * @param millisUntilFinished 当前时间
     */
    void onTick(long millisUntilFinished);

    /**
     * Timer 结束通知
     */
    void onFinish();
}

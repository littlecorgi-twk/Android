package com.littlecorgi.washingmachine.controller;

/**
 * 洗衣机的洗涤控制
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public interface WashingController {
    /**
     * 启动洗衣机
     * 如果洗衣机设定完成，并且门以关闭，则可以启动
     * @return 成功启动返回true，无法启动返回false
     */
    public boolean start();

    /**
     * 暂停洗衣机
     * 当洗衣机在运行过程中，用户暂停，则暂停。非运行过程中按下暂停无需反应。
     * @return 暂停成功返回true，失败返回false
     */
    public boolean pause();

    /**
     * 洗衣是否被暂停
     * @return 暂停返回true
     */
    public boolean isPaused();

    /**
     * 洗衣是否完成
     * @return 完成返回true
     */
    public boolean isFinished();

    /**
     * 计算此次程序所需的总时间
     * @return 总时间
     */
    public int getAllTime();

    /**
     * 强制停止
     */
    public void forceStop();
}

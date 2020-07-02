package com.littlecorgi.washingmachine.controller;

/**
 * 关于洗衣机门的一系列控制操作
 *
 * @author littlecorgi
 * @date 2020-04-03 23:41
 */
public interface DoorController {
    /**
     * 开门
     * 如果门当前是关着的，没有上锁(也就是没有工作)，则可以开门，否则不可开门
     *
     * @return 开门成功返回true，开门失败返回false
     */
    public boolean openDoor();

    /**
     * 关门
     * 门开着才能关，
     *
     * @return 关门成功返回true，否则false
     */
    public boolean closeDoor();

    /**
     * 锁门
     * 门关着才能锁门
     *
     * @return 锁门成功返回true，否则false
     */
    public boolean lockDoor();

    /**
     * 解锁
     *
     * @return 解锁成功返回true，否则false
     */
    public boolean unLockDoor();

    /**
     * 判断门是否关着
     *
     * @return 门关着返回true，否则false
     */
    public boolean isDoorClosed();

    /**
     * 判断门是否上锁
     *
     * @return 门上锁返回true，否则false
     */
    public boolean isDoorLocked();

}

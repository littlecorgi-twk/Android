package com.example.a1203.uibestpractice;

/**
 * @author littlecorgi
 * @Date 2018-08-14 08:41
 * @email a1203991686@126.com
 */
public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    /**
     * 返回消息内容
     * @return 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 返回消息类型
     * @return 消息类型
     */
    public int getType() {
        return type;
    }
}

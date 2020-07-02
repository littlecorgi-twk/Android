package com.littlecorgi.washingmachine.view;

import top.defaults.view.PickerView;

/**
 * @author littlecorgi
 */
public class Item implements PickerView.PickerItem {

    private String text;

    public Item(String s) {
        text = s;
    }

    @Override
    public String getText() {
        return text;
    }
}
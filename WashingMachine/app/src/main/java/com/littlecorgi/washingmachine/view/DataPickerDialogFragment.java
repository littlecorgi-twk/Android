package com.littlecorgi.washingmachine.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.littlecorgi.washingmachine.R;

import java.util.Objects;

import top.defaults.view.PickerView;

/**
 * @author littlecorgi
 */
public class DataPickerDialogFragment extends DialogFragment {

    private PickerView mPickerView;
    private PickerView.Adapter mAdapter;
    private int selectedIndex;
    private OnSelectedListener mOnSelectedListener;

    public DataPickerDialogFragment(PickerView.Adapter mAdapter, int selectedIndex) {
        this.mAdapter = mAdapter;
        this.selectedIndex = selectedIndex;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_data_picker, null, false);
        builder.setView(view);
        mPickerView = view.findViewById(R.id.picker_view);
        mPickerView.setAdapter(mAdapter);
        mPickerView.setSelectedItemPosition(selectedIndex);
        mPickerView.setOnSelectedItemChangedListener(new PickerView.OnSelectedItemChangedListener() {
            @Override
            public void onSelectedItemChanged(PickerView pickerView, int previousPosition, int selectedItemPosition) {
                mOnSelectedListener.onSelected(selectedItemPosition);
            }
        });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.0f;
            window.setAttributes(windowParams);
        }
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        mOnSelectedListener = listener;
    }

    public interface OnSelectedListener {
        public void onSelected(int selectedItemPosition);
    }
}

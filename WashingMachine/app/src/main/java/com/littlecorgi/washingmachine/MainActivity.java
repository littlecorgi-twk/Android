package com.littlecorgi.washingmachine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.littlecorgi.washingmachine.model.models.BaseModelEnum;
import com.littlecorgi.washingmachine.model.intelligentmodel.RPM;
import com.littlecorgi.washingmachine.model.intelligentmodel.RinsingTimes;
import com.littlecorgi.washingmachine.model.intelligentmodel.SpinTime;
import com.littlecorgi.washingmachine.model.intelligentmodel.Temperature;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingMode;
import com.littlecorgi.washingmachine.model.intelligentmodel.WashingTime;
import com.littlecorgi.washingmachine.model.standardmodel.ReserveTime;
import com.littlecorgi.washingmachine.model.standardmodel.Water;
import com.littlecorgi.washingmachine.view.DataPickerDialogFragment;
import com.littlecorgi.washingmachine.view.Item;

import top.defaults.view.PickerView;

/**
 * @author littlecorgi
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WashingMode[] mWashingModes = new WashingMode[]{
            WashingMode.SOFT, WashingMode.STANDARD, WashingMode.POEWRFUL
    };
    private RinsingTimes[] mRinsingTimes = new RinsingTimes[]{
            RinsingTimes.ONE, RinsingTimes.TWO, RinsingTimes.THREE, RinsingTimes.FOUR, RinsingTimes.FIVE
    };
    private WashingTime[] mWashingTimes = new WashingTime[]{
            WashingTime.TEN, WashingTime.FIFTEEN, WashingTime.TWENTY, WashingTime.TWENTY_FIVE,
            WashingTime.THIRTY, WashingTime.THIRTY_FIVE, WashingTime.FORTY, WashingTime.FORTY_FIVE,
            WashingTime.FIFTY, WashingTime.FIFTY_FIVE, WashingTime.SIXTY
    };
    private SpinTime[] mSpinTimes = new SpinTime[]{
            SpinTime.THREE, SpinTime.FIVE, SpinTime.TEN, SpinTime.FIFTEEN, SpinTime.TWENTY, SpinTime.TWENTY_FIVE, SpinTime.THIRTY
    };
    private Temperature[] mTemperatures = new Temperature[]{
            Temperature.COLD, Temperature.TWENTY, Temperature.FORTY, Temperature.SIXTY, Temperature.NINETY_FIVE
    };
    private RPM[] mRpm = new RPM[]{
            RPM.RPM_400, RPM.RPM_600, RPM.RPM_800, RPM.RPM_1000, RPM.RPM_1200, RPM.RPM_1400
    };
    private ReserveTime[] mReserveTimes = new ReserveTime[]{
            ReserveTime.ZERO, ReserveTime.HALF_AN_HOUR, ReserveTime.HOUR_1, ReserveTime.HOUR_2, ReserveTime.HOUR_3,
            ReserveTime.HOUR_4, ReserveTime.HOUR_5, ReserveTime.HOUR_6, ReserveTime.HOUR_7, ReserveTime.HOUR_8,
            ReserveTime.HOUR_9, ReserveTime.HOUR_10, ReserveTime.HOUR_11, ReserveTime.HOUR_12, ReserveTime.HOUR_13,
            ReserveTime.HOUR_14, ReserveTime.HOUR_15, ReserveTime.HOUR_16, ReserveTime.HOUR_17, ReserveTime.HOUR_16,
            ReserveTime.HOUR_17, ReserveTime.HOUR_18, ReserveTime.HOUR_19, ReserveTime.HOUR_20, ReserveTime.HOUR_21,
            ReserveTime.HOUR_22, ReserveTime.HOUR_23
    };
    private Water[] mWaters = new Water[]{
            Water.LOW, Water.MEDIUM_LOW, Water.MEDIUM, Water.MEDIUM_HIGH, Water.HIGH
    };
    private BaseModelEnum[] mBaseModels = new BaseModelEnum[]{
            BaseModelEnum.Customize, BaseModelEnum.Cotton, BaseModelEnum.Mix, BaseModelEnum.Woo,
            BaseModelEnum.DownJacket, BaseModelEnum.Big, BaseModelEnum.ECO, BaseModelEnum.SpinOnly,
            BaseModelEnum.Kid, BaseModelEnum.SelfClean, BaseModelEnum.UnderWear,
            BaseModelEnum.Fast15Min, BaseModelEnum.Fast30Min
    };

    private TimerTickListener timerTickListener = new TimerTickListener() {
        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished / 1000);
            mProgressBar.setProgress(progress);
            String text = "剩余：" + progress + "s";
            mTextView.setText(text);
        }

        @Override
        public void onFinish() {
            mSwRunning.setChecked(false);
            mSwRunning.setClickable(false);
        }
    };

    private WashingMachine mWashingMachine = new WashingMachine(BaseModelEnum.Mix, this, timerTickListener);
    private ProgressBar mProgressBar;
    private Switch mSwCloseDoor;
    private Switch mSwLockDoor;
    private Switch mSwRunning;
    private TextView mTvWashingMode;
    private TextView mTvRinsingTimes;
    private TextView mTvWashingTime;
    private TextView mTvSpinTime;
    private TextView mTvTemperature;
    private TextView mTvRpm;
    private TextView mTvReserveTime;
    private TextView mTvWater;
    private TextView mTvBaseModel;
    private TextView mTextView;
    private int mWashingModeIndex = 1;
    private int mRinsingTimesIndex = 1;
    private int mWashingTimeIndex = 4;
    private int mSpinTimeIndex = 2;
    private int mTemperatureIndex = 1;
    private int mRpmIndex = 3;
    private int mReserveTimeIndex = 0;
    private int mWaterIndex = 2;
    private int mBaseModelIndex = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        initSwitch();
    }

    private void initView() {
        Log.d(TAG, "initView: 初始化view");
        mTvWashingMode = findViewById(R.id.textView_washing_mode);
        mTvRinsingTimes = findViewById(R.id.textView_rinsing_times);
        mTvWashingTime = findViewById(R.id.textView_washing_time);
        mTvSpinTime = findViewById(R.id.textView_spin_time);
        mTvTemperature = findViewById(R.id.textView_temperature);
        mTvRpm = findViewById(R.id.textView_rpm);
        mTvReserveTime = findViewById(R.id.textView_reserve);
        mTvWater = findViewById(R.id.textView_water);
        mTvBaseModel = findViewById(R.id.textView_base_model);
        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
        mSwCloseDoor = findViewById(R.id.switch_door_close);
        mSwLockDoor = findViewById(R.id.switch_door_lock);
        mSwRunning = findViewById(R.id.switch_running);
    }

    private void initClick() {
        findViewById(R.id.button_washing_mode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 洗地模式 1");
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mWashingModes.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mWashingModes[index].getName());
                    }
                }, mWashingModeIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvWashingMode.setText(mWashingModes[selectedItemPosition].getName());
                        mWashingModeIndex = selectedItemPosition;
                        mWashingMachine.setWashingMode(mWashingModes[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "洗涤模式");
            }
        });
        findViewById(R.id.button_RinsingTimes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mRinsingTimes.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mRinsingTimes[index].getTimes() + "");
                    }
                }, mRinsingTimesIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvRinsingTimes.setText(mRinsingTimes[selectedItemPosition].getTimes() + "");
                        mRinsingTimesIndex = selectedItemPosition;
                        mWashingMachine.setRinsingTimes(mRinsingTimes[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "漂洗次数");
            }
        });
        findViewById(R.id.button_washing_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mWashingTimes.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mWashingTimes[index].getTime() + "");
                    }
                }, mWashingTimeIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvWashingTime.setText(mWashingTimes[selectedItemPosition].getTime() + "min");
                        mWashingTimeIndex = selectedItemPosition;
                        mWashingMachine.setWashingTime(mWashingTimes[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "洗涤时间");
            }
        });
        findViewById(R.id.button_spin_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mSpinTimes.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mSpinTimes[index].getTime() + "");
                    }
                }, mSpinTimeIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvSpinTime.setText(mSpinTimes[selectedItemPosition].getTime() + "min");
                        mSpinTimeIndex = selectedItemPosition;
                        mWashingMachine.setSpinTime(mSpinTimes[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "脱水时间");
            }
        });
        findViewById(R.id.button_temperature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mTemperatures.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mTemperatures[index].getTemperature() + "");
                    }
                }, mTemperatureIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvTemperature.setText(
                                (mTemperatures[selectedItemPosition].getTemperature() == 0)
                                        ? "冷水"
                                        : mTemperatures[selectedItemPosition].getTemperature() + "℃");
                        mTemperatureIndex = selectedItemPosition;
                        mWashingMachine.setTemperature(mTemperatures[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "水温");
            }
        });
        findViewById(R.id.button_rpm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mRpm.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mRpm[index].getRpm() + "");
                    }
                }, mRpmIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvRpm.setText(mRpm[selectedItemPosition].getRpm() + "rpm");
                        mRpmIndex = selectedItemPosition;
                        mWashingMachine.setRpm(mRpm[selectedItemPosition]);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "转速");
            }
        });
        findViewById(R.id.button_water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mWaters.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mWaters[index].getWater() + "");
                    }
                }, mWaterIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvWater.setText(mWaters[selectedItemPosition].getWater());
                        mWaterIndex = selectedItemPosition;
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "转速");
            }
        });
        findViewById(R.id.button_reserve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mReserveTimes.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        int temp = mReserveTimes[index].getReserveTime();
                        String result;
                        if (temp == 0) {
                            result = "0H";
                        } else if (temp == 30) {
                            result = "0.5H";
                        } else {
                            result = temp / 60 + "H";
                        }
                        return new Item(result);
                    }
                }, mReserveTimeIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        int temp = mReserveTimes[selectedItemPosition].getReserveTime();
                        if (temp == 0) {
                            mTvReserveTime.setText("0H");
                        } else if (temp == 30) {
                            mTvReserveTime.setText("0.5H");
                        } else {
                            mTvReserveTime.setText(temp / 60 + "H");
                        }
                        mReserveTimeIndex = selectedItemPosition;
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "转速");
            }
        });
        findViewById(R.id.button_base_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment(new PickerView.Adapter() {
                    @Override
                    public int getItemCount() {
                        return mBaseModels.length;
                    }

                    @Override
                    public PickerView.PickerItem getItem(int index) {
                        return new Item(mBaseModels[index].getName());
                    }
                }, mBaseModelIndex);
                dialogFragment.setOnSelectedListener(new DataPickerDialogFragment.OnSelectedListener() {
                    @Override
                    public void onSelected(int selectedItemPosition) {
                        mTvBaseModel.setText(mBaseModels[selectedItemPosition].getName());
                        mBaseModelIndex = selectedItemPosition;

                        BaseModelEnum tempBaseModel = mBaseModels[selectedItemPosition];
                        setModel(tempBaseModel);
                        mWashingMachine.setModel(tempBaseModel);
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "模式");
            }
        });
        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwRunning.setChecked(true);
                mSwRunning.setClickable(true);
            }
        });
        findViewById(R.id.button_force_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSwRunning.isChecked()) {
                    mWashingMachine.forceStop();
                    mSwRunning.setChecked(false);
                    mSwRunning.setClickable(false);
                    mProgressBar.setProgress(0);
                    mTextView.setText("洗衣机停止中");
                }
            }
        });
    }

    private void setModel(BaseModelEnum model) {
        if (model.getWashingMode() != null) {
            mTvWashingMode.setText(model.getWashingMode().getName());
            for (int i = 0; i < mWashingModes.length; i++) {
                if (mWashingModes[i] == model.getWashingMode()) {
                    mWashingModeIndex = i;
                }
            }
        } else {
            mTvWashingMode.setText("null");
            mWashingModeIndex = 0;
        }
        if (model.getRinsingTimes() != null) {
            mTvRinsingTimes.setText(model.getRinsingTimes().getTimes() + "");
            for (int i = 0; i < mRinsingTimes.length; i++) {
                if (mRinsingTimes[i] == model.getRinsingTimes()) {
                    mRinsingTimesIndex = i;
                }
            }
        } else {
            mTvRinsingTimes.setText("null");
            mRinsingTimesIndex = 0;
        }
        if (model.getWashingTime() != null) {
            mTvWashingTime.setText(model.getWashingTime().getTime() + "min");
            for (int i = 0; i < mWashingTimes.length; i++) {
                if (mWashingTimes[i] == model.getWashingTime()) {
                    mWashingTimeIndex = i;
                }
            }
        } else {
            mTvWashingTime.setText("null");
            mWashingTimeIndex = 0;
        }
        if (model.getSpinTime() != null) {
            mTvSpinTime.setText(model.getSpinTime().getTime() + "min");
            for (int i = 0; i < mSpinTimes.length; i++) {
                if (mSpinTimes[i] == model.getSpinTime()) {
                    mSpinTimeIndex = i;
                }
            }
        } else {
            mTvSpinTime.setText("null");
            mSpinTimeIndex = 0;
        }
        if (model.getTemperature() != null) {
            mTvTemperature.setText((model.getTemperature().getTemperature() == 0)
                    ? "冷水"
                    : model.getTemperature().getTemperature() + "℃");
            for (int i = 0; i < mTemperatures.length; i++) {
                if (mTemperatures[i] == model.getTemperature()) {
                    mTemperatureIndex = i;
                }
            }
        } else {
            mTvTemperature.setText("null");
            mTemperatureIndex = 0;
        }
        if (model.getRpm() != null) {
            mTvRpm.setText(model.getRpm().getRpm() + "rpm");
            for (int i = 0; i < mRpm.length; i++) {
                if (mRpm[i] == model.getRpm()) {
                    mRpmIndex = i;
                }
            }
        } else {
            mTvRpm.setText("null");
            mRpmIndex = 0;
        }
    }

    private void initProgressBar() {
        mProgressBar.setMax(mWashingMachine.getAllTime());
    }

    private void initSwitch() {
        mSwCloseDoor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWashingMachine.closeDoor();
                } else {
                    mWashingMachine.openDoor();
                }
            }
        });
        mSwLockDoor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSwCloseDoor.setChecked(true);
                    mSwCloseDoor.setClickable(false);
                    mWashingMachine.lockDoor();
                } else {
                    mSwCloseDoor.setClickable(true);
                    mWashingMachine.unLockDoor();
                }
            }
        });
        mSwRunning.setClickable(false);
        mSwRunning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSwCloseDoor.setChecked(true);
                    mSwCloseDoor.setClickable(false);
                    mSwLockDoor.setChecked(true);
                    mSwLockDoor.setClickable(false);
                    mWashingMachine.start();
                    initProgressBar();
                    Log.d(TAG, "onCheckedChanged: 启动了");
                } else {
                    mSwCloseDoor.setClickable(false);
                    mSwLockDoor.setClickable(true);
                    mWashingMachine.pause();
                    Log.d(TAG, "onCheckedChanged: 暂停了");
                }
            }
        });
    }
}
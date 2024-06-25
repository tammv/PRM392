package com.example.ex20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (level == -1 || scale == -1) {
            Log.e(TAG, "Battery level or scale not available");
            return;
        }

        float batteryPct = (level / (float) scale) * 100;
        Log.d(TAG, "Battery level: " + batteryPct + "%");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (batteryPct < 15) {
                    Toast.makeText(context, "Battery level is low: " + batteryPct + "%", Toast.LENGTH_LONG).show();
                } else if (batteryPct < 30) {
                    Toast.makeText(context, "Battery level is moderate: " + batteryPct + "%", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Battery level is good: " + batteryPct + "%", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

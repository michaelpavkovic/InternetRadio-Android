package com.mpavkovic.internetradio.utils;

import android.os.PowerManager;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Detects if screen of phone turns off
 */
public class SleepDetector
{
    PowerManager pm;

    //Constructor
    public SleepDetector()
    {

    }

    public boolean isAsleep()
    {
        boolean isAsleep = false;

        if (!Radio.radioUi.powerManager.isInteractive()) isAsleep = false;

        return isAsleep;
    }

}

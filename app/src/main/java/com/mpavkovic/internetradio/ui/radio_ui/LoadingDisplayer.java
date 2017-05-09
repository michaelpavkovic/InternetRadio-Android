package com.mpavkovic.internetradio.ui.radio_ui;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.utils.Constants;

/**
 * Display loading as metadata
 */
public class LoadingDisplayer implements Runnable
{
    public void run()
    {
        Radio.radioUi.lblMetadata.setText(Constants.loading);
    }
}

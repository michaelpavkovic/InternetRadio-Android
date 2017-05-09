package com.mpavkovic.internetradio.ui.radio_ui;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.utils.Constants;

/**
 * Runs on a separate thread, updates album art, artist, title, and genre
 */
public class MetadataUpdater implements Runnable
{
    public void run()
    {
        StringBuilder metadataBuilder = new StringBuilder();

        metadataBuilder.append(Radio.currentSong.getSongArtist());
        metadataBuilder.append(Constants.separator);
        metadataBuilder.append(Radio.currentSong.getSongName());
        metadataBuilder.append(Constants.separator);
        metadataBuilder.append(Radio.currentSong.getSongGenre());

        Radio.radioUi.lblMetadata.setText(metadataBuilder.toString());
    }
}

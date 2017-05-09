package com.mpavkovic.internetradio.activities.nowplaying.button_commands;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.song.SiriusXmPlaylistGetter;
import com.mpavkovic.internetradio.song.SongManager;
import com.mpavkovic.internetradio.ui.radio_ui.LoadingDisplayer;
import com.mpavkovic.internetradio.ui.radio_ui.MetadataUpdater;
import com.mpavkovic.internetradio.utils.Constants;

/**
 * Button Command for imageButtonPlay
 */
public class ImageButtonPlayCommand
{
    public void start()
    {
        if (Radio.radioUi.firstPlay)
        {
            Radio.radioUi.firstPlay = false;

            //Prepare playlist
            SiriusXmPlaylistGetter playlistGetter = new SiriusXmPlaylistGetter(Radio.selectedGenre);

            try
            {
                Radio.radioHandler.post(new LoadingDisplayer());
            }
            catch (Exception e)
            {
                System.out.println("[Internet Radio Warning] Song metadata could not be updated.");
            }

            Radio.radioUi.lblMetadata.setText(Constants.loading);
            Radio.songManager = new SongManager(playlistGetter.createXmPlaylist());
            Radio.currentSong = Radio.songManager.findNextSong(Radio.currentIndex);
            Radio.currentIndex++;

            try
            {
                Radio.radioHandler.post(new MetadataUpdater());
            }
            catch (Exception e)
            {
                System.out.println("[Internet Radio Warning] Song metadata could not be updated.");
            }

        }
        Radio.currentSong.statusChange = true;

        if (!Radio.currentSong.isPlaying())
        {
            Radio.currentSong.play();
            Radio.pauseClicked = false;
        }
        else
        {
            Radio.currentSong.pause();
            Radio.pauseClicked = true;
        }
    }
}

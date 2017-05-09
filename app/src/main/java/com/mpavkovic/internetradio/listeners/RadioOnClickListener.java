package com.mpavkovic.internetradio.listeners;

import android.view.View;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.activities.nowplaying.button_commands.ImageButtonHideCommand;
import com.mpavkovic.internetradio.activities.nowplaying.button_commands.ImageButtonPlayCommand;
import com.mpavkovic.internetradio.activities.nowplaying.button_commands.ImageButtonShowCommand;
import com.mpavkovic.internetradio.activities.nowplaying.button_commands.ImageButtonSkipNextCommand;
import com.mpavkovic.internetradio.activities.nowplaying.button_commands.ImageButtonSkipPreviousCommand;

/**
 * Button Click listener for MainMenu buttons
 */
public class RadioOnClickListener implements View.OnClickListener
{
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //Show or hide extra controls
            case R.id.imageButtonShow:
                System.out.println("[Button Listeners] imageButtonShow was clicked.");
                ImageButtonShowCommand showCommand = new ImageButtonShowCommand();
                showCommand.start();
                break;

            case R.id.imageButtonHide:
                System.out.println("[Button Listeners] imageButtonHide was clicked.");
                ImageButtonHideCommand hideCommand = new ImageButtonHideCommand();
                hideCommand.start();
                break;

            //Playback Controls
            case R.id.imageButtonPlay:
                System.out.println("[Button Listeners] imageButtonPlay was clicked.");

                ImageButtonPlayCommand playCommand = new ImageButtonPlayCommand();
                playCommand.start();
                break;

            case R.id.imageButtonSkipPrevious:
                System.out.println("[Button Listeners] imageButtonSkipPrevious was clicked.");

                ImageButtonSkipPreviousCommand previousCommand = new ImageButtonSkipPreviousCommand();
                previousCommand.start();
                break;

            case R.id.imageButtonSkipNext:
                System.out.println("[Button Listeners] imageButtonSkipNext was clicked.");

                ImageButtonSkipNextCommand nextCommand = new ImageButtonSkipNextCommand();
                nextCommand.start();
                break;
        }
    }
}

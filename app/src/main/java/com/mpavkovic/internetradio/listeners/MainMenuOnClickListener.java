package com.mpavkovic.internetradio.listeners;

import android.view.View;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.activities.mainmenu.button_commands.ImageButtonRadioCommand;

/**
 * Button Click listener for MainMenu buttons
 */
public class MainMenuOnClickListener implements View.OnClickListener
{

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButtonArtists:
                System.out.println("[Button Listeners] imageButtonArtists was clicked.");

                break;

            case R.id.imageButtonRadio:
                System.out.println("[Button Listeners] imageButtonRadio was clicked.");

                //Show the radio activity
                ImageButtonRadioCommand command = new ImageButtonRadioCommand();
                command.start();

                break;

            case R.id.imageButtonSongs:
                System.out.println("[Button Listeners] imageButtonSongs was clicked.");

                break;
        }
    }
}

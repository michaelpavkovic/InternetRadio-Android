package com.mpavkovic.internetradio.activities.mainmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.mpavkovic.internetradio.R;
import com.mpavkovic.internetradio.listeners.MainMenuOnClickListener;
import com.mpavkovic.internetradio.ui.main_menu_ui.MainMenuUI;

public class MainMenu extends AppCompatActivity
{
    MainMenuUI ui;
    MainMenuOnClickListener mainMenuListener;

    public static Context mmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mmContext = this.getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ui = new MainMenuUI();
        mainMenuListener = new MainMenuOnClickListener();

        ui.imageButtonArtists = (ImageButton) findViewById(R.id.imageButtonArtists);
        ui.imageButtonArtists.setOnClickListener(mainMenuListener);

        ui.imageButtonRadio = (ImageButton) findViewById(R.id.imageButtonRadio);
        ui.imageButtonRadio.setOnClickListener(mainMenuListener);

        ui.imageButtonSongs = (ImageButton) findViewById(R.id.imageButtonSongs);
        ui.imageButtonSongs.setOnClickListener(mainMenuListener);

        System.out.println("[UI] UI for MainMenu was successfully created.");
    }
}

package com.mpavkovic.internetradio.activities.mainmenu.button_commands;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.mpavkovic.internetradio.activities.mainmenu.MainMenu;
import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Button Command for imageButtonRadio
 */
public class ImageButtonRadioCommand extends AppCompatActivity
{
    public void start()
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(MainMenu.mmContext, Radio.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainMenu.mmContext.startActivity(intent);
            }
        });
    }
}

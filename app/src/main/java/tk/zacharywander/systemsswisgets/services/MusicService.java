package tk.zacharywander.systemsswisgets.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.AudioManager;
import android.os.SystemClock;
import android.view.KeyEvent;

import tk.zacharywander.systemsswisgets.misc.Util;
import tk.zacharywander.systemsswisgets.misc.Values;

public class MusicService extends IntentService
{
    private AudioManager audioManager;

    public MusicService()
    {
        super("MusicService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

            final String action = intent.getAction();

            if (action.equals(Values.MUSIC_INTENT_ACTION)) {
                KeyEvent downEvent;
                KeyEvent upEvent;
                long eventtime;

                switch (intent.getIntExtra(Values.MUSIC_INTENT_ACTION, -2)) {
                    case -2:
                        return;
                    case Values.MUSIC_BACK:
                        eventtime = SystemClock.uptimeMillis();

                        downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
                        audioManager.dispatchMediaKeyEvent(downEvent);

                        upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
                        audioManager.dispatchMediaKeyEvent(upEvent);

                        break;
                    case Values.MUSIC_PLAYPAUSE:
                        eventtime = SystemClock.uptimeMillis();

                        downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
                        audioManager.dispatchMediaKeyEvent(downEvent);

                        upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
                        audioManager.dispatchMediaKeyEvent(upEvent);

                        break;
                    case Values.MUSIC_NEXT:
                        eventtime = SystemClock.uptimeMillis();

                        downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
                        audioManager.dispatchMediaKeyEvent(downEvent);

                        upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
                        audioManager.dispatchMediaKeyEvent(upEvent);

                        break;
                    case Values.MUSIC_OPEN:
                        Util.openApp(this, intent.getStringExtra("packageName"));

                        break;
                }
            }
        }
    }
}

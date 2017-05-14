package readwithme.com;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by Mark on 1/6/2017.
 */

public class BackgroundAudioService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;
    int[] musicFiles;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        musicFiles = new int[]{R.raw.bensound_cute, R.raw.bensound_littleidea, R.raw.good_imes,R.raw.happy, R.raw.the_clouds};
        mediaPlayer = MediaPlayer.create(getApplicationContext(), musicFiles[1]);
        mediaPlayer.setVolume(0.5f , 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }

}

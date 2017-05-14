package readwithme.com;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mark on 12/19/2016.
 */

public class tools {

    private static   Intent playbackServiceIntent;
    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }




    /*public static void Player(final Context context, final Activity activity, final LayoutInflater inflater) {
        final PlayerHelper db = new PlayerHelper(activity);


        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(activity);
        dialogBuilder.setTitle("Player Name");
        // LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_newplayer, null);
        final EditText txt_player = (EditText) dialogView.findViewById(R.id.txt_playername_);
        //txt_player.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button cmd_submit = (Button) dialogView.findViewById(R.id.cmd_savePlayer);
        dialogBuilder.setView(dialogView);
        final android.app.AlertDialog alertDialog = dialogBuilder.create();
        cmd_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(), "New Player", Toast.LENGTH_SHORT).show();
                if (txt_player.getText().toString().length() <= 0) {
                    txt_player.setError("Player name is required");
                } else {

                    userInfo.name = txt_player.getText().toString();
                    if (db.getCheckDuplicate(txt_player.getText().toString()) >= 1) {
                        Toast.makeText(context, "Already Exist", Toast.LENGTH_SHORT).show();
                    } else {
                        PlayerHelper db = new PlayerHelper(activity);
                        db.addPlayer(new Player(0, userInfo.name, userInfo.age,userInfo.grade,userInfo.teacher,"1"));
                        Intent intent = new Intent(context, SelectTeacherActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(0, 0);
                        activity.finish();
                        alertDialog.dismiss();

                    }
                }
            }

            private void overridePendingTransition(int i, int i1) {
            }
        });
        alertDialog.show();
    }
*/
    public static void ReadPlayer(Activity activity) {
        PlayerHelper db = new PlayerHelper(activity);

        List<Player> players = db.getAllPlayer();
        variables.CustomListViewValuesArr.clear();
        for (Player player : players) {
            player.setCn(player.getCn());
            player.setName(player.getName());
            player.setAge(player.getAge());
            player.setGrade(player.getGrade());
            player.setTeacherIndex(player.getTeacherIndex());
            player.setRemark(player.getRemark());
            variables.CustomListViewValuesArr.add(player);
        }
    }


    public static int shuffleArray(int array) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.indexOf(0);
    }


    public static void showImage(Activity context, int img) {
      final Dialog  builder = new Dialog(context);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setCancelable(true);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(img);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    builder.dismiss();
                }
            }, 500);

        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }

  public static   void speakNow(final Context context, final TextToSpeech t1, final String sttrig) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                t1.speak(sttrig, TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 100);
    }

}

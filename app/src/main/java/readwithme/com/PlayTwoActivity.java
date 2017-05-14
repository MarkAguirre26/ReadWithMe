package readwithme.com;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressCustom;

public class PlayTwoActivity extends Activity implements RecognitionListener {

    int drawableIndex = 0;
    int[] drawableItems;

    List<String> drawableName;
    ImageView img_bubble_g1_l2, img_teacher_img_bubble_g1_l2, grade1_l2_item_1, grade1_l2_item_2;
    Button cmd_next_g1l2;

    private int speechIndex = 0;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "PlayTwoActivity";
    ACProgressCustom progressDialog;
    Dialog builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_two);
        tools.setFullScreen(this);

        cmd_next_g1l2 = (Button) findViewById(R.id.cmd_next_g1l2);
        img_bubble_g1_l2 = (ImageView) findViewById(R.id.img_bubble_g1_l2);
        img_teacher_img_bubble_g1_l2 = (ImageView) findViewById(R.id.img_teacher_img_bubble_g1_l2);
        grade1_l2_item_1 = (ImageView) findViewById(R.id.grade1_l2_item_1);
        grade1_l2_item_2 = (ImageView) findViewById(R.id.grade1_l2_item_2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g1_l2);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g1_l);

        drawableItems = new int[]{R.drawable.grade_1_lesso2_a, R.drawable.grade_1_lesso2_b, R.drawable.grade_1_lesso2_c, R.drawable.grade_1_lesso2_d, R.drawable.grade_1_lesso2_conco_a, R.drawable.grade_1_lesso2_conco_b, R.drawable.grade_1_lesso2_conco_c};
        drawableName = Arrays.asList("bed", "bad", "bet", "Ben", "map", "mad", "Matt", "math", "Mac");
        //progressDialog = new ProgressDialog(this);
      //  progressDialog.setTitle("Please wait..");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        if (userInfo.teacher == 1) {
            img_teacher_img_bubble_g1_l2.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher_img_bubble_g1_l2.setImageResource(R.drawable.ms_sophia);
        }

        img_bubble_g1_l2.setImageResource(drawableItems[5]);

        grade1_l2_item_1.setImageResource(drawableItems[0]);
        grade1_l2_item_2.setImageResource(drawableItems[1]);

        cmd_next_g1l2.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.INVISIBLE);
        grade1_l2_item_1.setVisibility(View.INVISIBLE);
        grade1_l2_item_2.setVisibility(View.INVISIBLE);

        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    toggleButton.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    toggleButton.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });

        startTimer();
    }



    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
      //  finish();
    }


    private void startTimer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img_bubble_g1_l2.setImageResource(drawableItems[4]);
                startTimerB();
            }
        };
        new Handler().postDelayed(runnable, 5000);
    }

    private void startTimerB() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                img_bubble_g1_l2.setVisibility(View.INVISIBLE);
                grade1_l2_item_1.setVisibility(View.VISIBLE);
                grade1_l2_item_2.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);

            }
        };
        new Handler().postDelayed(runnable, 5000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.show();
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);


    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // returnedText = errorMessage;
        toggleButton.setChecked(false);
        progressDialog.dismiss();
        toggleButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        toggleButton.setVisibility(View.VISIBLE);
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        progressDialog.dismiss();
        speechIndex++;
        for (String result : matches) {
            //   speechResultsArray.add(result);

            try {


                Thread.sleep(200);

                if (speechIndex == 2) {
                    img_bubble_g1_l2.setImageResource(drawableItems[6]);
                    grade1_l2_item_1.setImageResource(drawableItems[0]);
                    grade1_l2_item_2.setImageResource(drawableItems[1]);
                    img_bubble_g1_l2.setVisibility(View.VISIBLE);
                    cmd_next_g1l2.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.INVISIBLE);

                } else if (speechIndex == 4) {

                   // Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();


                    if (drawableName.contains(result)) {
                       // tools.showImage(this, R.drawable.check);
                        variables.playerScore++;
                        break;
                    } else {
                        tools.showImage(this, R.drawable.wrong);
                        break;
                    }

                } else if (drawableName.contains(result)) {
                  //  tools.showImage(this, R.drawable.check);
                    variables.playerScore++;
                    break;
                } else {
                    tools.showImage(this, R.drawable.wrong);
                    break;
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        Log.d("resultt", speechIndex + " " + matches.toString());
    }


    public void cmd_next_Clicked(View view) {
        //  img_bubble_g1_l2.setImageResource(drawableItems[6]);
        grade1_l2_item_1.setImageResource(drawableItems[2]);
        grade1_l2_item_2.setImageResource(drawableItems[3]);
        img_bubble_g1_l2.setVisibility(View.VISIBLE);
        cmd_next_g1l2.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.VISIBLE);
        img_bubble_g1_l2.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }


}
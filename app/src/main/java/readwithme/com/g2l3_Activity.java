package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g2l3_Activity extends Activity implements RecognitionListener {

    ImageView img_teacher, img_subject, img_bubble, img_title_g2l2;

    String[] drawableName;

    Button cmd_next;
    boolean isNext = true;

    private ToggleButton cmd_speak;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g2l3_Activity";
    ACProgressCustom progressDialog;
    int rawIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g2l3);
        tools.setFullScreen(this);


        cmd_next = (Button) findViewById(R.id.cmd_next_g2l3);
        img_title_g2l2 = (ImageView) findViewById(R.id.img_title_g2l3);
        img_teacher = (ImageView) findViewById(R.id.img_teacher_g2l3);
        img_subject = (ImageView) findViewById(R.id.img_subject_g2l3);
        img_bubble = (ImageView) findViewById(R.id.img_bubble_g2l3);
        // img_highlight1 = (ImageView) findViewById(R.id.img_highlight1_g2l3);
        // img_highlight2 = (ImageView) findViewById(R.id.img_highlight2_g2l3);


        progressBar = (ProgressBar) findViewById(R.id.progressBar_g2l3);
        cmd_speak = (ToggleButton) findViewById(R.id.cmd_speak_g2l3);

        cmd_speak.setVisibility(View.INVISIBLE);
        img_subject.setVisibility(View.INVISIBLE);
        img_title_g2l2.setVisibility(View.INVISIBLE);
        // imgSetVisible(img_highlight1, false);

        //  progressDialog = new ProgressDialog(this);
        //  progressDialog.setTitle("Please wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();

        drawableName = new String[]{"dance", "take", "country", "cry", "drum", "dress", "back", "lack", "hug", "mug", "share", "care"};


        if (userInfo.teacher == 1) {
            img_teacher.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher.setImageResource(R.drawable.ms_sophia);
        }


        ////////////////////////////////////////////
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        cmd_speak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cmd_speak.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    cmd_speak.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });

    }



    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
     //   finish();
    }

    public void cmd_next_Clicked(View view) {
        img_title_g2l2.setVisibility(View.VISIBLE);
        img_subject.setVisibility(View.VISIBLE);
        cmd_speak.setVisibility(View.VISIBLE);
        cmd_next.setVisibility(View.INVISIBLE);
        img_bubble.setVisibility(View.INVISIBLE);
        //   imgSetVisible(img_highlight1, true);


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
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        cmd_speak.setChecked(false);
        progressDialog.show();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // Toast.makeText(getApplicationContext(),"Network Connection",Toast.LENGTH_SHORT).show();
        cmd_speak.setVisibility(View.VISIBLE);
        cmd_speak.setChecked(false);
        progressDialog.dismiss();
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
        cmd_speak.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 1) {
                    img_subject.setImageResource(R.drawable.g2l3_nr_1_left);
                    checkAnswer(drawableName, result);
                    break;
                } else if (rawIndex == 2) {
                    img_subject.setImageResource(R.drawable.g2l3_nr_2_right);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 3) {
                    img_subject.setImageResource(R.drawable.g2l3_nr_2_left);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 4) {
                    img_subject.setImageResource(R.drawable.g2l3_nr_3_right);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 5) {
                    img_subject.setImageResource(R.drawable.g2l3_nr_3_left);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 6) {
                    img_title_g2l2.setImageResource(R.drawable.g2l2_bubble_1_title2);
                    img_subject.setImageResource(R.drawable.g2l3_r_1_right);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 7) {
                    img_subject.setImageResource(R.drawable.g2l3_r_1_left);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 8) {
                    img_subject.setImageResource(R.drawable.g2l3_r_2_right);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 9) {
                    img_subject.setImageResource(R.drawable.g2l3_r_2_left);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 10) {
                    img_subject.setImageResource(R.drawable.g2l3_r_3_right);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 11) {
                    img_subject.setImageResource(R.drawable.g2l3_r_2_left);
                    checkAnswer(drawableName, result);
                    break;

                } else if (rawIndex == 12) {
                    checkAnswer(drawableName, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        progressDialog.dismiss();
        Log.d("result", matches.toString());

    }

    private void checkAnswer(String[] s, String result) {
        List<String> list = Arrays.asList(s);
        if (list.contains(result)) {
            variables.playerScore += 1;
            tools.showImage(this, R.drawable.check);
        } else {
            tools.showImage(this, R.drawable.wrong);
        }
        rawIndex++;
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

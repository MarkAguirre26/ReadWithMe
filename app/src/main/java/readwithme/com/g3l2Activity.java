package readwithme.com;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g3l2Activity extends Activity implements RecognitionListener {

    int drawableIndex = 0;
    int[] drawableItems;

    String[] drawableName;
    ImageView img_bubble_g1_l2, img_teacher, img_subject, img_title_g3l2;
    Button cmd_next_g1l2;
    int convoIndex = 1;
    private int speechIndex = 0;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g3l2Activity";
    ACProgressCustom progressDialog;
    Dialog builder;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g3l2);
        tools.setFullScreen(this);



        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "A safety sign is a picture that alerts people of posible dangers or hazards");

        cmd_next_g1l2 = (Button) findViewById(R.id.cmd_next_g3l2);
        img_bubble_g1_l2 = (ImageView) findViewById(R.id.img_bubble_g3l2);
        img_teacher = (ImageView) findViewById(R.id.img_teacher_g3l2);
        img_subject = (ImageView) findViewById(R.id.img_subject_g3l2);
        img_title_g3l2 = (ImageView) findViewById(R.id.img_title_g3l2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_g3l2);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g3l2);


        img_title_g3l2.setVisibility(View.INVISIBLE);

        drawableName = new String[]{"fire", " fire extinguisher", "pedestrian crossing", " crossing", " emercency exit", "high voltage", "flammable", "toxic"};
        drawableItems = new int[]{R.drawable.g3l2_subject_1, R.drawable.g3l2_subject_2, R.drawable.g3l2_subject_3, R.drawable.g3l2_subject_4, R.drawable.g3l2_subject_5, R.drawable.g3l2_subject_6};
       // progressDialog = new ProgressDialog(this);
       // progressDialog.setTitle("Please wait..");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        if (userInfo.teacher == 1) {
            img_teacher.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher.setImageResource(R.drawable.ms_sophia);
        }

        img_bubble_g1_l2.setImageResource(R.drawable.g3l2_convo_1);
        img_subject.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.INVISIBLE);


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
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
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

        if (convoIndex == 1) {
            convoIndex += 1;
            img_bubble_g1_l2.setImageResource(R.drawable.g3l2_convo_2);
        } else if (convoIndex == 2) {
            img_title_g3l2.setVisibility(View.VISIBLE);
            img_subject.setImageResource(R.drawable.g3l2_subject_1);
            img_subject.setVisibility(View.VISIBLE);
            toggleButton.setVisibility(View.VISIBLE);
            img_bubble_g1_l2.setVisibility(View.INVISIBLE);
            cmd_next_g1l2.setVisibility(View.INVISIBLE);
        }


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
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        progressDialog.dismiss();
        speechIndex++;
        for (String result : matches) {
            try {
                Thread.sleep(200);

                if (drawableIndex == 5) {
                    //  checkAnswer(drawableName, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;
                }else{
                    checkAnswer(drawableName, result);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drawableIndex++;
        if (speechIndex <= 5) {
            img_subject.setImageResource(drawableItems[speechIndex]);
        }
        Log.d("result", speechIndex + " " + matches.toString());
    }

    private void checkAnswer(String[] s, String result) {
        List<String> list = Arrays.asList(s);
        if (list.contains(result)) {
            variables.playerScore +=1;
            tools.showImage(this, R.drawable.check);
        } else {
            tools.showImage(this, R.drawable.wrong);
        }


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
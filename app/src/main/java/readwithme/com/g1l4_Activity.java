package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g1l4_Activity extends Activity implements RecognitionListener {
    ImageView img_g1l3_I1, img_g1l3_I2, img_g1l3_I3, img_g1l3_I4, img_g1l3_I5;
    ACProgressCustom progressDialog;

    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g1l4_Activity";
    int rawIndex = 1;
    String[] drawableWords1, drawableWords2, drawableWords3, drawableWords4, drawableWords5;
TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g1l4);
        tools.setFullScreen(this);
        img_g1l3_I1 = (ImageView) findViewById(R.id.img_g1l4_highligh1);
        img_g1l3_I2 = (ImageView) findViewById(R.id.img_g1l4_highligh2);
        img_g1l3_I3 = (ImageView) findViewById(R.id.img_g1l4_highligh3);
        img_g1l3_I4 = (ImageView) findViewById(R.id.img_g1l4_highligh4);
        img_g1l3_I5 = (ImageView) findViewById(R.id.img_g1l4_highligh5);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "Read the words that have initial vowel sound");

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g1l4);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_lesson4);

        //progressDialog = new ProgressDialog(this);
        //progressDialog.setTitle("Wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        imgSetVisible(img_g1l3_I1);

        drawableWords1 = new String[]{"ambulance", "ambience", "Cumbiadance", "ambiance"};
        drawableWords2 = new String[]{"excellent", "eggplant", "X-Men", "Iceland", "XClan"};
        drawableWords3 = new String[]{"icecream", "icecream", "Iscream"};
        drawableWords4 = new String[]{"hours", "owl", "our", "hour"};
        drawableWords5 = new String[]{"umbrella", "umbrellas", "unbrella"};


        progressBar.setVisibility(View.INVISIBLE);
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
    }


    void imgSetVisible(ImageView imageView) {
        img_g1l3_I1.setVisibility(View.INVISIBLE);
        img_g1l3_I2.setVisibility(View.INVISIBLE);
        img_g1l3_I3.setVisibility(View.INVISIBLE);
        img_g1l3_I4.setVisibility(View.INVISIBLE);
        img_g1l3_I5.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
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
        toggleButton.setChecked(false);
        progressDialog.show();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // returnedText.setText(errorMessage);
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
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 1) {
                    imgSetVisible(img_g1l3_I2);
                    checkAnswer(drawableWords1, result);
                    break;
                } else if (rawIndex == 2) {
                    imgSetVisible(img_g1l3_I3);
                    checkAnswer(drawableWords2, result);
                    break;

                } else if (rawIndex == 3) {
                    imgSetVisible(img_g1l3_I4);
                    checkAnswer(drawableWords3, result);
                    break;

                } else if (rawIndex == 4) {
                    imgSetVisible(img_g1l3_I5);
                    checkAnswer(drawableWords4, result);
                    break;

                } else if (rawIndex == 5) {
                    imgSetVisible(img_g1l3_I5);
                    checkAnswer(drawableWords5, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, ResultActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    private void checkAnswer(String[] s, String result) {
        progressDialog.dismiss();
        List<String> list = Arrays.asList(s);
        if (list.contains(result)) {
            variables.playerScore +=1;
            tools.showImage(this, R.drawable.check);
        } else {
            tools.showImage(this, R.drawable.wrong);
        }

        rawIndex++;
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
    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
     //   finish();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Activity_MenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}

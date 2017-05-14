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
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g2A3Activity extends Activity implements RecognitionListener {
TextToSpeech t1;
    ImageView img_h1;
    ImageView img_h2;
    ImageView img_h3;
    ImageView img_h4;
    ImageView img_h5;
    ACProgressCustom progressDialog;
    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g2A3Activity";
    int rawIndex = 1;
    // String[] drawableWords1, drawableWords2, drawableWords3, drawableWords4, drawableWords5, drawableWords6, drawableWords7, drawableWords8, drawableWords9, drawableWords10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2a3_);
        tools.setFullScreen(this);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "Fill in the blank with the correct consonant blends");

        img_h1 = (ImageView) findViewById(R.id.img_g2a3_h1);
        img_h2 = (ImageView) findViewById(R.id.img_g2a3_h2);
        img_h3 = (ImageView) findViewById(R.id.img_g2a3_h3);
        img_h4 = (ImageView) findViewById(R.id.img_g2a3_h4);
        img_h5 = (ImageView) findViewById(R.id.img_g2a3_h5);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g2a3);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g2a3);

      //  progressDialog = new ProgressDialog(this);
      //  progressDialog.setTitle("Wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        imgSetVisible(img_h1, false);


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


    void imgSetVisible(ImageView imageView, boolean isTrue) {
        img_h1.setVisibility(View.INVISIBLE);
        img_h2.setVisibility(View.INVISIBLE);
        img_h3.setVisibility(View.INVISIBLE);
        img_h4.setVisibility(View.INVISIBLE);
        img_h5.setVisibility(View.INVISIBLE);
        if (isTrue == true) {
            imageView.setVisibility(View.VISIBLE);
        }

    }


    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
      //  finish();
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
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Activity_MenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
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

        try {
            Thread.sleep(200);
            checkAnswer(matches);
            if (rawIndex > 5) {
                startActivity(new Intent(this, ResultActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rawIndex++;
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    private void checkAnswer(ArrayList<String> matches) {
        progressDialog.dismiss();
        rawIndex++;
        for (String result : matches) {
            if (result.contains("dragon") || result.contains("d") || result.contains("dra")) {
                tools.showImage(this, R.drawable.check);
                imgSetVisible(img_h1, true);
                variables.playerScore += 1;
                break;
            } else if (result.contains("shrimp") || result.contains("shr")) {
                tools.showImage(this, R.drawable.check);
                imgSetVisible(img_h2, true);
                variables.playerScore += 1;
                break;
            } else if (result.contains("crow") || result.contains("cr")) {
                tools.showImage(this, R.drawable.check);
                imgSetVisible(img_h3, true);
                variables.playerScore += 1;
                break;
            } else if (result.contains("dream") || result.contains("dr")) {
                tools.showImage(this, R.drawable.check);
                imgSetVisible(img_h4, true);
                variables.playerScore += 1;
                break;
            } else if (result.contains("match") || result.contains("tch")) {
                tools.showImage(this, R.drawable.check);
                imgSetVisible(img_h5, true);
                variables.playerScore += 1;
                break;
            } else {
                tools.showImage(this, R.drawable.wrong);
            }
        }

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
}

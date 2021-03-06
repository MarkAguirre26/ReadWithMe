package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g2A3subActivity extends Activity implements RecognitionListener {

    ImageView img_h1, img_h2, img_h3, img_h4, img_h5, img_h6, img_h7, img_h8, img_h9, img_h10;

    ACProgressCustom progressDialog;
    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g2A3Activity";
    int rawIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2a3_sub);
        tools.setFullScreen(this);

        img_h1 = (ImageView) findViewById(R.id.img_g2a3_h1);
        img_h2 = (ImageView) findViewById(R.id.img_g2a3_h2);
        img_h3 = (ImageView) findViewById(R.id.img_g2a3_h3);
        img_h4 = (ImageView) findViewById(R.id.img_g2a3_h4);
        img_h5 = (ImageView) findViewById(R.id.img_g2a3_h5);
        img_h6 = (ImageView) findViewById(R.id.img_g2a3_h6);
        img_h7 = (ImageView) findViewById(R.id.img_g2a3_h7);
        img_h8 = (ImageView) findViewById(R.id.img_g2a3_h8);
        img_h9 = (ImageView) findViewById(R.id.img_g2a3_h9);
        img_h10 = (ImageView) findViewById(R.id.img_g2a3_h10);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g2a3sub);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g2a3sub);


        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        imgSetVisible();


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


    void imgSetVisible() {
        img_h1.setVisibility(View.INVISIBLE);
        img_h2.setVisibility(View.INVISIBLE);
        img_h3.setVisibility(View.INVISIBLE);
        img_h4.setVisibility(View.INVISIBLE);
        img_h5.setVisibility(View.INVISIBLE);
        img_h6.setVisibility(View.INVISIBLE);
        img_h7.setVisibility(View.INVISIBLE);
        img_h8.setVisibility(View.INVISIBLE);
        img_h9.setVisibility(View.INVISIBLE);
        img_h10.setVisibility(View.INVISIBLE);

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

    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
        //finish();
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
            if (rawIndex > 10) {
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
            if (result.contains("took") || result.contains("tuk")) {
                tools.showImage(this, R.drawable.check);
                img_h1.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("brake ") || result.contains("break")) {
                tools.showImage(this, R.drawable.check);
                img_h2.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("but") || result.contains("butt")) {
                tools.showImage(this, R.drawable.check);
                img_h3.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("hud") || result.contains("wood") || result.contains("hood")) {
                tools.showImage(this, R.drawable.check);
                img_h4.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("nook") || result.contains("nuke")) {
                tools.showImage(this, R.drawable.check);
                img_h5.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            }else if (result.contains("frree")) {
                tools.showImage(this, R.drawable.check);
                img_h6.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("home")) {
                tools.showImage(this, R.drawable.check);
                img_h7.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("trip")) {
                tools.showImage(this, R.drawable.check);
                img_h8.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("made")) {
                tools.showImage(this, R.drawable.check);
                img_h9.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            } else if (result.contains("she")) {
                tools.showImage(this, R.drawable.check);
                img_h10.setVisibility(View.VISIBLE);
                variables.playerScore += 1;
                break;
            }else {
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

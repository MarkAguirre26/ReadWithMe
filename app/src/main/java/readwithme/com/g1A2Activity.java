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
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g1A2Activity extends Activity implements RecognitionListener {

    ImageView img_teacher_g1a2, imf_bubble_g1a2, img_h1, img_h2, img_h3, img_h4, img_h5;
    ACProgressCustom progressDialog;

    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g2A1Activity";
    int rawIndex = 1;
    // String[] drawableWords1, drawableWords2, drawableWords3, drawableWords4, drawableWords5, drawableWords6, drawableWords7, drawableWords8, drawableWords9, drawableWords10;

    RelativeLayout rel_Subject_g1a2;
    Button cmd_next_g1a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g1a2);
        tools.setFullScreen(this);

        img_h1 = (ImageView) findViewById(R.id.img_g1a2_h1);
        img_h2 = (ImageView) findViewById(R.id.img_g1a2_h2);
        img_h3 = (ImageView) findViewById(R.id.img_g1a2_h3);
        img_h4 = (ImageView) findViewById(R.id.img_g1a2_h4);
        img_h5 = (ImageView) findViewById(R.id.img_g1a2_h5);
        img_teacher_g1a2 = (ImageView) findViewById(R.id.img_teacher_g1a2);
        imf_bubble_g1a2 = (ImageView) findViewById(R.id.imf_bubble_g1a2);

        rel_Subject_g1a2 = (RelativeLayout) findViewById(R.id.rel_Subject_g1a2);
        cmd_next_g1a2 = (Button) findViewById(R.id.cmd_next_g1a2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g1a2);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g1a2);

        //  progressDialog = new ProgressDialog(this);
        // progressDialog.setTitle("Wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();


        img_teacher_g1a2.setVisibility(View.VISIBLE);
        imf_bubble_g1a2.setVisibility(View.VISIBLE);
        rel_Subject_g1a2.setVisibility(View.INVISIBLE);
        cmd_next_g1a2.setVisibility(View.VISIBLE);
        toggleButton.setVisibility(View.INVISIBLE);
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

    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
       // finish();
    }

    public void cmd_next_g1a2_Clicked(View view) {
        img_teacher_g1a2.setVisibility(View.INVISIBLE);
        imf_bubble_g1a2.setVisibility(View.INVISIBLE);
        rel_Subject_g1a2.setVisibility(View.VISIBLE);
        cmd_next_g1a2.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.VISIBLE);
        imgSetVisible(img_h1, false);
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
        Log.i(LOG_TAG, "onResults");
        toggleButton.setVisibility(View.VISIBLE);
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for (String result : matches) {
            try {
                Thread.sleep(200);
                checkAnswer(result);

                if (rawIndex >= 5) {
                    startActivity(new Intent(this, ResultActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        rawIndex++;
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    private void checkAnswer(String result) {
        progressDialog.dismiss();
        if (result.contains("r") || result.contains("ar") || result.contains("spider")) {
            variables.playerScore += 1;
            tools.showImage(this, R.drawable.check);
            imgSetVisible(img_h1, true);
        } else if (result.contains("y") || result.contains("butterfly")) {
            variables.playerScore += 1;
            tools.showImage(this, R.drawable.check);
            imgSetVisible(img_h2, true);
        } else if (result.contains("l") || result.contains("snail")) {
            tools.showImage(this, R.drawable.check);
            variables.playerScore += 1;
            imgSetVisible(img_h3, true);
        } else if (result.contains("grasshopper") || result.contains("r")) {
            tools.showImage(this, R.drawable.check);
            variables.playerScore += 1;
            imgSetVisible(img_h4, true);
        } else if (result.contains("g") || result.contains("laddybug") || result.contains("bug")) {
            tools.showImage(this, R.drawable.check);
            variables.playerScore += 1;
            imgSetVisible(img_h5, true);
        } else {
            tools.showImage(this, R.drawable.wrong);
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

package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g2q3Activity extends Activity implements RecognitionListener {
    TextToSpeech t1;
    Button cmd_ok;
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
        setContentView(R.layout.activity_g2q3);
        tools.setFullScreen(this);
        cmd_ok = (Button) findViewById(R.id.cmd_ok);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_g2q3);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_g2q3);
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();


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
           t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
       tools.speakNow(getApplicationContext(),t1,"Listen and Read the rhyming words in each sentense.");

    }

    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
       // finish();
    }


    public void cmd_ok_Clicked(View view) {


        if (rawIndex == 1) {
            if (t1 != null) {
                t1.stop();
            }
            tools.speakNow(getApplicationContext(),t1,"Dan and stan plan to go to the football game this weekend.");
        } else if (rawIndex == 2) {
            if (t1 != null) {
                t1.stop();
            }
            tools.speakNow(getApplicationContext(),t1,"Don't set the hot pot down ont he plastic.");
        } else if (rawIndex == 3) {
            if (t1 != null) {
                t1.stop();
            }
            tools.speakNow(getApplicationContext(),t1,"The bear stole the apple and the pear from the campers.");
        } else if (rawIndex == 4) {
            if (t1 != null) {
                t1.stop();
            }
            tools.speakNow(getApplicationContext(),t1,"jack blew on the glue to make it try faster.");
        } else if (rawIndex == 5) {
            if (t1 != null) {
                t1.stop();
            }
            tools.speakNow(getApplicationContext(),t1,"The flea jumped off the dog and landed on the tree.");
        }
        try {
            Thread.sleep(500);
            if (rawIndex >= 1) {
                toggleButton.setVisibility(View.VISIBLE);
                cmd_ok.setVisibility(View.INVISIBLE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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

        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), QuizMenuActivity.class));
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
        toggleButton.setVisibility(View.INVISIBLE);
        cmd_ok.setVisibility(View.VISIBLE);
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
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        try {
            Thread.sleep(200);
            checkAnswer(matches);
            if (rawIndex >= 5) {
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
        toggleButton.setVisibility(View.INVISIBLE);
        cmd_ok.setVisibility(View.VISIBLE);
        for (String result : matches) {
            if (result.contains("Dan and Stan")) {
                tools.showImage(this, R.drawable.check);

                variables.playerScore += 1;
                break;
            } else if (result.contains("hot and pot ")) {
                tools.showImage(this, R.drawable.check);

                variables.playerScore += 1;
                break;
            } else if (result.contains("bear and pear")) {
                tools.showImage(this, R.drawable.check);

                variables.playerScore += 1;
                break;
            } else if (result.contains("blue and glue")) {
                tools.showImage(this, R.drawable.check);

                variables.playerScore += 1;
                break;
            } else if (result.contains("flea and tree")) {
                tools.showImage(this, R.drawable.check);

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

package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressCustom;

public class LessonTwoActivity extends Activity implements RecognitionListener {

    ImageView img_lessontwo_bench, img_lessontwo_swing, img_rock, img_lessontwo_girl, img_lessontwo_sandbox, img_lessontwo_slide, img_lessontwo_table, img_bucket;
    int[] drawableObjects;
    List<String> drawableName;

    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    TextView txt_timer_lessonTwo;
    private boolean isTimerStart = false;
    ACProgressCustom progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
        tools.setFullScreen(this);
        img_rock = (ImageView) findViewById(R.id.img_rock);
        img_bucket = (ImageView) findViewById(R.id.img_bucket);
        txt_timer_lessonTwo = (TextView) findViewById(R.id.txt_timer_lessonTwo);
        img_lessontwo_bench = (ImageView) findViewById(R.id.img_lessontwo_bench);
        img_lessontwo_swing = (ImageView) findViewById(R.id.img_lessontwo_swing);
        img_lessontwo_girl = (ImageView) findViewById(R.id.img_lessontwo_girl);
        img_lessontwo_sandbox = (ImageView) findViewById(R.id.img_lessontwo_sandbox);
        img_lessontwo_slide = (ImageView) findViewById(R.id.img_lessontwo_slide);
        img_lessontwo_table = (ImageView) findViewById(R.id.img_lessontwo_table);
        drawableObjects = new int[]{R.drawable.lesson_two_bench_selected, R.drawable.lesson_two_bucket_selected, R.drawable.lesson_two_girl_selected, R.drawable.lesson_two_table_selected, R.drawable.lesson_two_slide_selected, R.drawable.lesson_two_rock_selected, R.drawable.lesson_two_sandbox_selected, R.drawable.lesson_two_swing_selected};
        drawableName = Arrays.asList("bench", "bucket", "fable", "Rock", "girl", "GERD", "light", "dirty", "barely", "Ben", "string", "table", "slide", "rock", "sandbox", "swing", "Babel", "market", "good", "burger", "Market", "panda", "Benz");


       // progressDialog = new ProgressDialog(this);
       // progressDialog.setTitle("Please wait...");

        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();

        progressBar = (ProgressBar) findViewById(R.id.progressBarLessontwo);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButtonlessontwo);

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
        //    startTimer();
        txt_timer_lessonTwo.setVisibility(View.INVISIBLE);
    }


    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
       // finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void gotoBack() {
        startActivity(new Intent(getApplicationContext(), QuizMenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotoBack();
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
        //  Toast.makeText(getApplicationContext(), "Network Connection", Toast.LENGTH_SHORT).show();
        toggleButton.setVisibility(View.VISIBLE);
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
        toggleButton.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";

        if (variables.playerScore == 5) {
            //    variables.playerScore +=1;
            variables.activityCameFrom = "LessonTwoActivity";

            startActivity(new Intent(getApplicationContext(), ResultActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            for (String result : matches) {
                text += result + "\n";

                // Log.d("Score",variables.playerScore+"");
                try {

                    Thread.sleep(200);
                    if (drawableName.contains(result)) {
                        variables.playerScore +=1;
                        tools.showImage(this, R.drawable.check);
                        //  Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
                        // variables.playerScore++;
                        if (result.contains("bench") || result.contains("Benz") || result.contains("Ben")) {

                            img_lessontwo_bench.setImageResource(R.drawable.lesson_two_bench_selected);
                            break;
                        } else if (result.contains("bucket") || result.contains("Market")) {
                            img_bucket.setImageResource(R.drawable.lesson_two_bucket_selected);
                            break;
                        } else if (result.contains("Rock")) {

                            img_rock.setImageResource(R.drawable.lesson_two_rock_selected);
                            break;
                        } else if (result.contains("sandbox") || result.contains("panda")) {
                            img_lessontwo_sandbox.setImageResource(R.drawable.lesson_two_sandbox_selected);
                            break;
                        } else if (result.contains("swing") || result.contains("string")) {
                            img_lessontwo_swing.setImageResource(R.drawable.lesson_two_swing_selected);
                            break;

                        }

                        break;
                    } else {
                        tools.showImage(this, R.drawable.wrong);
                        // Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }

        progressDialog.dismiss();
        returnedText = text;
        Log.d("result", matches.toString());
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


}

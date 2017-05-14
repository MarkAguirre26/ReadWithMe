package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class PlayThreeActivity extends Activity implements RecognitionListener {
    ImageView img_teacher_play_3, img_vowels_long_play_3, img_vowels_short_play_3;
    RelativeLayout RelativeLayout_play_3;
    //  Button cmd_Lesson3_play;
    RelativeLayout rel_lesson_3_play;
    //  int[] drawableName_normal, drawableName_selected;

    ACProgressCustom progressDialog;
    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "PlayThreeActivity";

    private int drawableIndex = 1;
    List<String> drawableNames_1_long;
    List<String> drawableNames_2_long;
    List<String> drawableNames_3_long;
    List<String> drawableNames_4_long;
    List<String> drawableNames_5_long;
    List<String> drawableNames_1_short;
    List<String> drawableNames_2_short;
    List<String> drawableNames_3_short;
    List<String> drawableNames_4_short;
    List<String> drawableNames_5_short;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_three);
        tools.setFullScreen(this);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "");


        // progressDialog = new ProgressDialog(this);
        // progressDialog.setTitle("Wait...");
        //variables.playerScore = 0;

        progressBar = (ProgressBar) findViewById(R.id.progressBar_lesson_3_play);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_lesson_3_play);


        rel_lesson_3_play = (RelativeLayout) findViewById(R.id.rel_lesson_3_play);
        // cmd_Lesson3_play = (Button) findViewById(R.id.cmd_Lesson3_play);
        img_teacher_play_3 = (ImageView) findViewById(R.id.img_teacher_play_3);
        //  img_vowels_play_3 = (ImageView) findViewById(R.id.img_vowels_play_3);
        //  img_bubble_play_3 = (ImageView) findViewById(R.id.img_bubble_play_3);

        RelativeLayout_play_3 = (RelativeLayout) findViewById(R.id.RelativeLayout_play_3);
        img_vowels_long_play_3 = (ImageView) findViewById(R.id.img_vowels_long_play_3);
        img_vowels_short_play_3 = (ImageView) findViewById(R.id.img_vowels_short_play_3);

        // drawableName_normal = new int[]{R.drawable.lesson_3_play_apple, R.drawable.lesson_3_play_belle, R.drawable.lesson_3_play_cake, R.drawable.lesson_3_play_cube, R.drawable.lesson_3_play_frog, R.drawable.lesson_3_play_jump, R.drawable.lesson_3_play_kite, R.drawable.lesson_3_play_nose, R.drawable.lesson_3_play_pig, R.drawable.lesson_3_play_wheel};
        // drawableName_selected = new int[]{R.drawable.lesson_3_play_apple_selected, R.drawable.lesson_3_play_belle_selected, R.drawable.lesson_3_play_cake_selected, R.drawable.lesson_3_play_cube_selected, R.drawable.lesson_3_play_frog_selected, R.drawable.lesson_3_play_jump_selected, R.drawable.lesson_3_play_kite_selected, R.drawable.lesson_3_play_nose_selected, R.drawable.lesson_3_play_pig_selected, R.drawable.lesson_3_play_wheel_selected};

        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();

        drawableNames_1_long = Arrays.asList("cake", "Kik", "Jake", "Kake", "cakes");
        drawableNames_2_long = Arrays.asList("weather", "riddle", "Reddit", "real", "weird", "will", "wheel", "whale", "Wale", "win");
        drawableNames_3_long = Arrays.asList("Skype", "kite", "pipe", "fight", "guide");
        drawableNames_5_long = Arrays.asList("Lowe's", "nose", "toast", "noce", "Moe's", "Lowe's", "nose", "toast", "no", "note", "kill", "Kia", "Guild", "Kim", "Cube");
        drawableNames_4_long = Arrays.asList("Kim", "kill", "Q", "Kik", "Kia", "Kim", "kill", "gym", "Cube");

        drawableNames_1_short = Arrays.asList("Apple", "Popple", "Coppell", "Popeyes", "purple", "Apple", "APPL", "apples", "iPod", "opal");
        drawableNames_2_short = Arrays.asList("bed", "better", "bad", "bet", "Bell", "bed", "better", "beds", "weather", "baddest", "men", "Mel", "mail", "mad", "man");
        drawableNames_3_short = Arrays.asList("Bing", "big", "dick", "date", "big", "Bing", "Pig", "ping");
        drawableNames_5_short = Arrays.asList("frog", "Prague", "proud", "krowd", "Frogg");
        drawableNames_4_short = Arrays.asList("dumb", "gum", "dump", "jump", "dum", "jump", "job", "dump", "John", "dumb");

        if (userInfo.teacher == 1) {
            img_teacher_play_3.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher_play_3.setImageResource(R.drawable.ms_sophia);
        }

        //  img_vowels_long_play_3.setVisibility(View.INVISIBLE);
        // img_vowels_short_play_3.setVisibility(View.INVISIBLE);
        //  toggleButton.setVisibility(View.INVISIBLE);


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

        //  cmd_Lesson3_play.setVisibility(View.INVISIBLE);
        img_vowels_long_play_3.setVisibility(View.VISIBLE);
        img_vowels_short_play_3.setVisibility(View.VISIBLE);
        // img_vowels_long_play_3.setImageResource(drawableName_selected[2]);
        //  img_vowels_short_play_3.setImageResource(drawableName_normal[0]);
        //img_vowels_play_3.setVisibility(rel_lesson_3_play.getRootView().INVISIBLE);
        // img_bubble_play_3.setVisibility(rel_lesson_3_play.getRootView().INVISIBLE);
        //  toggleButton.setVisibility(View.VISIBLE);


    }

    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
       // finish();
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
        String text = "";
        progressDialog.dismiss();
        Log.d("TAG", drawableIndex + "");
        drawableIndex++;
        for (String result : matches) {
            text += result + "\n";
            try {
                Thread.sleep(200);

                if (drawableIndex == 1) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_cake_selected);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_apple);
                    if (drawableNames_1_long.contains(result)) {
                        tools.showImage(this, R.drawable.check);
                        variables.playerScore++;
                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex == 2) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_cake);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_apple_selected);

                    if (drawableNames_1_short.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;
                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex == 3) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_wheel_selected);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_belle);


                    if (drawableNames_2_long.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }

                    break;
                } else if (drawableIndex == 4) {


                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_wheel);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_belle_selected);


                    if (drawableNames_2_short.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;
                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }

                    break;
                } else if (drawableIndex == 5) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_kite_selected);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_pig);

                    if (drawableNames_3_long.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex == 6) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_kite);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_pig_selected);


                    if (drawableNames_3_short.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex == 7) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_nose_selected);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_frog);

                    if (drawableNames_4_long.contains(result)) {


                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex == 8) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_nose);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_frog_selected);

                    if (drawableNames_4_short.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }

                    break;
                } else if (drawableIndex == 9) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_cube_selected);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_jump);
                    //  img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_cube);
                    //  img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_jump_selected);

                    if (drawableNames_5_long.contains(result)) {
                        tools.showImage(this, R.drawable.check);

                        variables.playerScore++;

                    } else {
                        tools.showImage(this, R.drawable.wrong);
                    }


                    break;
                } else if (drawableIndex >= 10) {
                    img_vowels_long_play_3.setImageResource(R.drawable.lesson_3_play_cube);
                    img_vowels_short_play_3.setImageResource(R.drawable.lesson_3_play_jump_selected);
                    if (drawableNames_5_short.contains(result)) {
                      variables.playerScore++;
                        Log.d("TAG", drawableIndex + "");
                    }
                    variables.activityCameFrom = "PlayThreeActivity";
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();

                    break;
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    Log.d("result",matches.toString());
    returnedText=text;
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

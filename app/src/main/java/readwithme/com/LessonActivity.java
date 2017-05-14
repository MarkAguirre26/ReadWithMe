package readwithme.com;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class LessonActivity extends Activity implements RecognitionListener {
    Dialog builder;
    TextView txt_words;
    ImageView img_object, img_faviantable;
    int[] objectItems;
    List<String> objectNames;
    String[] letters;
    int rowIndex = 0;
    TextToSpeech t1;
    RelativeLayout board_lesson_1_quiz;
    private final int REQ_CODE_SPEECH_INPUT = 100;


    private TextView returnedText, txt_lesson_1_quiz;
    private ToggleButton cmd_speakLessonOne;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    ACProgressCustom progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        tools.setFullScreen(this);

        board_lesson_1_quiz = (RelativeLayout) findViewById(R.id.board_lesson_1_quiz);
        txt_lesson_1_quiz = (TextView) findViewById(R.id.txt_lesson_1_quiz);
        txt_words = (TextView) findViewById(R.id.txt_words);
        returnedText = (TextView) findViewById(R.id.textView1);
        img_object = (ImageView) findViewById(R.id.img_object);
        img_faviantable = (ImageView) findViewById(R.id.img_faviantable);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        cmd_speakLessonOne = (ToggleButton) findViewById(R.id.cmd_speakLessonOne);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(), t1, "Pronounce the beginning consonant in the picture\n");


        // progressDialog =  new ProgressDialog(this);
        //  progressDialog.setTitle("Please wait");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        objectItems = new int[]{R.drawable.bat, R.drawable.cake, R.drawable.zibra, R.drawable.house, R.drawable.pencil};
        letters = new String[]{"B", "C", "Z", "H", "P"};
        // objectNames = Arrays.asList("bird", "cake", "butt", "cape", "Benzel", "benzoyl", "benzyl", "gate", "benzene", "cancel", "Benson","map", "Map", "zibra", "house", "pencil");
        objectNames = Arrays.asList("B", "be", "bee", "beef", "V", "C", "sea", "see", "SI", "CE", "X", "h", "8", "eggs", "it's", "be", "bee", "big", "p");
        img_object.setImageResource(objectItems[rowIndex]);
        txt_lesson_1_quiz.setText(letters[rowIndex]);

        if (userInfo.teacher == 2) {
            img_faviantable.setImageResource(R.drawable.pink_owl_teacher);
            board_lesson_1_quiz.setBackgroundResource(R.drawable.pink_owl_teacher_board);
        }

        //  img_subView.setImageResource(objectItems[tools.shuffleArray(objectItems.length)]);

//Initialized speech controls needed


///////CODE for speech
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        cmd_speakLessonOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cmd_speakLessonOne.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    cmd_speakLessonOne.setVisibility(View.VISIBLE);
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
        cmd_speakLessonOne.setChecked(false);

        progressDialog.show();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // Toast.makeText(getApplicationContext(),"Network Connection",Toast.LENGTH_SHORT).show();
        returnedText.setText(errorMessage);
        cmd_speakLessonOne.setChecked(false);
        progressDialog.dismiss();
        cmd_speakLessonOne.setVisibility(View.VISIBLE);
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

        progressDialog.dismiss();
        cmd_speakLessonOne.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches) {
            text += result + "\n";
            Log.d("Result", result);
            if (rowIndex >= 4) {
                variables.activityCameFrom = "LessonActivity";
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else {
                try {
                    Thread.sleep(200);
                    if (objectNames.contains(result)) {
                        variables.playerScore += 1;
                        variables.playerScore++;
                        progressDialog.dismiss();
                        showImage(R.drawable.check);
                        break;
                    } else {
                        progressDialog.dismiss();
                        showImage(R.drawable.wrong);
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        Log.d("result", matches.toString());
        returnedText.setText(text);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void cmd_lessonActivity_Clicked(View view) {


        ToggleButton b = (ToggleButton) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {

            case 1:
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
                    try {
                        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                    } catch (ActivityNotFoundException a) {
                        Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Internet is required", Toast.LENGTH_SHORT).show();
                }

                break;
        }


    }



  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String res = result.get(0);
                    txt_words.setText(res);
                    if(rowIndex>= 4){
                      startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                    }else {
                        if (res.contains(objectNames[rowIndex])) {
                            showImage(R.drawable.check);
                        } else {
                            showImage(R.drawable.wrong);
                        }
                        img_subView.setImageResource(objectItems[tools.shuffleArray(objectItems.length)]);
                        rowIndex++;
                        img_object.setImageResource(objectItems[rowIndex]);
                    }
                }
                break;
            }
        }

    }
*/

    public void showImage(int img) {
        builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setCancelable(false);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(img);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
        try {
            //Close builder after 1 second.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    builder.dismiss();
                }
            }, 1000);

            //  img_subView.setImageResource(objectItems[tools.shuffleArray(objectItems.length)]);
            rowIndex++;
            img_object.setImageResource(objectItems[rowIndex]);
            txt_lesson_1_quiz.setText(letters[rowIndex]);

        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }

    private void gotoBack() {
        startActivity(new Intent(getApplicationContext(), Activity_MenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotoBack();
    }
}

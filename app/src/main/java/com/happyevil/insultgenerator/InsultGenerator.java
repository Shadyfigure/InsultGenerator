package com.happyevil.insultgenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import java.util.HashMap;


public class InsultGenerator extends Activity implements TextToSpeech.OnInitListener, OnUtteranceCompletedListener
{

    private Button but_generate;
    private Button but_speak;
    private TextView txt_insult;
    private String[] insultsCol1;
    private String[] insultsCol2;
    private String[] insultsCol3;
    private TextToSpeech tts;

    private String toShow;
    private String toSay;
    private String toSend;

    private ShareActionProvider mShareActionProvider;
    private Intent shareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insult_generator);

        initializeUI();

        if(savedInstanceState != null)
        {
            txt_insult.setText(savedInstanceState.getString("insult"));
        }
    }

    public void initializeUI()
    {
        txt_insult=(TextView)findViewById(R.id.Text);

        tts = new TextToSpeech(InsultGenerator.this, InsultGenerator.this);

        fillInsults();

        but_generate=(Button)findViewById(R.id.generate);
        but_generate.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                generateInsult();
                                                txt_insult.setText(toShow);
                                            }
                                        }

        );

        but_speak=(Button)findViewById(R.id.speak);
        but_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!tts.isSpeaking())
                {
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSay);
                    tts.speak(toSay, TextToSpeech.QUEUE_ADD, params);
                    but_speak.setText("STOP!");
                    Log.i("SPEAKING","SPEAKING");
                }
                else
                {
                    tts.stop();
                }
            }
        });

        generateInsult();
        txt_insult.setText(toShow);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                but_speak.setText("Say It!");
            }
        });
    }

    @Override
    public void onInit(int status)
    {
        tts.setOnUtteranceCompletedListener(this);
    }

    @Override
    protected void onDestroy()
    {
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        super.onDestroy();
    }

    private void generateInsult()
    {
        toSay = "";
        toShow = "";
        toSend = "";

        int num1 = (int)Math.ceil(Math.random()*18);
        int num2 = (int)Math.ceil(Math.random()*18);
        int num3 = (int)Math.ceil(Math.random()*19);

        toSay = toSay + insultsCol1[num1] +
                " " + insultsCol2[num2] +
                " " + insultsCol3[num3] +
                "!";
        toSay= toSay.toLowerCase();

        toShow = toShow + insultsCol1[num1] +
                "\n" + insultsCol2[num2] +
                "\n" + insultsCol3[num3] +
                "!";

        toSend = toSend + insultsCol1[num1] +
                " " + insultsCol2[num2] +
                " " + insultsCol3[num3] +
                "!";

        createShareIntent();

        if(mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void fillInsults()
    {
        insultsCol1 = new String[19];
        insultsCol2 = new String[19];
        insultsCol3 = new String[20];

        insultsCol1[0] = "LAZY";
        insultsCol1[1] = "STUPID";
        insultsCol1[2] = "INSECURE";
        insultsCol1[3] = "IDIOTIC";
        insultsCol1[4] = "SLIMY";
        insultsCol1[5] = "SMELLY";
        insultsCol1[6] = "POMPOUS";
        insultsCol1[7] = "COMMUNIST";
        insultsCol1[8] = "DICK-NOSE";
        insultsCol1[9] = "PIE-EATING";
        insultsCol1[10] = "RACIST";
        insultsCol1[11] = "ELITIST";
        insultsCol1[12] = "WHITE TRASH";
        insultsCol1[13] = "DRUG-LOVING";
        insultsCol1[14] = "BUTTER-FACE";
        insultsCol1[15] = "TONE DEAF";
        insultsCol1[16] = "UGLY";
        insultsCol1[17] = "CREEPY";
        insultsCol1[18] = "SLUTTY";

        insultsCol2[0] = "DOUCHE";
        insultsCol2[1] = "ASS";
        insultsCol2[2] = "TURD";
        insultsCol2[3] = "RECTUM";
        insultsCol2[4] = "BUTT";
        insultsCol2[5] = "COCK";
        insultsCol2[6] = "SHIT";
        insultsCol2[7] = "CROTCH";
        insultsCol2[8] = "BITCH";
        insultsCol2[9] = "TURD";
        insultsCol2[10] = "PRICK";
        insultsCol2[11] = "SLUT";
        insultsCol2[12] = "TAINT";
        insultsCol2[13] = "FUCK";
        insultsCol2[14] = "DICK";
        insultsCol2[15] = "BONER";
        insultsCol2[16] = "SHART";
        insultsCol2[17] = "NUT";
        insultsCol2[18] = "SPHINCTER";

        insultsCol3[0] = "PILOT";
        insultsCol3[1] = "CANOE";
        insultsCol3[2] = "CAPTAIN";
        insultsCol3[3] = "PIRATE";
        insultsCol3[4] = "HAMMER";
        insultsCol3[5] = "KNOB";
        insultsCol3[6] = "BOX";
        insultsCol3[7] = "JOCKEY";
        insultsCol3[8] = "NAZI";
        insultsCol3[9] = "WAFFLE";
        insultsCol3[10] = "GOBLIN";
        insultsCol3[11] = "BLOSSUM";
        insultsCol3[12] = "BISCUIT";
        insultsCol3[13] = "CLOWN";
        insultsCol3[14] = "SOCKET";
        insultsCol3[15] = "MONSTER";
        insultsCol3[16] = "HOUND";
        insultsCol3[17] = "DRAGON";
        insultsCol3[18] = "BALLOON";
        insultsCol3[19] = "COMET";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.insult_generator, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);

        mShareActionProvider = (ShareActionProvider)menu.findItem(R.id.action_share).getActionProvider();

        mShareActionProvider.setShareIntent(shareIntent);

        return super.onCreateOptionsMenu(menu);
    }

    public Intent createShareIntent()
    {
        shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "INSULT");
        shareIntent.putExtra(Intent.EXTRA_TEXT, toSend);
        shareIntent.setType("text/plain");
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("insult",txt_insult.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        txt_insult.setText(savedInstanceState.getString("insult"));
    }
}

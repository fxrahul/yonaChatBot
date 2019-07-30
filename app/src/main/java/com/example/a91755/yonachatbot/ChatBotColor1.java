package com.example.a91755.yonachatbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a91755.yonachatbot.MessageAdapter;
import com.example.a91755.yonachatbot.ResponseMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ChatBotColor1 extends AppCompatActivity {

    EditText userInput;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<ResponseMessage> responseMessageList;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ImageButton btnSpeak;
    TextView btnLogout,changeTheme;
    String userQuery;
    int isExists=0;
    private FirebaseAuth auth;
    String userSpeechQuery;
    DatabaseReference databaseQuestions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot_color1);
        auth = FirebaseAuth.getInstance();
        databaseQuestions = FirebaseDatabase.getInstance().getReference("unknown_questions");

        userInput = findViewById(R.id.userInput);
        recyclerView = findViewById(R.id.conversation);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnLogout = (TextView) findViewById(R.id.logout);
        changeTheme = findViewById(R.id.change_theme);
        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatBotColor1.this,ChatBot.class));
                finish();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
                Toast.makeText(ChatBotColor1.this,"Log Out Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ChatBotColor1.this,MainscreenActivity.class));
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(messageAdapter);



//        ResponseMessage responseMessage = new ResponseMessage(userInput.getText().toString(), true);
//        responseMessageList.add(responseMessage);
//        String userQuery = userInput.getText().toString();
//        ChatBot.RetrieveFeedTask task=new ChatBot.RetrieveFeedTask();
//        task.execute(userQuery);



        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {

                    userQuery = userInput.getText().toString();
                    userInput.setText("");
                    ResponseMessage responseMessage = new ResponseMessage(userQuery, true);
                    responseMessageList.add(responseMessage);
                    messageAdapter.notifyDataSetChanged();
                    if (!isLastVisible()) {
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    }

                    ChatBotColor1.RetrieveFeedTask task=new ChatBotColor1.RetrieveFeedTask();
                    task.execute(userQuery);
                }
                return false;
            }
        });
    }

    //for speech input

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn\\'t support speech input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String check = result.get(0);
                    for (int i = 0 ; i<check.length() ; i++) {
                        if (check.charAt(i) == '@') {
                            isExists = 1;
                            break;
                        }
                    }

                    if(isExists==1){
                        userSpeechQuery = result.get(0).toLowerCase().replace(" ","");
                        Log.d("isworking","working");
                    }else {
                        userSpeechQuery = result.get(0).toLowerCase();
                    }
                    ResponseMessage responseMessage = new ResponseMessage(userSpeechQuery, true);
                    responseMessageList.add(responseMessage);
                    messageAdapter.notifyDataSetChanged();
                    if (!isLastVisible()) {
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    }
                    ChatBotColor1.RetrieveFeedTask task=new ChatBotColor1.RetrieveFeedTask();
                    task.execute(userSpeechQuery);


                }
                break;
            }

        }
    }



    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }

    // Create GetText Method
    public String GetText(String query) throws UnsupportedEncodingException {

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("https://api.api.ai/v1/query?v=20150910");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Authorization", "Bearer 3e478e3db1b14905ae8c3a153636fe38");
            conn.setRequestProperty("Content-Type", "application/json");

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            JSONArray queryArray = new JSONArray();
            queryArray.put(query);
            jsonParam.put("query", queryArray);
//            jsonParam.put("name", "order a medium pizza");
            jsonParam.put("lang", "en");
            jsonParam.put("sessionId", "1234567890");


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            Log.d("karma", "after conversion is " + jsonParam.toString());
            wr.write(jsonParam.toString());
            wr.flush();
            Log.d("karma", "json is " + jsonParam);

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;


            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();



            JSONObject object1 = new JSONObject(text);
            JSONObject object = object1.getJSONObject("result");
            JSONObject fulfillment = null;
            String speech = null;
//            if (object.has("fulfillment")) {
            fulfillment = object.getJSONObject("fulfillment");
//                if (fulfillment.has("speech")) {
            speech = fulfillment.optString("speech");
//                }
//            }


            Log.d("karma ", "response is " + text);
            return speech;

        } catch (Exception ex) {
            Log.d("karma", "exception at last " + ex);
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        return null;
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String s = null;
            try {

                s = GetText(voids[0]);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.d("karma", "Exception occurred " + e);
            }

            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ChatBotColor1.this,s,Toast.LENGTH_LONG);
            ResponseMessage responseMessage2 = new ResponseMessage(s, false);
            responseMessageList.add(responseMessage2);
            messageAdapter.notifyDataSetChanged();
            if(s.equals("I didn't get that. Can you say it again?")){
                if(userQuery == null){
                    //do for userspeechquery
                    String  id = databaseQuestions.push().getKey();
                    UnknownQuestions unknownQuestions = new UnknownQuestions(id,userSpeechQuery);
                    databaseQuestions.child(id).setValue(unknownQuestions);
                    Toast.makeText(ChatBotColor1.this,"Yona Cannot identify this query, this query will be added soon!",Toast.LENGTH_LONG).show();

                }
                else {
                    //do for userquery

                    String id = databaseQuestions.push().getKey();
                    UnknownQuestions unknownQuestions = new UnknownQuestions(id, userQuery);
                    databaseQuestions.child(id).setValue(unknownQuestions);
                    Toast.makeText(ChatBotColor1.this, "Yona Cannot identify this query, this query will be added soon!", Toast.LENGTH_LONG).show();

                 }
                }
            if (!isLastVisible()) {
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
            }

        }
    }





}

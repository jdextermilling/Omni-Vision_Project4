package com.example.omni_vision;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * Created by JacobDexter-Milling on 5/10/16.
 */

// Incoming webhook endpoint = https://hooks.slack.com/services/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

public class SlackFragment extends Fragment{

    SlackAPIService slackAPIService;

    String myMessage;
    String initialMessage = "Send a message to Funny Biz~";
    ArrayList<String> messageList;
    ArrayAdapter adapter;

    public static final String BASE_URL = "https://hooks.slack.com/services/T0351JZQ0/" +
            "B17M3E46M/DiP0k4QOett4tzCXVjt544OD";

    Retrofit retrofit;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slack_fragemnt, container, false);

        messageList = new ArrayList();
        messageList.add(initialMessage);
        messageList.add(myMessage);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        adapter.notifyDataSetChanged();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        slackAPIService = retrofit.create(SlackAPIService.class);
        Call<SlackMessage> toSend = slackAPIService.sendMessage(new SlackMessage(myMessage));
        toSend.enqueue(new Callback<SlackMessage>() {
            @Override
            public void onResponse(Call<SlackMessage> call, Response<SlackMessage> response) {
                Log.i("SlackFragment", "Message posted" + myMessage);
            }

            @Override
            public void onFailure(Call<SlackMessage> call, Throwable t) {
                Log.i("SlackFragment", "Message failed to post" + myMessage);
            }
        });



        return view;
    }

    public void setmyMessage(String myMessage){
        this.myMessage = myMessage;
    }




}

package com.example.omni_vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
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

public class SlackFragment extends Fragment {

    private static final String CURRENT_MESSAGELIST = "current_messagelist";
    SharedPreferences sharedPreferences5;

    SlackAPIService slackAPIService;

    String initialMessage = "Send a message to Funny Biz~";
    ArrayList<String> messageList;
    ArrayAdapter adapter;
    ListView slackListView;

    public static final String BASE_URL = "https://hooks.slack.com/services/T0351JZQ0/";
    //"B17M3E46M/DiP0k4QOett4tzCXVjt544OD";

    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slack_fragemnt, container, false);

        slackListView = (ListView) view.findViewById(R.id.slack_listView);
        messageList = new ArrayList<>();
        messageList.add(initialMessage);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, messageList);
        slackListView.setAdapter(adapter);



        sharedPreferences5 = getContext().getSharedPreferences("countPrefFive", Context.MODE_PRIVATE);



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        slackAPIService = retrofit.create(SlackAPIService.class);


        return view;
    }

    public void setMyMessage(final String myMessage) {
        messageList.add(myMessage);
        adapter.notifyDataSetChanged();

        slackAPIService.sendMessage(new SlackMessage(myMessage))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("SlackFragment", "Message posted: " + myMessage);
                        Toast.makeText(getContext(), "Message posted", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        Log.e("SlackFragment", "Message failed to post: " + myMessage);
                        messageList.remove(myMessage);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}

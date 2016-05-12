package com.example.omni_vision;

import com.example.omni_vision.Model.SlackMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by JacobDexter-Milling on 5/10/16.
 */
public interface SlackAPIService {

    @POST("B17M3E46M/DiP0k4QOett4tzCXVjt544OD")
    Call<Void> sendMessage(@Body SlackMessage slackMessage);
}

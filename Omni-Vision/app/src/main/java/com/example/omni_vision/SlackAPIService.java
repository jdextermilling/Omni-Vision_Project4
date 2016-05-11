package com.example.omni_vision;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by JacobDexter-Milling on 5/10/16.
 */
public interface SlackAPIService {

    @POST("B17M3E46M/DiP0k4QOett4tzCXVjt544OD")
    Call<SlackMessage> sendMessage(@Body SlackMessage slackMessage);
}

package com.example.omni_vision;

/**
 * Created by JacobDexter-Milling on 5/11/16.
 */
public class SlackMessageList {
    private static SlackMessageList ourInstance = new SlackMessageList();



    public static SlackMessageList getInstance() {
        return ourInstance;
    }

    private SlackMessageList() {
    }
}

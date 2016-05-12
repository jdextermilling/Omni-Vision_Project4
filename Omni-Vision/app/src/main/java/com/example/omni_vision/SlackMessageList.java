package com.example.omni_vision;

import java.util.ArrayList;

/**
 * Created by JacobDexter-Milling on 5/11/16.
 */
public class SlackMessageList {
    public ArrayList<String> posts;

    private SlackMessageList() {
        posts = new ArrayList<String>();
    }

    private static SlackMessageList instance;

    public static SlackMessageList getInstance() {
        if (instance == null) instance = new SlackMessageList();
        return instance;
    }
}

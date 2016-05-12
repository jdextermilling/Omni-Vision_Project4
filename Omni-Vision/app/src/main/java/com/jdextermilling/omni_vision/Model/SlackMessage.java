package com.jdextermilling.omni_vision.Model;

/**
 * Created by JacobDexter-Milling on 5/10/16.
 */
public class SlackMessage {

    String text;

    public SlackMessage(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

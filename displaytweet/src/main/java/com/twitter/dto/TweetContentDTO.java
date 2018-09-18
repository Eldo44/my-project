package com.twitter.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class is created so that only message/tweet text is shown in the UI layer
 */
public class TweetContentDTO {
    @ApiModelProperty(notes = "The tweet text")
    private String messageText;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}

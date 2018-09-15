package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetDTO {

    @JsonProperty(TweetConstants.MESSAGE_ID)
    private String messageId;

    @JsonProperty(TweetConstants.CREATED_DATE)
    private Date creationDate;

    @JsonProperty(TweetConstants.TEXT)
    private String messageText;

    @JsonProperty(TweetConstants.AUTHOR)
    private AuthorDTO authorDTO;

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public String getMessageId() {
        return messageId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    @Override
    public int hashCode() {
        return messageId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        TweetDTO otherTweetDTO = (TweetDTO) obj;
        return Objects.equals(this.getMessageId(), otherTweetDTO.getMessageId());
    }

    @Override
    public String toString() {
        return "TweetDTO [messageId=" + messageId + ", creationDate=" + creationDate + ", messageText=" + messageText
                + ", authorDTO=" + authorDTO + "]";
    }

}

package com.twitter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Message")
public class Tweet implements Serializable {

    private static final long serialVersionUID = 2893905046340295111L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "message_id")
    private String messageId;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "message_text")
    private String messageText;
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", messageId='" + messageId + '\'' +
                ", creationDate=" + creationDate +
                ", messageText='" + messageText + '\'' +
                ", author=" + author +
                '}';
    }

}

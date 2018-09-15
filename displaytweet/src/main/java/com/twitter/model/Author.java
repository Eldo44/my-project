package com.twitter.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Author")
public class Author implements Serializable {

    private static final long serialVersionUID = 2893905046340295023L;
    @Id
    @Column(name = "author_id")
    private String userId;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "screen_name")
    private String screenName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "AuthorDTO [userId=" + userId + ", creationDate=" + creationDate + ", screenName=" + screenName + "]\n";
    }
}

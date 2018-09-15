package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDTO {

	@JsonProperty(TweetConstants.USER_ID)
	private String userId;

	@JsonProperty(TweetConstants.CREATED_DATE)
	private Date creationDate;

	@JsonProperty(TweetConstants.SCREEN_NAME)
	private String screenName;

	public String getUserId() {
		return userId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getScreenName() {
		return screenName;
	}

	@Override
	public String toString() {
		return "AuthorDTO [userId=" + userId + ", creationDate=" + creationDate + ", screenName=" + screenName + "]\n";
	}

	@Override
	public int hashCode() {
		return userId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		AuthorDTO otherAuthorDTO = (AuthorDTO) obj;
		return Objects.equals(this.getUserId(), otherAuthorDTO.getUserId());
	}

}

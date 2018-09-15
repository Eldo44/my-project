package com.twitter.oauth;

import java.io.IOException;

public interface TwitterClient {
	void track(String trackParameter) throws TwitterAuthenticationException, IOException;
}

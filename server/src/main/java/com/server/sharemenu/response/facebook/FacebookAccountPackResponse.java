package com.server.sharemenu.response.facebook;

/**
 * The class is used to transport data between client - server
 */
public class FacebookAccountPackResponse {
    FacebookAccountsResponse[] data;

    public FacebookAccountsResponse[] getData() {
        return data;
    }

    public void setData(FacebookAccountsResponse[] data) {
        this.data = data;
    }
}

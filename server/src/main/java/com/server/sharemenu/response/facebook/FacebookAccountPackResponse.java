package com.server.sharemenu.response.facebook;

//Класът се използва за транспортиране на данни между client - server
public class FacebookAccountPackResponse {
    FacebookAccountsResponse[] data;

    public FacebookAccountsResponse[] getData() {
        return data;
    }

    public void setData(FacebookAccountsResponse[] data) {
        this.data = data;
    }
}

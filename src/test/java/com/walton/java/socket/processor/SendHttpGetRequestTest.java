package com.walton.java.socket.processor;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class SendHttpGetRequestTest {
    @Test
    public void testExecute() throws IOException {
        URL stubUrl = new URL("https://www.google.com.tw/");
        SendHttpGetRequest sendHttpGetRequest = new SendHttpGetRequest(stubUrl);
        Socket socket = new Socket(stubUrl.getHost(),443);
        boolean expected = true;
        boolean actual = sendHttpGetRequest.execute(socket.getOutputStream());
        Assert.assertEquals(expected,actual);
    }
}

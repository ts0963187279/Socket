package com.walton.java.socket.processor;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SendStringToServerTest {
    @Test
    public void testExecute() throws IOException {
        ServerSocket stubServerSocket = new ServerSocket(6666);
        Socket socket = new Socket("127.0.0.1",6666);
        SendStringToServer sendStringToServer = new SendStringToServer(socket);
        boolean actual = sendStringToServer.execute("test");
        boolean expected = true;
        Assert.assertEquals(expected,actual);

        stubServerSocket.close();
        actual = sendStringToServer.execute("test");
        expected = false;
        Assert.assertEquals(expected,actual);
    }
}

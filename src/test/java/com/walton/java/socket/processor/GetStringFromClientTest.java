/*
 * Copyright (C) 2017 RS Wong <ts0963187279@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walton.java.socket.processor;

import org.junit.Assert;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class GetStringFromClientTest {
    @Test
    public void testExecute() throws IOException {
        ServerSocket stubServerSocket = new ServerSocket(6666);
        Socket stubSocket = new Socket("127.0.0.1",6666);
        GetStringFromClient getStringFromClient = new GetStringFromClient(stubServerSocket.accept());
        DataOutputStream outputStream = new DataOutputStream(stubSocket.getOutputStream());
        outputStream.writeUTF("test");
        outputStream.flush();

        String expected = "test";
        String actual = getStringFromClient.execute(null);
        Assert.assertEquals(expected,actual);

        outputStream.writeUTF("abc");
        outputStream.flush();
        expected = "abc";
        actual = getStringFromClient.execute(null);
        Assert.assertEquals(expected,actual);

        stubSocket.close();
        stubServerSocket.close();
    }
    @Test
    public void testClose() throws IOException {
        ServerSocket stubServerSocket = new ServerSocket(6666);
        Socket stubSocket = new Socket("127.0.0.1",6666);
        DataOutputStream dataOutputStream = new DataOutputStream(stubSocket.getOutputStream());
        dataOutputStream.writeUTF("test");
        dataOutputStream.flush();
        GetStringFromClient getStringFromClient = new GetStringFromClient(stubServerSocket.accept());
        getStringFromClient.execute(null);

        boolean expected = true;
        boolean actual = getStringFromClient.close();
        Assert.assertEquals(expected,actual);

        stubServerSocket.close();
        stubSocket.close();
    }
}

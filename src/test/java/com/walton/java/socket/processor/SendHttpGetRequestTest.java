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

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

import poisondog.core.Mission;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;

public class GetSSLSocket implements Mission<String> {
    private int port;
    public GetSSLSocket(int port){
        this.port = port;
    }
    @Override
    public Socket execute(String hostName){
        Socket socket = null;
        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
            socket = factory.createSocket(hostName,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}

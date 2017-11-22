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

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class DownloadData implements Mission<URL>{
    private int port;
    private File file;
    public DownloadData(File file){
        port = 443;
        this.file = file;
    }
    public Void execute(URL url){
        String hostName = url.getHost();
        Socket socket;
        socket = new GetSSLSocket(port).execute(hostName);
        try {
            OutputStream outputStream = socket.getOutputStream();
            new SendHttpGetRequest(url).execute(outputStream);
            OutputStream fileOutputStream = new FileOutputStream(file);
            GetHttpResponseData getHttpResponseData = new GetHttpResponseData(fileOutputStream);
            getHttpResponseData.execute(socket);
            outputStream.close();
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
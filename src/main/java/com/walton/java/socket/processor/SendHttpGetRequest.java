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

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.URL;

public class SendHttpGetRequest implements Mission<OutputStream>{
    private URL url;
    public SendHttpGetRequest(URL url){
        this.url = url;
    }
    @Override
    public Boolean execute(OutputStream outputStream){
        try {
            DataOutputStream bw = new DataOutputStream(outputStream);
            bw.writeBytes("GET " + url.getFile() + " HTTP/1.1\r\n");
            bw.writeBytes("HOST: " + url.getHost() + "\r\n");
            bw.writeBytes("\r\n\r\n");
            bw.flush();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

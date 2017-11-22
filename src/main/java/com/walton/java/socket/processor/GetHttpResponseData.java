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

public class GetHttpResponseData implements Mission<Socket> {
    private OutputStream outputStream;
    public GetHttpResponseData(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    @Override
    public Void execute(Socket socket) throws IOException {
        int count, offset, len = 0;
        DataInputStream in = new DataInputStream(socket.getInputStream());
//        File file = new File("outputTemp");
//        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        boolean eohFound = false;
        int length = 0;
        while ((count = in.read(buffer)) != -1) {
            offset = 0;
            if (!eohFound) {
                String string = new String(buffer, 0, count);
                GetHttpContentLength getHttpContentLength = new GetHttpContentLength();
                length = getHttpContentLength.execute(string);
                int indexOfEOH = string.indexOf("\r\n\r\n");
                if (indexOfEOH != -1) {
                    count = count - indexOfEOH - 4;
                    offset = indexOfEOH + 4;
                    eohFound = true;
                }
            }
            if(eohFound){
                len += count;
                outputStream.write(buffer, offset, count);
                outputStream.flush();
                System.out.println(len+"/"+length);
                if (len == length)
                    break;
            }
        }
        outputStream.close();
        in.close();
        return null;
    }
    private class GetHttpContentLength implements Mission<String> {
        @Override
        public Integer execute(String string){
            int contentLength;
            int index = string.indexOf("Content-Length: ");
            String tmp = "";
            for (int i = index + 16; string.charAt(i) != '\r'; i++) {
                tmp += string.charAt(i);
            }
            contentLength = Integer.parseInt(tmp);
            return contentLength;
        }
    }
}



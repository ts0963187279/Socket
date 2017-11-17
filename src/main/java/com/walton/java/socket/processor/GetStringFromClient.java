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

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class GetStringFromClient implements Mission<Void> {
    private Socket socket;
    private DataInputStream dataInputStream;
    public GetStringFromClient(Socket socket) throws IOException {
        this.socket = socket;
    }
    @Override
    public String execute(Void aVoid) throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        String string = dataInputStream.readUTF();
        return string;
    }
    public boolean close(){
        try {
            dataInputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

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

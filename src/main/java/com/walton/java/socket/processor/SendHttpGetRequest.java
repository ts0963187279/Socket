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

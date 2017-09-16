package com.gas.trackle.service;

import com.gas.trackle.domain.Article;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

@Service
public class SearchServiceImpl implements SearchService {

    private Socket socket = null;
    private String serverIp = "localhost";
    private int serverPort = 9999;

    @Override
    public List<Article> search(String query) {
        try {
            socket = new Socket(serverIp, serverPort);
            System.out.println("server connection success");

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osw);

            pw.println(query);
            pw.flush();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Scanner in = new Scanner(inputStream);

            String rcv = in.nextLine();



            String str = in.next();

            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

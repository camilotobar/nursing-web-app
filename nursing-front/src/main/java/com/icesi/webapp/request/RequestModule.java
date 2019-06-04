package com.icesi.webapp.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icesi.webapp.LocalDateAdapter;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Service
public class RequestModule {

    public final static String BACK_ROOT_PATH = "http://localhost:20000/";

    public String GETRequest(String url) throws IOException {

        URL urlForGetRequest = new URL(BACK_ROOT_PATH + url);
        String readLine = null;

        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
//        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here

        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while ((readLine = in.readLine()) != null)
                response.append(readLine);

            in.close();
            System.out.println(response.toString());
            return response.toString();
        } else
            return "GET NOT WORKED";
    }

    public String POSTRequest(String url, Object body) throws IOException{

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String json = gson.toJson(body);
//        System.out.println(json);

        URL obj = new URL(BACK_ROOT_PATH + url);
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
//        postConnection.setRequestProperty("userId", "a1bcdefgh");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);

        OutputStream os = postConnection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            System.out.println(response.toString());
            return response.toString();

        } else
            return "POST NOT WORKED";
    }
}

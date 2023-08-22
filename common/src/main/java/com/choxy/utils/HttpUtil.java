package com.choxy.utils;


import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.Arrays;

public class HttpUtil {

    public String doGet() {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ClassicHttpRequest httpRequest = ClassicRequestBuilder.get("").build();
            HttpEntity entity = httpClient.execute(httpRequest, classicHttpResponse -> {
                HttpEntity responseEntity = classicHttpResponse.getEntity();
                EntityUtils.consume(responseEntity);
                return responseEntity;
            });
            return entity.getContentEncoding();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String doPost() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            ClassicHttpRequest httpRequest = ClassicRequestBuilder.post("")
                    .setEntity(new UrlEncodedFormEntity(Arrays.asList(new BasicNameValuePair("", ""), new BasicNameValuePair("", ""))))
                    .build();
            HttpEntity entity = httpClient.execute(httpRequest, response -> {
                HttpEntity httpEntity = response.getEntity();
                return httpEntity;
            });
            return entity.getContentEncoding();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}

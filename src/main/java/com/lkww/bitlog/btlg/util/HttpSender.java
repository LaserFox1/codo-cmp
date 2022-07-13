package com.lkww.bitlog.btlg.util;

import com.lkww.bitlog.btlg.exceptions.HttpException;
import com.lkww.bitlog.btlg.exceptions.ServiceException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpSender {

    public static String post(JSONObject obj, String url) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String objS = obj.toJSONString();

            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI(url))
                    .addParameter("content", objS)
                    .build();

            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
                return objS;
            }
        } catch (IOException ioE) {
            throw HttpException.cannotPostIO(ioE);
        } catch (URISyntaxException uE) {
            throw HttpException.cannotPostURI(uE);
        }
    }
}

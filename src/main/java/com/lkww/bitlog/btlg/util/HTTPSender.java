package com.lkww.bitlog.btlg.util;

import com.lkww.bitlog.btlg.service.Exception.PluginException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.lambdaworks.redis.*;

public class HTTPSender {

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
            throw PluginException.cannotPostIO(ioE);
        } catch (URISyntaxException uE) {
            throw PluginException.cannotPostURI(uE);
        }
    }
}

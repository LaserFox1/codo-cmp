package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.util.HTTPSender;
import com.lkww.bitlog.btlg.util.TestExtractor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CoDoService {

    @Value("${input.base_url}")
    String BASE_URL_IN;
    @Value("${output.base_url}")
    String BASE_URL_OUT;

    public List<File> getAllFeatureFiles(String ID) {
        System.out.println(HTTPSender.get(BASE_URL_IN + "/" + ID));
        return Arrays.asList(Objects.requireNonNull(HTTPSender.getFile(BASE_URL_IN + ID).listFiles()));
    }

    public String getFeatureFile(String ID) {
        return HTTPSender.get(BASE_URL_IN + "/" + ID);
    }

    public String sendJSONObj(String ID) {
        return HTTPSender.post(
                TestExtractor.extract(getAllFeatureFiles(ID)),
                BASE_URL_OUT);
    }
}

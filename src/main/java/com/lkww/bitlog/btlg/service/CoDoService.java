package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.tools.HTTPPost;
import com.lkww.bitlog.btlg.tools.TestExtractor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CoDoService {

    @Value("${input.base_url}")
    String BASE_URL_IN;
    @Value("${output.base_url}")
    String BASE_URL_OUT;
    public List<File> getAllFeatureFiles(String ID){
        return null;
    }

    public String sendJSONObj(String ID){
        HTTPPost.post(TestExtractor.extract(getAllFeatureFiles(ID)),BASE_URL_OUT);
    }
}

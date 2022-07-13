package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.classes.Project;
import com.lkww.bitlog.btlg.util.HTTPSender;
import com.lkww.bitlog.btlg.util.ObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CoDoService {

    @Value("${input.base_url}")
    String BASE_URL_IN;
    @Value("${output.base_url}")
    String BASE_URL_OUT;

    @Scheduled(fixedRate = 5000)
    @Async
    public void RunScript() {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python C:\\hello_world.py");
            for (Project p : ObjectBuilder.builder(proc.getInputStream())) {
                HTTPSender.post(p.JSONize(), BASE_URL_OUT);
            }
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

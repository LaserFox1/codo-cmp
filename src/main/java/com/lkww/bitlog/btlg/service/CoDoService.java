package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.domain.Project;
import com.lkww.bitlog.btlg.exceptions.CrawlerException;
import com.lkww.bitlog.btlg.util.ObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CoDoService {

    @Value("${input.joined}")
    private String IN;
    @Value("${output.joined}")
    private String OUT;

    ProjectService service = new ProjectService();


    @Scheduled(fixedRate = 5000)
    @Async
    public void RunScript() {
        Process proc;
        try {
             String[] cmd = {
                    "python",
                     String.valueOf(getClass().getClassLoader().getResource("get.py")),
                     IN
            };
            proc = Runtime.getRuntime().exec(cmd);
            for (Project p : ObjectBuilder.builder(proc.getInputStream())) {
                service.create(p);
            }
            proc.waitFor();
        } catch (IOException ioE) {
            throw  CrawlerException.execCmdNotFound(ioE);
        }
        catch (InterruptedException iE){
            throw CrawlerException.execInterrupted(iE);
        }
        catch (Throwable t){
            throw CrawlerException.execUndetermined(t);
        }
    }
}

package com.lkww.codo.codo.service;

import com.lkww.codo.codo.domain.Project;
import com.lkww.codo.codo.exceptions.CrawlerException;
import com.lkww.codo.codo.exceptions.ServiceException;
import com.lkww.codo.codo.persistence.ProjectRepository;
import com.lkww.codo.codo.util.ObjectBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CoDoService {

    @Value("${input.joined}")
    private String IN;
    @Value("${output.joined}")
    private String OUT;
    @Autowired
    private final ProjectRepository rep = new ProjectRepository();


    @Scheduled(fixedRate = 600000)
    @Async
    public void RunScript() {
        String[] cmd = {
                "python",
                "src/main/resources/magic-crawler/magic.py"
        };
        ProcessBuilder pB = new ProcessBuilder(cmd);

        try {

            Process proc = pB.start();
            InputStream is = proc.getInputStream();
            for (Project p : ObjectBuilder.builder(is)) {
                try {
                    rep.save(p);
                } catch (PersistenceException pEx) {
                    throw ServiceException.cannotCreateEntity(p, pEx);
                } catch (Throwable t) {
                    throw ServiceException.cannotCreateEntityForUndeterminedReason(p, t);
                }
            }

            System.out.println(Arrays.toString(
                    new String[]{
                            new BufferedReader(
                                    new InputStreamReader(
                                            proc.getErrorStream()
                                    )
                            )
                                    .lines()
                                    .collect(Collectors.toList())
                                    .toString()
                    })
                    + " e");
            System.out.println(proc.waitFor());



        /*for (Project p : ObjectBuilder.builder(getClass().getClassLoader().getResourceAsStream("SampleOutput.txt"))) {
            try {
                rep.save(p);
            } catch (PersistenceException pEx) {
                throw ServiceException.cannotCreateEntity(p, pEx);
            } catch (Throwable t) {
                throw ServiceException.cannotCreateEntityForUndeterminedReason(p, t);
            }

        }*/
        } catch (IOException ioE) {
            throw CrawlerException.execCmdNotFound(ioE);
        } catch (InterruptedException iE) {
            throw CrawlerException.execInterrupted(iE);
        } catch (Throwable t) {
            throw CrawlerException.execUndetermined(t);
        }
    }
}

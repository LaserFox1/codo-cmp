package com.lkww.codo.codo.service;

import com.lkww.codo.codo.domain.Project;
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


    @Scheduled(fixedRate = 5000)
    @Async
    public void RunScript() {
        Process proc;
        /*try {
             String[] cmd = {
                    "python",
                     String.valueOf(getClass().getClassLoader().getResource("get.py")),
                     IN
            };
            proc = Runtime.getRuntime().exec(cmd);
            for (Project p : ObjectBuilder.builder(proc.getInputStream())) {
                service.create(p);
            }
            proc.waitFor();*/

        for (Project p : ObjectBuilder.builder(getClass().getClassLoader().getResourceAsStream("SampleOutput.txt"))) {
            try {
                rep.save(p).JSONize();
            } catch (PersistenceException pEx) {
                throw ServiceException.cannotCreateEntity(p, pEx);
            } catch (Throwable t) {
                throw ServiceException.cannotCreateEntityForUndeterminedReason(p, t);
            }

        }

        /*} catch (IOException ioE) {
            throw  CrawlerException.execCmdNotFound(ioE);
        }
        catch (InterruptedException iE){
            throw CrawlerException.execInterrupted(iE);
        }
        catch (Throwable t){
            throw CrawlerException.execUndetermined(t);
        }*/
    }
}

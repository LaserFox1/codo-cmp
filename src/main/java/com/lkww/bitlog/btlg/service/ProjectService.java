package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.domain.Project;
import com.lkww.bitlog.btlg.exceptions.CrawlerException;
import com.lkww.bitlog.btlg.exceptions.ServiceException;
import com.lkww.bitlog.btlg.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    ProjectRepository rep = new ProjectRepository();

    public Project create(Project project){
        try {
            return rep.save(project);
        }
        catch (PersistenceException pEx) {
            throw ServiceException.cannotCreateEntity(project, pEx);
        } catch (Throwable t) {
            throw ServiceException.cannotCreateEntityForUndeterminedReason(project, t);
        }
    }


    public Optional<Project> getById(long ID){
        return rep.findProjectById(ID);
    }

    public List<Project> getAll(){
        return rep.findAll();
    }

    public long delete(long ID){
        return rep.deleteProject(ID);
    }

}

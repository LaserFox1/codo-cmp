package com.ind.codocmp.service;

import com.ind.codocmp.domain.Project;
import com.ind.codocmp.exceptions.ServiceException;
import com.ind.codocmp.persistence.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository rep = new ProjectRepository();

    public Project create(Project project){
        try {
            System.out.println("gamingMoment");
            return rep.save(project);
        }
        catch (PersistenceException pEx) {
            throw ServiceException.cannotCreateEntity(project, pEx);
        } catch (Throwable t) {
            throw ServiceException.cannotCreateEntityForUndeterminedReason(project, t);
        }
    }


    public Optional<Project> getById(String ID){
        return rep.findProjectById(ID);
    }

    public List<Project> getAll(){
        return rep.findAll();
    }

    public long delete(String ID){
        return rep.deleteProject(ID);
    }

    public void deleteAll(){
        getAll().forEach(p -> rep.deleteProject(p.getProjectID()));
    }

}

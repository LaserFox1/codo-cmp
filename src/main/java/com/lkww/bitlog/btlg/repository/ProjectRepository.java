package com.lkww.bitlog.btlg.repository;

import com.lkww.bitlog.btlg.domain.Project;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository {
    public static final String HASH_KEY = "Project";
    private RedisTemplate template;
    public Project save(Project project){
        template.opsForHash().put(HASH_KEY, project.getID(), project);
        return project;
    }
    public Optional<Project> findProjectById(long id){
        return Optional.of((Project)template.opsForHash().get(HASH_KEY, id));
    }
    public List<Project> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }
    public long deleteProject(long id){
        return template.opsForHash().delete(HASH_KEY, id);
    }
}

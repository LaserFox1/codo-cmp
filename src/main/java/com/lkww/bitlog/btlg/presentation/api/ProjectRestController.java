package com.lkww.bitlog.btlg.presentation.api;

import com.lkww.bitlog.btlg.domain.Project;
import com.lkww.bitlog.btlg.service.ProjectService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ProjectRestController.BASE_URL)
public class ProjectRestController {

    public static final String BASE_URL = "api/project";
    private static final String PATH_ID = "/{id}";
    public static final String BASE_ID = BASE_URL + PATH_ID;
    private static final String PATH_INDEX = "/";
    private static final String PATH_INDEX2 = "";
    private final ProjectService service = new ProjectService();


    @GetMapping(PATH_ID)
    public HttpEntity<String> getProject(@PathVariable long id) {
        var result = service.getById(id);
        return result
                .<HttpEntity<String>>map(project ->
                        ResponseEntity.ok(project.JSONize().toJSONString()))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping({PATH_INDEX, PATH_INDEX2})
    public HttpEntity<List<String>> getAll() {
        var result = service.getAll()
                .stream()
                .map(Project::JSONize)
                .map(String::valueOf)
                .collect(Collectors.toList());
        return result.size() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }
}

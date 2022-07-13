package com.lkww.bitlog.btlg.presentation.api;

import com.lkww.bitlog.btlg.service.CoDoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(CoDoRestController.BASE_URL)
public class CoDoRestController {

    public static final String BASE_URL = "/features";
    private static final String PATH_ID = "/{id}";
    public static final String BASE_ID = BASE_URL + PATH_ID;
    private static final String PATH_INDEX = "/";
    private static final String PATH_INDEX2 = "";
    CoDoService service = new CoDoService();

    @GetMapping({PATH_INDEX, PATH_INDEX2})
    public HttpEntity<List<String>> getAllFeatureFiles() {
        List<String> result = service.findAllFeatureFiles();

        return result.size() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping(PATH_ID)
    public HttpEntity<String> getFeatureFile(@PathVariable String id) {
        String result = service.findFeatureFile(id);
        return result.length() > 0 ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    @PostMapping(PATH_ID)
    public HttpEntity<String> postJSONObj(@PathVariable String id) {
        return ResponseEntity.ok(service.sendJSONObj(id));
    }
}

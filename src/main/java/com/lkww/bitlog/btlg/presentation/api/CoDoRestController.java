package com.lkww.bitlog.btlg.presentation.api;

import com.lkww.bitlog.btlg.service.CoDoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping()
public class CoDoRestController {

    public static  String BASE_URL;
    public String PATH_ID;

    CoDoService service = new CoDoService();

    @GetMapping()
    public List<ResponseEntity<File>> getFeatureFiles(@PathVariable String ID){
        return service.getAllFeatureFiles(ID)
                .stream()
                .map(ResponseEntity::ok)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<String> postJSONObj(String obj){
        return ResponseEntity.ok(service.sendJSONObj(obj));
    }
}

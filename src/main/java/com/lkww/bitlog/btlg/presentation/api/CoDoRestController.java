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
@RequestMapping("/api")
public class CoDoRestController {

    CoDoService service = new CoDoService();

    @GetMapping("/{ID}")
    public List<ResponseEntity<File>> getFeatureFiles(@PathVariable String ID){
        return service.getAllFeatureFiles(ID)
                .stream()
                .map(ResponseEntity::ok)
                .collect(Collectors.toList());
    }

    @PostMapping("/{ID}")
    public ResponseEntity<String> postJSONObj(@PathVariable String ID){
        return ResponseEntity.ok(service.sendJSONObj(ID));
    }
}

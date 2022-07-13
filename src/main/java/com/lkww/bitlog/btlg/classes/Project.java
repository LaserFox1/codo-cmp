package com.lkww.bitlog.btlg.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    String projectID;
    List<Feature> features;

    public JSONObject JSONize(){
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        obj.put("Project", projectID);
        for(Feature feature : features){
            arr.add(feature.JSONize());
        }
        obj.put("Features", arr);
        return obj;
    }
}

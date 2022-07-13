package com.lkww.bitlog.btlg.domain.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {

    String featureName;
    String description;
    List<Scenario> scenarios;

    public JSONObject JSONize(){
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        obj.put("Feature", featureName);
        obj.put("Description", description);
        for(Scenario scenario : scenarios){
            arr.add(scenario.JSONize());
        }
        obj.put("Scenarios", arr);
        return obj;
    }
}

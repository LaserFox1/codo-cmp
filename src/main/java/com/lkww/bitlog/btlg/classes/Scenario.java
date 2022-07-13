package com.lkww.bitlog.btlg.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {

    String scenarioName;
    String given;
    String when;
    String then;

    public JSONObject JSONize(){
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        obj.put("Scenario", scenarioName);

        JSONObject givenObj = new JSONObject();
        givenObj.put("Given", given);
        arr.add(givenObj);

        JSONObject whenObj = new JSONObject();
        whenObj.put("When", when);
        arr.add(whenObj);

        JSONObject thenObj = new JSONObject();
        thenObj.put("Then", then);
        arr.add(thenObj);

        obj.put("Syntax", arr);
        return obj;
    }
}

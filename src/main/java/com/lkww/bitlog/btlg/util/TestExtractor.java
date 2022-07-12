package com.lkww.bitlog.btlg.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.List;


public class TestExtractor {

    public static JSONObject extract(List<File> files){
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        //obj.put("Project", project.getId());
        //obj.put("Version", project.getVersion());
        for (File f : files) {
            arr.add(ObjectBuilder.buildFeature(f).JSONize());
        }
        obj.put("Features", arr);
        return  obj;
    }
}

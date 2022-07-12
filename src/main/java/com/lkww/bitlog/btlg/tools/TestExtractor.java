package com.lkww.bitlog.btlg.tools;

import com.lkww.bitlog.btlg.tools.ObjectBuilder;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.model.fileset.FileSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.lkww.bitlog.btlg.domain.Feature;

import java.io.File;
import java.util.List;

import static com.lkww.bitlog.btlg.tools.FileHandler.getIncludedFiles;

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

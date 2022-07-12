package com.lkww.bitlog.btlg.service;

import com.lkww.bitlog.btlg.tools.ObjectBuilder;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.model.fileset.FileSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.lkww.bitlog.btlg.domain.Feature;
import static com.lkww.bitlog.btlg.tools.FileHandler.getIncludedFiles;

public class TestExtractor {

    public static JSONObject extract(MavenProject project, FileSet fileset){
        String[] includedFiles = getIncludedFiles(fileset);

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        obj.put("Project", project.getId());
        obj.put("Version", project.getVersion());
        for (String s : includedFiles) {
            arr.add(ObjectBuilder.buildFeature(s).JSONize());
        }
        obj.put("Features", arr);
        return  obj;
    }
}

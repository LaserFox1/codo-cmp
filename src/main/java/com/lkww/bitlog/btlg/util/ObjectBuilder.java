package com.lkww.bitlog.btlg.util;


import com.lkww.bitlog.btlg.domain.classes.Feature;
import com.lkww.bitlog.btlg.domain.Project;
import com.lkww.bitlog.btlg.domain.classes.Scenario;
import com.lkww.bitlog.btlg.exceptions.CrawlerException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.lkww.bitlog.btlg.util.InputHandler.*;

public class ObjectBuilder {

    public static List<Project> builder(InputStream stream) {

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        boolean isDescription = false;


        List<Scenario> scenarios = new ArrayList<>();
        List<Feature> features = new ArrayList<>();
        List<Project> projects = new ArrayList<>();

        try {
            Scenario scenario = null;
            Feature feature = null;
            Project project = null;

            StringBuilder description = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty() || line.isBlank())
                    continue;

                List<String> tokens = tokenize(line);
                String keyword = tokens.get(0);


                switch (keyword) {
                    case "Project:":
                        project = new Project();
                        project.setProjectID(concat(line, "Project:"));
                        break;
                    case "Feature:":
                        feature = new Feature();
                        feature.setFeatureName(concat(line, "Feature:"));
                        isDescription = true;
                        break;
                    case "Scenario:":
                        isDescription = false;
                        scenario = new Scenario();
                        scenario.setScenarioName(concat(line, "Scenario"));
                        break;

                    case "Given":
                        assert scenario != null;
                        scenario.setGiven(concat(line));
                        break;
                    case "When":
                        assert scenario != null;
                        scenario.setWhen(concat(line));
                        break;
                    case "Then":
                        assert scenario != null;
                        scenario.setThen(concat(line));
                        break;


                    case "ProjectDone":
                        assert project != null;
                        project.setFeatures(features);
                        projects.add(project);
                        break;
                    case "FeatureDone":
                        assert feature != null;
                        feature.setScenarios(scenarios);
                        feature.setDescription(description.toString());
                        features.add(feature);
                        break;
                    case "ScenarioDone":
                        scenarios.add(scenario);
                        break;

                }

                if (!keyword.equals("Feature:") && isDescription) {
                    description.append(concat(line));
                }
            }
            in.close();
        } catch (IOException ioE) {
            throw CrawlerException.readIO(ioE);
        }
        catch (Throwable t){
            throw CrawlerException.readUndetermined(t);
        }

        return projects;
    }
}

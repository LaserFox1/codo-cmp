package com.lkww.bitlog.btlg.util;


import com.lkww.bitlog.btlg.classes.Feature;
import com.lkww.bitlog.btlg.classes.Scenario;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.lkww.bitlog.btlg.util.InputHandler.*;

public class ObjectBuilder {

    private static Scenario buildScenario(List<String> arr) {
        return new Scenario(
                concat(arr.get(0), "Scenario:"),
                concat(arr.get(1)),
                concat(arr.get(2)),
                concat(arr.get(3))
        );
    }

    public static Feature buildFeature(File f) {

        List<String> arr = InputHandler.read(f);

        String featureName = "";
        StringBuilder description = new StringBuilder();
        boolean isDescription = false;
        List<Scenario> scenarios = new ArrayList<>();

        for (String line : arr) {
            List<String> tokens = tokenize(line);
            if (line.isEmpty() || line.isBlank())
                continue;

            String keyword = tokens.get(0);
            if (keyword.equals("Given") || keyword.equals("When") || keyword.equals("Then"))
                continue;

            if (keyword.equals("Feature:")) {
                featureName = concat(line, "Feature:");
                isDescription = true;
            } else if (keyword.equals("Scenario:")) {
                scenarios.add(buildScenario(arr.subList(arr.indexOf(line), arr.indexOf(line) + 4)));
                isDescription = false;
            }

            if (arr.indexOf(line) > 0 && isDescription) {
                description.append(concat(line));
            }
        }
        return new Feature(featureName, description.toString(), scenarios);
    }
}

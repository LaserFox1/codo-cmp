package com.lkww.bitlog.btlg.tools;


import com.lkww.bitlog.btlg.domain.Feature;
import com.lkww.bitlog.btlg.domain.Scenario;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.lkww.bitlog.btlg.tools.InputHandler.*;

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

            switch (keyword) {
                case "Feature:" -> {
                    featureName = concat(line, "Feature:");
                    isDescription = true;
                }
                case "Scenario:" -> {
                    scenarios.add(buildScenario(arr.subList(arr.indexOf(line), arr.indexOf(line) + 4)));
                    isDescription = false;
                }
            }
            if (arr.indexOf(line) > 0 && isDescription) {
                description.append(concat(line));
            }
        }
        return new Feature(featureName, description.toString(), scenarios);
    }
}

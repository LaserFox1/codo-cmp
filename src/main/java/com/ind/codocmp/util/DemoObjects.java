package com.ind.codocmp.util;

import com.ind.codocmp.domain.weak.Feature;
import com.ind.codocmp.domain.weak.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoObjects {

    public static List<Feature> demoFeatureList() {
        Scenario[] scenarios1 = {
                Scenario.builder()
                        .scenarioName("<SubKrank1-1>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank1-2>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank1-3>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build()
        };
        Scenario[] scenarios2 = {
                Scenario.builder()
                        .scenarioName("<SubKrank2-1>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank2-2>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank2-3>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build()
        };
        Scenario[] scenarios3 = {
                Scenario.builder()
                        .scenarioName("<SubKrank3-1>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank3-2>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build(),
                Scenario.builder()
                        .scenarioName("<SubKrank3-3>")
                        .given("<statement>")
                        .when("<statement>")
                        .then("<statement>")
                        .build()
        };

        Feature[] features = {
                Feature.builder()
                        .featureName("TopKrank1")
                        .description("Sadly a demo feature for now...")
                        .scenarios(Arrays.asList(scenarios1))
                        .build(),
                Feature.builder()
                        .featureName("TopKrank2")
                        .description("Sadly a demo feature for now...")
                        .scenarios(Arrays.asList(scenarios2))
                        .build(),
                Feature.builder()
                        .featureName("TopKrank3")
                        .description("Sadly a demo feature for now...")
                        .scenarios(Arrays.asList(scenarios3))
                        .build()
        };
        return Arrays.asList(features);
    }
}

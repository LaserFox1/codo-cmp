package com.lkww.bitlog.btlg.util;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputHandler {
    public static List<String> tokenize(String s) {
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        List<String> arr = new ArrayList<>();
        while(stringTokenizer.hasMoreTokens()){
            arr.add(stringTokenizer.nextToken());
        }
        return arr;
    }

    public static String concat(String input, String excluded) {
        StringBuilder s = new StringBuilder();

        List<String> arr = tokenize(input);
        for (String s1 : arr) {
            if(!s1.equals(excluded))
                s.append(s1).append(" ");
        }
        return s.toString();
    }
    public static String concat(String input) {
        StringBuilder s = new StringBuilder();

        List<String> arr = tokenize(input);
        for (String s1 : arr) {
            s.append(s1).append(" ");
        }
        return s.toString();
    }

}
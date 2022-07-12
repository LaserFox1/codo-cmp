package com.lkww.bitlog.btlg.tools;

import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;

import java.util.Arrays;

public class FileHandler {
    private static FileSet defaultSet(){
        FileSet fs = new FileSet();
        fs.setDirectory("src/test/resources/features");
        fs.setIncludes(Arrays.stream(new String[]{"*.feature"}).toList());
        return  fs;
    }

    public static String[] getIncludedFiles(FileSet fileset){
        FileSetManager fileSetManager = new FileSetManager();
        return fileSetManager.getIncludedFiles(fileset != null ? fileset : defaultSet());
    }

}

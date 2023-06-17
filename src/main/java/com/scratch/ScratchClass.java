package com.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScratchClass {
    public static void main(String[] args){
        System.out.println(
            Folder.createDefaultFolderList().stream()
                .map(Folder::getName)
                .collect(Collectors.joining("/"))
        );
    }
}

class Folder {
    private String id;
    private String name;

    public Folder(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static ArrayList<Folder> createDefaultFolderList(){
        ArrayList<Folder> folders = new ArrayList<Folder>();
        folders.addAll(
            List.of(
                new Folder("111", "FOLDER_ONE"),
                new Folder("222", "FOLDER_TWO")));
        return folders;
    }
}
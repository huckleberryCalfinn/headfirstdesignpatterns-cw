package com.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FolderNameJoinerClass {
    public static void main(String[] args){
        System.out.println(
            Folder0.createDefaultFolderList()
                .stream()
                .map(Folder0::getName)
                .collect(Collectors.joining(", "))
        );
    }
}


class Folder0 {
    private String name;
    private Integer id;
    public Folder0(String name, Integer id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public Integer getId(){
        return this.id;
    }

    public static List<Folder0> createDefaultFolderList(){
        ArrayList<Folder0> folders = new ArrayList<Folder0>();
        folders.addAll(
            List.of(
                new Folder0("FOLDER_A", 0),
                new Folder0("FOLDER_B", 1)
            )
        );
        return folders;
    }
}

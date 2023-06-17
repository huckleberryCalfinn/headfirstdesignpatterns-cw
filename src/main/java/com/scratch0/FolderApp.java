package com.scratch0;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FolderApp {
    public static void main(String[] args){
        for (Folder folder : Folder.createDefaultFolders()){
            System.out.println(folder);
        }
        System.out.println(
            Folder.createDefaultFolders().stream().map(folder -> folder.getName()).collect(
                Collectors.joining("/")
            )
        );
    }
}

class Folder {
    private final Integer id;
    private final String name;

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Folder(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public static FolderBuilder builder(){
        return new FolderBuilder();
    }

    public static List<Folder> createDefaultFolders(){
        ArrayList<Folder> folders = new ArrayList<Folder>();
        folders.addAll(List.of(
            new Folder.FolderBuilder().id(0).name("FOLDER_0").build(),
            new Folder.FolderBuilder().id(1).name("FOLDER_1").build(),
            new Folder.FolderBuilder().id(2).name("FOLDER_2").build()
            ));
        return folders;
    }

    static class FolderBuilder {
        private Integer id;
        private String name;

        public FolderBuilder(){}

        public FolderBuilder id(Integer folderId){
            this.id = folderId;
            return this;
        }
        public FolderBuilder name(String folderName){
            this.name = folderName;
            return this;
        }
        public Folder build(){
            return new Folder(this.id, this.name);
        }
    }

    public String toString(){
        return "Folder(id=" + this.id + ",name=" + this.name + ")";
    }
}

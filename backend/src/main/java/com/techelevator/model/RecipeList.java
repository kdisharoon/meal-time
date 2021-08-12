package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {
    // class is created to allow recipes to be added to an array of objects
    // list is used instead of array to allow for resizing as the amount of recipes per day/meal is unknown

    private List<Long> rIds;
    private List<String> rNames;

    public RecipeList(){
        rIds= new ArrayList<>();
        rNames= new ArrayList<>();
    }

    public void addToList(long id){
        rIds.add(id);

    }

    public long[] getArray(){
        Long[] s = rIds.toArray(new Long[0]);
        long[] a = new long[s.length];
        for (int i = 0; i< s.length; i++){
            a[i]= s[i];
        }
        return a;
    }

    public void addToList(String name){
        rNames.add(name);
    }

    public String[] getStringArray(){
        String[] s = rNames.toArray(new String[0]);
        return s;

    }


}

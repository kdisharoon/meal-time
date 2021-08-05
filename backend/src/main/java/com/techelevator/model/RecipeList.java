package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {

    private List<Long> rIds;

    public RecipeList(){
        rIds= new ArrayList<>();
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
}

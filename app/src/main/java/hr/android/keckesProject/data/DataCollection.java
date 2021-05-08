package hr.android.keckesProject.data;

import java.util.ArrayList;
import java.util.Arrays;

import hr.android.keckesProject.R;

public class DataCollection {

    private final ArrayList<Data> collection = new ArrayList<>(Arrays.asList(
            new Data("C++",         "Ovo je neki description za C++.",      R.drawable.cpp,         "C%2B%2B"),
            new Data("Java",        "Ovo je neki description za Javu.",     R.drawable.java,        "Java_(programming_language)"),
            new Data("Python",      "Ovo je neki description za Python.",   R.drawable.python,      "Python_(programming_language)"),
            new Data("JavaScript",  "Ovo je neki description za JS.",       R.drawable.javascript,  "JavaScript"),
            new Data("Kotlin",      "Ovo je neki description za Kotlin.",   R.drawable.kotlin,      "Kotlin_(programming_language)"),
            new Data("Ruby",        "Ovo je neki description za Ruby",      R.drawable.ruby,        "Ruby_(programming_language)"),
            new Data("C#",          "Ovo je neki description za C#.",       R.drawable.c_sharp,     "C_Sharp_(programming_language)"),
            new Data("Go",          "Ovo je neki description za Go.",       R.drawable.go,          "Go_(programming_language)"),
            new Data("Swift",       "Ovo je neki description za Swift",     R.drawable.swift,       "Swift_(programming_language)")
    ));

    public ArrayList<Data> getCollection() {
        return collection;
    }

    public void addData(Data data) {
        collection.add(data);
    }

    public int getItemCount(){ return collection.size(); }
}

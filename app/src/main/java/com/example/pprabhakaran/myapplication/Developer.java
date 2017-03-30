package com.example.pprabhakaran.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pprabhakaran on 3/30/17.
 */

public class Developer {

    private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.languages = new HashSet<>();
        this.name = name;
    }

    public void add(String language) {
        this.languages.add(language);
    }

    public Set<String> getLanguages() {
        return languages;
    }
}

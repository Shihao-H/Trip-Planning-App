package com.tripco.t03.server;

public class Optimization {

    public String label;
    public String description;

    /**
     * Default constructor.
     */
    public Optimization() {
        label = null;
        description = null;
    }

    /**
     * Constructor.
     * @param lab String.
     * @param desc String.
     */
    public Optimization(String lab, String desc) {
        this.label = lab;
        this.description = desc;
    }
}
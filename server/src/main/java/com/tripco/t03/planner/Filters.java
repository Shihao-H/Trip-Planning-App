package com.tripco.t03.planner;

/**
 * The Filters class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Filters {

    public String name;
    public String[] values;

    /**
     * This is a default constructor.
     */
    public Filters() {
        name = null;
        values = null;
    }

    /**
     * This is a constructor.
     */
    public Filters(String name, String[] values) {
        this.name = name;
        this.values = values;
    }
}

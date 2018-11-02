package com.tripco.t03.planner;

/**
 * The Filters class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Filter {

    public String name;
    public String[] values;

    /**
     * This is a default constructor.
     */
    public Filter() {
        name = null;
        values = null;
    }

    /**
     * This is a constructor.
     * @param name String.
     * @param values String array.
     */
    public Filter(String name, String[] values) {
        this.name = name;
        this.values = values;
    }
}

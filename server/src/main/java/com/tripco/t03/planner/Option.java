package com.tripco.t03.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

    public String units;
    public String unitName;
    public Double unitRadius;
    public String optimization;
    public String map;

    /**
     * Default constructor.
     */
    Option() {
        this.units = null;
        this.unitName = null;
        this.unitRadius = null;
        this.optimization = "none";
        this.map = "svg";
    }

    /**
     * Constructor for Options that are not user defined.
     * @param units String unit type.
     */
    Option(String units) {
        this.units = units;
        this.unitName = null;
        this.unitRadius = null;
        this.optimization = "none";
        this.map = "svg";
    }

    /**
     * Constructor for basic unit with optimization.
     * @param units        String unit type.
     * @param optimization String optimization type.
     */
    Option(String units, String optimization) {
        this.units = units;
        this.optimization = optimization;
        this.unitName = null;
        this.unitRadius = null;
        this.map = "svg";
    }

    /**
     * Constructor for basic unit with optimization.
     *
     * @param units        String unit type.
     * @param optimization String optimization type.
     * @param map          String map type.
     */
    Option(String units, String optimization, String map) {
        this.units = units;
        this.optimization = optimization;
        this.map = map;
    }

    /**
     * Constructor for user defined units.
     * @param units      String unit type.
     * @param unitName   String name of user defined units.
     * @param unitRadius double radius of Earth in user defined units.
     */
    Option(String units, String unitName, Double unitRadius) {
        this.units = units;
        this.unitName = unitName;
        this.unitRadius = unitRadius;
        this.optimization = "none";
        this.map = "svg";
    }

    /**
     * Constructor for user defined units and optimization.
     * @param units      String unit type.
     * @param unitName   String name of user defined units.
     * @param unitRadius double radius of Earth in user defined units.
     */
    Option(String units, String unitName, Double unitRadius, String optimization) {
        this.units = units;
        this.unitName = unitName;
        this.unitRadius = unitRadius;
        this.optimization = optimization;
        this.map = "svg";
    }


    /**
     * Constructor for user defined units, optimization, map.
     * @param units        String unit type.
     * @param unitName     String name of user defined units.
     * @param unitRadius   double radius of Earth in user defined units.
     * @param optimization String optimization type.
     * @param map          String map type.
     */
    Option(String units, String unitName, Double unitRadius, String optimization, String map) {
        this.units = units;
        this.unitName = unitName;
        this.unitRadius = unitRadius;
        this.optimization = optimization;
        this.map = map;
    }

    /**
     * Compares one Option object to another Option object.
     * @param option Option object.
     * @return boolean true is equal, false if not equal.
     */
    boolean equals(Option option) {
        if ((this.unitName != null) && (this.unitRadius != 0.0)) {
            return this.unitName.equalsIgnoreCase(option.unitName) 
                   && this.unitRadius.equals(option.unitRadius) 
                   && this.units.equalsIgnoreCase(option.units);
        } else {
            return this.units.equalsIgnoreCase(option.units);
        }
    }

    /**
     * Print a formatted Option object.
     * @return Option object in string form.
     */
    public String toString() {
        String out = String.format("Option: units: %s", units);
        if (unitName != null) {
            out += String.format(", Unit name: %s, Unit Radius of Earth: %s\n",
                    this.unitName, this.unitRadius);
        }
        return out;
    }
}


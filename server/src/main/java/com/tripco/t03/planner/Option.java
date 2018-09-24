package com.tripco.t03.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

  public String units;
  public String unitName;
  public String unitRadius;

  /**
   * Default constructor.
   */
  public Option(){
    units = null;
    unitName = null;
  }

  /**
   * @param units String unit type
   * Constructor for Options that are not user defined.
   */
  public Option(String units){
    this.units = units;
    this.unitName = null;
    this.unitRadius = "";

  }

  /**
   * @param units String unit type.
   * @param unitName String name of user defined units.
   * @param unitRadius double radius of Earth in user defined units.
   * Constructor for user defined units.
   */
  public Option(String units, String unitName, String unitRadius){
    this.units  = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
  }

  /**
   * @param option Option object.
   * @return boolean true is equal, false if not equal.
   * Compares one Option object to another Option object.
   */
  public boolean equals(Option option){
      if((this.unitName != null) && (!this.unitRadius.equals(""))) {
          System.out.format("unitName: %b, unitRad: %b, units: %b\n", this.unitName.equalsIgnoreCase(option.unitName), this.unitRadius.equals(option.unitRadius), this.units.equalsIgnoreCase(option.units));
          return this.unitName.equalsIgnoreCase(option.unitName) && this.unitRadius.equals(option.unitRadius) && this.units.equalsIgnoreCase(option.units);
      }else{
          return this.units.equalsIgnoreCase(option.units);
      }
  }

  /**
   * @return Option object in string form.
   */
  public String toString(){
    String out = String.format("Option: units: %s", units);
    if(unitName != null){
      out += String.format(", Unit name: %s, Unit Radius of Earth: %s\n", this.unitName, this.unitRadius);
    }
    return out;
  }
}

package com.tripco.t03.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

  public String units;
  public String unitName;
  public double unitRadius;

  public Option(){
    units = null;
    unitName = null;
  }

  public Option(String units){
    this.units = units;
  }

  public Option(String units, String unitName, double unitRadius){
    this.units  = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
  }

  public String toString(){
    String out = String.format("Option: units: %s", units);
    if(unitName != null){
      out += String.format(", Unit name: %s, Unit Radius of Earth: %f\n", this.unitName, this.unitRadius);
    }
    return out;
  }
}

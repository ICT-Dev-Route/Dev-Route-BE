package com.teamdevroute.devroute.video.enums;

public enum TechnologyStackName {

    htmlcss,python,java,javascript,spring,nodejs,react,Vuejs,Angular,
    android,ios,
    DATA_SCIENCE,
    ARTIFICIAL_INTELLIGENCE;

    // Custom method to get the string in the desired format
    public String toLowerCaseHyphen() {
        return this.name().toLowerCase().replace('_', '-');
    }

    // Static method to convert a string back to the enum
    public static TechnologyStackName fromString(String value) {
        return TechnologyStackName.valueOf(value.toUpperCase().replace('-', '_'));
    }


}

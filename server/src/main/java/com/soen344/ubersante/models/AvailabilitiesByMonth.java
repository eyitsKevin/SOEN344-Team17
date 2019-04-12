package com.soen344.ubersante.models;

import java.util.List;

public class AvailabilitiesByMonth {

    public boolean hasChanged = true;
    public List<Availability> listAvail;

    public AvailabilitiesByMonth(){
        this.hasChanged = true;
        this.listAvail = null;
    }

}
package com.soen344.ubersante.models;

import java.util.HashMap;
import java.util.Map;

public class ClinicAvailabilities {

   // public AvailabilitiesByMonth[] annual = new AvailabilitiesByMonth[12];
   // public AvailabilitiesByMonth[] walkin = new AvailabilitiesByMonth[12];
    public Map<Integer, AvailabilitiesByMonth> annual = new HashMap<Integer, AvailabilitiesByMonth>();
    public Map<Integer, AvailabilitiesByMonth> walkin = new HashMap<Integer, AvailabilitiesByMonth>();
    public ClinicAvailabilities(){
        for(int i = 1; i<13; i++){
            annual.put(i, new AvailabilitiesByMonth());
            walkin.put(i, new AvailabilitiesByMonth());
        }
    }
    
}
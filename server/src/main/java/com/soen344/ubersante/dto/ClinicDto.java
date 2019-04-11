package com.soen344.ubersante.dto;

import com.soen344.ubersante.models.Clinic;

import java.util.List;

public class ClinicDto {

    private Clinic clinic;

    private List<DoctorDetails> doctorDetailsList;

    private List<NurseDetails> nurseDetails;

    public ClinicDto(Clinic clinic, List<DoctorDetails> doctorDetailsList, List<NurseDetails> nurseDetails) {
        this.clinic = clinic;
        this.doctorDetailsList = doctorDetailsList;
        this.nurseDetails = nurseDetails;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<DoctorDetails> getDoctorDetailsList() {
        return doctorDetailsList;
    }

    public void setDoctorDetailsList(List<DoctorDetails> doctorDetailsList) {
        this.doctorDetailsList = doctorDetailsList;
    }

    public List<NurseDetails> getNurseDetails() {
        return nurseDetails;
    }

    public void setNurseDetails(List<NurseDetails> nurseDetails) {
        this.nurseDetails = nurseDetails;
    }


}

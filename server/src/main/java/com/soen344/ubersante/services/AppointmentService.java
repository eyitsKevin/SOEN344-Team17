package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.AppointmentDetails;
import com.soen344.ubersante.dto.PatientDetails;
import com.soen344.ubersante.models.Appointment;
import com.soen344.ubersante.models.Patient;
import com.soen344.ubersante.repositories.AppointmentRepository;
import com.soen344.ubersante.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;



    public List<Appointment> findAppointmentForPatient(PatientDetails patientDetails) {
        Patient patient = patientRepository.findByHealthCard(patientDetails.getHealthCard());
        return appointmentRepository.findAppointmentByPatientId(patient.getId());
    }

    public List<AppointmentDetails> getAppointmentDetails(List<Appointment> appointmentList) {
        List<AppointmentDetails> detailList = new ArrayList<>();
        for (Appointment appointment: appointmentList) {
            AppointmentDetails appointmentDetails = new AppointmentDetails (
                    appointment.getPatient(),
                    appointment.getDoctor(),
                    appointment.getCreatedBy(),
                    appointment.getAppointmentType(),
                    appointment.getDate().toLocalDate().toString(),
                    appointment.getTime().toLocalTime().toString(),
                    appointment.getCreatedAt().toString()
                    );
            detailList.add(appointmentDetails);
        }
        return detailList;
    }
}

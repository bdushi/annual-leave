package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.LeaveTypes;
import de.dlh.lhind.annualleave.repository.LeaveTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypesService {
    private final LeaveTypesRepository leaveTypesRepository;

    @Autowired
    public LeaveTypesService(LeaveTypesRepository leaveTypesRepository) {
        this.leaveTypesRepository = leaveTypesRepository;
    }

    public List<LeaveTypes> findAll() {
        return leaveTypesRepository.findAll();
    }
}

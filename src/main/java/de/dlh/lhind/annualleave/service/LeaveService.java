package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.repository.LeaveRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }
}

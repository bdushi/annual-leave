package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.model.LeaveTypes;
import de.dlh.lhind.annualleave.service.LeaveTypesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/leaveTypes")
public class LeaveTypesController {
    private final LeaveTypesService leaveTypesService;
    public LeaveTypesController(LeaveTypesService leaveTypesService) {
        this.leaveTypesService = leaveTypesService;
    }
    @GetMapping
    ResponseEntity<List<LeaveTypes>> findAll() {
        return new ResponseEntity<>(leaveTypesService.findAll(), HttpStatus.OK);
    }
}

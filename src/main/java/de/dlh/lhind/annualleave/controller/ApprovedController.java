package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.service.ApprovedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/approved")
public class ApprovedController {
    private final ApprovedService approvedService;
    public ApprovedController(ApprovedService approvedService) {
        this.approvedService = approvedService;
    }
    @GetMapping(params = {"id"})
    ResponseEntity<List<Approved>> findAllByLeaveId(@RequestParam("id") long id) {
        return new ResponseEntity<>(approvedService.findAllByLeaveId(id), HttpStatus.OK);
    }
}

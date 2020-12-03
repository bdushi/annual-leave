package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.dto.LeaveDto;
import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.service.LeaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/leave")
public class LeaveController {
    private final LeaveService leaveService;
    public LeaveController(
            LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @GetMapping(params = {"search", "page", "size"})
    ResponseEntity<Page<Leave>> findAll(@RequestParam("search") String search, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new ResponseEntity<>(
                leaveService.findAll(search, PageRequest.of(page, size)), HttpStatus.OK
        );
    }

    @GetMapping("user")
    ResponseEntity<List<Leave>> findByUser(@RequestParam("username") String username) {
        return new ResponseEntity<>(leaveService.findByUser(username), HttpStatus.OK);
    }

    @PostMapping("/request")
    ResponseEntity<Leave> create(@RequestBody LeaveDto leaveDto) {
        return new ResponseEntity<>(
                leaveService.applyForLeave(leaveDto), HttpStatus.CREATED
        );
    }

    @PreAuthorize("haseRole(ADMIN, 'SUPERVISOR')")
    @PostMapping("/approved")
    ResponseEntity<Leave> approved(@RequestBody Leave leave) {
        return new ResponseEntity<>(
                leaveService.approveLeave(leave), HttpStatus.CREATED
        );
    }
}

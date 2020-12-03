package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.service.ApprovedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return new ResponseEntity<>(approvedService.findApprovedId(id), HttpStatus.OK);
    }

    @GetMapping(params = {"id", "search", "page", "size"})
    ResponseEntity<Page<Approved>> findAllByLeaveId(@RequestParam("id") long id, @RequestParam("search") String search, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new ResponseEntity<>(approvedService.approved(id, search, PageRequest.of(page, size)), HttpStatus.OK);
    }
}

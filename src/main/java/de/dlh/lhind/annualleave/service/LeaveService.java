package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.authentication.AuthenticationFacade;
import de.dlh.lhind.annualleave.dto.LeaveDto;
import de.dlh.lhind.annualleave.event.ApprovedLeaveRequestListener;
import de.dlh.lhind.annualleave.event.LeaveListener;
import de.dlh.lhind.annualleave.event.OnApprovedLeaveRequestEvent;
import de.dlh.lhind.annualleave.event.OnLeaveEvent;
import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final UserService userService;
    private final LeaveListener leaveListener;
    private final ApprovedLeaveRequestListener approvedLeaveRequestListener;

    @Autowired
    public LeaveService(
            LeaveRepository leaveRepository,
            UserService userService,
            LeaveListener leaveListener,
            ApprovedLeaveRequestListener approvedLeaveRequestListener) {
        this.leaveRepository = leaveRepository;
        this.userService = userService;
        this.leaveListener = leaveListener;
        this.approvedLeaveRequestListener = approvedLeaveRequestListener;
    }

    public Leave applyForLeave(LeaveDto leaveDto) {
        User user = userService.findByUsername();
        if(ChronoUnit.DAYS.between(user.getEmploymentDate(), ZonedDateTime.now()) < 90) {
            throw new RuntimeException("You must have ate least 90 days employeed");
        } else if(leaveDto.getEndDate().compareTo(leaveDto.getStartDate()) < 1) {
            throw new RuntimeException("Start Date must be grater than End Date");
        } else {
            Leave leave = leaveRepository.save(
                    new Leave(
                            0,
                            ZonedDateTime.now(),
                            leaveDto.getStartDate(),
                            leaveDto.getEndDate(),
                            leaveDto.getDescription(),
                            leaveDto.getComment(),
                            user,
                            leaveDto.getLeaveTypes(),
                            new ArrayList<>()
                    )
            );
            leaveListener.onApplicationEvent(new OnLeaveEvent(leave));
            return leave;
        }
    }
    @Transactional
    public Leave approveLeave(Leave leave) {
        if(leave.getApproved() != null) {
            try {
                Leave mLeave = leaveRepository
                        .findById(leave.getId()).orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Leave with " + leave.getId() + " Not Founded"));
                mLeave.setApproved(leave.getApproved());
                Leave approvedLeave = leaveRepository.save(mLeave);
                approvedLeaveRequestListener.onApplicationEvent(new OnApprovedLeaveRequestEvent(approvedLeave));
                return approvedLeave;
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        } else {
            throw new RuntimeException("You must provide an Approved entity");
        }
    }

    public List<Leave> findAll() {
        return leaveRepository.findAll();
    }

    public Page<Leave> findAll(int size, int page) {
        return leaveRepository.findAll(PageRequest.of(size, page));
    }

    public List<Leave> findByUser(String username) {
        return leaveRepository.findAll((Specification<Leave>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("requestedBy").get("username"), username)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

package basic.booking.controller;

import basic.booking.domain.Reserve;
import basic.booking.domain.Subject;
import basic.booking.domain.User;
import basic.booking.repos.ReserveRepo;
import basic.booking.repos.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class ReservationController {
    @Autowired
    private ReserveRepo reserveRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    private Integer subjectForReserve;


//    @PostMapping("subjectForReserve")
//    public String defineSubjectForReserve(@RequestParam Integer subjectForReserve){
//        this.subjectForReserve=subjectRepo.findByIDSubject(subjectForReserve);
//        return ("/reservation");
//    }

    @GetMapping("/reservation/{IDSubjectForReserve}")
    public String reserveSubject(
            @PathVariable Integer IDSubjectForReserve, Map<String, Integer> model){
//        Reserve reserve=new Reserve(subjectRepo.findByIDSubject(subjectForReserve));
//        reserveRepo.save(reserve);
        model.put("IDSubjectForReserve",IDSubjectForReserve);
        return "reservation";
    }

    @PostMapping("/reservation/**")
    public String submitReserve(
            @RequestParam Date wanteddate,
            @RequestParam Integer IDSubjectForReserve,
            @AuthenticationPrincipal User user,
            Map<String, Object> model){
        Date date;

        Reserve reserve=new Reserve(wanteddate, subjectRepo.findByIDSubject(IDSubjectForReserve), user);
        reserveRepo.save(reserve);

       return "redirect:/";

    }


//    @PostMapping("submit-reserv")
//    public void reserveSubject(
//            @RequestParam Date wanteddate, @RequestParam Integer IDSubjectForReserve, @AuthenticationPrincipal User user, Map<String, Object> model){
//        Reserve reserve=new Reserve(wanteddate, subjectRepo.findByIDSubject(IDSubjectForReserve), user);
//        reserveRepo.save(reserve);
//
//    }


    @GetMapping("/reservation")
    public String addNewObject(){return "reservation";}



}





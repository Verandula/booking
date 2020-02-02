package basic.booking.controller;

import basic.booking.domain.MetroStation;
import basic.booking.domain.Subject;
import basic.booking.domain.User;
import basic.booking.repos.SubjectRepo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ReestrController {
    @Autowired
    private SubjectRepo subjectRepo;


    @GetMapping("/")
    public String mainReestr(Map<String, Object> model){
        Iterable<Subject> subjects =subjectRepo.findAll();
        model.put("subjects", subjects);
        return "main-reestr";
    }

    @GetMapping("/add-new-object")
    public String addNewObject(Map<String, Object> model){return "add-new-object";}


    //    TODO: для проверки - убрать userToControl
    @PostMapping("/add-new-object")
    public void add(
            @AuthenticationPrincipal User userToControl,
            @RequestParam String chooseSubjectName,
            @RequestParam Integer choosePrice,
            @RequestParam String chooseRegion,
            @RequestParam String chooseAddress,
            @RequestParam Integer chooseArea,
            @RequestParam String chooseInfo,
            @RequestParam(required = false) String chooseMedia, Map<String, Object> model){
        Subject subject=new Subject(chooseSubjectName,choosePrice,chooseRegion, chooseAddress, chooseArea, chooseInfo,chooseMedia, userToControl);
        subjectRepo.save(subject);
//
//        Iterable<Subject> subjects =subjectRepo.findAll();
//        model.put("subjects", subjects);
//        return "main-reestr";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model){
        return "login";
    }


//    TODO Фильтры
    @PostMapping("filter")
    public String filter(@RequestParam(required = false) Integer filterPrice, @RequestParam(required = false) String filterRegion,
                         @RequestParam(required = false) Integer filterArea, @RequestParam(required = false) String filterMedia, Map<String, Object> model){
        Iterable<Subject> subjects;

        if(filterPrice!=null & (filterRegion!=null && !filterRegion.isEmpty()) & filterArea!=null & (filterMedia!=null && filterMedia.isEmpty())){
            subjects=subjectRepo.findByPriceEqualsOrRegionEqualsOrAreaEqualsOrMediaEquals(filterPrice, filterRegion, filterArea, filterMedia);
        }else {
            subjects=subjectRepo.findAll();
        }

        model.put("subjects", subjects);
        return mainReestr(model);
    }


}





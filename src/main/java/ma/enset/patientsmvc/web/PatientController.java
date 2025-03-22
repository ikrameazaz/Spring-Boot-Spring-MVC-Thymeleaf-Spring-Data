package ma.enset.patientsmvc.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page",defaultValue = "0") int page ,
                        @RequestParam(name="size",defaultValue = "4") int size ,
                        @RequestParam(name="keyword",defaultValue = "") String key) {
        // List<Patient> patients = patientRepository.findAll();
        // Page<Patient> patients = patientRepository.findAll(PageRequest.of(page,size));
        // Page<Patient> patients = patientRepository.chercher(key,PageRequest.of(page,size));
        Page<Patient> patients = patientRepository.findByNomContains(key,PageRequest.of(page,size));

        model.addAttribute("Listpatients", patients.getContent());//Permet d’envoyer des données à la page HTML (Thymeleaf ).
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",key);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id,int page ,String keyword){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword ;

    }

}

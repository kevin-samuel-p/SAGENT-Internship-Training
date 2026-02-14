package in.edu.eec.easwari.College.Admission.System.controller;

import in.edu.eec.easwari.College.Admission.System.entity.AdmissionApplication;
import in.edu.eec.easwari.College.Admission.System.entity.User;
import in.edu.eec.easwari.College.Admission.System.repository.ApplicationRepository;
import in.edu.eec.easwari.College.Admission.System.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationRepository appRepo;
    private final UserRepository userRepo;
    private final String UPLOAD_DIR = "uploads/";

    // Constructor Injection...
    public ApplicationController(ApplicationRepository appRepo, UserRepository userRepo) {
        this.appRepo = appRepo;
        this.userRepo = userRepo;
    }

    // --- STUDENT ENDPOINTS ---

    @PostMapping("/student/apply")
    public AdmissionApplication apply(
            @RequestParam("name") String name, // redundancy for form, usually stored in User
            @RequestParam("dob") String dob,
            @RequestParam("address") String address,
            @RequestParam("grades") String grades,
            @RequestParam("course") String course,
            @RequestParam("file") MultipartFile file) throws IOException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepo.findByEmail(email).orElseThrow();

        // Save File
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        AdmissionApplication app = new AdmissionApplication();
        app.setStudent(student);
        app.setDob(dob);
        app.setAddress(address);
        app.setGrades(grades);
        app.setCourse(course);
        app.setDocumentPath(fileName);
        app.setStatus("APPLIED");
        app.setFeePaid(false); // Default

        return appRepo.save(app);
    }

    @PostMapping("/student/pay/{appId}")
    public AdmissionApplication payFee(@PathVariable Long appId) {
        AdmissionApplication app = appRepo.findById(appId).orElseThrow();
        app.setFeePaid(true);
        app.setStatus("PAID");
        return appRepo.save(app);
    }
    
    @PostMapping("/student/cancel/{appId}")
    public AdmissionApplication cancel(@PathVariable Long appId) {
        AdmissionApplication app = appRepo.findById(appId).orElseThrow();
        app.setStatus("CANCELLED");
        return appRepo.save(app);
    }

    @GetMapping("/student/my-applications")
    public List<AdmissionApplication> myApps() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepo.findByEmail(email).orElseThrow();
        return appRepo.findByStudent(student);
    }

    // --- OFFICER ENDPOINTS ---

    @GetMapping("/admin/applications")
    public List<AdmissionApplication> getAllApps() {
        return appRepo.findAll();
    }

    @PutMapping("/admin/update-status/{appId}")
    public AdmissionApplication updateStatus(@PathVariable Long appId, @RequestParam String status) {
        AdmissionApplication app = appRepo.findById(appId).orElseThrow();
        app.setStatus(status);
        return appRepo.save(app);
    }
}
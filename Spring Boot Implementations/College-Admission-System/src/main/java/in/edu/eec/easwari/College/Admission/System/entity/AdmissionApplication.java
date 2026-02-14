package in.edu.eec.easwari.College.Admission.System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdmissionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    // Personal Details
    private String dob;
    private String address;

    // Academic Details
    private String grades; // e.g., "90%"
    private String subjects;

    private String course;
    private String documentPath; // Path to uploaded file
    
    // Status: APPLIED, PAID, UNDER_REVIEW, ACCEPTED, REJECTED, CANCELLED
    private String status; 
    private boolean feePaid;
}
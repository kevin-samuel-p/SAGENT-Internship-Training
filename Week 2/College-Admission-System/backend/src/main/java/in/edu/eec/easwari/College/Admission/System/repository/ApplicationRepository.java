package in.edu.eec.easwari.College.Admission.System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.College.Admission.System.entity.AdmissionApplication;
import in.edu.eec.easwari.College.Admission.System.entity.User;

@Repository
public interface ApplicationRepository extends JpaRepository<AdmissionApplication, Long> {
    List<AdmissionApplication> findByStudent(User student);
}
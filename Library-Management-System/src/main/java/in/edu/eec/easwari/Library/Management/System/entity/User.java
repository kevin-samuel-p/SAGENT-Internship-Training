package in.edu.eec.easwari.Library.Management.System.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.edu.eec.easwari.Library.Management.System.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String userEmail;
    private String userPassword;
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Borrowing> borrowings;
}

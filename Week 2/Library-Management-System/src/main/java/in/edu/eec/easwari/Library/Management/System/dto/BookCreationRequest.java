package in.edu.eec.easwari.Library.Management.System.dto;

import lombok.Data;

@Data
public class BookCreationRequest {
    private String title;
    private String author;
    private String subject;
}

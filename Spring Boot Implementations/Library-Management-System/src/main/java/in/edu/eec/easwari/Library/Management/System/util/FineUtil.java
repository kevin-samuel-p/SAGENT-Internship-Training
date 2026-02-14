package in.edu.eec.easwari.Library.Management.System.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import in.edu.eec.easwari.Library.Management.System.entity.Borrowing;
import in.edu.eec.easwari.Library.Management.System.enums.BookStatus;

public class FineUtil {

    private FineUtil() {}   // Prevent instantiation
    
    public static double calculateFine(Borrowing borrowing) {
        double fine = 0.0;

        if (borrowing.getDueDate() != null) {
            LocalDate comparisonDate = (borrowing.getReturnDate() != null) 
                    ? borrowing.getReturnDate()
                    : LocalDate.now();

            if (comparisonDate.isAfter(borrowing.getDueDate())) {
                long daysLate = ChronoUnit.DAYS.between(
                    borrowing.getDueDate(),
                    comparisonDate
                );

                fine += daysLate * 5.0;     // Placeholder rate (for future alterations)
            }
        }

        if (borrowing.getBook().getBookStatus() == BookStatus.DAMAGED) {
            fine += 50.0;   // Placeholder damage fee (for future alterations)
        }

        if (borrowing.getBook().getBookStatus() == BookStatus.LOST) {
            fine += 200.0;  // Placeholder lost fee (for future alterations)
        }

        return fine;
    }
}

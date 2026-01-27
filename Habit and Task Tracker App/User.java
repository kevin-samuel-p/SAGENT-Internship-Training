import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class User {
    private int userId;
    private String name;
    private String email;
    private char gender;
    private String country;
    private int[] date;

    Map<String, Habit> userHabits = new HashMap<>();

    User(int userId, String name, String email, char gender, String country, int[] date) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.date = Arrays.copyOf(date, 3);
    }

    String userProfile() {
        StringBuilder profileDescription = new StringBuilder();
        profileDescription.append(String.format("Name:\t%s\n", this.name));
        profileDescription.append(String.format("E-mail:\t%s\n", this.email));
        profileDescription.append(String.format("Gender:\t%s\n", stringifyGender()));
        profileDescription.append(String.format("Country: %s\n", this.country));
        profileDescription.append(String.format("DOB:\t%s %d, %d\n", stringifyMonth(), date[1], date[2]));
        profileDescription.append(String.format("\nHabits:\n"));

        return profileDescription.toString();
    }

    void createHabit() {

    }

    // Helper function
    private String stringifyGender() {
        switch(this.gender) {
            case 'm':
            case 'M':
                return "Male";
                
            case 'f':
            case 'F':
                return "Female";

            default:
                return "UNKNOWN";
        }
    }

    // Helper function
    private String stringifyMonth() {
        switch(this.date[0]) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "UNKNOWN";
        }
    }
}
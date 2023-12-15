package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterName {
    @NotEmpty(message = "Fullis a mandatory field")
    @Size(min = 3, max = 20, message = "Full Name must be between 3 to 20 characters")
    String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date must be a past date less than today")
    private Date dateOfBirth;

    @Email(message = "Invalid Email Format")
    @Size(max = 30, message = "Email length exceeded 30 characters")
    @NotBlank(message = "Email is a mandatory field")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered")
    private String phoneNo;

    @NotEmpty(message = "Fullis a mandatory field")
    @Size(min = 1, max = 1, message = "Must be m or f")
    String gender;

    @Min(value = 1500, message = "Minimum salary starts from 1500")
    @Max(value = 500000, message = "Maximum salary cannot exceeds 500000")
    private Integer salary;

    @Digits(fraction = 0, integer = 4, message = "Postal code format must be 6 digits")
    @Min(value = 1, message = "Starts from 1")
    @Max(value = 1000, message = "Ends at 1000")
    @NotNull
    private Integer notick;

}

package edu.school21.cinema.models;

import edu.school21.cinema.validation.ValidPassword;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chat_user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "{errors.incorrect.firstName}")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "{errors.incorrect.lastName}")
    private String lastName;

    @NotEmpty(message = "{errors.incorrect.email}")
    @Email
    @Column(name="email")
    private String email;

    @Pattern(regexp="(^$|\\+\\d\\(\\d{3}\\)\\d{7})", message="{errors.incorrect.phone}")
    @NotEmpty(message = "{errors.incorrect.phone}")
    @Column(name="phone_number")
    private String phoneNumber;

    @ValidPassword(message = "{errors.incorrect.password}")
    @Column(name = "password")
    private String password;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;
}

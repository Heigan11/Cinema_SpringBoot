package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

//import static edu.school21.cinema.models.Role.USER;
//import static edu.school21.cinema.models.Status.ACTIVE;


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

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    public User(){
//        this.role = USER;
//        this.status = ACTIVE;
//    }
}

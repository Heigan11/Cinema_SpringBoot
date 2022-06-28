package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seats")
    private int seats;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Session> sessions;
}

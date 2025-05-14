package io.github.abid_hussain36.agora.entities;

import io.github.abid_hussain36.agora.utils.enums.Interest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String fName;

    private String lName;

    @Column(nullable = false)
    private String universityAffiliation;

    @Column(nullable = false)
    private LocalDate dob;

    // Creates a new Join Table user_interests with user_id(foreign key) and interest field
    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_interests", joinColumns = @JoinColumn(name="user_id"))
    @Column(name="interest", nullable = false)
    private List<Interest> interests = new ArrayList<>();

    //private List<User> favoriteUsers;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Event> hostedEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="student_registration",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @Column(nullable = false)
    private List<Event> registeredEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="student_saved",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @Column(nullable = false)
    private List<Event> savedEvents = new ArrayList<>();

    @Transient
    private Integer eventHostCount;

    @Transient
    private Integer eventAttendCount;
}

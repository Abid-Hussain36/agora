package io.github.abid_hussain36.agora.user;

import io.github.abid_hussain36.agora.event.Event;
import io.github.abid_hussain36.agora.utils.Interest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name="interest", nullable = true)
    private List<Interest> interests;

    @ManyToMany
    @JoinTable(
            name="user_favorites",
            joinColumns = @JoinColumn(name="user_id"), // Foreign Key
            inverseJoinColumns = @JoinColumn(name="favorite_user_id") // Columns we want
    )
    private List<User> favoriteUsers;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Event> hostedEvents;

    @ManyToMany
    @JoinTable(
            name="event_attendees",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> registeredEvents;

    @ManyToMany
    @JoinTable(
            name="user_saved",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> savedEvents;

    @Transient
    private int eventHostCount;

    @Transient
    private int registerCount;
}

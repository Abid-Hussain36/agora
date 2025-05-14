package io.github.abid_hussain36.agora.event;

import io.github.abid_hussain36.agora.user.User;
import io.github.abid_hussain36.agora.utils.EventType;
import io.github.abid_hussain36.agora.utils.Interest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="events")
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate start;

    private LocalDate end;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="event_interests", joinColumns = @JoinColumn(name="user.id"))
    @Column(name="interests")
    private List<Interest> interests;

    private String subject;

    @Column(nullable = false)
    private String universityAffiliation;

    private int registrationLimit;

    private String additionalNote;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name="host_id", nullable = false)
    private User host;

    @ManyToMany(mappedBy = "registeredEvents")
    private List<User> attendees;
}

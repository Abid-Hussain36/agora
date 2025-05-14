package io.github.abid_hussain36.agora.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.abid_hussain36.agora.utils.enums.EventType;
import io.github.abid_hussain36.agora.utils.enums.Interest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private LocalDate event_date;

    @Column(nullable = false)
    private LocalTime event_start;

    private LocalTime event_end;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="event_interests", joinColumns = @JoinColumn(name="event_id"))
    @Column(name="interests", nullable = false)
    private List<Interest> interests = new ArrayList<>();

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
    @JsonBackReference
    private User host;

    @ManyToMany(mappedBy = "registeredEvents")
    @JsonBackReference
    private List<User> attendees = new ArrayList<>();

    public Event(String name, LocalDate event_date, LocalTime event_start, LocalTime event_end, EventType type, List<Interest> interests, String subject, String universityAffiliation, int registrationLimit, String additionalNote, String streetAddress, String city, String state, String country, User host, List<User> attendees) {
        this.name = name;
        this.event_date = event_date;
        this.event_start = event_start;
        this.event_end = event_end;
        this.type = type;
        this.interests = interests;
        this.subject = subject;
        this.universityAffiliation = universityAffiliation;
        this.registrationLimit = registrationLimit;
        this.additionalNote = additionalNote;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.host = host;
        this.attendees = attendees;
    }

    public Event() {
        this.name = "name";
        this.event_date = LocalDate.now();
        this.event_start = LocalTime.now();
        this.event_end = LocalTime.now();
        this.type = EventType.ACADEMIC;
        this.interests = new ArrayList<>();
        this.subject = "subject";
        this.universityAffiliation = "universityAffiliation";
        this.registrationLimit = 10;
        this.additionalNote = "additionalNote";
        this.streetAddress = "streetAddress";
        this.city = "city";
        this.state = "state";
        this.country = "country";
        this.host = new User();
        this.attendees = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public LocalTime getEventStart() {
        return event_start;
    }

    public void setEventStart(LocalTime event_start) {
        this.event_start = event_start;
    }

    public LocalTime getEventEnd() {
        return event_end;
    }

    public void setEventEnd(LocalTime event_end) {
        this.event_end = event_end;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUniversityAffiliation() {
        return universityAffiliation;
    }

    public void setUniversityAffiliation(String universityAffiliation) {
        this.universityAffiliation = universityAffiliation;
    }

    public int getRegistrationLimit() {
        return registrationLimit;
    }

    public void setRegistrationLimit(int registrationLimit) {
        this.registrationLimit = registrationLimit;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }
}

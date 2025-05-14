package io.github.abid_hussain36.agora.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.abid_hussain36.agora.utils.enums.Interest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(name="interest", nullable = false)
    private List<Interest> interests = new ArrayList<>();

    //private List<User> favoriteUsers;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Event> hostedEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="student_registration",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonManagedReference
    private List<Event> registeredEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="student_saved",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> savedEvents = new ArrayList<>();

    @Transient
    private Integer eventHostCount;

    @Transient
    private Integer eventAttendCount;

    public User(String username, String password, String email, String fName, String lName, String universityAffiliation, LocalDate dob, List<Interest> interests, List<Event> hostedEvents, List<Event> registeredEvents, List<Event> savedEvents, Integer eventHostCount, Integer eventAttendCount) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.universityAffiliation = universityAffiliation;
        this.dob = dob;
        this.interests = interests;
        this.hostedEvents = hostedEvents;
        this.registeredEvents = registeredEvents;
        this.savedEvents = savedEvents;
        this.eventHostCount = eventHostCount;
        this.eventAttendCount = eventAttendCount;
    }

    public User() {
        this.username = "username";
        this.password = "password";
        this.email = "email";
        this.fName = "fName";
        this.lName = "lName";
        this.universityAffiliation = "universityAffiliation";
        this.dob = LocalDate.now();
        this.interests = new ArrayList<>();
        this.hostedEvents = new ArrayList<>();
        this.registeredEvents = new ArrayList<>();
        this.savedEvents = new ArrayList<>();
        this.eventHostCount = 0;
        this.eventAttendCount = 0;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getUniversityAffiliation() {
        return universityAffiliation;
    }

    public void setUniversityAffiliation(String universityAffiliation) {
        this.universityAffiliation = universityAffiliation;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public List<Event> getHostedEvents() {
        return hostedEvents;
    }

    public void setHostedEvents(List<Event> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }

    public List<Event> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(List<Event> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public List<Event> getSavedEvents() {
        return savedEvents;
    }

    public void setSavedEvents(List<Event> savedEvents) {
        this.savedEvents = savedEvents;
    }

    public Integer getEventHostCount() {
        return eventHostCount;
    }

    public void setEventHostCount(Integer eventHostCount) {
        this.eventHostCount = eventHostCount;
    }

    public Integer getEventAttendCount() {
        return eventAttendCount;
    }

    public void setEventAttendCount(Integer eventAttendCount) {
        this.eventAttendCount = eventAttendCount;
    }
}

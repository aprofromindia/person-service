package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apro on 18-07-2017.
 */

@Entity
@Getter
@NoArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@EqualsAndHashCode(of = "id")
public class Person {

    @Id
    @GeneratedValue
    @Column(insertable = false, updatable = false)
    private long id;

    @NotNull
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "person_device",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private final List<Device> devices = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime appears;

    @Column(nullable = false)
    private LocalDateTime disappears;

    @Range(min = 0, max = 150)
    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex gender;

    public Person(LocalDateTime appears, LocalDateTime disappears, int age, Sex gender) {
        this.appears = appears;
        this.disappears = disappears;
        this.age = age;
        this.gender = gender;
    }

    public void addDevice(@NotNull Device device) {
        devices.add(device);
        device.getPeople().add(this);
    }

//    public void removeDevice(@NotNull Device device) {
//        devices.remove(device);
//        device.getPeople().remove(this);
//    }

    public enum Sex {
        MALE,
        FEMALE
    }
}

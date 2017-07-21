package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private Device device;

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

    public Person(Device device, LocalDateTime appears, LocalDateTime disappears, int age, Sex gender) {
        this.device = device;
        this.appears = appears;
        this.disappears = disappears;
        this.age = age;
        this.gender = gender;
    }

    public enum Sex {
        MALE,
        FEMALE
    }
}

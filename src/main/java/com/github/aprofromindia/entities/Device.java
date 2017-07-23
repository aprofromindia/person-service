package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Apro on 19-07-2017.
 */

@Entity
@NoArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@EqualsAndHashCode(of = "id")
@Getter
public class Device implements Serializable {
    @Id
    @NotNull
    private long id;

    @OneToMany(mappedBy = "device")
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "device")
    private Set<Person> people = new HashSet<>();

    public void addPerson(@NotNull Person person) {
        people.add(person);
    }


    public Device(long id) {
        this.id = id;
    }

    public void addEvent(@NotNull Event event) {
        events.add(event);
        event.setDevice(this);
    }
}

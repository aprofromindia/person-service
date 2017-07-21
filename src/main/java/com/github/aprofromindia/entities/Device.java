package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apro on 19-07-2017.
 */

@Entity
@NoArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@EqualsAndHashCode(of = "id")
public class Device {
    @Id
    private long id;

    @OneToMany(mappedBy = "device")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    private List<Person> people = new ArrayList<>();

    public Device(long id) {
        this.id = id;
    }

    public void addEvent(@NotNull Event event) {
        events.add(event);
        event.setDevice(this);
    }

    public void addPerson(@NotNull Person person){
        people.add(person);
    }
}

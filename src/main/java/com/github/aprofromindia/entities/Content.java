package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
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
public class Content implements Serializable {
    @Id
    private long id;

    @OneToMany(mappedBy = "content")
    private Set<Event> events = new HashSet<>();

    public Content(long id) {
        this.id = id;
    }

    public void addEvent(@NotNull Event event) {
        events.add(event);
        event.setContent(this);
    }
}

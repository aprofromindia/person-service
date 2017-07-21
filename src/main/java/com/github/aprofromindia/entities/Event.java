package com.github.aprofromindia.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Apro on 18-07-2017.
 */

@Entity
@NoArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue
    @Column(insertable = false, updatable = false)
    private long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content content;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL) // TODO: 21-07-2017  
    @JoinColumn(name = "device_id")
    private Device device;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    private LocalDateTime timeStamp;

    public Event(Content content, Device device, Type type, LocalDateTime timeStamp) {
        this.content = content;
        this.device = device;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public enum Type {
        START,
        END
    }
}

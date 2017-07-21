package com.github.aprofromindia.init;

import com.github.aprofromindia.controllers.PeopleController;
import com.github.aprofromindia.entities.Content;
import com.github.aprofromindia.entities.Device;
import com.github.aprofromindia.entities.Event;
import com.github.aprofromindia.entities.Person;
import com.github.aprofromindia.repositories.EventsRepository;
import com.github.aprofromindia.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class CsvLoader {

    private final PersonRepository personRepository;
    private final EventsRepository eventsRepository;
    @Value("classpath:/persons.csv")
    private Resource personRes;
    @Value("classpath:/events.csv")
    private Resource eventRes;

    @EventListener(ContextRefreshedEvent.class)
    public void setupDb() {
        updateDB(personRes, (String[] splits) -> {
            final Device device = new Device(Long.parseLong(splits[0]));
            final LocalDateTime appears = LocalDateTime.parse(splits[1],
                    DateTimeFormatter.ofPattern(PeopleController.DATE_TIME_PATTERN));
            final LocalDateTime disappears = LocalDateTime.parse(splits[2],
                    DateTimeFormatter.ofPattern(PeopleController.DATE_TIME_PATTERN));
            final int age = Integer.parseInt(splits[3]);
            final Person.Sex sex = Person.Sex.valueOf(splits[4].toUpperCase());

            Person person = new Person(appears, disappears, age, sex);
            person.addDevice(device);
            personRepository.save(person);
        });

//        updateDB(eventRes, (String[] splits) -> {
//            final Content content = new Content(Long.parseLong(splits[0]));
//            final Device device = new Device(Long.parseLong(splits[1]));
//            final Event.Type type = Event.Type.valueOf(splits[2].toUpperCase());
//            final LocalDateTime timeStamp = LocalDateTime.parse(splits[3],
//                    DateTimeFormatter.ofPattern(PeopleController.DATE_TIME_PATTERN));
//
//            Event event = new Event(content, device, type, timeStamp);
//            content.addEvent(event);
//            device.addEvent(event);
//            eventsRepository.save(event);
//        });
    }

    private void updateDB(@NotNull Resource resource, @NotNull Consumer<String[]> consumer) {
        try (Stream<String> lines = Files.lines(Paths.get(resource.getURI()))) {
            lines.forEach(line -> consumer.accept(line.split(",")));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}

package com.github.aprofromindia.services;

import com.github.aprofromindia.repositories.PersonRepository;
import com.github.aprofromindia.viewModels.AverageAgeViewModel;
import com.github.aprofromindia.viewModels.GenderDistViewModel;
import com.github.aprofromindia.viewModels.PersonCountViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

@Service
@RequiredArgsConstructor
public class EventReadServiceImpl implements EventReadService {

    private final PersonRepository repository;

    @Override
    public PersonCountViewModel getCountBy(LocalDateTime start, LocalDateTime end, long deviceId, long contentId) {
        final long count = repository.personCountByDeviceAndContent(deviceId, contentId, start, end);
        return new PersonCountViewModel(start, end, deviceId, contentId, count);
    }

    @Override
    public AverageAgeViewModel getAvgAge(LocalDateTime start, LocalDateTime end, long deviceId, long contentId) {
        final float avgAge = repository.avgAgeByDevicesAndContent(deviceId, contentId, start, end);
        return new AverageAgeViewModel(start, end, deviceId, contentId, avgAge);
    }

    @Override
    public GenderDistViewModel getGenderDist(LocalDateTime start, LocalDateTime end, long deviceId, long contentId) {
        final GenderDistViewModel.GenderDist genderDist = null;
        return new GenderDistViewModel(start, end, deviceId, contentId, genderDist);
    }
}

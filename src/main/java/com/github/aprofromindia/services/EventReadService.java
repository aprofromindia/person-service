package com.github.aprofromindia.services;

import com.github.aprofromindia.viewModels.AverageAgeViewModel;
import com.github.aprofromindia.viewModels.GenderDistViewModel;
import com.github.aprofromindia.viewModels.PersonCountViewModel;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

public interface EventReadService {
    PersonCountViewModel getCountBy(LocalDateTime start, LocalDateTime end, long deviceId, long contentId);

    AverageAgeViewModel getAvgAge(LocalDateTime start, LocalDateTime end, long deviceId, long contentId);

    GenderDistViewModel getGenderDist(LocalDateTime start, LocalDateTime end, long deviceId, long contentId);
}

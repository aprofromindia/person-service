package com.github.aprofromindia.viewModels;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

@Getter
public class AverageAgeViewModel extends BaseViewModel {
    private float avgAge;

    public AverageAgeViewModel(LocalDateTime start, LocalDateTime end, long deviceId, long contentId, float avgAge) {
        super(start, end, deviceId, contentId);
        this.avgAge = avgAge;
    }
}

package com.github.aprofromindia.viewModels;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

@Value
@EqualsAndHashCode(callSuper = true)
public class PersonCountViewModel extends BaseViewModel {
    private long views;

    public PersonCountViewModel(LocalDateTime start, LocalDateTime end, long deviceId, long contentId, long views) {
        super(start, end, deviceId, contentId);
        this.views = views;
    }
}

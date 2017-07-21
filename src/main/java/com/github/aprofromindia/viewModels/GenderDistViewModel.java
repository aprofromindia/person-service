package com.github.aprofromindia.viewModels;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

@Getter
public class GenderDistViewModel extends BaseViewModel{
    private GenderDist genderDist;

    public GenderDistViewModel(LocalDateTime start, LocalDateTime end, long deviceId, long contentId, GenderDist genderDist) {
        super(start, end, deviceId, contentId);
        this.genderDist = genderDist;
    }

    @Value
    public static class GenderDist{
        private long male;
        private long female;
    }
}

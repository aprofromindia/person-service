package com.github.aprofromindia.viewModels;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Apro on 19-07-2017.
 */

@Getter
public class GenderDistViewModel extends BaseViewModel {
    private GenderDist genderDist;

    public GenderDistViewModel(LocalDateTime start, LocalDateTime end, long deviceId, long contentId, GenderDist genderDist) {
        super(start, end, deviceId, contentId);
        this.genderDist = genderDist;
    }

    @Getter
    public static class GenderDist {
        private double male;
        private double female;

        public GenderDist(Map<String, Long> map) {
            final double sum = map.get("male") + map.get("female");
            this.male = map.get("male") / sum;
            this.female = map.get("female") / sum;
        }
    }
}

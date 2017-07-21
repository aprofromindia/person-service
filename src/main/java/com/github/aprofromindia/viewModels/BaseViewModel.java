package com.github.aprofromindia.viewModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.aprofromindia.controllers.PeopleController;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by Apro on 19-07-2017.
 */

@Getter
abstract class BaseViewModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PeopleController.DATE_TIME_PATTERN)
    protected LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PeopleController.DATE_TIME_PATTERN)
    protected LocalDateTime end;
    protected long deviceId;
    protected long contentId;

    BaseViewModel(LocalDateTime start, LocalDateTime end, long deviceId, long contentId) {
        this.start = start;
        this.end = end;
        this.deviceId = deviceId;
        this.contentId = contentId;
    }
}

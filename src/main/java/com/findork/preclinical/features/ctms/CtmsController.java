package com.findork.preclinical.features.ctms;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/ctms/subject-tracking")
public class CtmsController {

    @GetMapping("/{subjectTrackingId}")
    public Object get(@PathVariable String subjectTrackingId) {
        return String.valueOf("dsadsa");
    }
}

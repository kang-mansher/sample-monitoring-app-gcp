package com.sabre.travelerprofile.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static com.sabre.travelerprofile.utils.Constants.*;

/**
 * Class for creating metrics for create, update and delete profile requests using micrometer
 */
@Component
public class MetricsHandler {

    private final MeterRegistry meterRegistry;

    public MetricsHandler(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * Method to increment create request count
     */
    public void incrementCreateRequestCount() {
        incrementRequestCount(CREATE_PROFILE_METRIC);
    }

    /**
     * Method to increment update request count
     */
    public void incrementUpdateRequestCount() {
        incrementRequestCount(UPDATE_PROFILE_METRIC);
    }

    /**
     * Method to increment delete request count
     */
    public void incrementDeleteRequestCount() {
        incrementRequestCount(DELETE_PROFILE_METRIC);
    }

    /**
     * Private Method to increment the count of requests
     * @param metricName - name of metric to increment
     */
    private void incrementRequestCount(String metricName) {
        //Generating Random number of request for populating Graph for metric
        IntStream.range(1, (int) (Math.random()*10)).forEach(i -> this.meterRegistry.counter(metricName).increment());
    }

}

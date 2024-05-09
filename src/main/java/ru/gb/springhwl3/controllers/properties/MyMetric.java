package ru.gb.springhwl3.controllers.properties;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MyMetric {

    private final Counter bookCounter;
    private final Counter refusalCounter;
    private boolean flag;

    public MyMetric(MeterRegistry meterRegistry) {
        bookCounter = meterRegistry.counter("custom_book_count");
        refusalCounter = meterRegistry.counter("custom_refusal_count");
        flag = true;
    }

    public void setBookCounter() {
        bookCounter.increment();
    }
    public void setRefusalCounter() {
        refusalCounter.increment();
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 0)
    public void reportBook() {
        if (flag) {
            bookCounter.increment();
            flag = false;
        }
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 0)
    public void report() {
        if (!flag) {
            refusalCounter.increment();
            flag = true;
        }
    }
}

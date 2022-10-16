package me.snowlight.employee.classification;

import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.transaction.TimeCard;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    private Map<Long, TimeCard> timeCards = new HashMap<>();

    public HourlyClassification(float hour) {
    }

    public void AddTimeCard(TimeCard timeCard) {
        this.timeCards.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(Long date) {
        return timeCards.get(date);
    }
}

package me.snowlight.employee.classification;

import me.snowlight.employee.model.PayCheck;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.TimeCard;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    public static final float WORKING_TIME = 8.0f;
    public static final double EXTRA_PAY = 1.5;
    private float hour;
    private Map<LocalDate, TimeCard> timeCards = new HashMap<>();

    public HourlyClassification(float hour) {
        this.hour = hour;
    }

    public void AddTimeCard(TimeCard timeCard) {
        this.timeCards.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(LocalDate date) {
        return timeCards.get(date);
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        double totalPay = 0.0;
        LocalDate payDate = payCheck.getPayDate();

        for (TimeCard t : timeCards.values()) {
            if (IsInPeriod(t, payDate))
                totalPay += calculatePayForTimeCard(t);
        }

        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        float hours = timeCard.getHours();
        double overtime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overtime;
        return straightTime * this.hour + (overtime * this.hour * 1.5f);
    }

    private boolean IsInPeriod(TimeCard timeCar, LocalDate payDate) {
        LocalDate payPeriodEndDate = payDate;
        LocalDate payPeriodStartDate = payDate.minusDays(5);

        return timeCar.getDate().isAfter(payPeriodStartDate.minusDays(1))
                && timeCar.getDate().isBefore(payPeriodEndDate.plusDays(1));
    }
}

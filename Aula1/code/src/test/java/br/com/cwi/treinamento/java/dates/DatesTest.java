package br.com.cwi.treinamento.java.dates;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class DatesTest {

    @Test
    public void comparaIdade(){
        LocalDate aluno1 = LocalDate.of(2000, 9, 15);
        LocalDate aluno2 = LocalDate.of(1999, 1, 23);

        Period period = Period.between(aluno2, aluno1);

        System.out.printf("%s years, %s months and %s days", period.getYears(), period.getMonths(), period.getDays());
        assertEquals(1, period.getYears());
        assertEquals(7, period.getMonths());
        assertEquals(23, period.getDays());

    }
    @Test
    public void localDateShouldStoreADateWithoutTime() {
        LocalDate now = LocalDate.now();

        System.out.println(now);
    }

    @Test
    public void shouldCreateAndPrintLocalDate() {
        LocalDate date = LocalDate.of(2018, Month.MAY, 6);

        System.out.println(date);
    }

    @Test
    public void shouldPrintPeriodBetweenTwoDates() {
        LocalDate now = LocalDate.now();
        LocalDate septemberEleven = LocalDate.of(2001, Month.SEPTEMBER, 11);

        Period period = Period.between(septemberEleven, now);

        System.out.printf("%s years, %s months and %s days", period.getYears(), period.getMonths(), period.getDays(), period.getChronology());
    }

    @Test
    public void shouldCreateAndPrintLocalTime() {
        LocalTime time = LocalTime.of(8, 0);

        System.out.println(time);
    }

    @Test
    public void shouldCalculateDiferenceBetweenTwoTimes() {
        LocalTime time = LocalTime.of(8, 0);
        LocalTime time2 = LocalTime.of(18, 0);

        long minutes = time.until(time2, ChronoUnit.MINUTES);

        assertEquals(600, minutes);
    }

    @Test
    public void shouldCreateAndPrintLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);
    }

    @Test
    public void shouldPrintHoursUntilWorldCup() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime worldCup2018Opening = LocalDateTime.of(2018, Month.JUNE, 14, 12, 0);

        long hoursUntilWorldCup = now.until(worldCup2018Opening, ChronoUnit.HOURS);

        System.out.println(hoursUntilWorldCup);
    }

    @Test
    public void shouldCalculateDurationOfFlight() {
        ZoneId saoPauloTimeZone = ZoneId.of("America/Sao_Paulo");
        ZoneId newYorkTimeZone = ZoneId.of("America/New_York");

        ZonedDateTime saoPauloDeparture = ZonedDateTime.of(LocalDateTime.of(2014, Month.APRIL, 4, 22, 00), saoPauloTimeZone);
        ZonedDateTime arrivalInNewYork = ZonedDateTime.of(LocalDateTime.of(2014, Month.APRIL, 5, 7, 00), newYorkTimeZone);

        Duration flightDuration = Duration.between(saoPauloDeparture, arrivalInNewYork);

        assertEquals(10, flightDuration.toHours());
    }

    @Test
    public void shouldFormatDate() {
        LocalDate now = LocalDate.of(2018, Month.MAY, 6);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = now.format(formatter);

        assertEquals("06/05/2018", formattedDate);
    }

}

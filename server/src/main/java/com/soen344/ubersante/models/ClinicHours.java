package com.soen344.ubersante.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
public class ClinicHours {

    @Column(name = "open_at")
    private LocalTime open;

    @Column(name = "close_at")
    private LocalTime close;

    public ClinicHours() {}

    public ClinicHours(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }

    public LocalTime getOpen() {
        return open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "ClinicHours{" +
                "open=" + open +
                ", close=" + close +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClinicHours)) return false;
        ClinicHours that = (ClinicHours) o;
        return open.equals(that.open) &&
                close.equals(that.close);
    }

    @Override
    public int hashCode() {
        return Objects.hash(open, close);
    }
}

package main.domain;

public class Weather {

    private int start;
    private int end;
    private int temeperature;
    private int precip;

    private String description;

    public Weather(int start, int end, int temeperature, int precip, String description) {
        this.start = start;
        this.end = end;
        this.temeperature = temeperature;
        this.precip = precip;
        this.description = description;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTemeperature() {
        return temeperature;
    }

    public void setTemeperature(int temeperature) {
        this.temeperature = temeperature;
    }

    public int getPrecip() {
        return precip;
    }

    public void setPrecip(int precip) {
        this.precip = precip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "start=" + start +
                ", end=" + end +
                ", temeperature=" + temeperature +
                ", precip=" + precip +
                ", description='" + description + '\'' +
                '}';
    }
}

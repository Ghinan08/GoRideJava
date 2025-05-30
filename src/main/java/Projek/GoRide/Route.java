package Projek.GoRide;

public class Route implements RideAction {
    private double distance;
    private double duration;

    public Route(double distance, double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public double estimateFare() {
        return distance * 2000; 
    }

    public double calculateEta() {
        return (distance / 40) * 60; 
    }

    @Override
    public void startRide() {

    }

    @Override
    public void endRide() {

    }

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return duration;
    }
}
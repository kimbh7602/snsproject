package edu.ssafy.boot.dto;

public class LocationVo {
    private double lat;
    private double lng;
    private double dist;

    

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public LocationVo() {
    }

    public LocationVo(double lat, double lng, double dist) {
        this.lat = lat;
        this.lng = lng;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "LocationVo [dist=" + dist + ", lat=" + lat + ", lng=" + lng + "]";
    }
}
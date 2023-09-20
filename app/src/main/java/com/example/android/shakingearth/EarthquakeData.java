package com.example.android.shakingearth;

public class EarthquakeData {

        private double magnitude;
        private String city;
        private long time;
        String earthquake_URL;

        EarthquakeData(double magnitude,String city,long time,String url){
            this.magnitude=magnitude;
            this.city=city;
            this.time=time;
            this.earthquake_URL=url;
        }

        public double getMagnitude() { return magnitude; }
        public String getCity() { return city; }
        public long getTime() { return time; }
        public String getEarthquake_URL(){return earthquake_URL; }
}

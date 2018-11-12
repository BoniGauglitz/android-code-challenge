package Model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    private long latitude;
    private long longitude;
}

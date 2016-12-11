
package engsoft.projects.role.util;

public class MockUserLocation {

    private static Double USER_LATITUDE = -23.2d;
    private static Double USER_LONGITUDE = -46d;

    private Double latitude;
    private Double longitude;

    public MockUserLocation() {

        this.latitude = USER_LATITUDE;
        this.longitude = USER_LONGITUDE;
    }
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


}

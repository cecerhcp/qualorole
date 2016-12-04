package engsoft.projects.role.models;


public class Location {

    private String country;
    private String state;
    private String city;
    private String street;
    private String locationNumber;
    private String zipCode;
    private Double approvalRating;
    private int costRating;
    private Double latitude;
    private Double longitude;

    public Location(String country, String state, String city, String street, String locationNumber,
    String zipCode, Double approvalRating, int costRating, Double latitude, Double longitude) {

        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.locationNumber = locationNumber;
        this.zipCode = zipCode;
        this.approvalRating = approvalRating;
        this.costRating = costRating;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCostRating() {
        return costRating;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Double getApprovalRating() {
        return approvalRating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }



}

package engsoft.projects.role.presenters;

import java.util.ArrayList;
import java.util.List;

import engsoft.projects.role.models.Category;
import engsoft.projects.role.models.Event;
import engsoft.projects.role.models.Location;
import engsoft.projects.role.util.MockDatabase;
import engsoft.projects.role.util.MockUserLocation;

public class SearchEventPresenter {

    MockDatabase database = new MockDatabase();
    public List<Event> doSearch(String name, Double minPrice, Double maxPrice, Integer radius,
                                List<Category> categories) {

        List<Event> allEvents = database.getEvents();
        List<Event> results = new ArrayList<>();

        for (Event event : allEvents) {

            List<Category> eventCategories = event.getCategories();
            Boolean belongsToSearchCategories = false;
            for (Category eventCategory : eventCategories) {

                if (categories.contains(eventCategory)) {
                    belongsToSearchCategories = true;
                    break;
                }
            }
            if (belongsToSearchCategories) results.add(event);
        }


        for (int i = results.size(); i > 0; i--) {

            Event event = results.get(i);
            Double price = event.getEntrancePrice();
            if (price < minPrice || price > maxPrice) {
                results.remove(event);
                continue;
            }

            if (!event.getName().contains(name)) {
                results.remove(event);
                continue;
            }

            Location eventLocation = event.getAddress();
            if (!isInRadius(eventLocation, radius)) {
                results.remove(event);
                continue;
            }

        }

        return results;
    }


    public boolean isInRadius(Location location, Integer radius) {

        MockUserLocation userLocation = new MockUserLocation();

        Double eventLatitude = location.getLatitude();
        Double eventLongitude = location.getLongitude();

        Double userLatitude = userLocation.getLatitude();
        Double userLongitude = userLocation.getLongitude();

        // return calculateDistanceWithBothLocations();
        return true;
    }


    public boolean isValidSearchString(String search) {

        return isEmptyTextField(search) && search != null;

    }

    public boolean isValidPriceRange(String minPrice, String maxPrice)
    {
        if (!isValidNumberField(minPrice) || !isValidNumberField(maxPrice)) return false;

        //stuff left to do yet
        
        return true;
    }

    public boolean isValidNumberField(String number) {

        try {

            Double.parseDouble(number);

        }

        catch (NumberFormatException e) {

            return false;
        }

        return true;
    }

    public boolean isEmptyTextField(String text) {

        return text.trim().isEmpty();
    }


}

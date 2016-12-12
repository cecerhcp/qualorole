
package engsoft.projects.role.presenters;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import engsoft.projects.role.activities.SearchEventActivity;
import engsoft.projects.role.models.Category;
import engsoft.projects.role.models.Event;
import engsoft.projects.role.models.Location;
import engsoft.projects.role.util.MockDatabase;
import engsoft.projects.role.util.MockUserLocation;

public class SearchEventPresenter {

    private SearchEventActivity myActivity;

    public SearchEventPresenter(SearchEventActivity activity) {

        this.myActivity = activity;

    }

    public SearchEventPresenter() {

        this.myActivity = null;

    }

    MockDatabase database = new MockDatabase();

    public List<Event> doSearch(String name, Double minPrice, Double maxPrice, Integer radius,
                                List<Category> categories) {

        List<Event> allEvents = database.getEvents();
        List<Event> results = new ArrayList<>();

        for (Event event : allEvents) {

            List<Category> eventCategories = event.getCategories();
            Boolean belongsToSearchCategories = false;
            for (Category eventCategory : eventCategories) {

                for (Category category : categories) {
                    if (category.getName().equals(eventCategory.getName())) {
                        belongsToSearchCategories = true;
                        break;
                    }
                }
                if (belongsToSearchCategories) break;
            }

            if (belongsToSearchCategories) results.add(event);
        }

        for (int i = results.size() -1; i >= 0; i--) {

            Event event = results.get(i);
            Double price = event.getEntrancePrice();
            if (price < minPrice || price > maxPrice) {
                results.remove(event);
                continue;
            }

            if (!event.getName().trim().isEmpty()) {
                if (!event.getName().toLowerCase().contains(name)) {
                    results.remove(event);
                    continue;
                }
            }


            Location eventLocation = event.getAddress();
            if (!isInRadius(eventLocation, radius)) {
                results.remove(event);
                continue;
            }

        }
        return results;
    }

    public List<Category> getCategoriesFromCheckBoxes() {

        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < myActivity.CATEGORIES; i++) {

            CheckBox categoryCheckBox = myActivity.mCheckBoxes.get(i);
            if (categoryCheckBox.isChecked()) {
                Category category = new Category(categoryCheckBox.getHint().toString());
                categories.add(category);
            }
        }

        return categories;
    }

    public boolean searchFormIsOk() {

        String searchString = myActivity.mSearch.getText().toString();
        String minPrice = myActivity.mMinPrice.getText().toString();
        String maxPrice = myActivity.mMaxPrice.getText().toString();

        if (!isValidPriceRange(minPrice, maxPrice)) {
            Toast.makeText(myActivity.getApplicationContext(), "Algum campo inválido",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean isInRadius(Location location, Integer radius) {

        MockUserLocation userLocation = new MockUserLocation();

        Double eventLatitude = location.getLatitude();
        Double eventLongitude = location.getLongitude();

        Double userLatitude = userLocation.getLatitude();
        Double userLongitude = userLocation.getLongitude();

        return distanceBetweenCoordinates(userLatitude, eventLatitude, userLongitude,
                eventLongitude) <= radius;
    }

    public static double distanceBetweenCoordinates(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }


    public boolean isValidSearchString(String search) {

        return !isEmptyTextField(search);

    }

    public boolean isValidPriceRange(String minPrice, String maxPrice)
    {
        if (!isValidPriceField(minPrice) || !isValidPriceField(maxPrice)) return false;

        if (minPrice.equals("") || maxPrice.equals("")) return true;

        Double min = Double.parseDouble(minPrice);
        Double max = Double.parseDouble(maxPrice);

        return min <= max;
    }

    public boolean isValidPriceField(String number) {

        try {

            Double.parseDouble(number);

        }

        catch (NumberFormatException e) {

            return number.equals("");
        }

        return Double.parseDouble(number) >= 0;
    }

    public boolean isEmptyTextField(String text) {

        return text.trim().isEmpty();
    }


}

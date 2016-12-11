package engsoft.projects.role.util;

import java.util.ArrayList;
import java.util.List;

import engsoft.projects.role.models.Category;
import engsoft.projects.role.models.Event;
import engsoft.projects.role.models.Location;

public class MockDatabase {

    private List<Category> categories;
    private List<Event> events;
    private List<Location> locations;

    public MockDatabase() { /*every constructor constructs the same initial database */
        this.categories = new ArrayList<>();
        this.events = new ArrayList<>();
        this.locations = new ArrayList<>();

        Location testLocation = new Location("Brazil", "Sao Paulo", "Santo Andre", "Rua Carinas", "218",
                "09185510", 5d, 2, -23d, -46d);
        this.addLocation(testLocation);
        Event testEvent = new Event("Festa Engsoft", 50d, testLocation);
        testEvent.addCategory(new Category("narua"));
        this.addEvent(testEvent);


    }

    public void addCategory(Category category) {

        this.categories.add(category);
    }

    public void addEvent(Event event) {

        this.events.add(event);
    }

    public void addLocation(Location location) {

        this.locations.add(location);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Location> getLocations() {
        return locations;
    }



}

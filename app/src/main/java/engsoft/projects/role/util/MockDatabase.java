
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

        Location testLocation1 = new Location("Brazil", "Sao Paulo", "Santo Andre", "Rua Carinas", "218",
                "09185510", 5d, 2, -23d, -46d);
        Location testLocation2 = new Location("Brazil", "Sao Paulo", "Santo Andre", "Rua Carinas", "218",
                "09185510", 5d, 2, -23d, -50d);
        this.addLocation(testLocation1);
        Event testEvent1 = new Event("Festa Engsoft", 50d, testLocation1);
        Event testEvent2 = new Event("Foo Fighters 2017", 250d, testLocation2);
        Event testEvent3 = new Event("Exposição MASP", 20d, testLocation1);
        Event testEvent4 = new Event("Bar da Praça", 0d, testLocation1);
        Event testEvent5 = new Event("Carnaval 2017", 0d, testLocation1);
        testEvent1.addCategory(new Category("narua"));
        testEvent4.addCategory(new Category("botecos"));
        testEvent5.addCategory(new Category("narua"));
        testEvent2.addCategory(new Category("shows"));
        testEvent3.addCategory(new Category("museus"));
        this.addEvent(testEvent1);
        this.addEvent(testEvent2);
        this.addEvent(testEvent3);
        this.addEvent(testEvent4);
        this.addEvent(testEvent5);
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

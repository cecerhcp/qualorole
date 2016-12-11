
package engsoft.projects.role.models;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String name;
    private List<Category> categories;
    private Double entrancePrice;
    private Location address;

    public Event(String name, Double entrancePrice, Location address) {

        this.name = name;
        this.entrancePrice = entrancePrice;
        this.address = address;
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Location getAddress() {
        return this.address;
    }

    public double getEntrancePrice() {

        return this.entrancePrice;
    }

    public List<Category> getCategories()
    {
        return this.categories;
    }

    public void addCategory(Category category) {

        this.categories.add(category);

    }

}

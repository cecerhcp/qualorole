package engsoft.projects.role.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<String> categoryTags;

    public String getName() {

        return this.name;

    }

    public Category(String name) {

        this.name = name;
        this.categoryTags = new ArrayList<String>();
    }

}

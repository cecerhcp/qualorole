
package engsoft.projects.role.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<String> categoryTags;
    public static String SHOWS = "shows";
    public static String BALADAS = "baladas";
    public static String MUSEUS = "museus";
    public static String ESPORTES = "esportes";
    public static String BOTECOS = "botecos";
    public static String NARUA = "narua";

    public String getName() {

        return this.name;

    }

    public Category(String name) {

        this.name = name;
        this.categoryTags = new ArrayList<String>();
    }

}

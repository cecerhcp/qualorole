
import org.junit.Assert;
import org.junit.Test;

import engsoft.projects.role.models.Category;
import engsoft.projects.role.models.Event;
import engsoft.projects.role.models.Location;
import engsoft.projects.role.presenters.SearchEventPresenter;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;
import java.util.List;

public class SearchEventPresenterTest {

    public static SearchEventPresenter mPresenter = new SearchEventPresenter();

    @Test
    public void  isInRadiusReturnFalseWhenLocationIsFarEnough() {
        Location testLocation = new Location("Brazil", "Sao Paulo", "Santo Andre", "Rua Carinas", "218",
                "09185510", 5d, 2, -23d, -46d);
        Assert.assertThat(mPresenter.isInRadius(testLocation, 50), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isInRadius(testLocation, 10), CoreMatchers.is(false));
    }


    @Test
    public void  emptyFieldIsCheckedRight() {
        Assert.assertThat(mPresenter.isEmptyTextField("-2"), CoreMatchers.is(false));
        Assert.assertThat(mPresenter.isEmptyTextField(""), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isEmptyTextField("  "), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isEmptyTextField("     "), CoreMatchers.is(true));
    }

    @Test
    public void  priceCannotBeNegativeNumber() {
        Assert.assertThat(mPresenter.isValidPriceField("-2"), CoreMatchers.is(false));
    }

    @Test
    public void  integrationTestSuccessfulSearchHasMoreThanZeroResults() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("narua"));
        List<Event> results = mPresenter.doSearch("engsoft", 10d, 60d, 50, categories);
        Assert.assertThat(results.isEmpty(), CoreMatchers.is(false));
    }

    @Test
    public void  integrationTestFailedSearchHasZeroResults() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("narua"));
        List<Event> results = mPresenter.doSearch("engsoft", 10d, 30d, 50, categories);
        Assert.assertThat(results.isEmpty(), CoreMatchers.is(true));
    }


    @Test
    public void  priceCannotBeNotANumber() {
        Assert.assertThat(mPresenter.isValidPriceField("a"), CoreMatchers.is(false));
        Assert.assertThat(mPresenter.isValidPriceField("$"), CoreMatchers.is(false));
        Assert.assertThat(mPresenter.isValidPriceField("   "), CoreMatchers.is(false));
    }

    @Test
    public void priceCannotBeGreaterThanMaxPrice() {
        Assert.assertThat(mPresenter.isValidPriceRange("22", "11"), CoreMatchers.is(false));
        Assert.assertThat(mPresenter.isValidPriceRange("22", "22"), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isValidPriceRange("22", "31"), CoreMatchers.is(true));
    }

    @Test
    public void priceRangeCanHaveEmptyPriceFields() {
        Assert.assertThat(mPresenter.isValidPriceRange("", ""), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isValidPriceRange("22", ""), CoreMatchers.is(true));
        Assert.assertThat(mPresenter.isValidPriceRange("", "31"), CoreMatchers.is(true));
    }
}

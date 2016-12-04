package engsoft.projects.role.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import android.widget.SeekBar.OnSeekBarChangeListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import engsoft.projects.role.R;
import engsoft.projects.role.models.Category;
import engsoft.projects.role.models.Event;
import engsoft.projects.role.presenters.SearchEventPresenter;

public class SearchEventActivity extends AppCompatActivity {

    public static int CATEGORIES = 6;

    public EditText mMinPrice;
    public EditText mMaxPrice;
    public EditText mSearch;
    public SeekBar mSeekBar;
    public List<CheckBox> mCheckBoxes;
    private Button mSearchButton;
    private TextView mRadius;
    private SearchEventPresenter myPresenter = new SearchEventPresenter(this);

    List<Event> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_event);

        mMinPrice = (EditText) findViewById(R.id.txtMin);
        mMaxPrice = (EditText) findViewById(R.id.txtMax);
        mSearch = (EditText) findViewById(R.id.txtSearch);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSearchButton = (Button) findViewById(R.id.btSearch);
        mRadius = (TextView) findViewById(R.id.txtRadius);

        /* binding checkBoxes to categories*/
        List<Integer> categoryCheckBoxesIds = new ArrayList<>();
        categoryCheckBoxesIds.add(R.id.cbBaladas);
        categoryCheckBoxesIds.add(R.id.cbBotecos);
        categoryCheckBoxesIds.add(R.id.cbEsportes);
        categoryCheckBoxesIds.add(R.id.cbMuseus);
        categoryCheckBoxesIds.add(R.id.cbRua);
        categoryCheckBoxesIds.add(R.id.cbShows);
        for (int i = 0; i < CATEGORIES; i++) {

            CheckBox category;
            category = (CheckBox) findViewById(categoryCheckBoxesIds.get(i));
            mCheckBoxes.add(category);
        }

        mSearchButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String searchString = mSearch.getText().toString();
                String minPrice = mMinPrice.getText().toString();
                String maxPrice = mMaxPrice.getText().toString();
                Integer radius = mSeekBar.getProgress();

                List<Category> categories = myPresenter.getCategoriesFromCheckBoxes();

                if (myPresenter.searchFormIsOk()) {

                  Double minimum = Double.parseDouble(minPrice);
                  Double maximum = Double.parseDouble(maxPrice);
                  results = myPresenter.doSearch(searchString, minimum, maximum, radius, categories);
                }
                else results = new ArrayList<>();

                // TODO implement loadingResults()

            }
        });

        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mRadius.setText(progress);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

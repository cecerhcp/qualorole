package engsoft.projects.role.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import android.widget.SeekBar.OnSeekBarChangeListener;

import org.w3c.dom.Text;

import engsoft.projects.role.R;
import engsoft.projects.role.presenters.SearchEventPresenter;

public class SearchEventActivity extends AppCompatActivity {

    EditText mMinPrice;
    EditText mMaxPrice;
    EditText mSearch;
    SeekBar mSeekBar;
    Button mSearchButton;
    TextView mRadius;
    SearchEventPresenter mPresenter = new SearchEventPresenter();

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

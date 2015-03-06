package com.gojimo.gojimojsonapi;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.gojimo.gojimojsonapi.adater.SubjectListAdapter;
import com.gojimo.gojimojsonapi.fragments.SubjectFragment;
import com.gojimo.gojimojsonapi.model.Qualification;
import com.gojimo.gojimojsonapi.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
* Subject Activity
* @author RouhAlavi
* @version 0.1.0
*/
public class SubjectActivity extends Activity {
    public static final String QUALIFICATION = "QUALIFICATION";
    private ListView listView;
    private SubjectListAdapter adapter;
    private List<Subject> subjectList = new ArrayList<Subject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        //
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_subj);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Qualification q = (Qualification)extras.get(QUALIFICATION);
            SubjectFragment subjectFragment = (SubjectFragment) getFragmentManager()
                    .findFragmentById(R.id.subjFragment);
            subjectFragment.setQualification(q);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subject, menu);
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();

    }


}

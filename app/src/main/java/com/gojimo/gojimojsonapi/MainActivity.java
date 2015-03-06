package com.gojimo.gojimojsonapi;

import com.gojimo.gojimojsonapi.fragments.QualificationFragment;
import com.gojimo.gojimojsonapi.fragments.SubjectFragment;
import com.gojimo.gojimojsonapi.model.Qualification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

/**
 * Main Activity Class
 *
 * @author RouhAlavi
 * @version 0.1.0
 *
 */
public class MainActivity extends Activity implements QualificationFragment.OnFragmentInteractionListener{
    /**
     * Starts Activity, sets the view and request json array from url
     * If data does not exist check whether if data saved to storage
     * @param savedInstanceState
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public void onFragmentInteraction(Qualification q) {
        SubjectFragment fragment = (SubjectFragment) getFragmentManager()
                .findFragmentById(R.id.subjFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setQualification(q);
            //Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    SubjectActivity.class);
            intent.putExtra(SubjectActivity.QUALIFICATION, q);
            startActivity(intent);

        }
    }

}

package com.gojimo.gojimojsonapi.adater;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gojimo.gojimojsonapi.R;
import com.gojimo.gojimojsonapi.model.Qualification;
import com.gojimo.gojimojsonapi.model.Subject;
import com.ocpsoft.pretty.time.PrettyTime;

import java.util.List;
import java.util.Random;

/**
 * Adapter for subject list
 * @author RouhAlavi
 * @version 0.1.0
 */
public class SubjectListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Subject> subjectItems;

    /**
     * Constructor for adapter
     * @param activity
     * @param subjectItems
     */
    public SubjectListAdapter(Activity activity, List<Subject> subjectItems) {
        this.activity = activity;
        this.subjectItems = subjectItems;
    }

    /**
     * return no of elements
     * @return int
     */
    @Override
    public int getCount() {
        return subjectItems.size();
    }

    /**
     * return subject at location
     * @param location
     * @return subject
     */
    @Override
    public Object getItem(int location) {
        return subjectItems.get(location);
    }

    /**
     * return id of element at position
     * @param position
     * @return long
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * return view of adapter
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.subject_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.subject_title);
        TextView subLetter = (TextView) convertView.findViewById(R.id.subject_letter);


        // getting qualification data for the row
        Subject s = subjectItems.get(position);

        // title
        title.setText(s.getTitle());

        // Subject First Letter
        subLetter.setText(s.getTitle().substring(0,1));
        subLetter.setBackgroundColor(s.getColour());

        return convertView;
    }

}

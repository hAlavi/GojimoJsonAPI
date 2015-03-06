package com.gojimo.gojimojsonapi.adater;

import com.gojimo.gojimojsonapi.R;
import com.gojimo.gojimojsonapi.app.AppController;
import com.gojimo.gojimojsonapi.model.Qualification;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.text.Html;

import com.gojimo.gojimojsonapi.util.BeautyColors;
import com.ocpsoft.pretty.time.PrettyTime;

import com.android.volley.toolbox.ImageLoader;

/**
 * Adapter for Qualification List
 * @author RouhAlavi
 * @version 0.1.0
 */
public class QualificationListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Qualification> qualificationItems;

    /**
     * Constructor for Adpater
     * @param activity
     * @param qualificationItems
     */
	public QualificationListAdapter(Activity activity, List<Qualification> qualificationItems) {
		this.activity = activity;
		this.qualificationItems = qualificationItems;
	}

    /**
     * Get Qualification Item Count
     * @return no of qualification
     */
	@Override
	public int getCount() {
		return qualificationItems.size();
	}

    /**
     * return Item at location
     * @param location
     * @return Qualification
     */
	@Override
	public Object getItem(int location) {
		return qualificationItems.get(location);
	}

    /**
     * return Item id at position
     * @param position
     * @return id
     */
	@Override
	public long getItemId(int position) {
		return position;
	}


    /**
     * Return view for adapter
     * @param position
     * @param convertView
     * @param parent
     * @return View
     */
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView created = (TextView) convertView.findViewById(R.id.created);
		TextView updated = (TextView) convertView.findViewById(R.id.updated);
		TextView subjects = (TextView) convertView.findViewById(R.id.subjects);
        TextView subCount = (TextView) convertView.findViewById(R.id.subjects_count);

        LinearLayout ll=(LinearLayout)convertView.findViewById(R.id.title_container);
        Random rnd= new Random();
        ll.setBackgroundColor(Color.rgb(180+rnd.nextInt(50),100+rnd.nextInt(50),190+rnd.nextInt(50)));


        LinearLayout ll2=(LinearLayout)convertView.findViewById(R.id.list_container);
        ll2.setBackgroundColor(Color.rgb(90+rnd.nextInt(50),170+rnd.nextInt(50),200+rnd.nextInt(50)));

        // getting qualification data for the row
		Qualification q = qualificationItems.get(position);

		// title
		title.setText(q.getTitle());
		
		// Subjects
		subjects.setText(Html.fromHtml(q.getSubjectsFormatted()), TextView.BufferType.SPANNABLE);
        subCount.setText(Integer.toString(q.getSubjects().size())+ " SB\n"+
                Integer.toString(q.getDefaultProducts().size())+ " PR");
        int g= (q.getSubjects().size()*20)%256;
        int b= (q.getSubjects().size()*7)%256;
        int r= (q.getSubjects().size()*2)%256;

        subCount.setBackgroundColor(Color.rgb(r,g,b));

        // created At
        // Show in a pretty format
        PrettyTime pt= new PrettyTime();
        created.setText(pt.format(q.getCreatedAt()));

        // updated At
        // Show in a pretty format
        updated.setText("Updated "+pt.format(q.getUpdatedAt()));

		return convertView;
	}

}
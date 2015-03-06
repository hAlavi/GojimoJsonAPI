package com.gojimo.gojimojsonapi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gojimo.gojimojsonapi.R;
import com.gojimo.gojimojsonapi.adater.SubjectListAdapter;
import com.gojimo.gojimojsonapi.model.Qualification;
import com.gojimo.gojimojsonapi.model.Subject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * interface
 * to handle interaction events.
 * Use the {@link SubjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author RouhAlavi
 * @version 0.1.0
 */
public class SubjectFragment extends Fragment {

    private ListView listView;
    private SubjectListAdapter adapter;
    private List<Subject> subjectList = new ArrayList<Subject>();

    public static SubjectFragment newInstance() {
        SubjectFragment fragment = new SubjectFragment();
        return fragment;
    }

    public SubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_subj, container, false);
        //
        listView = (ListView) myView.findViewById(R.id.list_subjects);
        adapter = new SubjectListAdapter(getActivity(), subjectList);
        listView.setAdapter(adapter);
        //
        //this.onBackPressed();
        return myView;

    }

    public void setQualification(Qualification q){
        if (q!=null) subjectList = q.getSubjects();
        adapter = new SubjectListAdapter(getActivity(), subjectList);
        listView.setAdapter(adapter);

    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}

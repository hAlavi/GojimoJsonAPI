package com.gojimo.gojimojsonapi.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.gojimo.gojimojsonapi.MainActivity;
import com.gojimo.gojimojsonapi.R;
import com.gojimo.gojimojsonapi.adater.QualificationListAdapter;
import com.gojimo.gojimojsonapi.app.AppController;
import com.gojimo.gojimojsonapi.model.JsonStorage;
import com.gojimo.gojimojsonapi.model.Product;
import com.gojimo.gojimojsonapi.model.Qualification;
import com.gojimo.gojimojsonapi.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QualificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QualificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author RouhAlavi
 * @version 0.1.0
 */
public class QualificationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String JSON_FILE_NAME="gojimo_data.json";

    // Gojimo json url
    private static final String url = "https://api.gojimo.net/api/v4/qualifications";
    private ProgressDialog pDialog;
    private List<Qualification> qualificationList = new ArrayList<Qualification>();
    private ListView listView;
    private QualificationListAdapter adapter;
    SwipeRefreshLayout swipeLayout;

    //
    private OnFragmentInteractionListener mListener;


    /**
     * return instance of  class(Singleton)
     * @return QualificationFragment
     */
    public static QualificationFragment newInstance() {
        QualificationFragment fragment = new QualificationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * constructor
     */
    public QualificationFragment() {
        // Required empty public constructor
    }

    /**
     * Oncreate of fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * onCreateView of fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_qual_list, container, false);
        swipeLayout = (SwipeRefreshLayout) myView.findViewById(R.id.fragment_swipe_refresh_layout);
        swipeLayout.setOnRefreshListener(this);

        listView = (ListView) myView.findViewById(R.id.list);
        adapter = new QualificationListAdapter(getActivity(), qualificationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the qualification Title
                try {
                    mListener.onFragmentInteraction(qualificationList.get(position));
                    //Toast.makeText(getActivity().getApplicationContext(), qualificationList.get(position).getTitle(),
                    //        Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getActivity().getApplicationContext(), "Qualification is corrupted!",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        requestJson();
        return myView;
    }

    /**
     * onAttach of fragment
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        requestJson();

    }

    /**
     * Request json data from url and then from storage
     * and update fragment
     */
    private void requestJson(){
        // Showing progress dialog before making http request
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();


        // Creating volley request obj
        JsonArrayRequest qualificationReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        qualificationList.clear();
                        JsonStorage.writeFileInternalStorage(response.toString(), getActivity().getApplicationContext(), JSON_FILE_NAME);
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                Qualification qualification =
                                        new Qualification(obj.getString("name"),
                                                obj.optString("created_at"),
                                                obj.optString("updated_at"),
                                                null,null);

                                // Subjects is json array
                                JSONArray subjArray = obj.getJSONArray("subjects");
                                ArrayList<Subject> subjects = new ArrayList<Subject>();
                                for (int j = 0; j < subjArray.length(); j++) {
                                    Subject sub = new Subject(subjArray.getJSONObject(j).getString("id"),
                                            subjArray.getJSONObject(j).optString("title"),
                                            subjArray.getJSONObject(j).optString("colour"));

                                    subjects.add(sub);
                                }
                                qualification.setSubjects(subjects);

                                // Subjects is json array
                                JSONArray prodArray = obj.getJSONArray("default_products");
                                ArrayList<Product> products = new ArrayList<Product>();
                                for (int k = 0; k < prodArray.length(); k++) {
                                    Product prod = new Product(prodArray.getJSONObject(k).getString("id"),
                                            prodArray.getJSONObject(k).optString("title"),
                                            prodArray.getJSONObject(k).optString("type"));

                                    products.add(prod);
                                }
                                qualification.setDefaultProducts(products);

                                // adding qualification to array
                                qualificationList.add(qualification);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                        swipeLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                try {
                    String json = JsonStorage.readFileInternalStorage(JSON_FILE_NAME, getActivity().getApplicationContext());
                    if (!json.equals("")) {
                        JSONArray response = new JSONArray(json);
                        // Parsing json
                        qualificationList.clear();
                        Toast.makeText(getActivity().getApplicationContext(),"JSON:"+response.length(), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Qualification qualification = new Qualification(obj.optString("name"),
                                        obj.optString("created_at"),
                                        obj.optString("updated_at"),
                                        null, null);


                                // Subjects is json array
                                JSONArray subjArray = obj.getJSONArray("subjects");
                                ArrayList<Subject> subjects = new ArrayList<Subject>();
                                for (int j = 0; j < subjArray.length(); j++) {
                                    Subject sub = new Subject(subjArray.getJSONObject(j).getString("id"),
                                            subjArray.getJSONObject(j).getString("title"),
                                            subjArray.getJSONObject(j).getString("colour"));

                                    subjects.add(sub);
                                }
                                qualification.setSubjects(subjects);

                                // Subjects is json array
                                JSONArray prodArray = obj.getJSONArray("default_products");
                                ArrayList<Product> products = new ArrayList<Product>();
                                for (int k = 0; k < prodArray.length(); k++) {
                                    Product prod = new Product(prodArray.getJSONObject(k).getString("id"),
                                            prodArray.getJSONObject(k).getString("title"),
                                            prodArray.getJSONObject(k).getString("type"));

                                    products.add(prod);
                                }
                                qualification.setDefaultProducts(products);

                                // adding qualification to array
                                qualificationList.add(qualification);


                            } catch (JSONException ex) {

                            }

                            hidePDialog();
                            swipeLayout.setRefreshing(false);

                        }
                    }

                } catch (Exception ex) {

                }

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(qualificationReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Qualification q);
    }

}

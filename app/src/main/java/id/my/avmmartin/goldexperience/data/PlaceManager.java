package id.my.avmmartin.goldexperience.data;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Vector;

import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.utils.Constants;

public class PlaceManager extends Vector<Place> {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String RATING = "rating";
    public static final String LATITUDE = "LAT";
    public static final String LONGITUDE = "LNG";

    void reloadOnlinePlacesData(final ProgressDialog progressDialog, final Runnable runnable) {
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Constants.JSON_URL_PLACE,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        clear();

                        for (int i = 0; i < response.length(); i++) {
                            add(new Place(response.getJSONObject(i)));
                        }

                    } catch (JSONException e) {
                        //
                    } finally {
                        progressDialog.dismiss();
                    }

                    runnable.run();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    progressDialog.dismiss();
                }
            }
        );

        requestQueue.add(jsonArrayRequest);
    }

    List<Place> getPlaces() {
        return PlaceManager.this;
    }

    Place getPlaceById(int placeId) {
        return get(placeId - 1); // Ahh, annoying offset on placeId
    }

    // constructor

    private RequestQueue requestQueue;

    PlaceManager(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);

        // TODO: hard coded database
        add(new Place(
            1, "Potato Head", 4,
            "The best place in Bali", -6.2000809, 106.7833355
        ));
        add(new Place(
            2, "Pink Beach", 5,
            "The best place in Lombok", -6.2261741, 106.9078293)
        );
    }
}

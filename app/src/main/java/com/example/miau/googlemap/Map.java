package com.example.miau.googlemap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <h1>Map fragment</h1>
 * Map fragment will contain
 * GoogleMap object
 *
 * @author Michał Kunda
 * @version alpha
 * @since 16.12.2018
 */
public class Map extends Fragment {

    /**
     * This method is executed on the class call.
     * @param savedInstanceState it gets Bundle object
     * @param container it gets ViewGroup object
     * @param inflater it gets LayoutInflater object
     * @return LayoutInflater object with name of the fragment, group of views and boolean if attached to root
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }


}

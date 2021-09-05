package host.avpp.ko_t_ff.dev_life_demo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import host.avpp.ko_t_ff.dev_life_demo.R;

/**
 * This is {@link Fragment} to show gifs.
 */
public class GifViewerFragment extends Fragment {

    public GifViewerFragment() {
    }


    public static GifViewerFragment newInstance(String param1, String param2) {
        GifViewerFragment fragment = new GifViewerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gif_viewer, container, false);
        return v;
    }
}
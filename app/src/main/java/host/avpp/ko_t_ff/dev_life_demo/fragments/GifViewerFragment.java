package host.avpp.ko_t_ff.dev_life_demo.fragments;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import host.avpp.ko_t_ff.dev_life_demo.R;
import host.avpp.ko_t_ff.dev_life_demo.data.GifInfo;

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

    private ImageButton prevBtn, nextBtn;
    private TextView descriptionTV;
    private ImageView outputIV;
    private GifViewerFragmentViewModel vm;
    private ConstraintLayout mainLO, errorLO;
    private ProgressBar loadingPB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gif_viewer, container, false);
        prevBtn = v.findViewById(R.id.gif_viewer_fragment_prev_btn);
        nextBtn = v.findViewById(R.id.gif_viewer_fragment_next_btn);
        descriptionTV = v.findViewById(R.id.gif_viewer_fragment_description_tv);
        outputIV = v.findViewById(R.id.gif_viewer_fragment_gif_iv);
        mainLO = v.findViewById(R.id.gif_viewer_fragment_main_area);
        errorLO = v.findViewById(R.id.gif_viewer_fragment_error_area);
        loadingPB = v.findViewById(R.id.gif_viewer_fragment_loading_pb);

        vm = new ViewModelProvider(requireActivity()).get(GifViewerFragmentViewModel.class);

        vm.isHasPrev().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasPrev) {
                prevBtn.setEnabled(hasPrev);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.prev();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.next();
            }
        });

        vm.getCurrentGif().observe(getViewLifecycleOwner(), new Observer<Pair<GifInfo, GifViewerFragmentViewModel.Status>>() {
            @Override
            public void onChanged(Pair<GifInfo, GifViewerFragmentViewModel.Status> arg) {
                if (GifViewerFragmentViewModel.Status.LOADING.equals(arg.second)) {
                    mainLO.setVisibility(View.VISIBLE);
                    errorLO.setVisibility(View.INVISIBLE);
                    loadingPB.setVisibility(View.VISIBLE);
                    descriptionTV.setText(R.string.gif_viewer_fragment_loading_text);
                    return;
                } else if (GifViewerFragmentViewModel.Status.ERROR.equals(arg.second)) {
                    mainLO.setVisibility(View.INVISIBLE);
                    errorLO.setVisibility(View.VISIBLE);
                    loadingPB.setVisibility(View.INVISIBLE);
                    return;
                }
                mainLO.setVisibility(View.VISIBLE);
                errorLO.setVisibility(View.INVISIBLE);
                loadingPB.setVisibility(View.INVISIBLE);
                GifInfo gifInfo = arg.first;
                descriptionTV.setText(gifInfo.getDescription());
                Glide.with(GifViewerFragment.this)
                        .load(gifInfo.getGifURL())
                        .thumbnail(Glide.with(GifViewerFragment.this).load(gifInfo.getPreviewURL()))
                        .centerCrop()
                        .into(outputIV);
            }
        });
        return v;
    }
}
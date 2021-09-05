package host.avpp.ko_t_ff.dev_life_demo.fragments;

import android.util.Pair;

import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import host.avpp.ko_t_ff.dev_life_demo.data.GifInfo;
import host.avpp.ko_t_ff.dev_life_demo.repositories.GifsLocalRepository;
import host.avpp.ko_t_ff.dev_life_demo.repositories.GifsRemoteRepository;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class GifViewerFragmentViewModel extends ViewModel {
    public enum Status {CACHED, LOADING, ERROR}

    private final LiveData<Boolean> hasPrev;
    public LiveData<Boolean> isHasPrev() {
        return hasPrev;
    }

    public void next() {
        Integer val = index.getValue();
        if (val != null)
            index.setValue(val + 1);
    }
    public void prev() {
        Integer val = index.getValue();
        if (val != null && val > 0)
            index.setValue(val - 1);
    }
    public void refresh() {
        index.setValue(index.getValue());
    }

    private final LiveData<Pair<GifInfo, Status>> currentGif;

    public LiveData<Pair<GifInfo, Status>> getCurrentGif() {
        return currentGif;
    }

    private final GifsLocalRepository localRepository;
    private final GifsRemoteRepository remoteRepository;
    private final MutableLiveData<Integer> index = new MutableLiveData<>(0);

    public GifViewerFragmentViewModel() {
        localRepository = GifsLocalRepository.instance.get();
        remoteRepository = GifsRemoteRepository.instance.get();
        currentGif = Transformations.switchMap(localRepository.getLiveData(), new Function<List<GifInfo>, LiveData<Pair<GifInfo, Status>>>() {
            @Override
            public LiveData<Pair<GifInfo, Status>> apply(List<GifInfo> allData) {
                return Transformations.switchMap(index, new Function<Integer, LiveData<Pair<GifInfo, Status>>>() {
                    @Override
                    public LiveData<Pair<GifInfo, Status>> apply(Integer idx) {
                        if (idx < allData.size()) {
                            return new MutableLiveData<>(new Pair<>(allData.get(idx), Status.CACHED));
                        }
                        final MutableLiveData<Pair<GifInfo, Status>> result = new MutableLiveData<>(new Pair<>(null, Status.LOADING));
                        remoteRepository.getRandom(new GifsRemoteRepository.GifInfoLoadedListener() {
                            @Override
                            public void loaded(GifInfo gi) {
                                localRepository.add(gi);
                            }

                            @Override
                            public void failed() {
                                result.setValue(new Pair<>(null, Status.ERROR));
                            }
                        });
                        return result;
                    }
                });
            }
        });
        hasPrev = Transformations.map(index, new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer idx) {
                return idx > 0;
            }
        });
    }
}

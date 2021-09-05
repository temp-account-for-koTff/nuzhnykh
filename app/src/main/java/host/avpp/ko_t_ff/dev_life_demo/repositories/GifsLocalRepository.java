package host.avpp.ko_t_ff.dev_life_demo.repositories;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import host.avpp.ko_t_ff.dev_life_demo.data.GifInfo;
import host.avpp.ko_t_ff.dev_life_demo.utils.SingletonManager;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class GifsLocalRepository {

    public static final SingletonManager<GifsLocalRepository> instance = new SingletonManager<>(new SingletonManager.Factory<GifsLocalRepository>() {
        @Override
        public GifsLocalRepository create() {
            return new GifsLocalRepository();
        }
    });

    private GifsLocalRepository() {
        liveData = new MutableLiveData<>(data);
    }

    private final List<GifInfo> data = new ArrayList<>();
    private final MutableLiveData<List<GifInfo>> liveData;

    public MutableLiveData<List<GifInfo>> getLiveData() {
        return liveData;
    }
    public void add(GifInfo gi) {
        data.add(gi);
        liveData.setValue(data);
    }
}

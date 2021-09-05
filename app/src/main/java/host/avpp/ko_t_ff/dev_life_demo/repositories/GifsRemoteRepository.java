package host.avpp.ko_t_ff.dev_life_demo.repositories;

import androidx.annotation.NonNull;
import host.avpp.ko_t_ff.dev_life_demo.data.GifInfo;
import host.avpp.ko_t_ff.dev_life_demo.data.api.ProviderOfServiceAPI;
import host.avpp.ko_t_ff.dev_life_demo.utils.SingletonManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class GifsRemoteRepository {

    public static final SingletonManager<GifsRemoteRepository> instance = new SingletonManager<>(new SingletonManager.Factory<GifsRemoteRepository>() {
        @Override
        public GifsRemoteRepository create() {
            return new GifsRemoteRepository();
        }
    });

    private GifsRemoteRepository() {

    }

    public interface GifInfoLoadedListener {
        void loaded(GifInfo gi);
        void failed();
    }
    public void getRandom(@NonNull GifInfoLoadedListener listener) {
        ProviderOfServiceAPI.create().getRandom().enqueue(new Callback<GifInfo>() {
            @Override
            public void onResponse(Call<GifInfo> call, Response<GifInfo> response) {
                listener.loaded(response.body());
            }

            @Override
            public void onFailure(Call<GifInfo> call, Throwable t) {
                listener.failed();
            }
        });
    }
}

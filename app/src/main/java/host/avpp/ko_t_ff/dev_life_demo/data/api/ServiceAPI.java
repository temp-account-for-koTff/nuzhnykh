package host.avpp.ko_t_ff.dev_life_demo.data.api;

import host.avpp.ko_t_ff.dev_life_demo.data.GifInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public interface ServiceAPI {
    @GET("random?json=true")
    Call<GifInfo> getRandom();
}

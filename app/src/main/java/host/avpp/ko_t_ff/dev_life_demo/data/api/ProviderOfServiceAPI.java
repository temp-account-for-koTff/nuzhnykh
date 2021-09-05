package host.avpp.ko_t_ff.dev_life_demo.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class ProviderOfServiceAPI {
    private static final String URL = "https://developerslife.ru/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static ServiceAPI create() {
        return retrofit.create(ServiceAPI.class);
    }
}

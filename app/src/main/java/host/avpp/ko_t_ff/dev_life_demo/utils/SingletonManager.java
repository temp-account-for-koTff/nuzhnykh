package host.avpp.ko_t_ff.dev_life_demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class SingletonManager<T> {
    public interface Factory<T> {
        T create();
    }

    private final Factory<T> factory;

    public SingletonManager(Factory<T> factory) {
        this.factory = factory;
    }

    private final Map<String, T> instances = new HashMap<>();
    public T get() {
        return get("");
    }
    public T get(String key) {
        if (instances.containsKey(key))
            return instances.get(key);
        T result = factory.create();
        instances.put(key, result);
        return result;
    }
}

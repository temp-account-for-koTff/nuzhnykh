package host.avpp.ko_t_ff.dev_life_demo.utils;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by Nuzhnykh Alexey on 05.09.2021.
 */

public class SimpleBooleanToggledLiveData extends MutableLiveData<Boolean> {
    public SimpleBooleanToggledLiveData(boolean value) {
        super(value);
    }
    public void toggle() {
        setValue(!getValue());
    }
}

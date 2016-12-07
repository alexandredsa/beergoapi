package br.com.beergo.beergoapp.domain.maps;

import com.google.gson.annotations.SerializedName;

public class MapsOpeningHours {
    @SerializedName("open_now")
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }
}

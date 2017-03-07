package hu.marton.tamas.movie.api;

import java.io.IOException;

/**
 * Created by tamas.marton on 07/03/2017.
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "Internet connection error! Pls connect to the internet!";
    }
}
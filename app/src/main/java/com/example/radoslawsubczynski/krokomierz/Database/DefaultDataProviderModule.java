package com.example.radoslawsubczynski.krokomierz.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2016-12-03.
 */

public abstract class DefaultDataProviderModule implements DataProviderModule {

    private List<DataProviderListener> dataProviderListeners = new ArrayList<>();


    public void registerListener(DataProviderListener dataProviderListener) {
        if (!dataProviderListeners.contains(dataProviderListener)) {
            dataProviderListeners.add(dataProviderListener);
        }
    }

    public void unregisterListener(DataProviderListener dataProviderListener) {
        if (!dataProviderListeners.contains(dataProviderListener)) {
            dataProviderListeners.remove(dataProviderListener);

        }
    }

    public void broadcastOnSearchUserSuccess(String searchUser) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onSearchSuccess(searchUser);
        }
    }

    public void broadcastOnSearchUserFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onSearchFail();
        }
    }

    @Override
    public void broadcastGetAllScoreFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetAllContatsFail();
        }
    }

    @Override
    public void broadcastGetAllScoreSuccess(String cos) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetAllContatsSuccess(cos);
        }
    }
}

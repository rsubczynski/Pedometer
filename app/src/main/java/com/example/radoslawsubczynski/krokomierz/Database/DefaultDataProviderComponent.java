package com.example.radoslawsubczynski.krokomierz.Database;

import com.example.radoslawsubczynski.krokomierz.Database.Listeners.OnGetAllScores;
import com.example.radoslawsubczynski.krokomierz.Database.Listeners.OnSearchStringValueListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2016-12-03.
 */

public class DefaultDataProviderComponent implements DataProviderComponent, DataProviderListener {

    private static DefaultDataProviderComponent defaultDataProviderComponent;

    public static DefaultDataProviderComponent getInstance() {
        if (defaultDataProviderComponent == null) {
            defaultDataProviderComponent = new DefaultDataProviderComponent();
        }
        return defaultDataProviderComponent;
    }

    private DataProviderModule dataProviderModule = new DatabaseSQLDataProviderModule();


    private static List<OnSearchStringValueListener> searchUserListeners = new ArrayList<>();
    private static List<OnGetAllScores> onGetAllScores = new ArrayList<>();

    private DefaultDataProviderComponent() {
        dataProviderModule.registerListener(this);
    }

    public void registerListenerSearchUser(OnSearchStringValueListener OnSearchStringValueListener) {
        if (!searchUserListeners.contains(OnSearchStringValueListener)) {
            searchUserListeners.add(OnSearchStringValueListener);

        }
    }

    public void unregisterListenerSearchUser(OnSearchStringValueListener OnSearchStringValueListener) {
        if (searchUserListeners.contains(OnSearchStringValueListener)) {
            searchUserListeners.remove(OnSearchStringValueListener);

        }
    }

    private void broadcastOnSearchUserSuccess(String searchUser) {
        for (OnSearchStringValueListener OnSearchStringValueListener : searchUserListeners) {
            OnSearchStringValueListener.onResponseSuccessSearchString(searchUser);
            searchUserListeners.remove(OnSearchStringValueListener);

        }
    }

    private void broadcastOnSearchUserFail() {
        for (OnSearchStringValueListener OnSearchStringValueListener : searchUserListeners) {
            OnSearchStringValueListener.onResponseFailSearchString();

        }
    }

    ///
    public void registerListenerGetAllScore(OnGetAllScores OnGetAllScores) {
        if (!onGetAllScores.contains(OnGetAllScores)) {
            onGetAllScores.add(OnGetAllScores);

        }
    }

    public void unregisterListenerGetAllScore(OnGetAllScores OnGetAllScores) {
        if (onGetAllScores.contains(OnGetAllScores)) {
            onGetAllScores.remove(OnGetAllScores);

        }
    }

    private void broadcastOnGetAllScoreSuccess(String cos) {
        for (OnGetAllScores OnGetAllScores : onGetAllScores) {
            OnGetAllScores.onResponseGetAllScoreSuccess(cos);
            onGetAllScores.remove(OnGetAllScores);

        }
    }

    private void broadcastOnGetAllScoreFail() {
        for (OnGetAllScores OnGetAllScores : onGetAllScores) {
            OnGetAllScores.onResponseOnGetAllScoreFail();

        }
    }

    ///
    @Override
    public void searchString(String SearchValue) {
        dataProviderModule.isStringExist(SearchValue);
    }

    @Override
    public void getAllScore() {
        dataProviderModule.getAllScore();
    }

    @Override
    public void onSearchSuccess(String searchUser) {
        broadcastOnSearchUserSuccess(searchUser);
    }

    @Override
    public void onSearchFail() {
        broadcastOnSearchUserFail();
    }

    @Override
    public void onGetAllContatsSuccess(String cos) {
        broadcastOnGetAllScoreSuccess(cos);
    }

    @Override
    public void onGetAllContatsFail() {
        broadcastOnGetAllScoreFail();
    }
}

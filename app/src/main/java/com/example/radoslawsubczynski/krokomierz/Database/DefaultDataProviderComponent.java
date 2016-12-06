package com.example.radoslawsubczynski.krokomierz.Database;

import com.example.radoslawsubczynski.krokomierz.Database.Listeners.OnGetAllCotacts;
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
    private static List<OnGetAllCotacts> onGetAllCotactses = new ArrayList<>();

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
    public void registerListenerGetAllContats(OnGetAllCotacts OnGetAllCotacts) {
        if (!onGetAllCotactses.contains(OnGetAllCotacts)) {
            onGetAllCotactses.add(OnGetAllCotacts);

        }
    }

    public void unregisterListenerGetAllContats(OnGetAllCotacts OnGetAllCotacts) {
        if (onGetAllCotactses.contains(OnGetAllCotacts)) {
            onGetAllCotactses.remove(OnGetAllCotacts);

        }
    }

    private void broadcastOnGetAllContatsSuccess(String cos) {
        for (OnGetAllCotacts OnGetAllCotacts : onGetAllCotactses) {
            OnGetAllCotacts.onResponseGetAllCotactsSucces(cos);
            onGetAllCotactses.remove(OnGetAllCotacts);

        }
    }

    private void broadcastOnGetAllContatsFail() {
        for (OnGetAllCotacts OnGetAllCotacts : onGetAllCotactses) {
            OnGetAllCotacts.onResponseOnGetAllCotactsFail();

        }
    }

    ///
    @Override
    public void searchString(String SearchValue, String firebaseURL) {
        dataProviderModule.isStringExist(SearchValue, firebaseURL);
    }

    @Override
    public void getAllCotacts() {
        dataProviderModule.getAllContact();
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
        broadcastOnGetAllContatsSuccess(cos);
    }

    @Override
    public void onGetAllContatsFail() {
        broadcastOnGetAllContatsFail();
    }
}

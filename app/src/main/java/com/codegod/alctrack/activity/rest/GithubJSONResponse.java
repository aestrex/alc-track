package com.codegod.alctrack.activity.rest;

import com.codegod.alctrack.activity.model.User;

/**
 * Created by alamzdayveed on 10/03/2017.
 */

public class GithubJSONResponse {
    private int total_count;
    private boolean incomplete_results;
    private User[] items;

    public int getTotal_count() {
        return total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public User[] getItems() {
        return items;
    }
}

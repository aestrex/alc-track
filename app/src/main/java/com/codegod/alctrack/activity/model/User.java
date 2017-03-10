package com.codegod.alctrack.activity.model;

/**
 * Created by alamzdayveed on 10/03/2017.
 */

public class User {
    private String
        login,
        avatar_url,
        gravatar_id,
        url,
        html_url,
        followers_url,
        following_url,
        gists_url,
        starred_url,
        subscriptions_url,
        organizations_url,
        repos_url,
        events_url,
        received_events_url,
        type;

    private int
        id,
        score;

    private boolean site_admin;

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public boolean isSite_admin() {
        return site_admin;
    }
}

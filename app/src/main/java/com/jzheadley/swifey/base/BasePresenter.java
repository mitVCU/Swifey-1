package com.jzheadley.swifey.base;

/**
 * Your main business logic should be written here.
 */
public interface BasePresenter {
    /**
     * Things that you do when the activity start should go here
     */
    void subscribe();

    /**
     * Things that you do when the activity stops or pauses should go here
     */
    void unsubscribe();
}

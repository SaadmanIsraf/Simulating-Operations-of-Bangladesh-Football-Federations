package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility;

/**
 * Implement this in any FXML controller that needs data passed into it
 * when a scene is switched (e.g. the logged-in user, a selected player,
 * a selected club). Keeps SceneSwitchingHelper generic instead of tied
 * to one specific domain class.
 */
public interface DataReceiver<T> {
    void receiveData(T data);
}


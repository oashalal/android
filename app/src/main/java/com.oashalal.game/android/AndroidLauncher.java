package com.oashalal.game.android;

import com.jme3.app.AndroidHarness;
import com.oashalal.game.game.Mygame;


public class AndroidLauncher extends AndroidHarness {

    public AndroidLauncher() {
        appClass = Mygame.class.getCanonicalName();
    }
}

package com.example.alan.italker;

import com.alan.push.common.activities.Application;
import com.example.alan.factory.Factory;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function :
 *         Issue :
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Factory.setup();
    }
}

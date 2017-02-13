package com.example.andresarango.swiper.fences;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.android.gms.awareness.fence.FenceState;

/**
 * Created by andresarango on 1/31/17.
 */

public class UserFenceReciever extends BroadcastReceiver {


    private final FenceManager mFenceManager;

    public UserFenceReciever(FenceManager fenceManager) {
        mFenceManager = fenceManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);
        if (TextUtils.equals(fenceState.getFenceKey(), FenceManager.FENCE_KEY)) {
            System.out.println("got fence key");
            if (fenceState.getCurrentState() == FenceState.TRUE) {
                mFenceManager.replaceFence();
            }
        } else {
            System.out.println("SOMETHING HAPPENED");
        }

    }
}

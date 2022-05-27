/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.eink.statusbar;

import android.annotation.Nullable;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import static android.view.Display.INVALID_DISPLAY;

import android.os.EinkManager;
import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.android.systemui.Dependency;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.statusbar.phone.CollapsedStatusBarFragment;
import com.android.systemui.R;

public class EinkCollapsedStatusBarFragment extends CollapsedStatusBarFragment {
    public static String TAG = "EinkCollapsedStatusBarFragment";
    private EinkManager mEinkManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mEinkManager == null){
            mEinkManager = (EinkManager) getContext().getSystemService(Context.EINK_SERVICE);
        }
        super.onViewCreated(view, savedInstanceState);
        ImageView home = view.findViewById(R.id.home);
        ImageView back = view.findViewById(R.id.back);
        ImageView recents = view.findViewById(R.id.recents);
        ImageView refresh = view.findViewById(R.id.refresh);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendKey(KeyEvent.KEYCODE_HOME); }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendKey(KeyEvent.KEYCODE_BACK); }
        });

        recents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendKey(KeyEvent.KEYCODE_APP_SWITCH); }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEinkManager != null) {
                    mEinkManager.sendOneFullFrame();
                    view.postInvalidate();
                }
            }
        });
    }


    private void sendKey(int code) {
        long when = SystemClock.uptimeMillis();
        final KeyEvent evDown = new KeyEvent(when, when, KeyEvent.ACTION_DOWN, code, 0 /* repeat */,
                0 /* metaState */, KeyCharacterMap.VIRTUAL_KEYBOARD, 0 /* scancode */,
                KeyEvent.FLAG_FROM_SYSTEM | KeyEvent.FLAG_VIRTUAL_HARD_KEY,
                InputDevice.SOURCE_KEYBOARD);
        final KeyEvent evUp = new KeyEvent(when, when, KeyEvent.ACTION_UP, code, 0 /* repeat */,
                0 /* metaState */, KeyCharacterMap.VIRTUAL_KEYBOARD, 0 /* scancode */,
                KeyEvent.FLAG_FROM_SYSTEM | KeyEvent.FLAG_VIRTUAL_HARD_KEY,
                InputDevice.SOURCE_KEYBOARD);

        InputManager.getInstance().injectInputEvent(evDown, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
        InputManager.getInstance().injectInputEvent(evUp, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
    }
}

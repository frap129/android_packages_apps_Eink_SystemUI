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

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSContainerImplController;
import com.android.systemui.qs.QSFragment;
import com.android.systemui.qs.QSTileHost;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.util.InjectionInflationController;

public class EinkQSFragment extends QSFragment {
    public EinkQSFragment(
            RemoteInputQuickSettingsDisabler remoteInputQsDisabler,
            InjectionInflationController injectionInflater,
            QSTileHost qsTileHost,
            StatusBarStateController statusBarStateController,
            CommandQueue commandQueue,
            QSContainerImplController.Builder qsContainerImplControllerBuilder) {
        super(remoteInputQsDisabler, injectionInflater, qsTileHost, statusBarStateController,
                commandQueue, qsContainerImplControllerBuilder);
    }
}

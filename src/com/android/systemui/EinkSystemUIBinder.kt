/*
 * Copyright (C) 2019 The Android Open Source Project
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
package com.android.systemui.dagger

import com.android.systemui.*
import com.android.systemui.accessibility.SystemActions
import com.android.systemui.accessibility.WindowMagnification
import com.android.systemui.biometrics.AuthController
import com.android.systemui.bubbles.dagger.BubbleModule
import com.android.systemui.eink.statusbar.EinkStatusBar
import com.android.systemui.globalactions.GlobalActionsComponent
import com.android.systemui.keyguard.KeyguardViewMediator
import com.android.systemui.keyguard.dagger.KeyguardModule
import com.android.systemui.pip.PipUI
import com.android.systemui.power.PowerUI
import com.android.systemui.recents.Recents
import com.android.systemui.recents.RecentsModule
import com.android.systemui.shortcut.ShortcutKeyDispatcher
import com.android.systemui.stackdivider.Divider
import com.android.systemui.statusbar.dagger.StatusBarModule
import com.android.systemui.statusbar.notification.InstantAppNotifier
import com.android.systemui.statusbar.phone.StatusBar
import com.android.systemui.statusbar.tv.TvStatusBar
import com.android.systemui.theme.ThemeOverlayController
import com.android.systemui.toast.ToastUI
import com.android.systemui.util.leak.GarbageMonitor
import com.android.systemui.volume.VolumeUI
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

/**
 * SystemUI objects that are injectable should go here.
 */
@Module(includes = [RecentsModule::class, StatusBarModule::class, BubbleModule::class, KeyguardModule::class])
abstract class EinkSystemUIBinder {
    /** Inject into AuthController.  */
    @Binds
    @IntoMap
    @ClassKey(AuthController::class)
    abstract fun bindAuthController(service: AuthController?): SystemUI?

    /** Inject into Divider.  */
    @Binds
    @IntoMap
    @ClassKey(Divider::class)
    abstract fun bindDivider(sysui: Divider?): SystemUI?

    /** Inject into GarbageMonitor.Service.  */
    @Binds
    @IntoMap
    @ClassKey(GarbageMonitor.Service::class)
    abstract fun bindGarbageMonitorService(sysui: GarbageMonitor.Service?): SystemUI?

    /** Inject into GlobalActionsComponent.  */
    @Binds
    @IntoMap
    @ClassKey(GlobalActionsComponent::class)
    abstract fun bindGlobalActionsComponent(sysui: GlobalActionsComponent?): SystemUI?

    /** Inject into InstantAppNotifier.  */
    @Binds
    @IntoMap
    @ClassKey(InstantAppNotifier::class)
    abstract fun bindInstantAppNotifier(sysui: InstantAppNotifier?): SystemUI?

    /** Inject into KeyguardViewMediator.  */
    @Binds
    @IntoMap
    @ClassKey(KeyguardViewMediator::class)
    abstract fun bindKeyguardViewMediator(sysui: KeyguardViewMediator?): SystemUI?


    /** Inject into PipUI.  */
    @Binds
    @IntoMap
    @ClassKey(PipUI::class)
    abstract fun bindPipUI(sysui: PipUI?): SystemUI?

    /** Inject into PowerUI.  */
    @Binds
    @IntoMap
    @ClassKey(PowerUI::class)
    abstract fun bindPowerUI(sysui: PowerUI?): SystemUI?

    /** Inject into Recents.  */
    @Binds
    @IntoMap
    @ClassKey(Recents::class)
    abstract fun bindRecents(sysui: Recents?): SystemUI?

    /** Inject into ScreenDecorations.  */
    @Binds
    @IntoMap
    @ClassKey(ScreenDecorations::class)
    abstract fun bindScreenDecorations(sysui: ScreenDecorations?): SystemUI?

    /** Inject into ShortcutKeyDispatcher.  */
    @Binds
    @IntoMap
    @ClassKey(ShortcutKeyDispatcher::class)
    abstract fun bindsShortcutKeyDispatcher(sysui: ShortcutKeyDispatcher?): SystemUI?

    /** Inject into SizeCompatModeActivityController.  */
    @Binds
    @IntoMap
    @ClassKey(SizeCompatModeActivityController::class)
    abstract fun bindsSizeCompatModeActivityController(
            sysui: SizeCompatModeActivityController?): SystemUI?

    /** Inject into SliceBroadcastRelayHandler.  */
    @Binds
    @IntoMap
    @ClassKey(SliceBroadcastRelayHandler::class)
    abstract fun bindSliceBroadcastRelayHandler(sysui: SliceBroadcastRelayHandler?): SystemUI?

    /** Inject into StatusBar.  */
    @Binds
    @IntoMap
    @ClassKey(StatusBar::class)
    abstract fun bindsStatusBar(sysui: StatusBar?): SystemUI?

    /** Inject into SystemActions.  */
    @Binds
    @IntoMap
    @ClassKey(SystemActions::class)
    abstract fun bindSystemActions(sysui: SystemActions?): SystemUI?

    /** Inject into ThemeOverlayController.  */
    @Binds
    @IntoMap
    @ClassKey(ThemeOverlayController::class)
    abstract fun bindThemeOverlayController(sysui: ThemeOverlayController?): SystemUI?

    /** Inject into ToastUI.  */
    @Binds
    @IntoMap
    @ClassKey(ToastUI::class)
    abstract fun bindToastUI(service: ToastUI?): SystemUI?

    /** Inject into VolumeUI.  */
    @Binds
    @IntoMap
    @ClassKey(VolumeUI::class)
    abstract fun bindVolumeUI(sysui: VolumeUI?): SystemUI?

    /** Inject into WindowMagnification.  */
    @Binds
    @IntoMap
    @ClassKey(WindowMagnification::class)
    abstract fun bindWindowMagnification(sysui: WindowMagnification?): SystemUI?
}
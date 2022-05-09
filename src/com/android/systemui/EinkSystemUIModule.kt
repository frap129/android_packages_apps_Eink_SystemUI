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

import android.content.Context
import android.os.Handler
import android.os.PowerManager
import com.android.keyguard.KeyguardViewController
import com.android.systemui.CarSystemUIRootComponent
import com.android.systemui.Dependency
import com.android.systemui.EinkSystemUIRootComponent
import com.android.systemui.broadcast.BroadcastDispatcher
import com.android.systemui.dagger.qualifiers.Background
import com.android.systemui.dagger.qualifiers.Main
import com.android.systemui.dock.DockManager
import com.android.systemui.dock.DockManagerImpl
import com.android.systemui.doze.DozeHost
import com.android.systemui.plugins.qs.QSFactory
import com.android.systemui.plugins.statusbar.StatusBarStateController
import com.android.systemui.power.EnhancedEstimates
import com.android.systemui.power.EnhancedEstimatesImpl
import com.android.systemui.qs.dagger.QSModule
import com.android.systemui.qs.tileimpl.QSFactoryImpl
import com.android.systemui.recents.Recents
import com.android.systemui.recents.RecentsImplementation
import com.android.systemui.stackdivider.DividerModule
import com.android.systemui.statusbar.CommandQueue
import com.android.systemui.statusbar.NotificationLockscreenUserManager
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl
import com.android.systemui.statusbar.notification.NotificationEntryManager.KeyguardEnvironment
import com.android.systemui.statusbar.phone.*
import com.android.systemui.statusbar.policy.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * A dagger module for injecting default implementations of components of System UI that may be
 * overridden by the System UI implementation.
 */
@Module(includes = [DividerModule::class, QSModule::class])
abstract class EinkSystemUIModule {
    @Binds
    abstract fun bindEnhancedEstimates(enhancedEstimates: EnhancedEstimatesImpl?): EnhancedEstimates?
    @Binds
    abstract fun bindNotificationLockscreenUserManager(
            notificationLockscreenUserManager: NotificationLockscreenUserManagerImpl?): NotificationLockscreenUserManager?

    @Binds
    @Singleton
    abstract fun bindQSFactory(qsFactoryImpl: QSFactoryImpl?): QSFactory?
    @Binds
    abstract fun bindDockManager(dockManager: DockManagerImpl?): DockManager?
    @Binds
    abstract fun bindKeyguardEnvironment(
            keyguardEnvironment: KeyguardEnvironmentImpl?): KeyguardEnvironment?

    @Binds
    abstract fun provideShadeController(shadeController: ShadeControllerImpl?): ShadeController?
    @Binds
    abstract fun bindHeadsUpManagerPhone(headsUpManagerPhone: HeadsUpManagerPhone?): HeadsUpManager?
    @Binds
    abstract fun bindDeviceProvisionedController(
            deviceProvisionedController: DeviceProvisionedControllerImpl?): DeviceProvisionedController?

    @Binds
    abstract fun bindKeyguardViewController(
            statusBarKeyguardViewManager: StatusBarKeyguardViewManager?): KeyguardViewController?

    @Binds
    abstract fun provideDozeHost(dozeServiceHost: DozeServiceHost?): DozeHost?

    @Binds
    abstract fun bindSystemUIRootComponent(
            systemUIRootComponent: EinkSystemUIRootComponent?): SystemUIRootComponent?

    companion object {
        @Singleton
        @Provides
        @Named(Dependency.LEAK_REPORT_EMAIL_NAME)
        fun provideLeakReportEmail(): String? {
            return null
        }

        @Provides
        @Singleton
        fun provideBatteryController(context: Context?,
                                     enhancedEstimates: EnhancedEstimates?, powerManager: PowerManager?,
                                     broadcastDispatcher: BroadcastDispatcher?, @Main mainHandler: Handler?,
                                     @Background bgHandler: Handler?): BatteryController {
            val bC: BatteryController = BatteryControllerImpl(context, enhancedEstimates, powerManager,
                    broadcastDispatcher, mainHandler, bgHandler)
            bC.init()
            return bC
        }

        @Singleton
        @Provides
        @Named(Dependency.ALLOW_NOTIFICATION_LONG_PRESS_NAME)
        fun provideAllowNotificationLongPress(): Boolean {
            return true
        }

        @Singleton
        @Provides
        fun provideHeadsUpManagerPhone(
                context: Context?,
                statusBarStateController: StatusBarStateController?,
                bypassController: KeyguardBypassController?,
                groupManager: NotificationGroupManager?,
                configurationController: ConfigurationController?): HeadsUpManagerPhone {
            return HeadsUpManagerPhone(context!!, statusBarStateController, bypassController,
                    groupManager, configurationController)
        }

        @Provides
        @Singleton
        fun provideRecents(context: Context?, recentsImplementation: RecentsImplementation?,
                           commandQueue: CommandQueue?): Recents {
            return Recents(context, recentsImplementation, commandQueue)
        }
    }
}
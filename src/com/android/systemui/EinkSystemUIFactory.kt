/*
 * Copyright (C) 2018 The Android Open Source Project
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
 * limitations under the License
 */
package com.android.systemui

import android.content.Context
import android.content.res.Resources
import com.android.systemui.dagger.SystemUIRootComponent

/**
 * Class factory to provide car specific SystemUI components.
 */
class EinkSystemUIFactory : SystemUIFactory() {
    override fun buildSystemUIRootComponent(context: Context): SystemUIRootComponent {
        return DaggerEinkSystemUIRootComponent.builder()
                .contextHolder(ContextHolder(context))
                .build()
    }

    override fun getSystemUIServiceComponents(resources: Resources): Array<String> {
        val names: MutableSet<String> = HashSet()
        for (s in super.getSystemUIServiceComponents(resources)) {
            names.add(s)
        }
        for (s in resources.getStringArray(R.array.config_systemUIServiceComponentsExclude)) {
            names.remove(s)
        }
        for (s in resources.getStringArray(R.array.config_systemUIServiceComponentsInclude)) {
            names.add(s)
        }
        return names.toTypedArray()
    }
}
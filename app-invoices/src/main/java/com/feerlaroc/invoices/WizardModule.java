/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feerlaroc.invoices;

import dagger.Module;
import dagger.Provides;

/**
 * Defines app-wide singletons
 */
@Module(addsTo = ActivityModule.class, library = true)

public class WizardModule {

    private final WizardState wizardState;

    public WizardModule() {
        wizardState = new WizardState();
    }

    @Provides
    public WizardState providesWizardModule() {
        return wizardState;
    }


    public class WizardState {

        public int count;

        public WizardState() {
            count = 0;
        }
    }

}

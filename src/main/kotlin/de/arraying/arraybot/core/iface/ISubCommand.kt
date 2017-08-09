package de.arraying.arraybot.core.iface

import de.arraying.arraybot.commands.other.CommandEnvironment

/**
 * Copyright 2017 Arraying
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
interface ISubCommand {

    /**
     * Gets the name.
     */
    fun getName(): String

    /**
     * Gets the aliases.
     */
    fun getAliases(): Array<String> {
        return arrayOf()
    }

    /**
     * What happens when the subcommand is invoked.
     */
    fun onSubCommand(environment: CommandEnvironment, args: Array<String>)

}
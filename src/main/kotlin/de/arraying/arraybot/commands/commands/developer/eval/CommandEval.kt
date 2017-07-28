package de.arraying.arraybot.commands.commands.developer.eval

import de.arraying.arraybot.commands.CommandEnvironment
import de.arraying.arraybot.commands.entities.DefaultCommand
import net.dv8tion.jda.core.Permission

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
class CommandEval:
        DefaultCommand("eval",
                CommandCategory.DEVELOPER,
                Permission.MESSAGE_WRITE) {

    /**
     * When the command is executed.
     */
    override fun onDefaultCommand(environment: CommandEnvironment, args: Array<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
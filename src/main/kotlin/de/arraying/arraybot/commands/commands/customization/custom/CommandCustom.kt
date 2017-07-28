package de.arraying.arraybot.commands.commands.customization.custom

import de.arraying.arraybot.commands.CommandEnvironment
import de.arraying.arraybot.commands.entities.DefaultCommand
import de.arraying.arraybot.commands.entities.SubCommand
import de.arraying.arraybot.language.Messages
import de.arraying.arraybot.utils.Utils
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
class CommandCustom(override val subCommands: Array<SubCommand>):
        DefaultCommand("custom",
                CommandCategory.CUSTOMIZATION,
                Permission.MANAGE_SERVER,
                subCommands) {

    /**
     * Invokes the command. Shows a list of commands.
     */
    override fun onDefaultCommand(environment: CommandEnvironment, args: Array<String>) {
        val channel = environment.channel
        val embed = Utils.getEmbed(channel)
                .setDescription(Messages.COMMAND_CUSTOM_EMBED_DESCRIPTION.content(channel))
                .addField(Messages.COMMAND_CUSTOM_EMBED_TITLE.content(channel),
                        Messages.COMMAND_CUSTOM_EMBED_VALUE.content(channel),
                        false)
        channel.sendMessage(embed.build()).queue()
    }

}
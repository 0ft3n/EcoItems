package com.willfp.ecoitems.libreforge

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.util.containsIgnoreCase
import com.willfp.ecoitems.items.EcoItem
import com.willfp.ecoitems.items.ecoItems
import com.willfp.libreforge.Dispatcher
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.ProvidedHolder
import com.willfp.libreforge.arguments
import com.willfp.libreforge.conditions.Condition
import com.willfp.libreforge.get
import org.bukkit.entity.Player

object ConditionHasEcoItem : Condition<NoCompileData>("has_ecoitem") {
    override val arguments = arguments {
        require("item", "You must specify the item!")
    }

    override fun isMet(
        dispatcher: Dispatcher<*>,
        config: Config,
        holder: ProvidedHolder,
        compileData: NoCompileData
    ): Boolean {
        val player = dispatcher.get<Player>() ?: return false

        return player.ecoItems
            .map { it.holder }
            .filterIsInstance<EcoItem>()
            .map { it.id.key }
            .containsIgnoreCase(config.getString("item"))
    }
}

package it.menzani.bts.components.disablecommand;

import it.menzani.bts.BornToSurvive;
import it.menzani.bts.components.SimpleComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Set;

public class DisableCommand extends SimpleComponent {
    private static final Set<String> consoleOnlyCommandLabels = Set.of(
            "plugins", "pl"
    );
    private static final Set<String> disabledCommandLabels = Set.of(
            "reload", "rl"
    );
    private static final String CANCEL_SUFFIX = "------";

    public DisableCommand(BornToSurvive bornToSurvive) {
        super(bornToSurvive);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String label = event.getMessage().substring(1);
        if (isDisabled(label) || consoleOnlyCommandLabels.contains(label)) {
            event.setMessage(label + CANCEL_SUFFIX);
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        String label = event.getCommand();
        if (isDisabled(label)) {
            event.setCommand(label + CANCEL_SUFFIX);
        }
    }

    private static boolean isDisabled(String label) {
        return disabledCommandLabels.contains(label);
    }
}
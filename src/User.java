package it.menzani.bts;

import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import com.destroystokyo.paper.profile.PlayerProfile;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class User implements Player {
    private static final String messagePrefix = ChatColor.BLUE + "BTS> " + ChatColor.GRAY;

    private final Player delegate;

    public User(Player delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    public void sendMessageFormat(String base, Object... important) {
        for (int i = 0; i < important.length; i++) {
            Object object = important[i];
            if (object instanceof Player) {
                object = ChatColor.RESET + ((Player) object).getDisplayName();
            }
            base = base.replace('{' + Integer.toString(i + 1) + '}', ChatColor.GREEN + object.toString() + ChatColor.GRAY);
        }
        sendMessage(messagePrefix + base);
    }

    public boolean addPotionEffect(PotionEffectType type, Duration duration, int level, PotionEffects.ParticleType particleType) {
        return addPotionEffect(PotionEffects.newPotionEffect(type, duration, level, particleType));
    }

    @Override
    public String getDisplayName() {
        return delegate.getDisplayName();
    }

    @Override
    public void setDisplayName(String s) {
        delegate.setDisplayName(s);
    }

    @Override
    public String getPlayerListName() {
        return delegate.getPlayerListName();
    }

    @Override
    public void setPlayerListName(String s) {
        delegate.setPlayerListName(s);
    }

    @Override
    public String getPlayerListHeader() {
        return delegate.getPlayerListHeader();
    }

    @Override
    public String getPlayerListFooter() {
        return delegate.getPlayerListFooter();
    }

    @Override
    public void setPlayerListHeader(String s) {
        delegate.setPlayerListHeader(s);
    }

    @Override
    public void setPlayerListFooter(String s) {
        delegate.setPlayerListFooter(s);
    }

    @Override
    public void setPlayerListHeaderFooter(String s, String s1) {
        delegate.setPlayerListHeaderFooter(s, s1);
    }

    @Override
    public void setCompassTarget(Location location) {
        delegate.setCompassTarget(location);
    }

    @Override
    public Location getCompassTarget() {
        return delegate.getCompassTarget();
    }

    @Override
    public InetSocketAddress getAddress() {
        return delegate.getAddress();
    }

    @Override
    public void sendRawMessage(String s) {
        delegate.sendRawMessage(s);
    }

    @Override
    public void kickPlayer(String s) {
        delegate.kickPlayer(s);
    }

    @Override
    public void chat(String s) {
        delegate.chat(s);
    }

    @Override
    public boolean performCommand(String s) {
        return delegate.performCommand(s);
    }

    @Override
    public boolean isSneaking() {
        return delegate.isSneaking();
    }

    @Override
    public void setSneaking(boolean b) {
        delegate.setSneaking(b);
    }

    @Override
    public boolean isSprinting() {
        return delegate.isSprinting();
    }

    @Override
    public void setSprinting(boolean b) {
        delegate.setSprinting(b);
    }

    @Override
    public void saveData() {
        delegate.saveData();
    }

    @Override
    public void loadData() {
        delegate.loadData();
    }

    @Override
    public void setSleepingIgnored(boolean b) {
        delegate.setSleepingIgnored(b);
    }

    @Override
    public boolean isSleepingIgnored() {
        return delegate.isSleepingIgnored();
    }

    @Override
    @Deprecated
    public void playNote(Location location, byte b, byte b1) {
        delegate.playNote(location, b, b1);
    }

    @Override
    public void playNote(Location location, Instrument instrument, Note note) {
        delegate.playNote(location, instrument, note);
    }

    @Override
    public void playSound(Location location, Sound sound, float v, float v1) {
        delegate.playSound(location, sound, v, v1);
    }

    @Override
    public void playSound(Location location, String s, float v, float v1) {
        delegate.playSound(location, s, v, v1);
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {
        delegate.playSound(location, sound, soundCategory, v, v1);
    }

    @Override
    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {
        delegate.playSound(location, s, soundCategory, v, v1);
    }

    @Override
    public void stopSound(Sound sound) {
        delegate.stopSound(sound);
    }

    @Override
    public void stopSound(String s) {
        delegate.stopSound(s);
    }

    @Override
    public void stopSound(Sound sound, SoundCategory soundCategory) {
        delegate.stopSound(sound, soundCategory);
    }

    @Override
    public void stopSound(String s, SoundCategory soundCategory) {
        delegate.stopSound(s, soundCategory);
    }

    @Override
    @Deprecated
    public void playEffect(Location location, Effect effect, int i) {
        delegate.playEffect(location, effect, i);
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {
        delegate.playEffect(location, effect, t);
    }

    @Override
    @Deprecated
    public void sendBlockChange(Location location, Material material, byte b) {
        delegate.sendBlockChange(location, material, b);
    }

    @Override
    public void sendBlockChange(Location location, BlockData blockData) {
        delegate.sendBlockChange(location, blockData);
    }

    @Override
    @Deprecated
    public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
        return delegate.sendChunkChange(location, i, i1, i2, bytes);
    }

    @Override
    public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {
        delegate.sendSignChange(location, strings);
    }

    @Override
    public void sendMap(MapView mapView) {
        delegate.sendMap(mapView);
    }

    @Override
    public BanEntry banPlayerFull(String reason) {
        return delegate.banPlayerFull(reason);
    }

    @Override
    public BanEntry banPlayerFull(String reason, String source) {
        return delegate.banPlayerFull(reason, source);
    }

    @Override
    public BanEntry banPlayerFull(String reason, Date expires) {
        return delegate.banPlayerFull(reason, expires);
    }

    @Override
    public BanEntry banPlayerFull(String reason, Date expires, String source) {
        return delegate.banPlayerFull(reason, expires, source);
    }

    @Override
    public BanEntry banPlayerIP(String reason, boolean kickPlayer) {
        return delegate.banPlayerIP(reason, kickPlayer);
    }

    @Override
    public BanEntry banPlayerIP(String reason, String source, boolean kickPlayer) {
        return delegate.banPlayerIP(reason, source, kickPlayer);
    }

    @Override
    public BanEntry banPlayerIP(String reason, Date expires, boolean kickPlayer) {
        return delegate.banPlayerIP(reason, expires, kickPlayer);
    }

    @Override
    public BanEntry banPlayerIP(String reason) {
        return delegate.banPlayerIP(reason);
    }

    @Override
    public BanEntry banPlayerIP(String reason, String source) {
        return delegate.banPlayerIP(reason, source);
    }

    @Override
    public BanEntry banPlayerIP(String reason, Date expires) {
        return delegate.banPlayerIP(reason, expires);
    }

    @Override
    public BanEntry banPlayerIP(String reason, Date expires, String source) {
        return delegate.banPlayerIP(reason, expires, source);
    }

    @Override
    public BanEntry banPlayerIP(String reason, Date expires, String source, boolean kickPlayer) {
        return delegate.banPlayerIP(reason, expires, source, kickPlayer);
    }

    @Override
    public void sendActionBar(String s) {
        delegate.sendActionBar(s);
    }

    @Override
    public void sendActionBar(char c, String s) {
        delegate.sendActionBar(c, s);
    }

    @Override
    public void sendMessage(BaseComponent component) {
        delegate.sendMessage(component);
    }

    @Override
    public void sendMessage(BaseComponent... components) {
        delegate.sendMessage(components);
    }

    @Override
    @Deprecated
    public void sendMessage(ChatMessageType position, BaseComponent... components) {
        delegate.sendMessage(position, components);
    }

    @Override
    public void setPlayerListHeaderFooter(BaseComponent[] baseComponents, BaseComponent[] baseComponents1) {
        delegate.setPlayerListHeaderFooter(baseComponents, baseComponents1);
    }

    @Override
    public void setPlayerListHeaderFooter(BaseComponent baseComponent, BaseComponent baseComponent1) {
        delegate.setPlayerListHeaderFooter(baseComponent, baseComponent1);
    }

    @Override
    @Deprecated
    public void setTitleTimes(int i, int i1, int i2) {
        delegate.setTitleTimes(i, i1, i2);
    }

    @Override
    @Deprecated
    public void setSubtitle(BaseComponent[] baseComponents) {
        delegate.setSubtitle(baseComponents);
    }

    @Override
    @Deprecated
    public void setSubtitle(BaseComponent baseComponent) {
        delegate.setSubtitle(baseComponent);
    }

    @Override
    @Deprecated
    public void showTitle(BaseComponent[] baseComponents) {
        delegate.showTitle(baseComponents);
    }

    @Override
    @Deprecated
    public void showTitle(BaseComponent baseComponent) {
        delegate.showTitle(baseComponent);
    }

    @Override
    @Deprecated
    public void showTitle(BaseComponent[] baseComponents, BaseComponent[] baseComponents1, int i, int i1, int i2) {
        delegate.showTitle(baseComponents, baseComponents1, i, i1, i2);
    }

    @Override
    @Deprecated
    public void showTitle(BaseComponent baseComponent, BaseComponent baseComponent1, int i, int i1, int i2) {
        delegate.showTitle(baseComponent, baseComponent1, i, i1, i2);
    }

    @Override
    public void sendTitle(Title title) {
        delegate.sendTitle(title);
    }

    @Override
    public void updateTitle(Title title) {
        delegate.updateTitle(title);
    }

    @Override
    public void hideTitle() {
        delegate.hideTitle();
    }

    @Override
    public void updateInventory() {
        delegate.updateInventory();
    }

    @Override
    @Deprecated
    public void awardAchievement(Achievement achievement) {
        delegate.awardAchievement(achievement);
    }

    @Override
    @Deprecated
    public void removeAchievement(Achievement achievement) {
        delegate.removeAchievement(achievement);
    }

    @Override
    @Deprecated
    public boolean hasAchievement(Achievement achievement) {
        return delegate.hasAchievement(achievement);
    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        delegate.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic, i);
    }

    @Override
    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        delegate.decrementStatistic(statistic, i);
    }

    @Override
    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        delegate.setStatistic(statistic, i);
    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return delegate.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        delegate.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return delegate.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic, material, i);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        delegate.decrementStatistic(statistic, material, i);
    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        delegate.setStatistic(statistic, material, i);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        delegate.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return delegate.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
        delegate.incrementStatistic(statistic, entityType, i);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
        delegate.decrementStatistic(statistic, entityType, i);
    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int i) {
        delegate.setStatistic(statistic, entityType, i);
    }

    @Override
    public void setPlayerTime(long l, boolean b) {
        delegate.setPlayerTime(l, b);
    }

    @Override
    public long getPlayerTime() {
        return delegate.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return delegate.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return delegate.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        delegate.resetPlayerTime();
    }

    @Override
    public void setPlayerWeather(WeatherType weatherType) {
        delegate.setPlayerWeather(weatherType);
    }

    @Override
    public WeatherType getPlayerWeather() {
        return delegate.getPlayerWeather();
    }

    @Override
    public void resetPlayerWeather() {
        delegate.resetPlayerWeather();
    }

    @Override
    public void giveExp(int amount) {
        delegate.giveExp(amount);
    }

    @Override
    public void giveExp(int i, boolean b) {
        delegate.giveExp(i, b);
    }

    @Override
    public int applyMending(int i) {
        return delegate.applyMending(i);
    }

    @Override
    public void giveExpLevels(int i) {
        delegate.giveExpLevels(i);
    }

    @Override
    public float getExp() {
        return delegate.getExp();
    }

    @Override
    public void setExp(float v) {
        delegate.setExp(v);
    }

    @Override
    public int getLevel() {
        return delegate.getLevel();
    }

    @Override
    public void setLevel(int i) {
        delegate.setLevel(i);
    }

    @Override
    public int getTotalExperience() {
        return delegate.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int i) {
        delegate.setTotalExperience(i);
    }

    @Override
    public float getExhaustion() {
        return delegate.getExhaustion();
    }

    @Override
    public void setExhaustion(float v) {
        delegate.setExhaustion(v);
    }

    @Override
    public float getSaturation() {
        return delegate.getSaturation();
    }

    @Override
    public void setSaturation(float v) {
        delegate.setSaturation(v);
    }

    @Override
    public int getFoodLevel() {
        return delegate.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int i) {
        delegate.setFoodLevel(i);
    }

    @Override
    public boolean getAllowFlight() {
        return delegate.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean b) {
        delegate.setAllowFlight(b);
    }

    @Override
    @Deprecated
    public void hidePlayer(Player player) {
        delegate.hidePlayer(player);
    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {
        delegate.hidePlayer(plugin, player);
    }

    @Override
    @Deprecated
    public void showPlayer(Player player) {
        delegate.showPlayer(player);
    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {
        delegate.showPlayer(plugin, player);
    }

    @Override
    public boolean canSee(Player player) {
        return delegate.canSee(player);
    }

    @Override
    public boolean isFlying() {
        return delegate.isFlying();
    }

    @Override
    public void setFlying(boolean b) {
        delegate.setFlying(b);
    }

    @Override
    public void setFlySpeed(float v) throws IllegalArgumentException {
        delegate.setFlySpeed(v);
    }

    @Override
    public void setWalkSpeed(float v) throws IllegalArgumentException {
        delegate.setWalkSpeed(v);
    }

    @Override
    public float getFlySpeed() {
        return delegate.getFlySpeed();
    }

    @Override
    public float getWalkSpeed() {
        return delegate.getWalkSpeed();
    }

    @Override
    @Deprecated
    public void setTexturePack(String s) {
        delegate.setTexturePack(s);
    }

    @Override
    @Deprecated
    public void setResourcePack(String s) {
        delegate.setResourcePack(s);
    }

    @Override
    public void setResourcePack(String s, byte[] bytes) {
        delegate.setResourcePack(s, bytes);
    }

    @Override
    public Scoreboard getScoreboard() {
        return delegate.getScoreboard();
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        delegate.setScoreboard(scoreboard);
    }

    @Override
    public boolean isHealthScaled() {
        return delegate.isHealthScaled();
    }

    @Override
    public void setHealthScaled(boolean b) {
        delegate.setHealthScaled(b);
    }

    @Override
    public void setHealthScale(double v) throws IllegalArgumentException {
        delegate.setHealthScale(v);
    }

    @Override
    public double getHealthScale() {
        return delegate.getHealthScale();
    }

    @Override
    public Entity getSpectatorTarget() {
        return delegate.getSpectatorTarget();
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        delegate.setSpectatorTarget(entity);
    }

    @Override
    @Deprecated
    public void sendTitle(String s, String s1) {
        delegate.sendTitle(s, s1);
    }

    @Override
    public void sendTitle(String s, String s1, int i, int i1, int i2) {
        delegate.sendTitle(s, s1, i, i1, i2);
    }

    @Override
    public void resetTitle() {
        delegate.resetTitle();
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i) {
        delegate.spawnParticle(particle, location, i);
    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {
        delegate.spawnParticle(particle, v, v1, v2, i);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {
        delegate.spawnParticle(particle, location, i, t);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {
        delegate.spawnParticle(particle, v, v1, v2, i, t);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {
        delegate.spawnParticle(particle, location, i, v, v1, v2);
    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        delegate.spawnParticle(particle, v, v1, v2, i, v3, v4, v5);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {
        delegate.spawnParticle(particle, location, i, v, v1, v2, t);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {
        delegate.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {
        delegate.spawnParticle(particle, location, i, v, v1, v2, v3);
    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        delegate.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {
        delegate.spawnParticle(particle, location, i, v, v1, v2, v3, t);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {
        delegate.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t);
    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return delegate.getAdvancementProgress(advancement);
    }

    @Override
    public int getClientViewDistance() {
        return delegate.getClientViewDistance();
    }

    @Override
    public String getLocale() {
        return delegate.getLocale();
    }

    @Override
    public boolean getAffectsSpawning() {
        return delegate.getAffectsSpawning();
    }

    @Override
    public void setAffectsSpawning(boolean b) {
        delegate.setAffectsSpawning(b);
    }

    @Override
    public void updateCommands() {
        delegate.updateCommands();
    }

    @Override
    public int getViewDistance() {
        return delegate.getViewDistance();
    }

    @Override
    public void setViewDistance(int i) {
        delegate.setViewDistance(i);
    }

    @Override
    public void setResourcePack(String s, String s1) {
        delegate.setResourcePack(s, s1);
    }

    @Override
    public PlayerResourcePackStatusEvent.Status getResourcePackStatus() {
        return delegate.getResourcePackStatus();
    }

    @Override
    @Deprecated
    public String getResourcePackHash() {
        return delegate.getResourcePackHash();
    }

    @Override
    public boolean hasResourcePack() {
        return delegate.hasResourcePack();
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return delegate.getPlayerProfile();
    }

    @Override
    public void setPlayerProfile(PlayerProfile playerProfile) {
        delegate.setPlayerProfile(playerProfile);
    }

    @Override
    public float getCooldownPeriod() {
        return delegate.getCooldownPeriod();
    }

    @Override
    public float getCooledAttackStrength(float v) {
        return delegate.getCooledAttackStrength(v);
    }

    @Override
    public void resetCooldown() {
        delegate.resetCooldown();
    }

    @Override
    public Spigot spigot() {
        return delegate.spigot();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public PlayerInventory getInventory() {
        return delegate.getInventory();
    }

    @Override
    public Inventory getEnderChest() {
        return delegate.getEnderChest();
    }

    @Override
    public MainHand getMainHand() {
        return delegate.getMainHand();
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property property, int i) {
        return delegate.setWindowProperty(property, i);
    }

    @Override
    public InventoryView getOpenInventory() {
        return delegate.getOpenInventory();
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        return delegate.openInventory(inventory);
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean b) {
        return delegate.openWorkbench(location, b);
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean b) {
        return delegate.openEnchanting(location, b);
    }

    @Override
    public void openInventory(InventoryView inventoryView) {
        delegate.openInventory(inventoryView);
    }

    @Override
    public InventoryView openMerchant(Villager villager, boolean b) {
        return delegate.openMerchant(villager, b);
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean b) {
        return delegate.openMerchant(merchant, b);
    }

    @Override
    public void closeInventory() {
        delegate.closeInventory();
    }

    @Override
    public void closeInventory(InventoryCloseEvent.Reason reason) {
        delegate.closeInventory(reason);
    }

    @Override
    @Deprecated
    public ItemStack getItemInHand() {
        return delegate.getItemInHand();
    }

    @Override
    @Deprecated
    public void setItemInHand(ItemStack itemStack) {
        delegate.setItemInHand(itemStack);
    }

    @Override
    public ItemStack getItemOnCursor() {
        return delegate.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(ItemStack itemStack) {
        delegate.setItemOnCursor(itemStack);
    }

    @Override
    public boolean hasCooldown(Material material) {
        return delegate.hasCooldown(material);
    }

    @Override
    public int getCooldown(Material material) {
        return delegate.getCooldown(material);
    }

    @Override
    public void setCooldown(Material material, int i) {
        delegate.setCooldown(material, i);
    }

    @Override
    public boolean isSleeping() {
        return delegate.isSleeping();
    }

    @Override
    public int getSleepTicks() {
        return delegate.getSleepTicks();
    }

    @Override
    public Location getBedSpawnLocation() {
        return delegate.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        delegate.setBedSpawnLocation(location);
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean b) {
        delegate.setBedSpawnLocation(location, b);
    }

    @Override
    public boolean sleep(Location location, boolean b) {
        return delegate.sleep(location, b);
    }

    @Override
    public void wakeup(boolean b) {
        delegate.wakeup(b);
    }

    @Override
    public Location getBedLocation() {
        return delegate.getBedLocation();
    }

    @Override
    public GameMode getGameMode() {
        return delegate.getGameMode();
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        delegate.setGameMode(gameMode);
    }

    @Override
    public boolean isBlocking() {
        return delegate.isBlocking();
    }

    @Override
    public boolean isHandRaised() {
        return delegate.isHandRaised();
    }

    @Override
    public int getExpToLevel() {
        return delegate.getExpToLevel();
    }

    @Override
    public Entity releaseLeftShoulderEntity() {
        return delegate.releaseLeftShoulderEntity();
    }

    @Override
    public Entity releaseRightShoulderEntity() {
        return delegate.releaseRightShoulderEntity();
    }

    @Override
    public boolean discoverRecipe(NamespacedKey namespacedKey) {
        return delegate.discoverRecipe(namespacedKey);
    }

    @Override
    public int discoverRecipes(Collection<NamespacedKey> collection) {
        return delegate.discoverRecipes(collection);
    }

    @Override
    public boolean undiscoverRecipe(NamespacedKey namespacedKey) {
        return delegate.undiscoverRecipe(namespacedKey);
    }

    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> collection) {
        return delegate.undiscoverRecipes(collection);
    }

    @Override
    @Deprecated
    public Entity getShoulderEntityLeft() {
        return delegate.getShoulderEntityLeft();
    }

    @Override
    @Deprecated
    public void setShoulderEntityLeft(Entity entity) {
        delegate.setShoulderEntityLeft(entity);
    }

    @Override
    @Deprecated
    public Entity getShoulderEntityRight() {
        return delegate.getShoulderEntityRight();
    }

    @Override
    @Deprecated
    public void setShoulderEntityRight(Entity entity) {
        delegate.setShoulderEntityRight(entity);
    }

    @Override
    public void openSign(Sign sign) {
        delegate.openSign(sign);
    }

    @Override
    public double getEyeHeight() {
        return delegate.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean b) {
        return delegate.getEyeHeight(b);
    }

    @Override
    public Location getEyeLocation() {
        return delegate.getEyeLocation();
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> set, int i) {
        return delegate.getLineOfSight(set, i);
    }

    @Override
    public Block getTargetBlock(Set<Material> set, int i) {
        return delegate.getTargetBlock(set, i);
    }

    @Override
    @Nullable
    public Block getTargetBlock(int maxDistance) {
        return delegate.getTargetBlock(maxDistance);
    }

    @Override
    @Nullable
    public Block getTargetBlock(int i, TargetBlockInfo.FluidMode fluidMode) {
        return delegate.getTargetBlock(i, fluidMode);
    }

    @Override
    @Nullable
    public BlockFace getTargetBlockFace(int maxDistance) {
        return delegate.getTargetBlockFace(maxDistance);
    }

    @Override
    @Nullable
    public BlockFace getTargetBlockFace(int i, TargetBlockInfo.FluidMode fluidMode) {
        return delegate.getTargetBlockFace(i, fluidMode);
    }

    @Override
    @Nullable
    public TargetBlockInfo getTargetBlockInfo(int maxDistance) {
        return delegate.getTargetBlockInfo(maxDistance);
    }

    @Override
    @Nullable
    public TargetBlockInfo getTargetBlockInfo(int i, TargetBlockInfo.FluidMode fluidMode) {
        return delegate.getTargetBlockInfo(i, fluidMode);
    }

    @Override
    @Nullable
    public Entity getTargetEntity(int maxDistance) {
        return delegate.getTargetEntity(maxDistance);
    }

    @Override
    @Nullable
    public Entity getTargetEntity(int i, boolean b) {
        return delegate.getTargetEntity(i, b);
    }

    @Override
    @Nullable
    public TargetEntityInfo getTargetEntityInfo(int maxDistance) {
        return delegate.getTargetEntityInfo(maxDistance);
    }

    @Override
    @Nullable
    public TargetEntityInfo getTargetEntityInfo(int i, boolean b) {
        return delegate.getTargetEntityInfo(i, b);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        return delegate.getLastTwoTargetBlocks(set, i);
    }

    @Override
    public Block getTargetBlockExact(int i) {
        return delegate.getTargetBlockExact(i);
    }

    @Override
    public Block getTargetBlockExact(int i, FluidCollisionMode fluidCollisionMode) {
        return delegate.getTargetBlockExact(i, fluidCollisionMode);
    }

    @Override
    public RayTraceResult rayTraceBlocks(double v) {
        return delegate.rayTraceBlocks(v);
    }

    @Override
    public RayTraceResult rayTraceBlocks(double v, FluidCollisionMode fluidCollisionMode) {
        return delegate.rayTraceBlocks(v, fluidCollisionMode);
    }

    @Override
    public int getRemainingAir() {
        return delegate.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int i) {
        delegate.setRemainingAir(i);
    }

    @Override
    public int getMaximumAir() {
        return delegate.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int i) {
        delegate.setMaximumAir(i);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return delegate.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        delegate.setMaximumNoDamageTicks(i);
    }

    @Override
    public double getLastDamage() {
        return delegate.getLastDamage();
    }

    @Override
    public void setLastDamage(double v) {
        delegate.setLastDamage(v);
    }

    @Override
    public int getNoDamageTicks() {
        return delegate.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int i) {
        delegate.setNoDamageTicks(i);
    }

    @Override
    public Player getKiller() {
        return delegate.getKiller();
    }

    @Override
    public void setKiller(@Nullable Player player) {
        delegate.setKiller(player);
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect) {
        return delegate.addPotionEffect(potionEffect);
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        return delegate.addPotionEffect(potionEffect, b);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> collection) {
        return delegate.addPotionEffects(collection);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return delegate.hasPotionEffect(potionEffectType);
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType potionEffectType) {
        return delegate.getPotionEffect(potionEffectType);
    }

    @Override
    public void removePotionEffect(PotionEffectType potionEffectType) {
        delegate.removePotionEffect(potionEffectType);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return delegate.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        return delegate.hasLineOfSight(entity);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return delegate.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean b) {
        delegate.setRemoveWhenFarAway(b);
    }

    @Override
    public EntityEquipment getEquipment() {
        return delegate.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean b) {
        delegate.setCanPickupItems(b);
    }

    @Override
    public boolean getCanPickupItems() {
        return delegate.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return delegate.isLeashed();
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return delegate.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(Entity entity) {
        return delegate.setLeashHolder(entity);
    }

    @Override
    public boolean isGliding() {
        return delegate.isGliding();
    }

    @Override
    public void setGliding(boolean b) {
        delegate.setGliding(b);
    }

    @Override
    public boolean isSwimming() {
        return delegate.isSwimming();
    }

    @Override
    public void setSwimming(boolean b) {
        delegate.setSwimming(b);
    }

    @Override
    public boolean isRiptiding() {
        return delegate.isRiptiding();
    }

    @Override
    public void setAI(boolean b) {
        delegate.setAI(b);
    }

    @Override
    public boolean hasAI() {
        return delegate.hasAI();
    }

    @Override
    public void setCollidable(boolean b) {
        delegate.setCollidable(b);
    }

    @Override
    public boolean isCollidable() {
        return delegate.isCollidable();
    }

    @Override
    public int getArrowsStuck() {
        return delegate.getArrowsStuck();
    }

    @Override
    public void setArrowsStuck(int i) {
        delegate.setArrowsStuck(i);
    }

    @Override
    public int getShieldBlockingDelay() {
        return delegate.getShieldBlockingDelay();
    }

    @Override
    public void setShieldBlockingDelay(int i) {
        delegate.setShieldBlockingDelay(i);
    }

    @Override
    public ItemStack getActiveItem() {
        return delegate.getActiveItem();
    }

    @Override
    public int getItemUseRemainingTime() {
        return delegate.getItemUseRemainingTime();
    }

    @Override
    public int getHandRaisedTime() {
        return delegate.getHandRaisedTime();
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return delegate.getAttribute(attribute);
    }

    @Override
    public void damage(double v) {
        delegate.damage(v);
    }

    @Override
    public void damage(double v, Entity entity) {
        delegate.damage(v, entity);
    }

    @Override
    public double getHealth() {
        return delegate.getHealth();
    }

    @Override
    public void setHealth(double v) {
        delegate.setHealth(v);
    }

    @Override
    @Deprecated
    public double getMaxHealth() {
        return delegate.getMaxHealth();
    }

    @Override
    @Deprecated
    public void setMaxHealth(double v) {
        delegate.setMaxHealth(v);
    }

    @Override
    @Deprecated
    public void resetMaxHealth() {
        delegate.resetMaxHealth();
    }

    @Override
    public Location getLocation() {
        return delegate.getLocation();
    }

    @Override
    public Location getLocation(Location location) {
        return delegate.getLocation(location);
    }

    @Override
    public void setVelocity(Vector vector) {
        delegate.setVelocity(vector);
    }

    @Override
    public Vector getVelocity() {
        return delegate.getVelocity();
    }

    @Override
    public double getHeight() {
        return delegate.getHeight();
    }

    @Override
    public double getWidth() {
        return delegate.getWidth();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return delegate.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return delegate.isOnGround();
    }

    @Override
    public World getWorld() {
        return delegate.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        return delegate.teleport(location);
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        return delegate.teleport(location, teleportCause);
    }

    @Override
    public boolean teleport(Entity entity) {
        return delegate.teleport(entity);
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        return delegate.teleport(entity, teleportCause);
    }

    @Override
    public CompletableFuture<Boolean> teleportAsync(Location loc) {
        return delegate.teleportAsync(loc);
    }

    @Override
    public CompletableFuture<Boolean> teleportAsync(Location loc, PlayerTeleportEvent.TeleportCause cause) {
        return delegate.teleportAsync(loc, cause);
    }

    @Override
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return delegate.getNearbyEntities(v, v1, v2);
    }

    @Override
    public int getEntityId() {
        return delegate.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return delegate.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return delegate.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int i) {
        delegate.setFireTicks(i);
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public boolean isDead() {
        return delegate.isDead();
    }

    @Override
    public boolean isValid() {
        return delegate.isValid();
    }

    @Override
    public Server getServer() {
        return delegate.getServer();
    }

    @Override
    @Deprecated
    public boolean isPersistent() {
        return delegate.isPersistent();
    }

    @Override
    @Deprecated
    public void setPersistent(boolean b) {
        delegate.setPersistent(b);
    }

    @Override
    @Deprecated
    public Entity getPassenger() {
        return delegate.getPassenger();
    }

    @Override
    @Deprecated
    public boolean setPassenger(Entity entity) {
        return delegate.setPassenger(entity);
    }

    @Override
    public List<Entity> getPassengers() {
        return delegate.getPassengers();
    }

    @Override
    public boolean addPassenger(Entity entity) {
        return delegate.addPassenger(entity);
    }

    @Override
    public boolean removePassenger(Entity entity) {
        return delegate.removePassenger(entity);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean eject() {
        return delegate.eject();
    }

    @Override
    public float getFallDistance() {
        return delegate.getFallDistance();
    }

    @Override
    public void setFallDistance(float v) {
        delegate.setFallDistance(v);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        delegate.setLastDamageCause(entityDamageEvent);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return delegate.getLastDamageCause();
    }

    @Override
    public UUID getUniqueId() {
        return delegate.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return delegate.getTicksLived();
    }

    @Override
    public void setTicksLived(int i) {
        delegate.setTicksLived(i);
    }

    @Override
    public void playEffect(EntityEffect entityEffect) {
        delegate.playEffect(entityEffect);
    }

    @Override
    public EntityType getType() {
        return delegate.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return delegate.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return delegate.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return delegate.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean b) {
        delegate.setCustomNameVisible(b);
    }

    @Override
    public boolean isCustomNameVisible() {
        return delegate.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean b) {
        delegate.setGlowing(b);
    }

    @Override
    public boolean isGlowing() {
        return delegate.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean b) {
        delegate.setInvulnerable(b);
    }

    @Override
    public boolean isInvulnerable() {
        return delegate.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return delegate.isSilent();
    }

    @Override
    public void setSilent(boolean b) {
        delegate.setSilent(b);
    }

    @Override
    public boolean hasGravity() {
        return delegate.hasGravity();
    }

    @Override
    public void setGravity(boolean b) {
        delegate.setGravity(b);
    }

    @Override
    public int getPortalCooldown() {
        return delegate.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int i) {
        delegate.setPortalCooldown(i);
    }

    @Override
    public Set<String> getScoreboardTags() {
        return delegate.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(String s) {
        return delegate.addScoreboardTag(s);
    }

    @Override
    public boolean removeScoreboardTag(String s) {
        return delegate.removeScoreboardTag(s);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return delegate.getPistonMoveReaction();
    }

    @Override
    public BlockFace getFacing() {
        return delegate.getFacing();
    }

    @Override
    public Location getOrigin() {
        return delegate.getOrigin();
    }

    @Override
    public boolean fromMobSpawner() {
        return delegate.fromMobSpawner();
    }

    @Override
    public Chunk getChunk() {
        return delegate.getChunk();
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {
        delegate.setMetadata(s, metadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return delegate.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(String s) {
        return delegate.hasMetadata(s);
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        delegate.removeMetadata(s, plugin);
    }

    @Override
    public void sendMessage(String s) {
        delegate.sendMessage(s);
    }

    @Override
    public void sendMessage(String[] strings) {
        delegate.sendMessage(strings);
    }

    @Override
    public boolean isPermissionSet(String s) {
        return delegate.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return delegate.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s) {
        return delegate.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return delegate.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return delegate.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return delegate.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return delegate.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return delegate.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        delegate.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        delegate.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return delegate.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return delegate.isOp();
    }

    @Override
    public void setOp(boolean b) {
        delegate.setOp(b);
    }

    @Override
    public String getCustomName() {
        return delegate.getCustomName();
    }

    @Override
    public void setCustomName(String s) {
        delegate.setCustomName(s);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        return delegate.launchProjectile(aClass);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        return delegate.launchProjectile(aClass, vector);
    }

    @Override
    public boolean isConversing() {
        return delegate.isConversing();
    }

    @Override
    public void acceptConversationInput(String s) {
        delegate.acceptConversationInput(s);
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return delegate.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        delegate.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        delegate.abandonConversation(conversation, conversationAbandonedEvent);
    }

    @Override
    public boolean isOnline() {
        return delegate.isOnline();
    }

    @Override
    public boolean isBanned() {
        return delegate.isBanned();
    }

    @Override
    public BanEntry banPlayer(String reason) {
        return delegate.banPlayer(reason);
    }

    @Override
    public BanEntry banPlayer(String reason, String source) {
        return delegate.banPlayer(reason, source);
    }

    @Override
    public BanEntry banPlayer(String reason, Date expires) {
        return delegate.banPlayer(reason, expires);
    }

    @Override
    public BanEntry banPlayer(String reason, Date expires, String source) {
        return delegate.banPlayer(reason, expires, source);
    }

    @Override
    public BanEntry banPlayer(String reason, Date expires, String source, boolean kickIfOnline) {
        return delegate.banPlayer(reason, expires, source, kickIfOnline);
    }

    @Override
    public boolean isWhitelisted() {
        return delegate.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean b) {
        delegate.setWhitelisted(b);
    }

    @Override
    public Player getPlayer() {
        return delegate.getPlayer();
    }

    @Override
    public long getFirstPlayed() {
        return delegate.getFirstPlayed();
    }

    @Override
    @Deprecated
    public long getLastPlayed() {
        return delegate.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return delegate.hasPlayedBefore();
    }

    @Override
    public long getLastLogin() {
        return delegate.getLastLogin();
    }

    @Override
    public long getLastSeen() {
        return delegate.getLastSeen();
    }

    @Override
    public Map<String, Object> serialize() {
        return delegate.serialize();
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        delegate.sendPluginMessage(plugin, s, bytes);
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return delegate.getListeningPluginChannels();
    }

    @Override
    public int getProtocolVersion() {
        return delegate.getProtocolVersion();
    }

    @Override
    @Nullable
    public InetSocketAddress getVirtualHost() {
        return delegate.getVirtualHost();
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}

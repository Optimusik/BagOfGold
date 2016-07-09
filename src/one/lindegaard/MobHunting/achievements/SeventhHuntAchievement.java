package one.lindegaard.MobHunting.achievements;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import one.lindegaard.MobHunting.ExtendedMobType;
import one.lindegaard.MobHunting.Messages;
import one.lindegaard.MobHunting.MobHunting;
import one.lindegaard.MobHunting.events.MobHuntKillEvent;

public class SeventhHuntAchievement implements ProgressAchievement, Listener {

	private ExtendedMobType mType;

	public SeventhHuntAchievement(ExtendedMobType entity) {
		mType = entity;
	}

	@Override
	public String getName() {
		return Messages.getString("achievements.hunter.7.name", "mob", mType.getFriendlyName());
	}

	@Override
	public String getID() {
		return "hunting-level7-" + mType.name().toLowerCase();
	}

	@Override
	public String getDescription() {
		return Messages.getString("achievements.hunter.7.description", "count", getMaxProgress(), "mob",
				mType.getFriendlyName());
	}

	@Override
	public double getPrize() {
		return MobHunting.getConfigManager().specialHunter7;
	}

	@Override
	public int getMaxProgress() {
		return mType.getMax() * 100;
	}

	@Override
	public String inheritFrom() {
		return "hunting-level6-" + mType.name().toLowerCase();
	}

	@Override
	public String nextLevelId() {
		return null;
	}

	@Override
	public String getPrizeCmd() {
		return MobHunting.getConfigManager().specialHunter7Cmd;
	}

	@Override
	public String getPrizeCmdDescription() {
		return MobHunting.getConfigManager().specialHunter7CmdDesc;
	}

	@Override
	public ItemStack getSymbol() {
		return new ItemStack(Material.DIAMOND_BLOCK);
	}

	@Override
	public ExtendedMobType getExtendedMobType() {
		return mType;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	private void onKillCompleted(MobHuntKillEvent event) {
		if (mType.matches(event.getKilledEntity()))
			MobHunting.getAchievements().awardAchievementProgress(this, event.getPlayer(), 1);
	}
}

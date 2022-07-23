package info.partonetrain.managear.trait;

import info.partonetrain.managear.ManaGear;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.gear.trait.SimpleTrait;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Collection;

public class ManaTrait extends SimpleTrait {

    public static final Serializer<ManaTrait> SERIALIZER = new Serializer<ManaTrait>(ManaGear.getId("mana"), ManaTrait::new);

    private static final int MANA_PER_DAMAGE_TIER_ONE = 60;
    private static final int MANA_PER_DAMAGE_TIER_TWO = 100;

    @Override
    public void onUpdate(TraitActionContext context, boolean isEquipped) {
        Player player = context.getPlayer();
        Level world = player.getLevel();
        int manaToUse;
        if (context.getTraitLevel() == 2)
            manaToUse = MANA_PER_DAMAGE_TIER_TWO;
        else
            manaToUse = MANA_PER_DAMAGE_TIER_ONE;

        ItemStack thisGear = context.getGear();
        if (!world.isClientSide() && player instanceof Player && isEquipped) {
            if (thisGear.getDamageValue() > 0) {
                if (ManaItemHandler.instance().requestManaExactForTool(thisGear, player, manaToUse, true)) {
                    thisGear.setDamageValue(thisGear.getDamageValue() - 1);
                }
            }
        }
    }

    @Override
    public Collection<String> getExtraWikiLines() {
        Collection<String> ret = super.getExtraWikiLines();
        ret.add("Mana I uses " + MANA_PER_DAMAGE_TIER_ONE + " mana per repair.");
        ret.add("Mana II uses " + MANA_PER_DAMAGE_TIER_TWO + " mana per repair.");
        return ret;
    }

    public ManaTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }
}

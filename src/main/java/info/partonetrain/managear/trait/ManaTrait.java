package info.partonetrain.managear.trait;

import info.partonetrain.managear.ManaGear;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.gear.trait.SimpleTrait;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Collection;

public class ManaTrait extends SimpleTrait {

    public static final Serializer<ManaTrait> SERIALIZER = new Serializer<>(ManaGear.getId("mana"), ManaTrait::new);

    private static final int MANA_PER_DAMAGE_TIER_ONE = 60;
    private static final int MANA_PER_DAMAGE_TIER_TWO = 100;

    @Override
    public void onUpdate(TraitActionContext context, boolean isEquipped) {
        PlayerEntity player = context.getPlayer();
        World world = player.getEntityWorld();
        int manaToUse;
        if (context.getTraitLevel() == 2)
            manaToUse = MANA_PER_DAMAGE_TIER_TWO;
        else
            manaToUse = MANA_PER_DAMAGE_TIER_ONE;

        ItemStack thisGear = context.getGear();
        if (!world.isRemote && player instanceof PlayerEntity && isEquipped) {
            if (thisGear.getDamage() > 0) {
                if (ManaItemHandler.instance().requestManaExactForTool(thisGear, (PlayerEntity) player, manaToUse, true)) {
                    thisGear.setDamage(thisGear.getDamage() - 1);
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

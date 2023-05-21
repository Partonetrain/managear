package info.partonetrain.managear.trait;

import info.partonetrain.managear.ManaGear;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.silentchaos512.gear.gear.trait.SimpleTrait;
import org.apache.logging.log4j.Level;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.ModSounds;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumBoots;
import vazkii.botania.common.item.equipment.tool.elementium.ItemElementiumAxe;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraSword;

import java.util.Collection;

public class ManaBurstTrait extends SimpleTrait {

    public static final Serializer<ManaBurstTrait> SERIALIZER = new Serializer<>(ManaGear.getId("manaburst"), ManaBurstTrait::new);

    @Override
    public void onItemSwing(ItemStack stack, LivingEntity entity, int traitLevel) {
        //Partonetrain#9679: willie can I copy this method for ManaGear :wacko:
        //williewillus#8490: sure
        Player player = (Player) entity;
        if(!entity.getLevel().isClientSide() && !player.isSpectator()) {
            if (!player.getMainHandItem().isEmpty()) {
                ItemStack gearItemStack = player.getMainHandItem();

                if (player.getAttackStrengthScale(0F) == 1) {
                    // The default instance is really our only way, ILensEffect (or any item interface for that matter)
                    // is not really possible with silent gear system because it is tied to the tool and not the trait
                    // (at least for now...)
                    ManaBurstEntity burst = ItemTerraSword.getBurst(player, ModItems.terraSword.getDefaultInstance());
                    player.getLevel().addFreshEntity(burst);

                    gearItemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    ManaGear.LOGGER.log(Level.INFO, gearItemStack.getDamageValue());

                    player.getLevel().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
                }
            }
        }
    }

    public ManaBurstTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

    @Override
    public Collection<String> getExtraWikiLines() {
        Collection<String> ret = super.getExtraWikiLines();
        ret.add("The Mana Burst is identical to that of a default, unenchanted Terra Blade. Uses 100 mana per burst.");
        return ret;
    }
}

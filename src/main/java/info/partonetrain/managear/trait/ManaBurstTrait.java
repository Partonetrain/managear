package info.partonetrain.managear.trait;

import info.partonetrain.managear.ManaGear;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.silentchaos512.gear.gear.trait.SimpleTrait;
import vazkii.botania.common.core.handler.ModSounds;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraSword;

public class ManaBurstTrait extends SimpleTrait {

    public static final Serializer<ManaBurstTrait> SERIALIZER = new Serializer<>(ManaGear.getId("manaburst"), ManaBurstTrait::new);

    @Override
    public void onItemSwing(ItemStack stack, LivingEntity entity, int traitLevel) {
        //Partonetrain#9679: willie can I copy this method for ManaGear :wacko:
        //williewillus#8490: sure
        PlayerEntity player = (PlayerEntity) entity;
        if (!player.getHeldItemMainhand().isEmpty() && player.getCooledAttackStrength(0.0F) == 1.0F) {
            EntityManaBurst burst = ((ItemTerraSword) ModItems.terraSword).getBurst(player, ModItems.terraSword.getDefaultInstance());
            player.world.addEntity(burst);
            player.getHeldItemMainhand().damageItem(1, player, (p) -> {
                p.sendBreakAnimation(Hand.MAIN_HAND);
            });
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), ModSounds.terraBlade, SoundCategory.PLAYERS, 0.4F, 1.4F);
        }
    }

    public ManaBurstTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }
}

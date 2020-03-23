package info.partonetrain.managear.init;

import info.partonetrain.managear.ManaGear;
import info.partonetrain.managear.trait.ManaTrait;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.traits.TraitSerializers;

public final class ManaGearTraits {

    public static final ResourceLocation MANA_TRAIT = ManaGear.getId("mana");


    public static void registerSerializers() {
        TraitSerializers.register(ManaTrait.SERIALIZER);
    }

    //private ManaTraits() {}

}

package info.partonetrain.managear.init;

import info.partonetrain.managear.trait.ManaBurstTrait;
import info.partonetrain.managear.trait.ManaTrait;
import net.silentchaos512.gear.gear.trait.TraitSerializers;

public final class ManaGearTraits {

    public static void registerSerializers() {
        TraitSerializers.register(ManaTrait.SERIALIZER);
        TraitSerializers.register(ManaBurstTrait.SERIALIZER);
        //PixieTrait uses silentgear:attribute_trait
    }

}

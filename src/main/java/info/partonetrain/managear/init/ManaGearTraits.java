package info.partonetrain.managear.init;

import info.partonetrain.managear.trait.ManaTrait;
import info.partonetrain.managear.trait.PixieTrait;
import net.silentchaos512.gear.traits.TraitSerializers;

public final class ManaGearTraits {

    public static void registerSerializers() {
        TraitSerializers.register(ManaTrait.SERIALIZER);
        TraitSerializers.register(PixieTrait.SERIALIZER);
    }

}

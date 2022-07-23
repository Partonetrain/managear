package info.partonetrain.managear;

import info.partonetrain.managear.trait.ManaBurstTrait;
import info.partonetrain.managear.trait.ManaTrait;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.silentchaos512.gear.api.GearApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ManaGear.MOD_ID)
public class ManaGear {
    public static final String MOD_ID = "managear";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public ManaGear() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        registerManagearTraits();
    }

    public void registerManagearTraits()
    {
        GearApi.registerTraitSerializer(ManaTrait.SERIALIZER);
        GearApi.registerTraitSerializer(ManaBurstTrait.SERIALIZER);
    }

    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}

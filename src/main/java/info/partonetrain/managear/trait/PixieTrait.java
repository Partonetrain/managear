package info.partonetrain.managear.trait;

import info.partonetrain.managear.ManaGear;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.traits.SimpleTrait;
import net.silentchaos512.gear.util.GearHelper;
import vazkii.botania.common.core.handler.PixieHandler;

public class PixieTrait extends SimpleTrait {

    public static final Serializer<PixieTrait> SERIALIZER = new Serializer<>(ManaGear.getId("pixie"), PixieTrait::new);


    @Override
    public void onGearCrafted(TraitActionContext context) {
        ManaGear.LOGGER.debug("PIXIE GEAR CRAFTED!");
        ItemStack thisGear = context.getGear();
        GearType type = GearHelper.getType(thisGear);
        ManaGear.LOGGER.debug("thisGear type:" + type.getName());
        final double HELMET_CHANCE = 0.11;
        final double CHEST_CHANCE = 0.17;
        final double LEGS_CHANCE = 0.15;
        final double BOOT_CHANCE = 0.09;
        final double MELEE_CHANCE = 0.05;

        if (type == GearType.HELMET) {
            thisGear.addAttributeModifier("botania.pixieSpawnChance", PixieHandler.makeModifier(EquipmentSlotType.HEAD, "Armor modifier", HELMET_CHANCE), EquipmentSlotType.HEAD);
        } else if (type == GearType.CHESTPLATE) {
            thisGear.addAttributeModifier("botania.pixieSpawnChance", PixieHandler.makeModifier(EquipmentSlotType.CHEST, "Armor modifier", CHEST_CHANCE), EquipmentSlotType.CHEST);
        } else if (type == GearType.LEGGINGS) {
            thisGear.addAttributeModifier("botania.pixieSpawnChance", PixieHandler.makeModifier(EquipmentSlotType.LEGS, "Armor modifier", LEGS_CHANCE), EquipmentSlotType.LEGS);
        } else if (type == GearType.BOOTS) {
            thisGear.addAttributeModifier("botania.pixieSpawnChance", PixieHandler.makeModifier(EquipmentSlotType.FEET, "Armor modifier", BOOT_CHANCE), EquipmentSlotType.FEET);
        } else if (type == GearType.DAGGER || type == GearType.KATANA || type == GearType.MACHETE || type == GearType.SPEAR || type == GearType.SWORD) {
            thisGear.addAttributeModifier("botania.pixieSpawnChance", PixieHandler.makeModifier(EquipmentSlotType.MAINHAND, "Sword modifier", MELEE_CHANCE), EquipmentSlotType.MAINHAND);
        }
    }

    private PixieTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

}

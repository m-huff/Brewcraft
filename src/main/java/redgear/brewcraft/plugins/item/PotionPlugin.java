package redgear.brewcraft.plugins.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import redgear.brewcraft.plugins.core.EffectPlugin;
import redgear.brewcraft.potions.FluidPotion;
import redgear.brewcraft.potions.MetaItemPotion;
import redgear.brewcraft.recipes.RecipeRegistry;
import redgear.brewcraft.utils.PotionRegistry;
import redgear.core.fluids.FluidUtil;
import redgear.core.mod.IPlugin;
import redgear.core.mod.ModUtils;
import redgear.core.util.SimpleItem;
import cpw.mods.fml.common.LoaderState.ModState;

public class PotionPlugin implements IPlugin {

	public static MetaItemPotion potions;
	public static ItemStack emptyBottle = new ItemStack(Items.glass_bottle, 1, 0);
	public static FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
	public static FluidStack lava = new FluidStack(FluidRegistry.LAVA, 1000);
	public static PotionRegistry potionRegistry = new PotionRegistry();
	public static RecipeRegistry recipeRegistry = new RecipeRegistry();
	public static final String potionTexture = "potionWhite";

	//Vanilla potions.
	public static FluidStack fluidAwkward, fluidVision, fluidVisionLong, fluidVisionVeryLong, fluidInvisible,
			fluidInvisibleLong, fluidInvisibleVeryLong, fluidWeakness, fluidWeaknessLong, fluidWeaknessVeryLong,
			fluidFireResist, fluidFireResistLong, fluidFireResistVeryLong, fluidSlowness, fluidSlownessLong,
			fluidSlownessVeryLong, fluidRegen, fluidRegenII, fluidRegenIII, fluidRegenLong, fluidRegenVeryLong,
			fluidFast, fluidFastII, fluidFastIII, fluidFastLong, fluidFastVeryLong, fluidStrength, fluidStrengthII,
			fluidStrengthIII, fluidStrengthLong, fluidStrengthVeryLong, fluidPoison, fluidPoisonII, fluidPoisonIII,
			fluidPoisonLong, fluidPoisonVeryLong, fluidHaste, fluidHasteII, fluidHasteIII, fluidHasteLong,
			fluidHasteVeryLong, fluidFatigue, fluidFatigueII, fluidFatigueIII, fluidFatigueLong, fluidFatigueVeryLong,
			fluidJump, fluidJumpII, fluidJumpIII, fluidJumpLong, fluidJumpVeryLong, fluidResistance, fluidResistanceII,
			fluidResistanceIII, fluidResistanceLong, fluidResistanceVeryLong, fluidHunger, fluidHungerII,
			fluidHungerIII, fluidHungerLong, fluidHungerVeryLong, fluidHealthBoost, fluidHealthBoostII,
			fluidHealthBoostIII, fluidHealthBoostLong, fluidHealthBoostVeryLong, fluidAbsorption, fluidAbsorptionII,
			fluidAbsorptionIII, fluidAbsorptionLong, fluidAbsorptionVeryLong, fluidSaturation, fluidSaturationII,
			fluidSaturationIII, fluidSaturationLong, fluidSaturationVeryLong, fluidWither, fluidWitherII,
			fluidWitherIII, fluidWitherLong, fluidWitherVeryLong, fluidHarm, fluidHarmII, fluidHarmIII, fluidHeal,
			fluidHealII, fluidHealIII, fluidWaterBreathe, fluidWaterBreatheLong, fluidWaterBreatheVeryLong,
			fluidNausea, fluidNauseaLong, fluidNauseaVeryLong, fluidBlindness, fluidBlindnessLong,
			fluidBlindnessVeryLong;

	//Brewcraft potions.
	public static FluidStack fluidFireImmunity, fluidFireImmunityII, fluidHolyWater, fluidHolyWaterII,
			fluidHolyWaterIII, fluidHolyWaterLong, fluidHolyWaterVeryLong, fluidAntidote, fluidAntidoteII,
			fluidAntidoteIII, fluidAntidoteIIII, fluidAntidoteLong, fluidAntidoteVeryLong, fluidFlight,
			fluidFlightLong, fluidFlightVeryLong, fluidCryo, fluidCryoLong, fluidCryoVeryLong, fluidBoom, fluidBoomII,
			fluidBoomIII, fluidBoomLong, fluidBoomVeryLong, fluidEternalFlame, fluidEternalFlameLong,
			fluidEternalFlameVeryLong, fluidFireEater, fluidFireEaterII, fluidFireEaterIII, fluidFireEaterLong,
			fluidFireEaterVeryLong;

	@Override
	public String getName() {
		return "PotionPlugin";
	}

	@Override
	public boolean shouldRun(ModUtils mod, ModState state) {
		return true;
	}

	@Override
	public boolean isRequired() {
		return true;
	}

	@Override
	public void preInit(ModUtils mod) {
		potions = new MetaItemPotion("RedGear.Brewcraft.Potions");
	}

	@Override
	public void Init(ModUtils mod) {
		fluidAwkward = createVanillaPotion("Awkward", 16, 0, 0, 0);
		fluidVision = createVanillaPotion("Vision", 8230, 16422, 180, 0);
		fluidVisionLong = createVanillaPotion("VisionLong", 8262, 16454, 480, 0);
		fluidInvisible = createVanillaPotion("Invisible", 8238, 16430, 180, 0);
		fluidInvisibleLong = createVanillaPotion("InvisibleLong", 8270, 16462, 480, 0);
		fluidRegen = createVanillaPotion("Regen", 8193, 16385, 45, 0);
		fluidRegenII = createVanillaPotion("RegenII", 8225, 16417, 120, 1);
		fluidRegenLong = createVanillaPotion("RegenLong", 8257, 16449, 120, 0);
		fluidFast = createVanillaPotion("Fast", 8194, 16386, 180, 0);
		fluidFastLong = createVanillaPotion("FastLong", 8258, 16450, 480, 0);
		fluidFastII = createVanillaPotion("FastII", 8226, 16418, 90, 1);
		fluidWeakness = createVanillaPotion("Weakness", 8232, 16424, 90, 0);
		fluidWeaknessLong = createVanillaPotion("WeaknessLong", 8264, 16456, 240, 0);
		fluidStrength = createVanillaPotion("Strength", 8201, 16393, 180, 0);
		fluidStrengthLong = createVanillaPotion("StrengthLong", 8265, 16457, 480, 0);
		fluidStrengthII = createVanillaPotion("StrengthII", 8233, 16425, 90, 1);
		fluidFireResist = createVanillaPotion("FireResist", 8227, 16419, 180, 0);
		fluidFireResistLong = createVanillaPotion("FireResistLong", 8259, 16451, 480, 0);
		fluidSlowness = createVanillaPotion("Slowness", 8234, 16426, 90, 0);
		fluidSlownessLong = createVanillaPotion("SlownessLong", 8266, 16458, 240, 0);
		fluidPoison = createVanillaPotion("Poison", 8196, 16388, 45, 0);
		fluidPoisonII = createVanillaPotion("PoisonII", 8228, 16420, 22, 1);
		fluidPoisonLong = createVanillaPotion("PoisonLong", 8260, 16452, 120, 0);
		fluidHarm = createVanillaPotion("Harm", 8268, 16460, 0, 0);
		fluidHarmII = createVanillaPotion("HarmII", 8236, 16428, 0, 1);
		fluidHeal = createVanillaPotion("Heal", 8261, 16453, 0, 0);
		fluidHealII = createVanillaPotion("HealII", 8229, 16421, 0, 1);
		fluidWaterBreathe = createVanillaPotion("WaterBreathe", 8237, 16429, 180, 0);
		fluidWaterBreatheLong = createVanillaPotion("WaterBreatheLong", 8269, 16461, 480, 0);

		fluidHaste = potionRegistry.addPotion("Haste", Potion.digSpeed, 90, 0, true);
		fluidHasteII = potionRegistry.addPotion("HasteII", Potion.digSpeed, 45, 1, true);
		fluidHasteIII = potionRegistry.addPotion("HasteIII", Potion.digSpeed, 25, 2, true);
		fluidHasteLong = potionRegistry.addPotion("HasteLong", Potion.digSpeed, 180, 0, true);
		fluidHasteVeryLong = potionRegistry.addPotion("HasteVeryLong", Potion.digSpeed, 360, 0, true);
		fluidFatigue = potionRegistry.addPotion("Fatigue", Potion.digSlowdown, 90, 0, true);
		fluidFatigueII = potionRegistry.addPotion("FatigueII", Potion.digSlowdown, 45, 1, true);
		fluidFatigueIII = potionRegistry.addPotion("FatigueIII", Potion.digSlowdown, 25, 2, true);
		fluidFatigueLong = potionRegistry.addPotion("FatigueLong", Potion.digSlowdown, 180, 0, true);
		fluidFatigueVeryLong = potionRegistry.addPotion("FatigueVeryLong", Potion.digSlowdown, 360, 0, true);
		fluidJump = potionRegistry.addPotion("Jump", Potion.jump, 60, 0, true);
		fluidJumpII = potionRegistry.addPotion("JumpII", Potion.jump, 30, 1, true);
		fluidJumpIII = potionRegistry.addPotion("JumpIII", Potion.jump, 15, 2, true);
		fluidJumpLong = potionRegistry.addPotion("JumpLong", Potion.jump, 120, 0, true);
		fluidJumpVeryLong = potionRegistry.addPotion("JumpVeryLong", Potion.jump, 240, 0, true);
		fluidNausea = potionRegistry.addPotion("Nausea", Potion.confusion, 30, 0);
		fluidNauseaLong = potionRegistry.addPotion("NauseaLong", Potion.confusion, 60, 0);
		fluidNauseaVeryLong = potionRegistry.addPotion("NauseaVeryLong", Potion.confusion, 120, 0);
		fluidResistance = potionRegistry.addPotion("Resistance", Potion.resistance, 90, 0, true);
		fluidResistanceII = potionRegistry.addPotion("ResistanceII", Potion.resistance, 45, 1, true);
		fluidResistanceIII = potionRegistry.addPotion("ResistanceIII", Potion.resistance, 20, 2, true);
		fluidResistanceLong = potionRegistry.addPotion("ResistanceLong", Potion.resistance, 180, 0, true);
		fluidResistanceVeryLong = potionRegistry.addPotion("ResistanceVeryLong", Potion.resistance, 360, 0, true);
		fluidBlindness = potionRegistry.addPotion("Blindness", Potion.blindness, 30, 0);
		fluidBlindnessLong = potionRegistry.addPotion("BlindnessLong", Potion.blindness, 60, 0);
		fluidBlindnessVeryLong = potionRegistry.addPotion("BlindnessVeryLong", Potion.blindness, 120, 0);
		fluidHunger = potionRegistry.addPotion("Hunger", Potion.hunger, 60, 0);
		fluidHungerII = potionRegistry.addPotion("HungerII", Potion.hunger, 30, 1);
		fluidHungerIII = potionRegistry.addPotion("HungerIII", Potion.hunger, 15, 2);
		fluidHungerLong = potionRegistry.addPotion("HungerLong", Potion.hunger, 120, 0);
		fluidHungerVeryLong = potionRegistry.addPotion("HungerVeryLong", Potion.hunger, 240, 0);
		fluidHealthBoost = potionRegistry.addPotion("HealthBoost", Potion.field_76434_w, 120, 0, true);
		fluidHealthBoostII = potionRegistry.addPotion("HealthBoostII", Potion.field_76434_w, 60, 1, true);
		fluidHealthBoostIII = potionRegistry.addPotion("HealthBoostIII", Potion.field_76434_w, 30, 2, true);
		fluidHealthBoostLong = potionRegistry.addPotion("HealthBoostLong", Potion.field_76434_w, 240, 0, true);
		fluidHealthBoostVeryLong = potionRegistry.addPotion("HealthBoostVeryLong", Potion.field_76434_w, 480, 0, true);
		fluidAbsorption = potionRegistry.addPotion("Absorption", Potion.field_76444_x, 60, 0);
		fluidAbsorptionII = potionRegistry.addPotion("AbsorptionII", Potion.field_76444_x, 30, 1);
		fluidAbsorptionIII = potionRegistry.addPotion("AbsorptionIII", Potion.field_76444_x, 15, 2);
		fluidAbsorptionLong = potionRegistry.addPotion("AbsorptionLong", Potion.field_76444_x, 120, 0);
		fluidAbsorptionVeryLong = potionRegistry.addPotion("AbsorptionVeryLong", Potion.field_76444_x, 240, 0);
		fluidSaturation = potionRegistry.addPotion("Saturation", Potion.field_76443_y, 1, 0);
		fluidSaturationII = potionRegistry.addPotion("SaturationII", Potion.field_76443_y, 1, 1);
		fluidSaturationIII = potionRegistry.addPotion("SaturationIII", Potion.field_76443_y, 1, 2);
		fluidSaturationLong = potionRegistry.addPotion("SaturationLong", Potion.field_76443_y, 1, 0);
		fluidSaturationVeryLong = potionRegistry.addPotion("SaturationVeryLong", Potion.field_76443_y, 1, 0);
		fluidWither = potionRegistry.addPotion("Wither", Potion.wither, 20, 0);
		fluidWitherII = potionRegistry.addPotion("WitherII", Potion.wither, 10, 1);
		fluidWitherIII = potionRegistry.addPotion("WitherIII", Potion.wither, 5, 2);
		fluidWitherLong = potionRegistry.addPotion("WitherLong", Potion.wither, 40, 0);
		fluidWitherVeryLong = potionRegistry.addPotion("WitherVeryLong", Potion.wither, 80, 0);

		fluidHolyWater = potionRegistry.addPotion("HolyWater", EffectPlugin.angel, 10, 0, true);
		fluidHolyWaterII = potionRegistry.addPotion("HolyWaterII", EffectPlugin.angel, 5, 1, true);
		fluidHolyWaterLong = potionRegistry.addPotion("HolyWaterLong", EffectPlugin.angel, 20, 0, true);
		fluidHolyWaterVeryLong = potionRegistry.addPotion("HolyWaterVeryLong", EffectPlugin.angel, 40, 0, true);
		fluidHolyWaterIII = potionRegistry.addPotion("HolyWaterIII", EffectPlugin.angel, 3, 2, true);
		fluidFlight = potionRegistry.addPotion("Flight", EffectPlugin.flight, 15, 0);
		fluidFlightLong = potionRegistry.addPotion("FlightLong", EffectPlugin.flight, 30, 0);
		fluidFlightVeryLong = potionRegistry.addPotion("FlightVeryLong", EffectPlugin.flight, 60, 0);
		fluidAntidote = potionRegistry.addPotion("Antidote", EffectPlugin.immunity, 60, 0, true);
		fluidAntidoteII = potionRegistry.addPotion("AntidoteII", EffectPlugin.immunity, 45, 1, true);
		fluidAntidoteIII = potionRegistry.addPotion("AntidoteIII", EffectPlugin.immunity, 30, 2, true);
		fluidAntidoteIIII = potionRegistry.addPotion("AntidoteIIII", EffectPlugin.immunity, 20, 3, true);
		fluidAntidoteLong = potionRegistry.addPotion("AntidoteLong", EffectPlugin.immunity, 120, 0, true);
		fluidAntidoteVeryLong = potionRegistry.addPotion("AntidoteVeryLong", EffectPlugin.immunity, 240, 0, true);
		fluidBoom = potionRegistry.addPotion("Boom", EffectPlugin.creeper, 8, 0, true);
		fluidBoomII = potionRegistry.addPotion("BoomII", EffectPlugin.creeper, 4, 1, true);
		fluidBoomIII = potionRegistry.addPotion("BoomIII", EffectPlugin.creeper, 4, 2, true);
		fluidBoomLong = potionRegistry.addPotion("BoomLong", EffectPlugin.creeper, 16, 0, true);
		fluidBoomVeryLong = potionRegistry.addPotion("BoomVeryLong", EffectPlugin.creeper, 32, 0, true);
		fluidCryo = potionRegistry.addPotion("Cryo", EffectPlugin.frozen, 8, 0, true);
		fluidCryoLong = potionRegistry.addPotion("CryoLong", EffectPlugin.frozen, 16, 0, true);
		fluidCryoVeryLong = potionRegistry.addPotion("CryoVeryLong", EffectPlugin.frozen, 30, 0, true);
		fluidEternalFlame = potionRegistry.addPotion("EternalFlame", EffectPlugin.flame, 30, 0);
		fluidEternalFlameLong = potionRegistry.addPotion("EternalFlameLong", EffectPlugin.flame, 60, 0);
		fluidEternalFlameVeryLong = potionRegistry.addPotion("EternalFlameVeryLong", EffectPlugin.flame, 120, 0);
		fluidFireEater = potionRegistry.addPotion("FireEater", EffectPlugin.fireEater, 45, 0, true);
		fluidFireEaterII = potionRegistry.addPotion("FireEaterII", EffectPlugin.fireEater, 30, 1, true);
		fluidFireEaterIII = potionRegistry.addPotion("FireEaterIII", EffectPlugin.fireEater, 15, 2, true);
		fluidFireEaterLong = potionRegistry.addPotion("FireEaterLong", EffectPlugin.fireEater, 90, 0, true);
		fluidFireEaterVeryLong = potionRegistry.addPotion("FireEaterVeryLong", EffectPlugin.fireEater, 180, 0, true);

		fluidRegenIII = potionRegistry.addPotion("RegenIII", Potion.regeneration, 8, 2);
		fluidRegenVeryLong = potionRegistry.addPotion("RegenVeryLong", Potion.regeneration, 180, 0);
		fluidFastIII = potionRegistry.addPotion("FastIII", Potion.moveSpeed, 8, 2, true);
		fluidFastVeryLong = potionRegistry.addPotion("FastVeryLong", Potion.moveSpeed, 960, 0);
		fluidStrengthIII = potionRegistry.addPotion("StrengthIII", Potion.damageBoost, 40, 2, true);
		fluidStrengthVeryLong = potionRegistry.addPotion("StrengthVeryLong", Potion.damageBoost, 960, 0);
		fluidFireImmunity = potionRegistry.addPotion("FireImmunity", EffectPlugin.fireproof, 35, 0, true);
		fluidFireImmunityII = potionRegistry.addPotion("FireImmunityII", EffectPlugin.fireproof, 15, 1, true);
		fluidFireResistVeryLong = potionRegistry.addPotion("FireResistVeryLong", Potion.fireResistance, 960, 0);
		fluidPoisonIII = potionRegistry.addPotion("PoisonIII", Potion.poison, 8, 2);
		fluidPoisonVeryLong = potionRegistry.addPotion("PoisonVeryLong", Potion.poison, 240, 0);
		fluidHarmIII = potionRegistry.addPotion("HarmIII", Potion.harm, 1, 2);
		fluidHealIII = potionRegistry.addPotion("HealIII", Potion.heal, 1, 2);
		fluidVisionVeryLong = potionRegistry.addPotion("VisionVeryLong", Potion.nightVision, 960, 0);
		fluidWeaknessVeryLong = potionRegistry.addPotion("WeaknessVeryLong", Potion.weakness, 480, 0, true);
		fluidSlownessVeryLong = potionRegistry.addPotion("SlownessVeryLong", Potion.moveSlowdown, 480, 0, true);
		fluidWaterBreatheVeryLong = potionRegistry.addPotion("WaterBreatheVeryLong", Potion.waterBreathing, 960, 0);

		fluidRegenIII = potionRegistry.addPotion("RegenIII", Potion.regeneration, 8, 2);
		fluidRegenVeryLong = potionRegistry.addPotion("RegenVeryLong", Potion.regeneration, 180, 0);
		fluidFastIII = potionRegistry.addPotion("FastIII", Potion.moveSpeed, 8, 2, true);
		fluidFastVeryLong = potionRegistry.addPotion("FastVeryLong", Potion.moveSpeed, 960, 0);
		fluidStrengthIII = potionRegistry.addPotion("StrengthIII", Potion.damageBoost, 40, 2, true);
		fluidStrengthVeryLong = potionRegistry.addPotion("StrengthVeryLong", Potion.damageBoost, 960, 0);
		fluidFireResistVeryLong = potionRegistry.addPotion("FireResistVeryLong", Potion.fireResistance, 960, 0);
		fluidPoisonIII = potionRegistry.addPotion("PoisonIII", Potion.poison, 8, 2);
		fluidPoisonVeryLong = potionRegistry.addPotion("PoisonVeryLong", Potion.poison, 240, 0);
		fluidHarmIII = potionRegistry.addPotion("HarmIII", Potion.harm, 1, 2);
		fluidHealIII = potionRegistry.addPotion("HealIII", Potion.heal, 1, 2);
		fluidVisionVeryLong = potionRegistry.addPotion("VisionVeryLong", Potion.nightVision, 960, 0);
		fluidWeaknessVeryLong = potionRegistry.addPotion("WeaknessVeryLong", Potion.weakness, 480, 0, true);
		fluidSlownessVeryLong = potionRegistry.addPotion("SlownessVeryLong", Potion.moveSlowdown, 480, 0, true);
		fluidWaterBreatheVeryLong = potionRegistry.addPotion("WaterBreatheVeryLong", Potion.waterBreathing, 960, 0);
	}

	@Override
	public void postInit(ModUtils mod) {
		recipeRegistry.addRecipe(water, fluidAwkward, Items.nether_wart);
		recipeRegistry.addRecipe(fluidAwkward, fluidFireResist, Items.magma_cream);
		recipeRegistry.addRecipe(fluidAwkward, fluidFast, Items.sugar);
		recipeRegistry.addRecipe(fluidAwkward, fluidHeal, Items.speckled_melon);
		recipeRegistry.addRecipe(fluidAwkward, fluidPoison, Items.spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidRegen, Items.ghast_tear);
		recipeRegistry.addRecipe(fluidAwkward, fluidStrength, Items.blaze_powder);
		recipeRegistry.addRecipe(fluidAwkward, fluidWeakness, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidResistance, ItemPlugin.steelScales.getStack());
		recipeRegistry.addRecipe(fluidResistance, fluidResistanceII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidResistanceII, fluidResistance, Items.redstone);
		recipeRegistry.addRecipe(fluidResistance, fluidResistanceLong, Items.redstone);
		recipeRegistry.addRecipe(fluidResistanceLong, fluidResistance, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidResistanceII, fluidResistanceIII, ItemPlugin.obsidianTear.getStack());
		recipeRegistry.addRecipe(fluidResistanceLong, fluidResistanceVeryLong, ItemPlugin.pureTear.getStack());
		recipeRegistry.addRecipe(fluidWeakness, fluidWeaknessLong, new ItemStack(Items.redstone));
		recipeRegistry.addRecipe(fluidWeaknessLong, fluidWeakness, new ItemStack(Items.glowstone_dust));
		recipeRegistry.addRecipe(fluidWeaknessLong, fluidWeaknessVeryLong, ItemPlugin.pureTear.getStack());
		recipeRegistry.addRecipe(fluidAwkward, fluidVision, Items.golden_carrot);
		recipeRegistry.addRecipe(fluidAwkward, fluidWaterBreathe, new ItemStack(Items.fish, 1, 3));
		recipeRegistry.addRecipe(fluidFireResist, fluidFireResistLong, Items.redstone);
		recipeRegistry.addRecipe(fluidFireResistLong, fluidFireResist, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFireResistLong, fluidFireResistVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidHeal, fluidHealII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHealII, fluidHeal, Items.redstone);
		recipeRegistry.addRecipe(fluidHealII, fluidHealIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidRegen, fluidRegenLong, Items.redstone);
		recipeRegistry.addRecipe(fluidRegenLong, fluidRegen, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidRegenLong, fluidRegenVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidRegen, fluidRegenII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidRegenII, fluidRegen, Items.redstone);
		recipeRegistry.addRecipe(fluidRegenII, fluidRegenIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidStrength, fluidStrengthII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidStrengthII, fluidStrength, Items.redstone);
		recipeRegistry.addRecipe(fluidStrengthII, fluidStrengthIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidStrength, fluidStrengthLong, Items.redstone);
		recipeRegistry.addRecipe(fluidStrengthLong, fluidStrength, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidStrengthLong, fluidStrengthVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidFast, fluidFastLong, Items.redstone);
		recipeRegistry.addRecipe(fluidFastLong, fluidFast, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFastLong, fluidFastVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidFast, fluidFastII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFastII, fluidFast, Items.redstone);
		recipeRegistry.addRecipe(fluidFastII, fluidFastIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidVision, fluidVisionLong, Items.redstone);
		recipeRegistry.addRecipe(fluidVisionLong, fluidVision, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidVisionLong, fluidVisionVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidVision, fluidInvisible, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidVisionLong, fluidInvisibleLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidVisionVeryLong, fluidInvisibleVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidInvisible, fluidInvisibleLong, Items.redstone);
		recipeRegistry.addRecipe(fluidInvisibleLong, fluidInvisible, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidInvisibleLong, fluidInvisibleVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidWaterBreathe, fluidWaterBreatheLong, Items.redstone);
		recipeRegistry.addRecipe(fluidWaterBreatheLong, fluidWaterBreathe, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidWaterBreatheLong, fluidWaterBreatheVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidHeal, fluidHarm, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHealII, fluidHarmII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHealIII, fluidHarmIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidPoison, fluidHarm, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidPoisonII, fluidHarmII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidPoisonIII, fluidHarmIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHarm, fluidHarmII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHarmII, fluidHarm, Items.redstone);
		recipeRegistry.addRecipe(fluidPoison, fluidPoisonLong, Items.redstone);
		recipeRegistry.addRecipe(fluidPoisonLong, fluidPoison, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidPoison, fluidPoisonII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidPoisonII, fluidPoison, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidVisionLong, fluidInvisibleLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFireResist, fluidSlowness, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFireResistLong, fluidSlownessLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFast, fluidSlowness, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFastLong, fluidSlownessLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFastVeryLong, fluidSlownessVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSlowness, fluidSlownessLong, Items.redstone);
		recipeRegistry.addRecipe(fluidSlownessLong, fluidSlowness, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidStrength, fluidWeakness, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidStrengthLong, fluidWeaknessLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidStrengthVeryLong, fluidWeaknessVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidRegen, fluidWeakness, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidRegenLong, fluidWeaknessLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidRegenVeryLong, fluidWeaknessVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHealII, fluidHeal, Items.redstone);
		recipeRegistry.addRecipe(fluidHarmII, fluidHeal, Items.redstone);
		recipeRegistry.addRecipe(fluidFireResistLong, fluidFireResist, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidSlownessLong, fluidSlowness, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidWeaknessLong, fluidWeakness, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidWaterBreatheLong, fluidWaterBreathe, Items.glowstone_dust);

		recipeRegistry.addRecipe(fluidAwkward, fluidFlight, ItemPlugin.goldenFeather);
		recipeRegistry.addRecipe(fluidFlight, fluidFlightLong, Items.redstone);
		recipeRegistry.addRecipe(fluidFlightLong, fluidFlight, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFlightLong, fluidFlightVeryLong, ItemPlugin.pureTear.getStack());
		recipeRegistry.addRecipe(fluidRegen, fluidHolyWater, ItemPlugin.holyDust);
		recipeRegistry.addRecipe(fluidRegenII, fluidHolyWaterII, ItemPlugin.holyDust);
		recipeRegistry.addRecipe(fluidRegenIII, fluidHolyWaterIII, ItemPlugin.holyDust);
		recipeRegistry.addRecipe(fluidRegenLong, fluidHolyWaterLong, ItemPlugin.holyDust);
		recipeRegistry.addRecipe(fluidRegenVeryLong, fluidHolyWaterVeryLong, ItemPlugin.holyDust);
		recipeRegistry.addRecipe(fluidHolyWater, fluidHarm, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHolyWaterII, fluidHarmII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHolyWaterIII, fluidHarmIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHolyWater, fluidHolyWaterII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHolyWaterII, fluidHolyWater, Items.redstone);
		recipeRegistry.addRecipe(fluidHolyWater, fluidHolyWaterLong, Items.redstone);
		recipeRegistry.addRecipe(fluidHolyWaterLong, fluidHolyWater, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHolyWaterII, fluidHolyWaterIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidHolyWaterLong, fluidHolyWaterVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidAwkward, fluidFlight, ItemPlugin.goldenFeather);
		recipeRegistry.addRecipe(fluidAntidoteII, fluidAntidoteIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidAntidoteLong, fluidAntidoteVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidWither, fluidBoom, Items.gunpowder);
		recipeRegistry.addRecipe(fluidWitherII, fluidBoomII, Items.gunpowder);
		recipeRegistry.addRecipe(fluidWitherIII, fluidBoomIII, Items.gunpowder);
		recipeRegistry.addRecipe(fluidWitherLong, fluidBoomLong, Items.gunpowder);
		recipeRegistry.addRecipe(fluidWitherVeryLong, fluidBoomVeryLong, Items.gunpowder);
		recipeRegistry.addRecipe(fluidBoom, fluidBoomII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidBoomII, fluidBoom, Items.redstone);
		recipeRegistry.addRecipe(fluidBoom, fluidBoomLong, Items.redstone);
		recipeRegistry.addRecipe(fluidBoomLong, fluidBoom, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidBoomII, fluidBoomIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidBoomLong, fluidBoomVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidSlowness, fluidCryo, Items.snowball);
		recipeRegistry.addRecipe(fluidCryo, fluidCryoLong, new ItemStack(Items.redstone));
		recipeRegistry.addRecipe(fluidCryoLong, fluidCryo, new ItemStack(Items.glowstone_dust));
		recipeRegistry.addRecipe(fluidCryoLong, fluidCryoVeryLong, ItemPlugin.pureTear.getStack());
		recipeRegistry.addRecipe(fluidSlownessLong, fluidCryoLong, Items.snowball);
		recipeRegistry.addRecipe(fluidSlownessVeryLong, fluidCryoVeryLong, Items.snowball);
		recipeRegistry.addRecipe(fluidSlowness, fluidSlownessLong, Items.redstone);
		recipeRegistry.addRecipe(fluidSlownessLong, fluidSlowness, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidSlownessLong, fluidSlownessVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(lava, fluidEternalFlame, new ItemStack(Items.fire_charge));
		recipeRegistry.addRecipe(fluidEternalFlame, fluidEternalFlameLong, new ItemStack(Items.redstone));
		recipeRegistry.addRecipe(fluidEternalFlameLong, fluidEternalFlame, new ItemStack(Items.glowstone_dust));
		recipeRegistry.addRecipe(fluidEternalFlameLong, fluidEternalFlameVeryLong, ItemPlugin.pureTear.getStack());
		recipeRegistry.addRecipe(lava, fluidFireEater, ItemPlugin.heartBlaze.getStack());
		recipeRegistry.addRecipe(fluidFireEater, fluidFireEaterII, new ItemStack(Items.glowstone_dust));
		recipeRegistry.addRecipe(fluidFireEaterII, fluidFireEater, new ItemStack(Items.redstone));
		recipeRegistry.addRecipe(fluidFireEaterII, fluidFireEaterIII, ItemPlugin.obsidianTear.getStack());
		recipeRegistry.addRecipe(fluidFireEater, fluidFireEaterLong, new ItemStack(Items.redstone));
		recipeRegistry.addRecipe(fluidFireEaterLong, fluidFireEater, new ItemStack(Items.glowstone_dust));
		recipeRegistry.addRecipe(fluidFireEaterLong, fluidFireEaterVeryLong, ItemPlugin.pureTear.getStack());

		recipeRegistry.addRecipe(fluidAwkward, fluidNausea, Items.poisonous_potato);
		recipeRegistry.addRecipe(fluidNausea, fluidNauseaLong, Items.redstone);
		recipeRegistry.addRecipe(fluidNauseaLong, fluidNausea, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidNauseaLong, fluidNauseaVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidAwkward, fluidBlindness, new ItemStack(Items.dye, 1, 0));
		recipeRegistry.addRecipe(fluidBlindness, fluidBlindnessLong, Items.redstone);
		recipeRegistry.addRecipe(fluidBlindnessLong, fluidBlindnessVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidBlindness, fluidVision, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidBlindnessLong, fluidVisionLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidBlindnessVeryLong, fluidVisionVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidHunger, Items.rotten_flesh);
		recipeRegistry.addRecipe(fluidHunger, fluidHungerII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHunger, fluidHungerLong, Items.rotten_flesh);
		recipeRegistry.addRecipe(fluidHungerII, fluidHungerIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidHungerLong, fluidHungerVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidHunger, fluidSaturation, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerII, fluidSaturationII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerIII, fluidSaturationIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerLong, fluidSaturationLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerVeryLong, fluidSaturationVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidSaturation, Items.cake);
		recipeRegistry.addRecipe(fluidHunger, fluidSaturation, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturation, fluidHunger, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerII, fluidSaturationII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturationII, fluidHungerII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerIII, fluidSaturationIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturationIII, fluidHungerIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerLong, fluidSaturationLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturationLong, fluidHungerLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHungerVeryLong, fluidSaturationVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturationVeryLong, fluidHungerVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidSaturation, fluidSaturationII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidSaturationII, fluidSaturationIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidSaturation, fluidSaturationLong, Items.redstone);
		recipeRegistry.addRecipe(fluidSaturationLong, fluidSaturationVeryLong, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidAwkward, fluidHaste, ItemPlugin.spiderFang);
		recipeRegistry.addRecipe(fluidFatigue, fluidHaste, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHaste, fluidFatigue, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFatigueII, fluidHasteII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHasteII, fluidFatigueII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFatigueIII, fluidHasteIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHasteIII, fluidFatigueIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFatigueLong, fluidHasteLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHasteLong, fluidFatigueLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidFatigueVeryLong, fluidHasteVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHasteVeryLong, fluidFatigueVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidHaste, fluidHasteII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHasteII, fluidHaste, Items.redstone);
		recipeRegistry.addRecipe(fluidHasteLong, fluidHaste, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHasteII, fluidHasteIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidHaste, fluidHasteLong, Items.redstone);
		recipeRegistry.addRecipe(fluidHasteLong, fluidHasteVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidFatigue, fluidHaste, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidFatigue, ItemPlugin.tiredSpores);
		recipeRegistry.addRecipe(fluidFatigue, fluidFatigueII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFatigueII, fluidFatigue, Items.redstone);
		recipeRegistry.addRecipe(fluidFatigueII, fluidFatigueIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidFatigue, fluidFatigueLong, Items.redstone);
		recipeRegistry.addRecipe(fluidFatigueLong, fluidFatigue, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidFatigueLong, fluidFatigueVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidAwkward, fluidHealthBoost, ItemPlugin.heartGold);
		recipeRegistry.addRecipe(fluidHealthBoost, fluidHealthBoostII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHealthBoostII, fluidHealthBoost, Items.redstone);
		recipeRegistry.addRecipe(fluidHealthBoostII, fluidHealthBoostIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidHealthBoost, fluidHealthBoostLong, Items.redstone);
		recipeRegistry.addRecipe(fluidHealthBoostLong, fluidHealthBoost, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidHealthBoostLong, fluidHealthBoostVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidAwkward, fluidAntidote, ItemPlugin.remedySalve);
		recipeRegistry.addRecipe(fluidAntidote, fluidAntidoteII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidAntidoteII, fluidAntidote, Items.redstone);
		recipeRegistry.addRecipe(fluidAntidoteII, fluidAntidoteIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidAntidote, fluidAntidoteLong, Items.redstone);
		recipeRegistry.addRecipe(fluidAntidoteLong, fluidAntidote, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidAntidoteLong, fluidAntidoteVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidAntidote, fluidWither, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidWither, fluidAntidote, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAntidoteII, fluidWitherII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidWitherII, fluidAntidoteII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAntidoteIII, fluidWitherIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidWitherIII, fluidAntidoteIII, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAntidoteLong, fluidWitherLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidWitherLong, fluidAntidoteLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAntidoteVeryLong, fluidWitherVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidWitherVeryLong, fluidAntidoteVeryLong, Items.fermented_spider_eye);
		recipeRegistry.addRecipe(fluidAwkward, fluidJump, Items.carrot);
		recipeRegistry.addRecipe(fluidJump, fluidJumpII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidJumpII, fluidJump, Items.redstone);
		recipeRegistry.addRecipe(fluidJumpII, fluidJumpIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidJump, fluidJumpLong, Items.redstone);
		recipeRegistry.addRecipe(fluidJumpLong, fluidJump, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidJumpLong, fluidJumpVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(lava, fluidWither, ItemPlugin.charredBone);
		recipeRegistry.addRecipe(fluidWither, fluidWitherII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidWitherII, fluidWitherIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidWither, fluidWitherLong, Items.redstone);
		recipeRegistry.addRecipe(fluidWitherLong, fluidWitherVeryLong, ItemPlugin.pureTear);
		recipeRegistry.addRecipe(fluidWitherII, fluidWither, Items.redstone);
		recipeRegistry.addRecipe(fluidWitherLong, fluidWither, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidAwkward, fluidAbsorption, ItemPlugin.heartSmall);
		recipeRegistry.addRecipe(fluidAbsorption, fluidAbsorptionII, Items.glowstone_dust);
		recipeRegistry.addRecipe(fluidAbsorptionII, fluidAbsorption, Items.redstone);
		recipeRegistry.addRecipe(fluidAbsorptionII, fluidAbsorptionIII, ItemPlugin.obsidianTear);
		recipeRegistry.addRecipe(fluidAbsorption, fluidAbsorptionLong, Items.redstone);
		recipeRegistry.addRecipe(fluidAbsorptionLong, fluidAbsorption, Items.glowstone_dust);
	}

	private FluidStack createVanillaPotion(String name, int metaBottle, int metaSplash, int duration, int strength) {
		FluidStack potion = new FluidStack(FluidUtil.createFluid(new FluidPotion("potion" + name, new SimpleItem(
				Items.potionitem, metaBottle), duration * 20, strength), potionTexture), 1000);
		FluidContainerRegistry.registerFluidContainer(potion, new ItemStack(Items.potionitem, 1, metaBottle),
				emptyBottle);
		if (metaSplash != 0)
			FluidContainerRegistry.registerFluidContainer(potion, new ItemStack(Items.potionitem, 1, metaSplash),
					ItemPlugin.splashBottle.getStack());
		return potion;
	}
}

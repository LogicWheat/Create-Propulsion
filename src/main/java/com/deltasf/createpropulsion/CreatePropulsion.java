package com.deltasf.createpropulsion;

import com.deltasf.createpropulsion.compat.computercraft.CCProxy;
import com.deltasf.createpropulsion.network.PropulsionPackets;
import com.deltasf.createpropulsion.particles.ParticleTypes;
import com.deltasf.createpropulsion.registries.PropulsionBlockEntities;
import com.deltasf.createpropulsion.registries.PropulsionBlocks;
import com.deltasf.createpropulsion.registries.PropulsionCreativeTab;
import com.deltasf.createpropulsion.registries.PropulsionFluids;
import com.deltasf.createpropulsion.registries.PropulsionItems;
import com.deltasf.createpropulsion.registries.PropulsionPartialModels;
import com.simibubi.create.compat.Mods;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModLoadingContext;

@Mod(CreatePropulsion.ID)
public class CreatePropulsion {
    public static final String ID = "createpropulsion";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);
    public static CreateRegistrate registrate() { return REGISTRATE; }

    public CreatePropulsion() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //Content
        ParticleTypes.register(modBus);
        PropulsionBlocks.register();
        PropulsionBlockEntities.register();
        PropulsionItems.register();
        PropulsionFluids.register();
        PropulsionPartialModels.register();
        PropulsionCreativeTab.register(modBus);
        PropulsionPackets.register();
        //Compat
        Mods.COMPUTERCRAFT.executeIfInstalled(() -> CCProxy::register);
        //Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PropulsionConfig.SERVER_SPEC, ID + "-server.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, PropulsionConfig.CLIENT_SPEC, ID + "-client.toml");
        //Registrate
        REGISTRATE.registerEventListeners(modBus);
    }
}

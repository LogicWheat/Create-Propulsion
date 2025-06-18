package com.deltasf.createpropulsion.network;

import com.deltasf.createpropulsion.CreatePropulsion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

//Better than PacketHandler
public class PropulsionPackets {
    private static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(CreatePropulsion.ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int id() { return packetId++; }

    public static void register() {
        INSTANCE.messageBuilder(SyncThrusterFuelsPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .encoder(SyncThrusterFuelsPacket::encode)
            .decoder(SyncThrusterFuelsPacket::decode)
            .consumerMainThread(SyncThrusterFuelsPacket::handle)
            .add();
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

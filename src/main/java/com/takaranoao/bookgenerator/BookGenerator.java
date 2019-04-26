package com.takaranoao.bookgenerator;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class BookGenerator implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("BookGenerator loaded!");
	}
}

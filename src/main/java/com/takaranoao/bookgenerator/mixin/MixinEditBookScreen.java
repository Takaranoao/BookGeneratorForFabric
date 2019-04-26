package com.takaranoao.bookgenerator.mixin;
import net.minecraft.client.gui.ingame.EditBookScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EditBookScreen.class)
public class MixinEditBookScreen {
	@Final
	@Shadow
	private PlayerEntity player;
	@Final
	@Shadow
	private List<String> pages;
	@Shadow
	private boolean dirty;
	@Inject(method = "finalizeBook",at = @At("HEAD"))
	private void onSendBookToServer(boolean publish, CallbackInfo ci){
		if(pages != null && pages.size()>0){
			if(pages.get(0).equals("AutoGen")){
				this.dirty = true;
				while (pages.size() < 100){
					this.pages.add("");
				}
				for (int i=0;i<pages.size();i++){
					StringBuilder t1 = new StringBuilder();
					while (t1.length()<255){
						t1.append((char) ((int) (Math.random() * 255) + 1));
					}
					pages.set(i, t1.toString());
				}
				if(player != null){
					player.sendMessage(new StringTextComponent(
							I18n.translate("bookgenerator.notify.autogen")
					));
				}

			}
		}
	}
}

package me.amar.grantxproject.BotEvents;

import me.amar.grantxproject.GrantXProject;
import me.amar.grantxproject.MinecraftEvents.GrantEvent;
import me.amar.grantxproject.MinecraftEvents.GrantExpire;
import me.amar.grantxproject.MinecraftEvents.GrantRevoke;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class DisableLogs extends ListenerAdapter {
    private static GrantXProject plugin = GrantXProject.getPlugin(GrantXProject.class);

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        if(args.length == 2){
            if(e.getMember().hasPermission(Permission.ADMINISTRATOR)){
                if(args[0].equalsIgnoreCase( "xdisable")){
                    // ok you may get a bitttttttt further from your mic
                    // like uh TA?? commands and sub commands? idk we can do that later
                    switch (args[1].toLowerCase()){
                        case "grant":
                            GrantEvent grantEvent = new GrantEvent();
                            grantEvent.setEnabled(false);
                                  break;
                        case "revoke":
                            GrantRevoke grantRevoke = new GrantRevoke();
                            grantRevoke.setEnabled(false);
                            break;
                        case "expire":
                            GrantExpire grantExpire = new GrantExpire();
                            grantExpire.setEnabled(false);
                            break;
                                  //they're tired so
                    }//cringe amirite

                }
            }
        }
    }
}

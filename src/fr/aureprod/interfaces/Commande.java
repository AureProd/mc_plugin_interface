package fr.aureprod.interfaces;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Commande implements CommandExecutor 
{
	@SuppressWarnings("unused")
	private Main main;
	
	public Commande(Main mainbis)
	{
		this.main = mainbis;
	}
	
	private Integer toChiffre(String txt, Player p)
	{
		char[] liste = txt.toCharArray();
		
		Integer i = liste.length;
		Integer resultat = 0;
		
		for (Character c : liste) 
		{
			i--;
			
			switch(c) 
			{
				case '0':
					resultat = (int) (resultat + (0 * Math.pow(10, i)));
					break;
				case '1':
					resultat = (int) (resultat + (1 * Math.pow(10, i)));
					break;
				case '2':
					resultat = (int) (resultat + (2 * Math.pow(10, i)));
					break;
				case '3':
					resultat = (int) (resultat + (3 * Math.pow(10, i)));
					break;
				case '4':
					resultat = (int) (resultat + (4 * Math.pow(10, i)));
					break;
				case '5':
					resultat = (int) (resultat + (5 * Math.pow(10, i)));
					break;
				case '6':
					resultat = (int) (resultat + (6 * Math.pow(10, i)));
					break;
				case '7':
					resultat = (int) (resultat + (7 * Math.pow(10, i)));
					break;
				case '8':
					resultat = (int) (resultat + (8 * Math.pow(10, i)));
					break;
				case '9':
					resultat = (int) (resultat + (9 * Math.pow(10, i)));
					break;
				default:
					return null;
			}
		}
		
		return resultat;
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("interface"))
		{
			if (sender instanceof Player) 
			{
				if (args.length == 0)
				{
					Player player = (Player) sender;
					
					String playerName = player.getName();
				    String playerUuid = player.getUniqueId().toString();
				    
				    File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Essentials").getDataFolder(), File.separator + "userdata");
				    File f = new File(userdata, File.separator + playerUuid + ".yml");
				    FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
				    String monaie = playerData.getString("money");
				    
				    if (toChiffre(monaie, player) != null)
				    {
				    	Integer monaieint = toChiffre(monaie, player);
					    
						player.sendMessage(ChatColor.BLUE + "Le joueur " + playerName + " possede " + monaieint + " $");
					} 
				    else
				    {
				    	player.sendMessage(ChatColor.RED + "Il y a une ERREUR !!!");
					}				
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "Il ne faut pas mettre d'arguments dans cette commande !!");
				}
			}
			else
			{
				System.out.println("Cette commande n'est pas accessible via la console !!");
			}
		}

		return false;
	}
}

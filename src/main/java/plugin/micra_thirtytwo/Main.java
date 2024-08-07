package plugin.micra_thirtytwo;

import command.EnemyDownCommand;
import command.EnemySpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public final class Main extends JavaPlugin implements Listener {

  private Player player;


  @Override
  public void onEnable() {
    EnemyDownCommand enemyDownCommand = new EnemyDownCommand(this);
    EnemySpawnCommand enemySpawnCommand = new EnemySpawnCommand();

    Bukkit.getPluginManager().registerEvents(enemyDownCommand, this);
    Bukkit.getPluginManager().registerEvents(this, this);
    Bukkit.getPluginManager().registerEvents(this, this);

    getCommand("enemyDown").setExecutor(enemyDownCommand);
    getCommand("enemySpawn").setExecutor(enemySpawnCommand);
  }


  // プレイヤーがマイクラサーバーに参加した時のイベント
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();

    // 少し遅延させてタイトルを表示
    new BukkitRunnable() {
      @Override
      public void run() {
        player.sendTitle("出現させた時だけ点数獲得!", "", 10, 90, 30);
      }
    }.runTaskLater(this, 20L); // 1秒遅延
  }

}
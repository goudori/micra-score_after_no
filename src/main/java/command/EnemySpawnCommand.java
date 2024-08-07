package command;

import command.BaseCommand;
import java.util.List;
import java.util.SplittableRandom;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class EnemySpawnCommand extends BaseCommand implements Listener {

  @Override
  public boolean onExecutePlayerCommand(Player player) {
    player.getWorld().spawnEntity(getEnemySpawnLocation(player), getEnemy());
    player.sendMessage("敵が出現しました!");
    return true;
  }

  @Override
  public boolean onExecuteNPCCommand(CommandSender sender) {
    return false;
  }

  /**
   * 敵の出現場所を取得します。出現エリアはX軸とZ軸は自分の位置からプラス、ランダムで-10〜9の値が設定されます。 Y軸はプレイヤーと同じ位置になります。
   *
   * @param player コマンドを実行したプレイヤー
   * @return 敵の出現場所
   */
  private Location getEnemySpawnLocation(Player player) {
    Location playerLocation = player.getLocation();
    int randomX = new SplittableRandom().nextInt(20) - 10;
    int randomZ = new SplittableRandom().nextInt(20) - 10;

    double x = playerLocation.getX() + randomX;
    double y = playerLocation.getY();
    double z = playerLocation.getZ() + randomZ;

    return new Location(player.getWorld(), x, y, z);
  }

  /**
   * ランダムで敵を抽選して、その結果の敵を取得します。
   *
   * @return 敵
   */
  private EntityType getEnemy() {
    List<EntityType> enemyList = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.WITCH);
    return enemyList.get(new SplittableRandom().nextInt(enemyList.size()));
  }
}
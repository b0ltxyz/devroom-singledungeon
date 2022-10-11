package me.rin.devroom.config;

import com.hakan.core.configuration.ConfigType;
import com.hakan.core.configuration.annotations.ConfigFile;
import com.hakan.core.configuration.annotations.ConfigValue;
import com.hakan.core.configuration.containers.yaml.YamlConfigContainer;
import me.rin.devroom.SingleDungeonPlugin;

import java.util.Arrays;
import java.util.List;


@ConfigFile(
        plugin = SingleDungeonPlugin.class,
        type = ConfigType.YAML,
        resource = "config.yml",
        path = "plugins/SingleDungeon/config.yml"
)
public class DungeonConfiguration extends YamlConfigContainer {

    @ConfigValue(path = "dungeon-location")
    public String dungeonLocation = "world:150:100:50";

    @ConfigValue(path = "mob-spawn-locations")
    public List<String> mobSpawnLocations = Arrays.asList("world:150:100:50");

    @ConfigValue(path = "mysql.host")
    public String mysqlHost = "127.0.0.1";

    @ConfigValue(path = "mysql.port")
    public Integer mysqlPort = 3306;

    @ConfigValue(path = "mysql.username")
    public String mysqlUsername = "devroom";

    @ConfigValue(path = "mysql.password")
    public String mysqlPassword = "devroom";


}

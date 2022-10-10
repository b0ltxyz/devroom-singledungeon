package me.rin.devroom.config;

import com.hakan.core.configuration.ConfigType;
import com.hakan.core.configuration.annotations.ConfigFile;
import com.hakan.core.configuration.annotations.ConfigValue;
import com.hakan.core.configuration.containers.yaml.YamlConfigContainer;
import me.rin.devroom.SingleDungeonPlugin;

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


}

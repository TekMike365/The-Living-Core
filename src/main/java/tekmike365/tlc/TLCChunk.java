package tekmike365.tlc;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.WorldChunk;

public class TLCChunk {

    public ServerWorld world;
    public WorldChunk chunk;

    public TLCChunk(ServerWorld world, WorldChunk chunk) {
        this.world = world;
        this.chunk = chunk;

        load();
    }

    public boolean has(ServerWorld world, WorldChunk chunk){
        return world == this.world && chunk == this.chunk;
    }

    public void save() {
        // TODO: implement
    }

    public void load() {
        // TODO: implement
    }
}

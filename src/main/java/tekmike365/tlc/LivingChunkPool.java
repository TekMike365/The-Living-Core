package tekmike365.tlc;

import java.util.ArrayList;
import java.util.List;

public class LivingChunkPool {

	private static List<LivingChunk> livingChunks = new ArrayList<>();

    public int size() {
        return livingChunks.size();
    }

    public int indexOf(LivingChunk tlcChunk) {
        for (int i = 0; i < livingChunks.size(); i++) {
            if (livingChunks.get(i).has(tlcChunk.world, tlcChunk.chunk))
                return i;
        }
        return -1;
    }

    public boolean has(LivingChunk tlcChunk) {
        return indexOf(tlcChunk) != -1;
    }

    public boolean addChunk(LivingChunk tlcChunk) {
        int tlcChunkIdx = indexOf(tlcChunk);
        if (tlcChunkIdx != -1)
            return false;
        livingChunks.add(tlcChunk);
        TheLivingCore.LOGGER.info("added TLCChunk: %s (size: %d)".formatted(tlcChunk.chunk.getPos().toString(), size()));
        return true;
    }

    public boolean removeChunk(LivingChunk tlcChunk) {
        int tlcChunkIdx = indexOf(tlcChunk);
        if (tlcChunkIdx == -1)
            return false;
        livingChunks.remove(tlcChunkIdx).save();
        TheLivingCore.LOGGER.info("removed TLCChunk: %s (size: %d)".formatted(tlcChunk.chunk.getPos().toString(), size()));
        return true;
    }

}

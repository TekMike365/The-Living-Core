package tekmike365.tlc;

import java.util.ArrayList;
import java.util.List;

public class LivingChunkPool {

	private static List<LivingChunk> tlcChunks = new ArrayList<>();

    public int size() {
        return tlcChunks.size();
    }

    public int indexOf(LivingChunk tlcChunk) {
        for (int i = 0; i < tlcChunks.size(); i++) {
            if (tlcChunks.get(i).has(tlcChunk.world, tlcChunk.chunk))
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
        tlcChunks.add(tlcChunk);
        TheLivingCore.LOGGER.info("added TLCChunk: %s (size: %d)".formatted(tlcChunk.chunk.getPos().toString(), size()));
        return true;
    }

    public boolean removeChunk(LivingChunk tlcChunk) {
        int tlcChunkIdx = indexOf(tlcChunk);
        if (tlcChunkIdx == -1)
            return false;
        tlcChunks.remove(tlcChunkIdx).save();
        TheLivingCore.LOGGER.info("removed TLCChunk: %s (size: %d)".formatted(tlcChunk.chunk.getPos().toString(), size()));
        return true;
    }

}

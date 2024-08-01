package com.flexicon.rel;

import java.util.List;

/**
 * A response containing information about a search on the rels table.
 *
 * @author isaac1000000
 */
public class RelResponse {

    /**
     * The number of results for the query, not necessarily the length of the response instance list. Pages and other
     * schemes may cause (intentional) disparity between these lengths.
     */
    private final long len;

    /**
     * The list of {@link Rel} items included in the response instance.
     */
    private final List<RelDTO> rels;

    public RelResponse(long len, List<RelDTO> rels) {
        this.len = len;
        this.rels = rels;
    }

    public long getLen() {
        return len;
    }

    public List<RelDTO> getRels() {
        return rels;
    }
}

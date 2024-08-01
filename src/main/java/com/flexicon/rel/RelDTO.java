package com.flexicon.rel;

import com.flexicon.word.Word;

/**
 *  A data transfer object for the {@link Rel} class
 *
 * @author isaac1000000
 */
public class RelDTO {

    /**
     * The id of the base word
     * @see Word
     */
    private Long baseId;

    /**
     * The readable string of the base word
     * @see Word
     */
    private String base;

    /**
     * The id of the target word
     * @see Word
     */
    private Long targetId;

    /**
     * The readable string of the target word
     * @see Word
     */
    private String target;

    /**
     * The definition of the target word
     * @see Word
     */
    private String targetDefinition;

    /**
     * The frequency of the target word
     * @see Word
     */
    private Double targetFrequency;

    /**
     * The strength of the relationship between base and target
     * @see Rel
     */
    private Double strength;

    /**
     * The {@link Rel} used to create the DTO
     *
     * @param rel
     */
    public RelDTO(Rel rel) {
        Word base = rel.getBase();
        Word target = rel.getTarget();
        this.baseId = base.getId();
        this.base = base.getWord();
        this.targetId = target.getId();
        this.target = target.getWord();
        this.targetDefinition = target.getDefinition();
        this.targetFrequency = target.getFrequency();
        this.strength = rel.getStrength();
    }

    public Long getBaseId() {
        return baseId;
    }

    public String getBase() {
        return base;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getTarget() {
        return target;
    }

    public String getTargetDefinition() {
        return targetDefinition;
    }

    public Double getTargetFrequency() {
        return targetFrequency;
    }

    public Double getStrength() {
        return strength;
    }
}

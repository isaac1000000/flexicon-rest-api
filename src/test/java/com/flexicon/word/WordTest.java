package com.flexicon.word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class WordTest {

    @Test
    void givenWordAsString_whenHashForId_thenReturnId() {
        // Includes negative id values
        String[] actualWords = new String[]{"hello", "injected", "deep", "emphasised"};
        Long[] actualIds = new Long[actualWords.length];
        int index = 0;
        for (String word : actualWords) {
            actualIds[index] = Word.toId(actualWords[index]);
            index++;
        }
        Long [] expectedIds = new Long[]{6719722671305337462L, -5221493511465913455L, 7360923990098895676L, -4020402198121088186L};
        assertArrayEquals(actualIds, expectedIds);
    }
}
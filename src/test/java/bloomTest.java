import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class bloomTest {

    Filter<Integer> filter1 = new Filter<>(3,1);
    Filter<Integer> filter2 = new Filter<>(100,2);
    Filter<Integer> sameFilter2 = new Filter<>(100,2);
    Bitset bitset = new Bitset(100);

    private List<Integer> getArr(){
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        return arr;
    }

    @Test
    void size(){
        assertEquals(filter1.size(), 3);
        assertEquals(filter2.size(), 100);
    }

    @Test
    void isEmpty(){
        assertTrue(filter1.isEmpty());
        assertTrue(filter2.isEmpty());
    }

    @Test
    void contains(){
        filter2.add(5);
        filter2.add(2);
        assertTrue(filter2.contains(5));
        assertTrue(filter2.contains(2));
        assertFalse(filter2.contains(1));
        //check toString()
        System.out.println(filter2.toString());
        //check equals()
        sameFilter2.add(5);
        sameFilter2.add(2);
        assertTrue(filter2.equals(sameFilter2));
    }

    @Test
    void addAll(){
        filter1.addAll(getArr());
        assertTrue(filter1.containsAll(getArr()));
    }

    @Test
    void isClear(){
        filter1.clear();
        assertTrue(filter1.isEmpty());
    }

    @Test
    void iterator(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.iterator());
    }

    @Test
    void toArray(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.toArray());
    }

    @Test
    void remove(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.remove(5));
    }

    @Test
    void removeAll(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.removeAll(getArr()));
    }

    @Test
    void retainAll(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.retainAll(getArr()));
    }

    @Test
    void toArrayObj(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                filter2.toArray(new Object[2]));
    }

    //bitset tests
    @Test
    void setBit(){
        bitset.setBit(11);
        assertTrue(bitset.getSetBit(11));
        assertFalse(bitset.getSetBit(1));
    }
}
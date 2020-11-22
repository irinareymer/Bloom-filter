import java.util.*;

public class Filter<T extends Comparable<T>> extends AbstractSet<T> implements Set<T> {

    private int size;
    private final int k;  // k - number of hash functions
    private Bitset bitset;

    public Filter(int size, int k){
        if (k <= 0 || size <= 0) throw new IllegalArgumentException();
        this.size = size;
        this.k = k;
        this.bitset = new Bitset(size);
    }

    public int getK() {
        return k;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < bitset.getBitSize()/32 ; i++)
            if (bitset.getBitset()[i] != 0) return false;
        return true;
    }

    private int hashFunction(T t, int i){
        return ((t.hashCode() + (1 << (i+17)%32) + 51) % size);
    }

    @Override
    public boolean add(T t) {
        for (int i = 0; i < k; i++) {
            int index = hashFunction(t, i);
            bitset.setBit(index);
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        for (int i = 0; i < k; i++) {
            int index = hashFunction(t, i);
            if (!bitset.getSetBit(index)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o: c){
            add((T) o);
        }
        return true;
    }

    @Override
    public void clear() {
        bitset.init(bitset.getBitSize()/32);
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o: c){
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Filter dif = (Filter) o;
        if (size != dif.size() || k != dif.getK()) return false;
        for (int i = 0; i < bitset.getBitSize()/32 ; i++) {
            if (this.bitset.getBitset()[i] != dif.bitset.getBitset()[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bitset, size, k);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < bitset.getBitSize()/32 ; i++) {
            str.append("bit [").append(i).append("] = ").append(bitset.getBitset()[i]).append(", ");
        }
        str.replace(str.lastIndexOf(","), str.lastIndexOf(" "), ".");
        return str.toString();
    }

//Unsupported
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }
}
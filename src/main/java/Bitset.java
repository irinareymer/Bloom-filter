public class Bitset {

    private final int[] bitset;
    private final int bitSize;

    public void init(int s){
        for (int i = 0; i < s; i++) this.bitset[i] = 0;
    }

    public Bitset(int size){
        int s = size / 32;
        if (size % 32 != 0) s++;
        this.bitset = new int[s];
        this.bitSize = s * 32;
        init(s);
    }

    public int[] getBitset() {
        return bitset;
    }

    public int getBitSize() {
        return bitSize;
    }

    public void setBit(int index){
        int bit = index / 32;
        int b = index % 32;
        bitset[bit] = bitset[bit] | (1 << b);
    }

    public boolean getSetBit(int index){
        int bit = index / 32;
        int b = index % 32;
        return ((bitset[bit] & (1 << b)) == (1 << b));
    }
}
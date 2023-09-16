package files;

public abstract class Storage implements Comparable<Storage> {
    public abstract double getSize();

    @Override
    public int compareTo(final Storage o) {
        return (int) Math.round(this.getSize() - o.getSize());
    }
}

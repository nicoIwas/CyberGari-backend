package main.file;

public abstract class Storage implements Comparable<Storage> {
    public abstract double getSize();
    public abstract int getFileCount();

    @Override
    public int compareTo(final Storage o) {
        return (int) Math.round(this.getSize() - o.getSize());
    }
}

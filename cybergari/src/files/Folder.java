package files;

import java.util.Set;
import java.util.TreeSet;

public class Folder extends Storage {
    private final Set<Storage> storages;

    public Folder() {
        this.storages = new TreeSet<>();
    }

    // Maybe a top-down folder creation is better
    public void addStorage(final Storage toAdd) {
        this.storages.add(toAdd);
    }

    @Override
    public double getSize() {
        return storages.stream().map(Storage::getSize).reduce(0.0, Double::sum);
    }
}

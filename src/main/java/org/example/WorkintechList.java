package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class WorkintechList<E> extends ArrayList<E> {

    public WorkintechList() {
        super();
    }

    public WorkintechList(Collection<? extends E> c) {
        super();
        this.addAll(c); // addAll override duplicate engeller
    }

    // Duplicate eklemeyi engelle
    @Override
    public boolean add(E e) {
        if (this.contains(e)) return false;
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (this.contains(element)) return;
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E item : c) {
            if (!this.contains(item)) {
                changed |= super.add(item);
            }
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean changed = false;
        int currentIndex = index;

        for (E item : c) {
            if (!this.contains(item)) {
                super.add(currentIndex, item);
                currentIndex++;
                changed = true;
            }
        }
        return changed;
    }

    // set de duplicate oluşturabilir → engelle
    @Override
    public E set(int index, E element) {
        E current = super.get(index);

        // aynı elemanı aynı yere set ise sorun yok
        if (current == null ? element == null : current.equals(element)) {
            return super.set(index, element);
        }

        // listede başka yerde varsa duplicate olur → engelle
        if (this.contains(element)) {
            return current; // değişiklik yapmıyoruz
        }

        return super.set(index, element);
    }

    /**
     * WorkintechList içindeki tüm değerleri sıralar:
     * - String ise A -> Z (case-insensitive)
     * - Number ise küçükten büyüğe
     * - Diğer tiplerde: toString() ile sıralar
     */
    public void sort() {
        if (this.isEmpty()) return;

        Object firstNonNull = null;
        for (E e : this) {
            if (e != null) {
                firstNonNull = e;
                break;
            }
        }
        if (firstNonNull == null) return;

        if (firstNonNull instanceof String) {
            super.sort(Comparator.comparing(
                    o -> (String) o,
                    String.CASE_INSENSITIVE_ORDER
            ));
        } else if (firstNonNull instanceof Number) {
            super.sort(Comparator.comparingDouble(o -> ((Number) o).doubleValue()));
        } else {
            super.sort(Comparator.comparing(o -> String.valueOf(o)));
        }
    }

    /**
     * Parametreyi listeden siler ve ardından sıralar.
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        this.sort();
        return removed;
    }
}
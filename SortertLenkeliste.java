class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) {
        if (erTom()) {
            super.leggTil(x);
            return;

        }
        else {
            for (int i = 0; i <super.stoerrelse(); i++) {
                if (hent(i).compareTo(x) > 0) {
                    super.leggTil(i,x);
                    return;
                }
            }
            super.leggTil(x);
            return;
        }
    }

    @Override
    public T fjern() {
        return super.fjern(sluttIndeks);
    }

    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }
}
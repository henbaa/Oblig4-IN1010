//IN1010
//Oblig 4
//Del C
import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {

    class Node {
        protected Node neste;
        protected T data;

        public Node(T data) {
            this.data = data;
        }
    }
    class LenkelisteIterator implements Iterator<T> {
        private Node p;

        public LenkelisteIterator(){
            p = start;
        }

        @Override
        public boolean hasNext() {
            return (p != null);
        }

        @Override
        public T next() {
            Node n = p;
            p = p.neste;
            return n.data;
        }
    }

    private Node start;
    protected int sluttIndeks = -1;

    public int stoerrelse() {
        return sluttIndeks+1;
    }

    public void leggTil(int pos, T x) {
        Node p = start;
        if (pos>sluttIndeks+1 || pos<0)
            kastUnntak(pos);

        if (pos == 0) {
            Node ny = (new Node(x));
            start = ny;
            ny.neste = p;
            sluttIndeks++;
        }

        else {
            for (int i = 0; i < pos-1; i++) {
                p = p.neste;
            }
            if (p.neste != null) {
                Node n = p.neste;
                Node ny = (new Node(x));
                p.neste = ny;
                ny.neste = n;
            }
            else {
                Node ny = (new Node(x));
                p.neste = ny;
            }
            sluttIndeks++;
        }

    }

    public void leggTil(T x) {
        if (erTom()) {
            start = new Node(x);
            sluttIndeks++;
        }
        else {
            Node p = start;

            while (p.neste != null) {
                p = p.neste;
            }
            p.neste = (new Node(x));
            sluttIndeks++;
        }
    }

    public void sett(int pos, T x) {
        if (ugyldigIndeks(pos))
            kastUnntak(pos);

        Node p = start;

        if (pos ==0) {
            if (erTom()) {
                leggTil(x);
            }
            else {
                Node ny = new Node(x);
                start = ny;
                ny.neste = p.neste;
            }
        }
        else {
            for (int i = 0; i <pos-1; i++) {
                p = p.neste;
            }
            Node fjernet = p.neste;
            Node ny = new Node(x);
            p.neste = ny;
            if (fjernet.neste != null) {
                ny.neste = fjernet.neste;
            }
        }
    }

    public T hent(int pos) {
        if (ugyldigIndeks(pos))
            kastUnntak(pos);

        Node p = start;
        for (int i = 0; i<pos; i++) {
            p=p.neste;
        }
        return p.data;
    }

    public T fjern(int pos) {
        if (ugyldigIndeks(pos))
            kastUnntak(pos);

        Node p = start;

        if (pos == 0) {
            if (sluttIndeks == 0) {
                T fjernet = p.data;
                start = null;
                sluttIndeks--;
                return fjernet;
            }
            else {
                start = p.neste;
                sluttIndeks--;
                return p.data;
            }
        }
        else {
            for (int i = 0; i < pos-1; i++) {
                p = p.neste;
            }
            Node fjernet = p.neste;
            if (fjernet.neste != null) {
                p.neste = fjernet.neste;
            }
             else {
                 p.neste = null;
            }
            sluttIndeks--;
            return fjernet.data;
        }
    }

    public T fjern() {
        if (erTom())
            kastUnntak(-1);

        Node p = start;

        if (sluttIndeks == 0) {
            T fjernet = p.data;
            start = null;
            sluttIndeks--;
            return fjernet;
        }
        else {
            start = p.neste;
            sluttIndeks--;
            return p.data;
        } 
    }

    public boolean erTom() {
        return (start == null);
    }

    public UgyldigListeIndeks kastUnntak(int pos) {
        throw new UgyldigListeIndeks(pos);
    }

    public boolean ugyldigIndeks(int pos) {
        return (pos < 0 || pos > sluttIndeks);
    }

    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }
}

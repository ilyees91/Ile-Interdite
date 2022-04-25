public enum Artefacts {
    air(0), eau(0), terre(0), feu(0);
    private int nbObject;
    private Artefacts(int i) {
        this.nbObject = i;
    }

    public int getNbObject() {
        return nbObject;
    }

    public void setNbObject(int nbObject) {
        this.nbObject = nbObject;
    }
}

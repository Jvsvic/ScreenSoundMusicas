package estudos.screensoundmusicas.model;

public enum Tipo {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");
    private String categoria;


    private Tipo(String categoria) {
        this.categoria = categoria;
    }
    public String getCategoria() {
        return categoria;
    }
}

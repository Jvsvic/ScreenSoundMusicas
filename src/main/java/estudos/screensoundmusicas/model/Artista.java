package estudos.screensoundmusicas.model;

import jakarta.persistence.*;


import jakarta.persistence.*;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipoCategoria;

    public Artista(String nome, Tipo tipoCategoria) {
        this.nome = nome;
        this.tipoCategoria = tipoCategoria;
    }

    public Tipo getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(Tipo tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Artista: " + getNome() + " - Tipo: " + getTipoCategoria().getCategoria();
    }
}

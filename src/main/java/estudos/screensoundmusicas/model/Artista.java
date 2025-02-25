package estudos.screensoundmusicas.model;

import jakarta.persistence.*;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista(String nome, Tipo tipoCategoria) {
        this.nome = nome;
        this.tipoCategoria = tipoCategoria;
    }

    public Artista() {

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

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "Artista: " + getNome() + " - Tipo: " + getTipoCategoria().getCategoria() + " Musica: " + getMusicas();
    }


}

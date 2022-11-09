package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "tab_mens_nf_rel")
public class MensNF {
    @Id
    @Column(name = "seq_mens_nf_rel")
    private Integer seqMensNfRel;
    @Column(name = "ind_tipo")
    private String indTipo;
    @Column(name = "cod_mensagem")
    private Integer codMensagem;
    @Column(name = "des_mensagem")
    private String desMensagem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seq_nota")
    private NFe nfe;

    public Integer getSeqMensNfRel() {
        return seqMensNfRel;
    }

    public void setSeqMensNfRel(Integer seqMensNfRel) {
        this.seqMensNfRel = seqMensNfRel;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

    public Integer getCodMensagem() {
        return codMensagem;
    }

    public void setCodMensagem(Integer codMensagem) {
        this.codMensagem = codMensagem;
    }

    public String getDesMensagem() {
        return desMensagem;
    }

    public void setDesMensagem(String desMensagem) {
        this.desMensagem = desMensagem;
    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

}

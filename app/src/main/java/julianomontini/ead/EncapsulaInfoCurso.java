package julianomontini.ead;

import java.io.Serializable;

public class EncapsulaInfoCurso implements Serializable {

    public EncapsulaInfoCurso(String nomeCurso, String descCurso, int srcImagem) {

        setNomeCurso(nomeCurso);
        setDescCurso(descCurso);
        setSrcImagem(srcImagem);
    }

    public EncapsulaInfoCurso(String nomeCurso, String descCurso, int srcImagem, int ID) {

        setNomeCurso(nomeCurso);
        setDescCurso(descCurso);
        setSrcImagem(srcImagem);
        setId(ID);
    }

    private String mNomeCurso;
    private String mDescCurso;
    private int mSrcImagem,mId;

    public String getNomeCurso() {
        return mNomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        mNomeCurso = nomeCurso;
    }

    public int getSrcImagem() {
        return mSrcImagem;
    }

    public void setSrcImagem(int srcImagem) {
        mSrcImagem = srcImagem;
    }

    public String getDescCurso() {
        return mDescCurso;
    }

    public void setDescCurso(String descCurso) {
        mDescCurso = descCurso;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}

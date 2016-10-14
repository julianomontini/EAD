package julianomontini.ead;

public class EncapsulaDesempenho {

    private String mNomeCurso;
    private Integer mAcertos;
    private Integer mErros;
    private Integer mRespondidos;
    private Integer mTotalQuestoes;

    public EncapsulaDesempenho(String nomeCurso, int acertos, int erros, int respondidos, int totalQuestoes) {

        setNomeCurso(nomeCurso);
        setAcertos(acertos);
        setErros(erros);
        setRespondidos(respondidos);
        setTotalQuestoes(totalQuestoes);

    }

    public String getNomeCurso() {
        return mNomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        mNomeCurso = nomeCurso;
    }

    public String getAcertos() {
        return mAcertos.toString();
    }

    public void setAcertos(int acertos) {
        mAcertos = acertos;
    }

    public String getErros() {
        return mErros.toString();
    }

    public void setErros(int erros) {
        mErros = erros;
    }

    public String getRespondidos() {
        return mRespondidos.toString();
    }

    public void setRespondidos(int respondidos) {
        mRespondidos = respondidos;
    }

    public String getTotalQuestoes() {
        return mTotalQuestoes.toString();
    }

    public void setTotalQuestoes(int totalQuestoes) {
        mTotalQuestoes = totalQuestoes;
    }
}

package julianomontini.ead;

import java.io.Serializable;

public class EncapsulaDadosExercicio implements Serializable{

    public EncapsulaDadosExercicio(int numeroQuestao, String questao, String opc1, String opc2, String opc3, String opc4, int Id,int certa,int numeroCurso, int IDUsuario){

        setNumeroQuestao(numeroQuestao);
        setQuestao(questao);
        setOpc1(opc1);
        setOpc2(opc2);
        setOpc3(opc3);
        setOpc4(opc4);
        setId(Id);
        setCerta(certa);
        setIDCurso(numeroCurso);
        setIDUsuario(IDUsuario);
    }

    private int mId,mCerta;
    private Integer mNumeroQuestao;
    private int IDCurso,IDUsuario;
    private String mQuestao, mOpc1, mOpc2, mOpc3, mOpc4;

    public String getNumeroQuestao() {
        return mNumeroQuestao.toString();
    }

    public void setNumeroQuestao(int numeroQuestao) {
        mNumeroQuestao = numeroQuestao;
    }

    public String getQuestao() {
        return mQuestao;
    }

    public void setQuestao(String questao) {
        mQuestao = questao;
    }

    public String getOpc1() {
        return mOpc1;
    }

    public void setOpc1(String opc1) {
        mOpc1 = opc1;
    }

    public String getOpc2() {
        return mOpc2;
    }

    public void setOpc2(String opc2) {
        mOpc2 = opc2;
    }

    public String getOpc3() {
        return mOpc3;
    }

    public void setOpc3(String opc3) {
        mOpc3 = opc3;
    }

    public String getOpc4() {
        return mOpc4;
    }

    public void setOpc4(String opc4) {
        mOpc4 = opc4;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getCerta() {
        return mCerta;
    }

    public void setCerta(int certa) {
        mCerta = certa;
    }

    public int getIDCurso() {
        return IDCurso;
    }

    public void setIDCurso(int IDCurso) {
        this.IDCurso = IDCurso;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }
}

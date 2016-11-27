package julianomontini.ead;

import java.io.Serializable;
import java.util.regex.Pattern;

public class ClassDadosAluno implements Serializable{

    private String mNome;
    private String mEmail;
    private String mSenha;
    private String mTel;
    private String mCpf;

    public ClassDadosAluno(String nome, String email, String senha, String c_senha, String tel, String cpf)
            throws Exception {

        setNome(nome);
        setEmail(email);
        setSenha(senha, c_senha);
        setTel(tel);
        setCpf(cpf);
    }

    public ClassDadosAluno() {


    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String nome) throws Exception {

        if (!nome.isEmpty()) {

            if (!Pattern.matches("[0-9]+", nome)) {

                mNome = nome;

            } else {

                throw new ClassExceptions("Digite apenas letras no nome");

            }

        } else {

            throw new ClassExceptions("Nome está vazio");

        }
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) throws Exception {
        if (email.contains("@") && email.contains(".com")) {

            mEmail = email;

        } else if (email.isEmpty()) {

            throw new ClassExceptions("Campo de email está vazio");

        } else {

            throw new ClassExceptions("Digite um email valido ( contém '@' e '.com' )");

        }
    }

    public String getSenha() {
        return mSenha;
    }

    public void setSenha(String senha, String conf_senha) throws Exception {
        if (!senha.isEmpty() && !conf_senha.isEmpty()) {

            if (senha.toString().equals(conf_senha.toString())) {

                mSenha = senha;

            } else {

                throw new ClassExceptions("As senhas digitadas não são iguais");

            }

        } else {

            throw new ClassExceptions("Os campos de senha estão vazios");

        }
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String tel) throws Exception {

        tel = tel.replaceAll(" ", "");

        if (Pattern.matches("[0-9]+", tel)) {

            mTel = tel;

        } else {

            throw new ClassExceptions("Digitar apenas números no telefone");

        }
    }

    public String getCpf() {
        return mCpf;
    }

    public void setCpf(String cpf) throws Exception {

        cpf = cpf.replaceAll(" ", "");

        if (!cpf.isEmpty()) {

            cpf.replaceAll(" ", "");

            ClassValidaCpf v_validacpf = new ClassValidaCpf();

            if (v_validacpf.CPF(cpf)) {

                mCpf = cpf;

            } else {

                throw new ClassExceptions("O cpf digitado é invalido");

            }

        } else {

            throw new ClassExceptions("O campo CPF está vazio");

        }
    }
}

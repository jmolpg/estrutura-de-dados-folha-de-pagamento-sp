import java.util.Date;
import java.util.StringJoiner;

public class RegistroFolhaPagamento implements Comparable<RegistroFolhaPagamento> {
    private String empresa;
    private String mes;
    private String ano;
    private String nome;
    private String cargo;
    private String lotacao;
    private String admissao;
    private String nascimento;
    private String vencimentos;
    private String encargos;
    private String beneficios;
    private String outrasRemuneracoes;
    private String vinculo;
    private String detalheVinculo;
    private String liminar;
    private String arquivoId;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        this.admissao = admissao;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getVencimentos() {
        return vencimentos;
    }

    public void setVencimentos(String vencimentos) {
        this.vencimentos = vencimentos;
    }

    public String getEncargos() {
        return encargos;
    }

    public void setEncargos(String encargos) {
        this.encargos = encargos;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getOutrasRemuneracoes() {
        return outrasRemuneracoes;
    }

    public void setOutrasRemuneracoes(String outrasRemuneracoes) {
        this.outrasRemuneracoes = outrasRemuneracoes;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getDetalheVinculo() {
        return detalheVinculo;
    }

    public void setDetalheVinculo(String detalheVinculo) {
        this.detalheVinculo = detalheVinculo;
    }

    public String getLiminar() {
        return liminar;
    }

    public void setLiminar(String liminar) {
        this.liminar = liminar;
    }

    public String getArquivoId() {
        return arquivoId;
    }

    public void setArquivoId(String arquivoId) {
        this.arquivoId = arquivoId;
    }

    @Override
    public String toString() {
        return "RegistroFolhaPagamento{" +
                "empresa='" + empresa + '\'' +
                ", mes=" + mes +
                ", ano=" + ano +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", lotacao='" + lotacao + '\'' +
                ", admissao='" + admissao + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", vencimentos=" + vencimentos +
                ", encargos=" + encargos +
                ", beneficios=" + beneficios +
                ", outrasRemuneracoes=" + outrasRemuneracoes +
                ", vinculo='" + vinculo + '\'' +
                ", detalheVinculo='" + detalheVinculo + '\'' +
                ", liminar='" + liminar + '\'' +
                ", arquivoId=" + arquivoId +
                '}';
    }

    @Override
    public int compareTo(RegistroFolhaPagamento o) {
        return this.nome.compareTo(o.nome);
    }
}

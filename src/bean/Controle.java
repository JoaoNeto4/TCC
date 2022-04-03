
package bean;

import java.sql.Time;

public class Controle {
    
    private int id;
    private String dia_inicio;
    private String dia_fim;
    private Time hora_inicio;
    private Time hora_fim;
    private String descricao;

    public String getDia_inicio() {
        return dia_inicio;
    }

    public void setDia_inicio(String dia_inicio) {
        this.dia_inicio = dia_inicio;
    }

    public String getDia_fim() {
        return dia_fim;
    }

    public void setDia_fim(String dia_fim) {
        this.dia_fim = dia_fim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

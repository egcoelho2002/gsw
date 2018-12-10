package br.com.gsw.egctest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "VALOR", nullable = false)
    private double valor;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public double getValor() {
	return valor;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	Cliente cliente = (Cliente) o;

	if (Double.compare(cliente.valor, valor) != 0)
	    return false;
	if (id != null ? !id.equals(cliente.id) : cliente.id != null)
	    return false;
	return nome != null ? nome.equals(cliente.nome) : cliente.nome == null;
    }

    @Override
    public int hashCode() {
	int result;
	long temp;
	result = id != null ? id.hashCode() : 0;
	result = 31 * result + (nome != null ? nome.hashCode() : 0);
	temp = Double.doubleToLongBits(valor);
	result = 31 * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public String toString() {
	return "Cliente [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
    }

}

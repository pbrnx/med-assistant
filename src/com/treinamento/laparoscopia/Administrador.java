// Administrador.java
package com.treinamento.laparoscopia.core;

import com.treinamento.laparoscopia.models.Procedimento;
import com.treinamento.laparoscopia.models.Usuario;

import java.util.List;

public class Administrador {
    public void configurarSistema() {
        System.out.println("Configurações do sistema atualizadas.");
    }

    public void adicionarProcedimento(List<Procedimento> procedimentos, Procedimento procedimento) {
        procedimentos.add(procedimento);
        System.out.println("Procedimento adicionado: " + procedimento.getNome());
    }

    public void removerProcedimento(List<Procedimento> procedimentos, Procedimento procedimento) {
        procedimentos.remove(procedimento);
        System.out.println("Procedimento removido: " + procedimento.getNome());
    }

    public void adicionarUsuario(List<Usuario> usuarios, Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário adicionado: " + usuario.getNome());
    }

    public void removerUsuario(List<Usuario> usuarios, Usuario usuario) {
        usuarios.remove(usuario);
        System.out.println("Usuário removido: " + usuario.getNome());
    }
}

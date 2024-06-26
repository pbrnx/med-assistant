package com.treinamento.laparoscopia.ui;

import com.treinamento.laparoscopia.core.Simulador;
import com.treinamento.laparoscopia.core.Administrador;
import com.treinamento.laparoscopia.models.Procedimento;
import com.treinamento.laparoscopia.models.SessaoTreinamento;
import com.treinamento.laparoscopia.models.Usuario;
import com.treinamento.laparoscopia.utils.Agendador;
import com.treinamento.laparoscopia.utils.Avaliador;
import com.treinamento.laparoscopia.utils.Relatorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InterfaceUsuario extends JFrame {
    private Simulador simulador;
    private Agendador agendador;
    private Avaliador avaliador;
    private Administrador administrador;
    private Relatorio relatorio;
    private List<SessaoTreinamento> sessoes;
    private List<Usuario> usuarios;
    private List<Procedimento> procedimentos;

    public InterfaceUsuario() {
        simulador = new Simulador();
        agendador = new Agendador();
        avaliador = new Avaliador();
        administrador = new Administrador();
        relatorio = new Relatorio();
        sessoes = new ArrayList<>();
        usuarios = new ArrayList<>();
        procedimentos = new ArrayList<>();
        popularProcedimentosIniciais();
        initUI();
    }

    private void popularProcedimentosIniciais() {
        procedimentos.add(new Procedimento("Colecistectomia", "Remoção da vesícula biliar"));
        procedimentos.add(new Procedimento("Apendicectomia", "Remoção do apêndice"));
    }

    private void initUI() {
        setTitle("Sistema de Treinamento Virtual");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JButton btnSimulate = new JButton("Simular Procedimento");
        JButton btnSchedule = new JButton("Agendar Sessão de Treinamento");
        JButton btnEvaluate = new JButton("Avaliar Sessão de Treinamento");
        JButton btnReport = new JButton("Gerar Relatório de Sessões");
        JButton btnConfigure = new JButton("Configurar Sistema");
        JButton btnExit = new JButton("Sair");

        buttonPanel.add(btnSimulate);
        buttonPanel.add(btnSchedule);
        buttonPanel.add(btnEvaluate);
        buttonPanel.add(btnReport);
        buttonPanel.add(btnConfigure);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.CENTER);

        btnSimulate.addActionListener(this::simulateProcedure);
        btnSchedule.addActionListener(this::scheduleSession);
        btnEvaluate.addActionListener(this::evaluateSession);
        btnReport.addActionListener(this::generateReport);
        btnConfigure.addActionListener(this::configureSystem);
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void simulateProcedure(ActionEvent e) {
        if (sessoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma sessão agendada. Por favor, agende uma sessão primeiro.");
            return;
        }

        SessaoTreinamento selected = (SessaoTreinamento) JOptionPane.showInputDialog(this, "Escolha uma sessão para simular:",
                "Simular Procedimento", JOptionPane.QUESTION_MESSAGE, null,
                sessoes.toArray(new SessaoTreinamento[0]), sessoes.get(0));

        if (selected != null) {
            String result = simulador.simularProcedimento(selected.getProcedimento());
            JOptionPane.showMessageDialog(this, result);
        }
    }

    private void scheduleSession(ActionEvent e) {
        JComboBox<Usuario> usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        JComboBox<Procedimento> procedureComboBox = new JComboBox<>(procedimentos.toArray(new Procedimento[0]));
        JTextField dateField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Usuário:"));
        panel.add(usuarioComboBox);
        panel.add(new JLabel("Procedimento:"));
        panel.add(procedureComboBox);
        panel.add(new JLabel("Data (dd/mm/aaaa):"));
        panel.add(dateField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agendar Sessão de Treinamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Usuario selectedUser = (Usuario) usuarioComboBox.getSelectedItem();
            Procedimento selectedProcedure = (Procedimento) procedureComboBox.getSelectedItem();
            String date = dateField.getText();
            SessaoTreinamento session = new SessaoTreinamento(selectedUser, selectedProcedure, date);
            agendador.agendarSessao(session);
            sessoes.add(session);
            JOptionPane.showMessageDialog(this, "Sessão agendada com sucesso!");
        }
    }

    private void evaluateSession(ActionEvent e) {
        if (sessoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há sessões para avaliar.");
            return;
        }

        SessaoTreinamento selected = (SessaoTreinamento) JOptionPane.showInputDialog(this, "Escolha uma sessão para avaliar:",
                "Avaliar Sessão de Treinamento", JOptionPane.QUESTION_MESSAGE, null,
                sessoes.toArray(new SessaoTreinamento[0]), sessoes.get(0));

        if (selected != null) {
            String feedback = JOptionPane.showInputDialog(this, "Digite o feedback para a sessão:");
            if (feedback != null && !feedback.trim().isEmpty()) {
                avaliador.avaliarSessao(selected, feedback);
                JOptionPane.showMessageDialog(this, "Feedback registrado com sucesso!");
            }
        }
    }

    private void generateReport(ActionEvent e) {
        relatorio.gerarRelatorio(sessoes);
        JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
    }

    private void configureSystem(ActionEvent e) {
        String[] options = {"Adicionar Procedimento", "Remover Procedimento", "Adicionar Usuário", "Remover Usuário", "Voltar"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção de configuração:",
                "Configurar Sistema", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);

        switch (choice) {
            case 0:
                adicionarProcedimento();
                break;
            case 1:
                removerProcedimento();
                break;
            case 2:
                adicionarUsuario();
                break;
            case 3:
                removerUsuario();
                break;
            default:
                break;
        }
    }

    private void adicionarProcedimento() {
        JTextField nomeField = new JTextField();
        JTextField descricaoField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome do Procedimento:"));
        panel.add(nomeField);
        panel.add(new JLabel("Descrição do Procedimento:"));
        panel.add(descricaoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Novo Procedimento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Procedimento novoProcedimento = new Procedimento(nomeField.getText(), descricaoField.getText());
            administrador.adicionarProcedimento(procedimentos, novoProcedimento);
            JOptionPane.showMessageDialog(this, "Procedimento " + nomeField.getText() + " adicionado com sucesso!");
        }
    }

    private void removerProcedimento() {
        Procedimento selected = (Procedimento) JOptionPane.showInputDialog(this, "Escolha um procedimento para remover:",
                "Remover Procedimento", JOptionPane.QUESTION_MESSAGE, null,
                procedimentos.toArray(new Procedimento[0]), procedimentos.get(0));

        if (selected != null) {
            administrador.removerProcedimento(procedimentos, selected);
            JOptionPane.showMessageDialog(this, "Procedimento removido com sucesso!");
        }
    }

    private void adicionarUsuario() {
        JTextField nomeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome do Usuário:"));
        panel.add(nomeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Novo Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Usuario novoUsuario = new Usuario(nomeField.getText(), "estudante");
            administrador.adicionarUsuario(usuarios, novoUsuario);
            JOptionPane.showMessageDialog(this, "Usuário " + nomeField.getText() + " adicionado com sucesso!");
        }
    }

    private void removerUsuario() {
        Usuario selected = (Usuario) JOptionPane.showInputDialog(this, "Escolha um usuário para remover:",
                "Remover Usuário", JOptionPane.QUESTION_MESSAGE, null,
                usuarios.toArray(new Usuario[0]), usuarios.get(0));

        if (selected != null) {
            administrador.removerUsuario(usuarios, selected);
            JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceUsuario());
    }
}

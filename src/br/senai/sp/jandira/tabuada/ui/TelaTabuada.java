package br.senai.sp.jandira.tabuada.ui;

import br.senai.sp.jandira.tabuada.model.Tabuada;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Optional;

public class TelaTabuada extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Definir o tamanho da tela
        stage.setResizable(false);
        stage.setTitle("Tabuada");

        // Criar o root - componente de leiaute principal
        VBox root = new VBox();

        // Criamos a cena e colocamos o root nela
        Scene scene = new Scene(root);

        // Criar o header da tela
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #a5a5a5");

        // Colocar o conteúdo do header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setPadding(new Insets(8, 8, 0, 8));
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 30;-fx-font-weight: bold");

        Label labelSubtitulo = new Label("Crie a sua tabuada que sua imaginação mandar.");
        labelSubtitulo.setPadding(new Insets(0, 8, 8, 8));
        labelSubtitulo.setStyle("-fx-text-fill: white");

        // Colocar os Labels dentro do header
        header.getChildren().addAll(labelTitulo, labelSubtitulo);

        // Criar o grid de formulário
        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(16, 8, 16, 8));

        // Criar o conteúdo do gridFormulario
        Label labelMultiplicando = new Label("Multiplicando:");
        TextField textFieldMultiplicando = new TextField();
        Label labelMenorMultiplicador = new Label("Menor Multiplicador:");
        TextField textFieldMenorMultiplicador = new TextField();
        Label labelMaiorMultiplicador = new Label("Maior Multiplicador:");
        TextField textFieldMaiorMultiplicador = new TextField();


        // Colocar os componentes no grid
        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        // Criar a caixa dos botões
        Pane paneButtons = new Pane();

        HBox boxBotoes = new HBox();
        boxBotoes.setPadding(new Insets(8));
        boxBotoes.setSpacing(10);
        paneButtons.getChildren().add(boxBotoes);

        // Criar os botões
        Button btCalcular = new Button("Calcular Media");
        btCalcular.setPrefWidth(70);
        btCalcular.setPrefHeight(30);
        Button btLimpar = new Button("Limpar");
        btLimpar.setPrefWidth(70);
        btLimpar.setPrefHeight(30);
        Button btSair = new Button("Sair");
        btSair.setPrefWidth(70);
        btSair.setPrefHeight(30);
        boxBotoes.getChildren().addAll(
                btCalcular,
                btLimpar,
                btSair
        );

        // Criar a caixa de resultado
        VBox boxResultado = new VBox();
        boxResultado.setPrefHeight(300);

        // Criar os componentes da boxResultado
        Label labelResultado = new Label("Resultado:");
        labelResultado.setPadding(new Insets(8, 8, 8, 8));
        labelResultado.setStyle("-fx-text-fill: #ca0000;-fx-font-size: 18;");
        ListView listaTabuada = new ListView();
        listaTabuada.setPadding(new Insets(8));

        // Adicionar os componentes na boxResultado
        boxResultado.getChildren().addAll(labelResultado, listaTabuada);

        // Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(paneButtons);
        root.getChildren().add(boxResultado);

        // Colocamos a cena no palco
        stage.setScene(scene);

        stage.show();

        // Configurando clique dos botões
        btCalcular.setOnAction(e -> {
            Tabuada tabuada = new Tabuada();

            tabuada.multiplicando =
                    Integer.parseInt(textFieldMultiplicando.getText());

            tabuada.multiplicadorInicial =
                    Integer.parseInt(textFieldMenorMultiplicador.getText());

            tabuada.multiplicadorFinal =
                    Integer.parseInt(textFieldMaiorMultiplicador.getText());

            String[] resultado = tabuada.calcularTabuada();
            listaTabuada.getItems().addAll(resultado);

            // gravar os dados da tabuada em arquivo
            Path arquivo = Path.of("C:\\Users\\25204051\\ds1t\\tabuada\\dados_tabuada.csv");

            String dados = textFieldMultiplicando.getText() + ";" + textFieldMenorMultiplicador.getText() + ";" + textFieldMaiorMultiplicador.getText() + ";" + LocalDateTime.now() + "\n";

            try {
                Files.writeString(arquivo, dados, StandardOpenOption.APPEND);
            } catch (IOException erro) {
                System.out.println(erro.getMessage());
            }

        });

        btLimpar.setOnAction(e -> {
            textFieldMultiplicando.clear();
            textFieldMenorMultiplicador.clear();
            textFieldMaiorMultiplicador.clear();
            listaTabuada.getItems().clear();
            textFieldMultiplicando.requestFocus();
        });

        btSair.setOnAction(e -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Sair do programa?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> resposta = alerta.showAndWait();

            if (resposta.get() == ButtonType.YES) {
                System.exit(0);
            }
        });
    }
}


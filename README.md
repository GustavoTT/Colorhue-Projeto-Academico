# 🔵🔴🟡 Colorhue 🟠🟣🟢 - Projeto Acadêmico
> Projeto desenvolvido em grupo com fins acadêmicos, desenvolvido perante demanda para a matéria de Dispositivos Móveis na Fatec Americana.

## Informações do projeto
Para fins avaliativos foi solicitado um projeto em grupo para entrega na matéria de disopositivos móveis de tema livre/aberto e que tivesse uma integração com banco de dados, no caso o Firebase. Após um debate com o grupo, decidimos que seria mais didático realizarmos a criação de um jogo no momento. Dentre as opções debatidas, escolhemos usar como inspiração o Jogo Genius (jogo de memorização de sequências de cores) para desenvolver nosso projeto.
Diante disso, desenvolvemos pelo Android Studio com Kotlin e integração ao Google Firebase, uma aplicação que:
- Recebe a dificuldade escolhida pelo jogador
- Gera, de acordo com o nível de dificuldade, sequências aleatórias de cores
- Solicita a sequência que o jogador memorizou
- Retorna ao jogador sua pontuação
- Possibilita o armazenamento da pontuação em um banco de dados

## Vídeo do funcionamento do projeto 🎞️
Link Google Drive -> https://drive.google.com/file/d/12cln6-YtfyQGoXXeuA6OwgUQRqKSUXzq/view?usp=drive_link 

## Funcionamento ⚙️
Optamos por utilizar de uma lógica relativamente mais simples, por conta do prazo estabelecido, entretanto funcional.
Utilizamos a seguinte lógica: 
- 🔵 O jogador seleciona sua dificuldade.
- 🟢 O sistema abre a tela do jogo com todos os botões desabilitados, somente o "COMEÇAR" disponível.
- 🔴 Após clicar em começar o jogador é impedido de realizar qualquer atividade, até que a sequência seja gerada.
- 🟣 A sequência de cores é gerada de forma aleatória, e com um temporizador (que pode ser acelerado ao aumentar a dificuldade).
- 🟤 Com a sequência já gerada, os botões das cores são liberados ao jogador e o mesmo deve informar aquilo que ele memorizou.
- ⚫ O sistema avalia se cada elemento inserido pelo jogador coincide com a sequência gerada.
- 🟡 Após inserir todas as cores, os botões desativam novamente, agora, somente o "VER PONTUAÇÃO" fica disponível.
- ⚪ Ao clicar para ver sua pontuação, o jogador é levado para uma tela onde pode analisar seu desempenho (acertos, erros e pontuação).
- 🟠 A opção de “VOLTAR AO MENU” fica disponível, entretanto, ao clicar o jogador pode escolher salvar ou não seu resultado. Caso não queira salvar, o jogador é redirecionado para o menu.
- 🔵 Caso o jogador queira salvar seu resultado, é solicitado um nome, que será atrelado ao resultado e servirá para identificação do jogador no ranking.
- 🟢 Ele é redirecionado para a tela do ranking, que exibe todas as pontuações guardadas no Banco de Dados da maior para a menor.
- 🔴 O jogador fica livre para caso queira jogar novamente.

## Grupo 🧑👩👩
- Gustavo Tagliatelli Teles
- Natalia Amorim Botasso
- Lorena Steinle Damião

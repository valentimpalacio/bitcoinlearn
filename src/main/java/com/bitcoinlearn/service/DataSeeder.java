package com.bitcoinlearn.service;

import com.bitcoinlearn.model.GlossaryTerm;
import com.bitcoinlearn.model.QuizQuestion;
import com.bitcoinlearn.repository.GlossaryTermRepository;
import com.bitcoinlearn.repository.QuizQuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final QuizQuestionRepository quizRepo;
    private final GlossaryTermRepository glossaryRepo;

    public DataSeeder(QuizQuestionRepository quizRepo, GlossaryTermRepository glossaryRepo) {
        this.quizRepo = quizRepo;
        this.glossaryRepo = glossaryRepo;
    }

    @Override
    public void run(String... args) {
        try {
            if (quizRepo.count() == 0) seedQuestions();
            if (glossaryRepo.count() == 0) seedGlossary();
        } catch (Exception e) {
            System.err.println("DataSeeder: " + e.getMessage());
        }
    }

    private void seedQuestions() {
        quizRepo.saveAll(List.of(
            new QuizQuestion("Quem criou o Bitcoin?",
                List.of("Elon Musk", "Satoshi Nakamoto", "Vitalik Buterin", "Bill Gates"), 1,
                "Satoshi Nakamoto e o pseudonimo do criador do Bitcoin, que publicou o whitepaper em 2008.", "Historia", "facil"),
            new QuizQuestion("Em que ano o Bitcoin foi lancado?",
                List.of("2008", "2009", "2010", "2011"), 1,
                "O Bitcoin foi lancado em janeiro de 2009, quando o primeiro bloco (genesis block) foi minerado.", "Historia", "facil"),
            new QuizQuestion("Qual o limite maximo de Bitcoins que podem existir?",
                List.of("10 milhoes", "21 milhoes", "100 milhoes", "Sem limite"), 1,
                "O suprimento maximo de Bitcoin e de 21 milhoes de moedas, um limite definido por seu codigo.", "Fundamentos", "facil"),
            new QuizQuestion("O que e blockchain?",
                List.of("Um tipo de banco digital", "Um livro-razao descentralizado e imutavel", "Uma rede social", "Um sistema de pagamento centralizado"), 1,
                "Blockchain e um ledger distribuido que registra todas as transacoes de forma transparente e imutavel.", "Blockchain", "facil"),
            new QuizQuestion("O que e uma carteira de Bitcoin (wallet)?",
                List.of("Um local fisico para guardar moedas", "Um software ou hardware que armazena chaves privadas", "Uma conta bancaria", "Um cartao de credito"), 1,
                "Carteiras Bitcoin armazenam chaves privadas que permitem acessar e gastar seus bitcoins.", "Fundamentos", "facil"),
            new QuizQuestion("O que e o halving do Bitcoin?",
                List.of("A divisao de um Bitcoin em partes menores", "A reducao pela metade da recompensa por bloco minerado", "A duplicacao do preco", "Um tipo de taxa"), 1,
                "Halving e um evento que ocorre a cada 210 mil blocos, reduzindo a recompensa de mineracao pela metade.", "Mineracao", "medio"),
            new QuizQuestion("O que e proof-of-work (PoW)?",
                List.of("Um sistema de votacao", "Um mecanismo de consenso que exige poder computacional", "Um tipo de carteira", "Uma exchange"), 1,
                "PoW e o mecanismo de consenso do Bitcoin que requer que mineradores resolvam problemas matematicos para validar blocos.", "Mineracao", "medio"),
            new QuizQuestion("O que e uma exchange de criptomoedas?",
                List.of("Um banco digital", "Uma plataforma para comprar e vender criptomoedas", "Um tipo de minerador", "Uma carteira fria"), 1,
                "Exchanges sao plataformas onde usuarios podem comprar, vender e trocar criptomoedas por outras moedas.", "Mercado", "facil"),
            new QuizQuestion("O que e uma chave privada no Bitcoin?",
                List.of("Uma senha que voce escolhe", "Um numero secreto que prova a propriedade dos bitcoins", "O endereco da carteira", "O nome do usuario"), 1,
                "A chave privada e um numero criptografico unico que da acesso e controle sobre os bitcoins.", "Seguranca", "medio"),
            new QuizQuestion("O que significa 'not your keys, not your coins'?",
                List.of("Voce precisa de chaves fisicas", "Se voce nao controla suas chaves privadas, nao controla seus bitcoins", "E um tipo de propaganda", "Nao tem significado real"), 1,
                "O ditado alerta que bitcoins em custodias de terceiros (exchanges) nao estao totalmente sob seu controle.", "Seguranca", "medio"),
            new QuizQuestion("O que e um node (no) na rede Bitcoin?",
                List.of("Um tipo de minerador", "Um computador que valida e propaga transacoes na rede", "Uma exchange", "Uma carteira"), 1,
                "Nodes sao computadores que rodam o software Bitcoin Core e ajudam a validar transacoes e manter a rede.", "Blockchain", "medio"),
            new QuizQuestion("Qual e o simbolo do Bitcoin?",
                List.of("$", "฿", "₿", "B"), 2,
                "O simbolo oficial do Bitcoin e ₿ (Unicode U+20BF), embora ฿ tambem seja usado informalmente.", "Fundamentos", "facil"),
            new QuizQuestion("O que e uma transacao on-chain?",
                List.of("Uma transacao registrada diretamente na blockchain", "Uma transferencia entre bancos", "Um pagamento com cartao", "Uma compra em loja"), 0,
                "Transacoes on-chain sao aquelas registradas permanentemente na blockchain do Bitcoin.", "Blockchain", "medio"),
            new QuizQuestion("O que e a Lightning Network?",
                List.of("Uma criptomoeda diferente", "Uma rede de pagamentos de segunda camada sobre o Bitcoin", "Um tipo de minerador", "Uma exchange descentralizada"), 1,
                "Lightning Network e uma solucao de segunda camada que permite pagamentos instantaneos e de baixo custo em Bitcoin.", "Blockchain", "dificil"),
            new QuizQuestion("O que e um endereco Bitcoin?",
                List.of("Seu nome na rede", "Um identificador alfanumerico unico para receber bitcoins", "Um numero de conta bancaria", "O IP do seu computador"), 1,
                "Enderecos Bitcoin sao strings alfanumericas que funcionam como 'numeros de conta' para receber pagamentos.", "Fundamentos", "facil"),
            new QuizQuestion("O que e o whitepaper do Bitcoin?",
                List.of("Um livro sobre Bitcoin", "O documento fundacional que descreve o protocolo Bitcoin", "Um contrato oficial", "Um site"), 1,
                "O whitepaper 'Bitcoin: A Peer-to-Peer Electronic Cash System' foi publicado por Satoshi em 2008.", "Historia", "medio"),
            new QuizQuestion("O que e mineracao de Bitcoin?",
                List.of("Criar novas moedas do zero", "O processo de validar transacoes e criar novos blocos usando poder computacional", "Comprar Bitcoin barato", "Um jogo digital"), 1,
                "Mineracao e o processo de usar hardware especializado para validar transacoes e proteger a rede.", "Mineracao", "medio"),
            new QuizQuestion("O que e FOMO no contexto de criptomoedas?",
                List.of("Fear Of Missing Out - medo de ficar de fora", "Um tipo de taxa", "Uma estrategia de investimento", "Um erro de transacao"), 0,
                "FOMO descreve a ansiedade de perder oportunidades de lucro, levando a decisoes impulsivas de compra.", "Mercado", "facil"),
            new QuizQuestion("O que significa HODL?",
                List.of("Hold On for Dear Life - segurar a moeda a longo prazo", "Uma ordem de venda", "Um tipo de exchange", "Um erro de digitacao"), 0,
                "HODL originou-se de um erro de digitacao de 'hold' e tornou-se um termo que significa nao vender seus bitcoins.", "Mercado", "facil"),
            new QuizQuestion("O Bitcoin e legal no Brasil?",
                List.of("Nao, e proibido", "Sim, e legal e regulamentado como ativo digital", "So e permitido para empresas", "Apenas para investidores credenciados"), 1,
                "O Bitcoin e legal no Brasil e classificado como ativo digital pela Receita Federal, com obrigacoes fiscais especificas.", "Mercado", "medio")
        ));
    }

    private void seedGlossary() {
        glossaryRepo.saveAll(List.of(
            new GlossaryTerm("Bitcoin", "Moeda digital descentralizada criada em 2009 por Satoshi Nakamoto. Opera sem autoridade central.", "Fundamentos"),
            new GlossaryTerm("Blockchain", "Livro-razao distribuido e imutavel que registra todas as transacoes do Bitcoin.", "Tecnologia"),
            new GlossaryTerm("Satoshi Nakamoto", "Pseudonimo do criador do Bitcoin, cuja identidade real permanece desconhecida.", "Historia"),
            new GlossaryTerm("Mineracao", "Processo de validar transacoes e criar novos blocos na blockchain, recompensado com novos Bitcoins.", "Tecnologia"),
            new GlossaryTerm("Halving", "Evento que reduz a recompensa de mineracao pela metade a cada 210.000 blocos (aproximadamente 4 anos).", "Tecnologia"),
            new GlossaryTerm("Carteira (Wallet)", "Software ou hardware que armazena chaves privadas necessarias para acessar e gastar Bitcoins.", "Fundamentos"),
            new GlossaryTerm("Chave Privada", "Numero secreto que prova a propriedade de Bitcoins. Quem a possui tem controle total sobre os fundos.", "Seguranca"),
            new GlossaryTerm("Chave Publica", "Derivada da chave privada, usada para gerar enderecos Bitcoin e verificar assinaturas.", "Seguranca"),
            new GlossaryTerm("Endereco Bitcoin", "Identificador alfanumerico unico usado para enviar e receber Bitcoins.", "Fundamentos"),
            new GlossaryTerm("Exchange", "Plataforma online onde usuarios compram, vendem e trocam criptomoedas.", "Mercado"),
            new GlossaryTerm("Lightning Network", "Rede de pagamentos de segunda camada que permite transacoes instantaneas e de baixissimo custo.", "Tecnologia"),
            new GlossaryTerm("Proof of Work (PoW)", "Mecanismo de consenso que exige que mineradores resolvam problemas computacionais para validar blocos.", "Tecnologia"),
            new GlossaryTerm("Node", "Computador que roda o software Bitcoin Core e participa da validacao e propagacao de transacoes.", "Tecnologia"),
            new GlossaryTerm("Transacao On-Chain", "Transacao registrada diretamente na blockchain, visivel publicamente e irreversivel.", "Fundamentos"),
            new GlossaryTerm("FOMO", "Fear Of Missing Out - ansiedade de perder oportunidades de lucro no mercado.", "Mercado"),
            new GlossaryTerm("HODL", "Hold On for Dear Life - estrategia de segurar Bitcoins a longo prazo sem vender.", "Mercado"),
            new GlossaryTerm("Altcoin", "Qualquer criptomoeda que nao seja Bitcoin, como Ethereum, Litecoin, etc.", "Mercado"),
            new GlossaryTerm("Mempool", "Conjunto de transacoes pendentes aguardando confirmacao por mineradores.", "Tecnologia"),
            new GlossaryTerm("Fork", "Divergencia na blockchain que pode resultar em duas redes separadas (ex: Bitcoin Cash).", "Tecnologia"),
            new GlossaryTerm("Nonce", "Numero usado uma vez no processo de mineracao para encontrar um hash valido do bloco.", "Tecnologia"),
            new GlossaryTerm("Hash", "Funcao matematica que transforma dados em uma string de tamanho fixo. Fundamental para a seguranca do Bitcoin.", "Tecnologia"),
            new GlossaryTerm("Dificuldade de Mineracao", "Medida de quao dificil e encontrar um novo bloco, ajustada automaticamente a cada 2016 blocos.", "Tecnologia"),
            new GlossaryTerm("Custodia", "Guarda de Bitcoins por terceiros, como exchanges. 'Not your keys, not your coins'.", "Seguranca"),
            new GlossaryTerm("Carteira Fria", "Carteira offline (hardware ou papel) que armazena chaves privadas sem conexao com internet.", "Seguranca"),
            new GlossaryTerm("Carteira Quente", "Carteira conectada a internet, mais conveniente porem menos segura que carteiras frias.", "Seguranca")
        ));
    }
}

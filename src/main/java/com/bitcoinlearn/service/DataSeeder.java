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
                "Satoshi Nakamoto é o pseudônimo do criador do Bitcoin, que publicou o whitepaper em 2008.", "História", "fácil"),
            new QuizQuestion("Em que ano o Bitcoin foi lançado?",
                List.of("2008", "2009", "2010", "2011"), 1,
                "O Bitcoin foi lançado em janeiro de 2009, quando o primeiro bloco (genesis block) foi minerado.", "História", "fácil"),
            new QuizQuestion("Qual o limite máximo de Bitcoins que podem existir?",
                List.of("10 milhões", "21 milhões", "100 milhões", "Sem limite"), 1,
                "O suprimento máximo de Bitcoin é de 21 milhões de moedas, um limite definido por seu código.", "Fundamentos", "fácil"),
            new QuizQuestion("O que é blockchain?",
                List.of("Um tipo de banco digital", "Um livro-razão descentralizado e imutável", "Uma rede social", "Um sistema de pagamento centralizado"), 1,
                "Blockchain é um ledger distribuído que registra todas as transações de forma transparente e imutável.", "Blockchain", "fácil"),
            new QuizQuestion("O que é uma carteira de Bitcoin (wallet)?",
                List.of("Um local físico para guardar moedas", "Um software ou hardware que armazena chaves privadas", "Uma conta bancária", "Um cartão de crédito"), 1,
                "Carteiras Bitcoin armazenam chaves privadas que permitem acessar e gastar seus bitcoins.", "Fundamentos", "fácil"),
            new QuizQuestion("O que é o halving do Bitcoin?",
                List.of("A divisão de um Bitcoin em partes menores", "A redução pela metade da recompensa por bloco minerado", "A duplicação do preço", "Um tipo de taxa"), 1,
                "Halving é um evento que ocorre a cada 210 mil blocos, reduzindo a recompensa de mineração pela metade.", "Mineração", "médio"),
            new QuizQuestion("O que é proof-of-work (PoW)?",
                List.of("Um sistema de votação", "Um mecanismo de consenso que exige poder computacional", "Um tipo de carteira", "Uma exchange"), 1,
                "PoW é o mecanismo de consenso do Bitcoin que requer que mineradores resolvam problemas matemáticos para validar blocos.", "Mineração", "médio"),
            new QuizQuestion("O que é uma exchange de criptomoedas?",
                List.of("Um banco digital", "Uma plataforma para comprar e vender criptomoedas", "Um tipo de minerador", "Uma carteira fria"), 1,
                "Exchanges são plataformas onde usuários podem comprar, vender e trocar criptomoedas por outras moedas.", "Mercado", "fácil"),
            new QuizQuestion("O que é uma chave privada no Bitcoin?",
                List.of("Uma senha que você escolhe", "Um número secreto que prova a propriedade dos bitcoins", "O endereço da carteira", "O nome do usuário"), 1,
                "A chave privada é um número criptográfico único que dá acesso e controle sobre os bitcoins.", "Segurança", "médio"),
            new QuizQuestion("O que significa 'not your keys, not your coins'?",
                List.of("Você precisa de chaves físicas", "Se você não controla suas chaves privadas, não controla seus bitcoins", "É um tipo de propaganda", "Não tem significado real"), 1,
                "O ditado alerta que bitcoins em custodias de terceiros (exchanges) não estão totalmente sob seu controle.", "Segurança", "médio"),
            new QuizQuestion("O que é um node (no) na rede Bitcoin?",
                List.of("Um tipo de minerador", "Um computador que valida e propaga transações na rede", "Uma exchange", "Uma carteira"), 1,
                "Nodes são computadores que rodam o software Bitcoin Core e ajudam a validar transações e manter a rede.", "Blockchain", "médio"),
            new QuizQuestion("Qual é o símbolo do Bitcoin?",
                List.of("$", "฿", "₿", "B"), 2,
                "O símbolo oficial do Bitcoin é ₿ (Unicode U+20BF), embora ฿ também seja usado informalmente.", "Fundamentos", "fácil"),
            new QuizQuestion("O que é uma transação on-chain?",
                List.of("Uma transação registrada diretamente na blockchain", "Uma transferência entre bancos", "Um pagamento com cartão", "Uma compra em loja"), 0,
                "Transações on-chain são aquelas registradas permanentemente na blockchain do Bitcoin.", "Blockchain", "médio"),
            new QuizQuestion("O que é a Lightning Network?",
                List.of("Uma criptomoeda diferente", "Uma rede de pagamentos de segunda camada sobre o Bitcoin", "Um tipo de minerador", "Uma exchange descentralizada"), 1,
                "Lightning Network é uma solução de segunda camada que permite pagamentos instantâneos e de baixo custo em Bitcoin.", "Blockchain", "difícil"),
            new QuizQuestion("O que é um endereço Bitcoin?",
                List.of("Seu nome na rede", "Um identificador alfanumérico único para receber bitcoins", "Um número de conta bancária", "O IP do seu computador"), 1,
                "Endereços Bitcoin são strings alfanuméricas que funcionam como 'números de conta' para receber pagamentos.", "Fundamentos", "fácil"),
            new QuizQuestion("O que é o whitepaper do Bitcoin?",
                List.of("Um livro sobre Bitcoin", "O documento fundacional que descreve o protocolo Bitcoin", "Um contrato oficial", "Um site"), 1,
                "O whitepaper 'Bitcoin: A Peer-to-Peer Electronic Cash System' foi publicado por Satoshi em 2008.", "História", "médio"),
            new QuizQuestion("O que é mineração de Bitcoin?",
                List.of("Criar novas moedas do zero", "O processo de validar transações e criar novos blocos usando poder computacional", "Comprar Bitcoin barato", "Um jogo digital"), 1,
                "Mineração é o processo de usar hardware especializado para validar transações e proteger a rede.", "Mineração", "médio"),
            new QuizQuestion("O que é FOMO no contexto de criptomoedas?",
                List.of("Fear Of Missing Out - medo de ficar de fora", "Um tipo de taxa", "Uma estratégia de investimento", "Um erro de transação"), 0,
                "FOMO descreve a ansiedade de perder oportunidades de lucro, levando a decisões impulsivas de compra.", "Mercado", "fácil"),
            new QuizQuestion("O que significa HODL?",
                List.of("Hold On for Dear Life - segurar a moeda a longo prazo", "Uma ordem de venda", "Um tipo de exchange", "Um erro de digitação"), 0,
                "HODL originou-se de um erro de digitação de 'hold' e tornou-se um termo que significa não vender seus bitcoins.", "Mercado", "fácil"),
            new QuizQuestion("O Bitcoin é legal no Brasil?",
                List.of("Não, é proibido", "Sim, é legal e regulamentado como ativo digital", "Só é permitido para empresas", "Apenas para investidores credenciados"), 1,
                "O Bitcoin é legal no Brasil é classificado como ativo digital pela Receita Federal, com obrigações fiscais específicas.", "Mercado", "médio")
        ));
    }

    private void seedGlossary() {
        glossaryRepo.saveAll(List.of(
            new GlossaryTerm("Bitcoin", "Moeda digital descentralizada criada em 2009 por Satoshi Nakamoto. Opera sem autoridade central.", "Fundamentos"),
            new GlossaryTerm("Blockchain", "Livro-razão distribuído e imutável que registra todas as transações do Bitcoin.", "Tecnologia"),
            new GlossaryTerm("Satoshi Nakamoto", "Pseudônimo do criador do Bitcoin, cuja identidade real permanece desconhecida.", "História"),
            new GlossaryTerm("Mineração", "Processo de validar transações e criar novos blocos na blockchain, recompensado com novos Bitcoins.", "Tecnologia"),
            new GlossaryTerm("Halving", "Evento que reduz a recompensa de mineração pela metade a cada 210.000 blocos (aproximadamente 4 anos).", "Tecnologia"),
            new GlossaryTerm("Carteira (Wallet)", "Software ou hardware que armazena chaves privadas necessárias para acessar e gastar Bitcoins.", "Fundamentos"),
            new GlossaryTerm("Chave Privada", "Número secreto que prova a propriedade de Bitcoins. Quem a possui tem controle total sobre os fundos.", "Segurança"),
            new GlossaryTerm("Chave Pública", "Derivada da chave privada, usada para gerar endereços Bitcoin é verificar assinaturas.", "Segurança"),
            new GlossaryTerm("Endereço Bitcoin", "Identificador alfanumérico único usado para enviar e receber Bitcoins.", "Fundamentos"),
            new GlossaryTerm("Exchange", "Plataforma online onde usuários compram, vendem e trocam criptomoedas.", "Mercado"),
            new GlossaryTerm("Lightning Network", "Rede de pagamentos de segunda camada que permite transações instantâneas e de baixíssimo custo.", "Tecnologia"),
            new GlossaryTerm("Proof of Work (PoW)", "Mecanismo de consenso que exige que mineradores resolvam problemas computacionais para validar blocos.", "Tecnologia"),
            new GlossaryTerm("Node", "Computador que roda o software Bitcoin Core e participa da validação e propagação de transações.", "Tecnologia"),
            new GlossaryTerm("Transação On-Chain", "Transação registrada diretamente na blockchain, visível publicamente e irreversível.", "Fundamentos"),
            new GlossaryTerm("FOMO", "Fear Of Missing Out - ansiedade de perder oportunidades de lucro no mercado.", "Mercado"),
            new GlossaryTerm("HODL", "Hold On for Dear Life - estratégia de segurar Bitcoins a longo prazo sem vender.", "Mercado"),
            new GlossaryTerm("Altcoin", "Qualquer criptomoeda que não seja Bitcoin, como Ethereum, Litecoin, etc.", "Mercado"),
            new GlossaryTerm("Mempool", "Conjunto de transações pendentes aguardando confirmação por mineradores.", "Tecnologia"),
            new GlossaryTerm("Fork", "Divergência na blockchain que pode resultar em duas redes separadas (ex: Bitcoin Cash).", "Tecnologia"),
            new GlossaryTerm("Nonce", "Número usado uma vez no processo de mineração para encontrar um hash válido do bloco.", "Tecnologia"),
            new GlossaryTerm("Hash", "Função matemática que transforma dados em uma string de tamanho fixo. Fundamental para a segurança do Bitcoin.", "Tecnologia"),
            new GlossaryTerm("Dificuldade de Mineração", "Medida de quão difícil é encontrar um novo bloco, ajustada automaticamente a cada 2016 blocos.", "Tecnologia"),
            new GlossaryTerm("Custódia", "Guarda de Bitcoins por terceiros, como exchanges. 'Not your keys, not your coins'.", "Segurança"),
            new GlossaryTerm("Carteira Fria", "Carteira offline (hardware ou papel) que armazena chaves privadas sem conexão com internet.", "Segurança"),
            new GlossaryTerm("Carteira Quente", "Carteira conectada a internet, mais conveniente porém menos segura que carteiras frias.", "Segurança")
        ));
    }
}

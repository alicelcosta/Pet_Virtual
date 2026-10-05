class Pet(val nome: String) {

    var fome = 50
        private set
    var felicidade = 50
        private set
    var cansaco = 50
        private set
    var idade = 0
        private set
    var vontadeBanheiro = 0
        private set
    var sujeira = 0
        private set


    private fun limitar(valor: Int) = valor.coerceIn(0, 100)



    fun verificarStatus() {

        println("\n Status de $nome \n")
        println("Idade: $idade")
        println("Fome: $fome/100")
        println("Felicidade: $felicidade/100")
        println("Cansaço: $cansaco/100")
        println("Vontade de ir ao banheiro: $vontadeBanheiro/100")
        println("Sujeira: $sujeira/100")

    }



    fun alimentar() {

        fome = limitar(fome - 10)
        vontadeBanheiro = limitar(vontadeBanheiro + 15)

        println("$nome comeu uma refeição deliciosa! A fome diminuiu.")

    }



    fun brincar() {

        felicidade = limitar(felicidade + 10)
        cansaco = limitar(cansaco + 10)
        sujeira = limitar(sujeira + 15)
        println("$nome adorou brincar! Ficou mais feliz, mas cansado e sujo.")

    }



    fun descansar(horas: Int) {

        val reducao = (horas * 100) / 8
        cansaco = limitar(cansaco - reducao)

        println("$nome dormiu por $horas hora(s). O cansaço diminuiu.")

    }



    fun irAoBanheiro() {

        vontadeBanheiro = 0
        println("$nome foi ao banheiro e está aliviado!")

    }



    fun tomarBanho() {

        sujeira = 0
        println("$nome tomou banho e está limpinho!") }



    fun passarTempo() {

        fome = limitar(fome + 3)
        felicidade = limitar(felicidade - 3)
        cansaco = limitar(cansaco + 10)
        idade += 1

    }



    fun verificarDerrota(): Boolean {

        when {

            fome >= 100 -> println("\n$nome morreu de fome! Fim de jogo.")
            cansaco >= 100 -> println("\n$nome desmaiou de cansaço! Fim de jogo.")
            felicidade <= 0 -> println("\n$nome ficou triste demais e fugiu! Fim de jogo.")
            vontadeBanheiro >= 100 -> println("\n$nome não aguentou... Fim de jogo.")
            sujeira >= 100 -> println("\n$nome ficou sujo demais e adoeceu! Fim de jogo.")

            else -> return false

        }

        return true
    }



    fun verificarVitoria(): Boolean {

        if (idade >= 50) {

            println("\nParabéns! $nome chegou aos 50 anos saudável e feliz! Você venceu!")
            return true

        }
        return false
    }



}



fun main() {

    println("Bem-vindo(a) ao Simulador de Animal de Estimação Virtual!")

    print("Digite o nome do seu novo Pet: ")
    val pet = Pet(readln())

    println("${pet.nome} nasceu! Cuide dele até a idade de 50 anos.")

    while (true) {

        println("\n Escolha uma ação \n")
        println("1. Alimentar")
        println("2. Brincar")
        println("3. Descansar")
        println("4. Levar ao banheiro")
        println("5. Dar banho")
        println("6. Verificar status")
        println("7. Deixar o tempo passar")
        println("8. Sair")
        print("Sua escolha: ")

        val escolha = readln().toIntOrNull()


        when (escolha) {

            6 -> { pet.verificarStatus(); continue }
            8 -> { println("Saindo do simulador. Até logo!"); break }
            null, !in 1..7 -> { println("Opção inválida! Tente novamente."); continue }

        }


        when (escolha) {

            1 -> pet.alimentar()
            2 -> pet.brincar()
            3 -> {

                print("Por quantas horas (1 a 8)? ")
                val horas = readln().toIntOrNull()
                if (horas == null || horas !in 1..8) {
                    println("Valor inválido! Digite entre 1 e 8.")
                    continue

                }

                pet.descansar(horas)
            }

            4 -> pet.irAoBanheiro()
            5 -> pet.tomarBanho()
            7 -> println("${pet.nome} passou o tempo olhando pro nada...")

        }

        pet.passarTempo()

        if (pet.verificarDerrota() || pet.verificarVitoria())
            break

    }
}
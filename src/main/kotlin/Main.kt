import java.io.File

val dataDir = File("data")
val contatos = File("data/contatos.txt")
val export_contatos = File("data/export_contatos.txt")

val ddds = listOf(11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 21L, 22L, 24L, 27L, 28L, 31L, 32L, 33L, 34L, 35L, 37L, 38L, 41L, 42L, 43L, 44L, 45L, 46L, 47L, 48L, 49L, 51L, 53L, 54L, 55L, 61L, 62L, 63L, 64L, 65L, 66L, 67L, 68L, 69L, 71L, 73L, 74L, 75L, 77L, 79L, 81L, 82L, 83L, 84L, 85L, 86L, 87L, 88L, 89L, 98L, 99L, 91L, 92L, 93L, 94L, 95L, 96L, 97L)
fun main(){
    if (!dataDir.exists()) dataDir.mkdir()
    if (!contatos.exists()) contatos.createNewFile()
    if (!export_contatos.exists()) export_contatos.createNewFile()

    var rodar = true

    while(rodar){

        var acao = menu()
        when (acao){
            1 -> {
                val nome = cadastrar()
                contatos.appendText(nome)
            }

            2 -> {
                var lista =contatos.readLines().toMutableList()
                lista = lista.sortedBy{it.first()}.toMutableList()
                for (i in lista){
                    println(i)
                }
            }

            3 -> {println(Buscar()) }

            4 -> {
                println("Atualizar: \n Nome : 1 \n Telefone : 2 \n Email : 3 \n")
                var escolha = readLine()!!.toInt()

                val type = listOf<String>("Nome", "Telefone", "Email")

                var lista =contatos.readLines().toMutableList()

                when (escolha){
                    1 -> {if(!(atualizador(type[0]))){
                            println("${type[1]} nao encontrado!!!")
                    }}
                    2 -> {if(!(atualizador(type[1]))){
                        println("${type[1]} nao encontrado!!!")
                    }}
                    3 -> {if(!(atualizador(type[2]))){
                        println("${type[2]} nao encontrado!!!")
                    }}
                }
            }

            5 -> {
                var lista =contatos.readLines().toMutableList()

                println("Deletar Contato(Busca por telefone): )")
                var telefone = readLine()!!.toLongOrNull()

                if (validarddd(telefone)) { 0 }

                else{println("INSIRA UM NUMERO VALIDO!!!")
                    continue }

                var num = 0
                for (i in lista){
                    var numeros = i.split(";")
                    if(numeros[1].toLongOrNull() == telefone){
                        lista.remove(i)
                           num++
                        break
                    }
                }

                if(num == 1){
                    contatos.writeText(lista.joinToString("\n"))
                }
                else {
                    println("TELEFONE NAO ENCONTRADO!!!")
                }
            }

            6 -> {
                var lista = contatos.readLines().toMutableList()
                for (i in lista){
                    var split = i.split(";")
                    export_contatos.appendText("\n Nome:${split[0]} \n Telefone: ${split[1]} \n Email: ${split[2]} \n")
                }
                var listaEXP = export_contatos.readLines().toMutableList()
                for (i in listaEXP){
                    println(i)
                }
            }

            7 -> {
                println("VOCE SAIU DO MENU!!")
                rodar = false
            }
        }
    }
}
import java.io.File
fun menu(): Int{
    println("MENU: \n 1- Adicionar Contatos \n 2- Listar contatos \n 3- Buscar contatos \n 4- Atualizar contato \n 5- Deletar contato \n 6 - Exportar contatos \n 7- Sair")
     var acao = readLine()!!.toInt()
    return acao
}

fun validarNome(nome: String):String{
    while(true){
        if (!(nome.toIntOrNull() == null) || !validacaoNome(nome)) {
            println("NOME INVALIDO!!")
            continue
        }
        return nomeContato(nome)
    }
}

fun validarTel(telefone:Long?): Long?{
    while(true){
        if(!(validarddd(telefone))){
            println("TELEFONE INVALIDO!!")
            continue
        }
        if(telefoneIgual(telefone)){
            println("TELEFONE JA INSERIDO!!!")
            continue
        }
        else return telefone

    }
}

fun verificaEmail(email:String):String{
    while(true) {
        var m = email
        m = email.lowercase()
        if (!(validarEmail(m))) {
            println("EMAIL INVALIDO!!")
            continue
        }
        if (emailIgual(m)) {
            println("EMAIL JA INSERIDO!!!")
            continue
        } else return m
    }
}

fun cadastrar():String {
    var rodar = true
    while (rodar) {
        println("NOME DO CONTATO:")
        var ncont = readLine().toString()
        ncont = validarNome(ncont)

        println("Telefone:")
        var telefone = readLine()!!.toLongOrNull()
        telefone = validarTel(telefone)

        println("Email:")
        var email = readLine().toString()
        verificaEmail(email)

        return "\n$ncont;$telefone;$email"
        rodar = false
    }
    return ""
}

fun validacaoNome(nome: String): Boolean{
    var name = nome.split(" ")
    if (name.size == 2){
        return true
    }
    else return false
}

fun nomeContato(nome: String): String{
    var split = nome.split(" ")
    var a = sanNome(split[0])
    var b = sanNome(split[1])

    return a+" "+b

}

fun sanNome(name: String): String{
    var name = name
    name = name.lowercase()
    name = name.replaceFirstChar {it.uppercase()}
    return name
}

fun validarddd(telefone: Long?):Boolean{
    if(telefone != null) {
        var dddtel = telefone / 1000000000
        var tell = telefone / 10000000000
        if (ddds.contains(dddtel) && ((tell < 10) && (tell > 1))) {
            return true
        }
        else return false
    }
    else return false
}

fun validarEmail(email: String): Boolean{
    if(email.contains("@") && email.contains(".com")) {
        return true
    }
    else return false
}

fun telefoneIgual(telefone: Long?):Boolean{
    var lista =contatos.readLines().toMutableList()
    for (i in lista) {
        var numeros = i.split(";")
        if (numeros[1].toLongOrNull() == telefone) {
            return true
        }
    }
    return false
}

fun emailIgual(email: String):Boolean {
    var lista = contatos.readLines().toMutableList()
    for (i in lista) {
        var numeros = i.split(";")
        if (numeros[2] == email) {
            return true
        }
    }
    return false
}

fun atualizador(tipo: String): Boolean{
    var lista =contatos.readLines().toMutableList()
    var num = 0
    when(tipo){
        "Nome" -> num
        "Telefone" -> num += 1
        "Email" -> num += 2
}
    println("Qual $tipo voce quer mudar?: ")
    val nome = readLine().toString()
            for (i in 0..lista.size-1) {
            val split = lista[i].split(";").toMutableList()
            if (nome == split[num]) {
                when(num){
                    0,2 -> {println("Para qual $tipo voce quer mudar?")
                            var NE = readLine().toString()
                            if(num == 0){
                            split[num] = validarNome(NE)
                            }
                            else split[num] = verificaEmail(NE)
                    }
                    1   ->  {println("Para qual $tipo voce quer mudar?")
                            var num2 = readLine()!!.toLongOrNull()
                        if (num2 != null) {
                            val telefoneValidado = validarTel(num2)
                            split[num] = telefoneValidado.toString()
                            }
                        else {
                            println("Telefone Invalido!")
                            return false
                        }
                        }
                }
                lista[i] = "${split[0]};${split[1]};${split[2]}"
                contatos.writeText(lista.joinToString("\n"))
                return true
            }
        }
    return false
}

fun Buscar(): List<String>{
    println("Busca: ")
    var busc = readLine().toString()
    var lista =contatos.readLines().toMutableList().filter{busc in it}
    return lista
}
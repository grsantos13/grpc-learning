package br.com.zup.categoria

class CategoriaResponse(categoria: Categoria) {
    val nome = categoria.nome
    val nomeCategoriaMae = categoria.categoriaMae?.nome
}

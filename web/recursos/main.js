
$(document).ready(function (){
    listarSelect()
    $("#btnConsultarCategoria").click(function () {
        limpiarSpan()
        var name = $("#consultarCategoria input").val();
        consultarCategoria(name)
    })


    $("#btnConsultarLibroxCategoria").click(function () {
        limpiarSpan()
        var categoria = $("#consultarLibroXcategoria select").val();
        consultarLibrosxCategoria(categoria)
    })

    $("#btnConsultarLibro").click(function () {
        limpiarSpan()
        var name = $("#consultarLibro input").val();
        console.log(name)
        consultarLibro(name)
    })
})


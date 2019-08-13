
$(document).ready(function () {
    listarSelect()
    
    $("#btnAgregar").click(function () {

        if (validarFormulario()){
            var nombre = $("#nombre").val();
            var autor = $("#autor").val()
            var idCategoria = $("#categorias").val();
            var estante =$("#estante").val()
            agregarLibro(nombre,autor,idCategoria,estante)
        }else{
            mostrarAlerta(false, "Verifique los campos")
        }

    })
})

function validarFormulario() {
    var nombre = $("#nombre").val();
    if (nombre==null || nombre=="" || nombre.length<3){
        return false;
    }
    var autor = $("#autor").val()
    if (autor==null || autor=="" || autor.length<3){
        return false;
    }
    var estante =$("#estante").val()
    if (estante==null || estante=="" || estante.length<2){
        return false;
    }

    return true;
}
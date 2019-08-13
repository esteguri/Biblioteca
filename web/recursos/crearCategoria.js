
$(document).ready(function () {
    
    $("#btnAgregar").click(function () {

        if (validarFormulario()){
            var categoria = $("#categoria").val();
            crearCategoria(categoria)
        }else{
            mostrarAlerta(false, "Verifique los campos")
        }

    })
})

function validarFormulario() {
    var categoria = $("#categoria").val();
    if (categoria==null || categoria=="" || categoria.length<3){
        return false;
    }
    return true;
}